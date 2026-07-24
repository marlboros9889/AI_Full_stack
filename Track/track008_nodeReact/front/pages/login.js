import { useState, useCallback } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { LOG_IN_REQUEST } from '../reducers/user';

export default function LoginPage() {
    const dispatch = useDispatch();
    const { isLoading, error, me } = useSelector(state => state.user);

    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const onChangeEmail = useCallback(e => setEmail(e.target.value), []);
    const onChangePassword = useCallback(e => setPassword(e.target.value), []);

    const onSubmit = useCallback(e => {
        e.preventDefault();
        dispatch({
            type: LOG_IN_REQUEST,
            data: { email, password }
        });
    }, [email, password, dispatch]);

    // 이미 로그인 되어 있다면
    if (me) {
        return <h3>이미 로그인 되어 있습니다. ({me.nickname})</h3>;
    }

    return (
        <div className="container my-4">
            <h3 className="mb-3">로그인</h3>
            
            <form className="w-50 mx-auto" onSubmit={onSubmit}>
                <div className="mb-3">
                    <input 
                        type="email" 
                        className="form-control" 
                        placeholder="이메일" 
                        value={email}
                        onChange={onChangeEmail}
                        required
                    />
                </div>

                <div className="mb-3">
                    <input 
                        type="password" 
                        className="form-control" 
                        placeholder="비밀번호" 
                        value={password}
                        onChange={onChangePassword}
                        required
                    />
                </div>

                <button 
                    type="submit" 
                    className="btn btn-primary w-100"
                    disabled={isLoading}
                >
                    {isLoading ? '로그인 중...' : '로그인'}
                </button>
            </form>

            {error && <div className="alert alert-danger mt-3">{error}</div>}
        </div>
    );
}