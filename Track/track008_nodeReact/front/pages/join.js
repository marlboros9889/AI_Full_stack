import { useSelector, useDispatch } from 'react-redux';
import { useState, useEffect } from 'react';
import { useRouter } from 'next/router';
import { 
    SIGN_UP_REQUEST,
    CHECK_EMAIL_REQUEST   // ✅ 이메일 중복확인 액션 추가
} from '../reducers/user';

// useSelector  - 전역상태
// useState     - 변수
// useEffect    - 변경감지
// useDispatch  - 스토어알림
// useRouter    - 경로

export default function JoinPage() {
    //1. 코드
    const dispatch = useDispatch();
    const router = useRouter();
    
    // useSelector로 user 상태 가져오기
    const { 
        me, 
        isLoading, 
        error, 
        signUpDone,
        emailCheckLoading,
        emailCheckDone,
        emailAvailable
    } = useSelector((state) => state.user);

    // 변수, 변수셋팅함수
    const [email, setEmail] = useState('');     
    const [password, setPassword] = useState('');
    const [nickname, setNickname] = useState('');   

    // 이메일 중복확인 함수
    const checkEmailDuplicate = () => {
        if (!email.trim()) {
            alert('이메일을 입력해 주세요');
            return;
        }

        dispatch({
            type: CHECK_EMAIL_REQUEST,
            data: { email: email.trim() }
        });
    };

    // 회원가입 요청 액션 dispatch
    const onSubmit = (e) => {
        e.preventDefault();

        if (!email.trim()) { alert('이메일을 입력해 주세요'); return; }
        if (!password.trim()) { alert('비밀번호를 입력해 주세요'); return; }
        if (!nickname.trim()) { alert('닉네임을 입력해 주세요'); return; }

        // 이메일 중복확인을 했는지 체크
        if (!emailCheckDone) {
            alert('이메일 중복확인을 해주세요.');
            return;
        }
        if (!emailAvailable) {
            alert('이미 사용 중인 이메일입니다. 다른 이메일을 사용해주세요.');
            return;
        }

        // Store: 액션 알림 useDispatch
        dispatch({ 
            type: SIGN_UP_REQUEST, 
            data: { 
                email: email.trim(), 
                password, 
                nickname: nickname.trim() 
            } 
        });
    };

    // 회원가입 성공 시 로그인 페이지로 이동
    useEffect(() => { 
        if (signUpDone) {
            router.push({
                pathname: '/login',
                query: { signUpSuccess: 'true' }
            });
        }
    }, [signUpDone, router]);
    
    // 이미 로그인 되어 있으면 사용자 목록으로 이동
    useEffect(() => {
        if (me) router.push('/users');
    }, [me, router]);

    //2. 뷰 - 렌더링
    return (
        <div className="container my-4">
            <h3 className="mb-3">회원가입</h3>
            <form className="w-50 mx-auto" onSubmit={onSubmit}>
                
                {/* 이메일 입력 + 중복확인 */}
                <div className="mb-3">
                    <div className="input-group">
                        <input 
                            type="email"  
                            className="form-control"  
                            placeholder="이메일입력" 
                            value={email}
                            onChange={(e) => {
                                setEmail(e.target.value);
                            }}
                        />
                        <button 
                            type="button"
                            className="btn btn-outline-secondary"
                            onClick={checkEmailDuplicate}
                            disabled={emailCheckLoading}
                        >
                            {emailCheckLoading ? '확인중...' : '중복확인'}
                        </button>
                    </div>
                    
                    {/* 중복확인 결과 메시지 */}
                    {emailCheckDone && (
                        <div className={`mt-2 small ${emailAvailable ? 'text-success' : 'text-danger'}`}>
                            {emailAvailable 
                                ? '✅ 사용 가능한 이메일입니다.' 
                                : '❌ 이미 사용 중인 이메일입니다.'}
                        </div>
                    )}
                </div>

                {/* 비밀번호 입력 */}
                <div className="mb-3">
                    <input 
                        type="password"  
                        className="form-control"  
                        placeholder="비밀번호입력" 
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                    />
                </div>

                {/* 닉네임 입력 */}
                <div className="mb-3">
                    <input 
                        type="text"  
                        className="form-control"  
                        placeholder="닉네임" 
                        value={nickname}
                        onChange={(e) => setNickname(e.target.value)}
                    />
                </div>

                {/* 버튼 입력 */}
                <div className="mb-3">
                    <button 
                        type="submit"  
                        className="btn btn-primary w-100" 
                        disabled={isLoading}
                    >
                        회원가입
                    </button>
                </div>            
            </form>
            
            {/* 에러 메시지 */}
            {error && <div className="alert alert-danger mt-3">{error}</div>}
        </div>
    );
}