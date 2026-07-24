import { useEffect, useState } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { 
    LOAD_USERS_REQUEST, 
    UPDATE_NICKNAME_REQUEST, 
    DELETE_USER_REQUEST,
    LOG_OUT_REQUEST 
} from '../reducers/user';

export default function UsersPage() {
    const dispatch = useDispatch();
    const { users, isLoading, me } = useSelector(state => state.user);

    const [editingId, setEditingId] = useState(null);
    const [newNickname, setNewNickname] = useState('');

    useEffect(() => {
        dispatch({ type: LOAD_USERS_REQUEST });
    }, [dispatch]);

    const startEdit = (user) => {
        setEditingId(user.id);
        setNewNickname(user.nickname);
    };

    const handleUpdateNickname = (id) => {
        if (!newNickname.trim()) return alert('닉네임을 입력하세요');
        dispatch({ type: UPDATE_NICKNAME_REQUEST, data: { id, nickname: newNickname } });
        setEditingId(null);
        setNewNickname('');
    };

    const handleDelete = (id) => {
        if (window.confirm('정말 삭제하시겠습니까?')) {
            dispatch({ type: DELETE_USER_REQUEST, data: { id } });
        }
    };

    const handleLogout = () => {
        if (window.confirm('로그아웃 하시겠습니까?')) {
            dispatch({ type: LOG_OUT_REQUEST });
        }
    };

    return (
        <div className="container my-4">
            <div className="d-flex justify-content-between align-items-center mb-4">
                <h3>👥 사용자 목록</h3>
                
                {/* 로그아웃 버튼 - 항상 보이게 */}
                {me && (
                    <button 
                        className="btn btn-outline-danger"
                        onClick={handleLogout}
                    >
                        로그아웃 ({me.nickname})
                    </button>
                )}
            </div>

            {/* 나머지 테이블 코드는 이전과 동일 */}
            <table className="table table-striped table-hover">
                <thead className="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>이메일</th>
                        <th>닉네임</th>
                        <th>액션</th>
                    </tr>
                </thead>
                <tbody>
                    {users.map(user => (
                        <tr key={user.id}>
                            <td>{user.id}</td>
                            <td>{user.email}</td>
                            <td>
                                {editingId === user.id ? (
                                    <input 
                                        type="text" 
                                        className="form-control form-control-sm"
                                        value={newNickname}
                                        onChange={e => setNewNickname(e.target.value)}
                                    />
                                ) : user.nickname}
                            </td>
                            <td>
                                {editingId === user.id ? (
                                    <>
                                        <button className="btn btn-success btn-sm me-2" onClick={() => handleUpdateNickname(user.id)}>저장</button>
                                        <button className="btn btn-secondary btn-sm" onClick={() => setEditingId(null)}>취소</button>
                                    </>
                                ) : (
                                    <>
                                        <button className="btn btn-warning btn-sm me-2" onClick={() => startEdit(user)}>수정</button>
                                        <button className="btn btn-danger btn-sm" onClick={() => handleDelete(user.id)}>삭제</button>
                                    </>
                                )}
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}







// <div className='container my-4'>
//     <h3 className='mb-3'>사용자목록</h3>
//     {/* 로딩/에러 상태 표시 */}
//     <div className='alert alert-info'>로딩중....</div>
//     <div className='alert alert-danger'>에러메시지</div>

//     {/* 사용자 목록 테이블 */}
//     <table className='table table-striped table-borderd table-hover'>
//         <caption>사용자목록</caption>
//         <thead>
//             <th scope='col'>EMAIL</th>
//             <th scope='col'>NICKNAME</th>
//             <th scope='col'>UPDATE/DELETE</th>
//         </thead>
//         <tbody>
//             <td>1@1</td>
//             <td>1</td>
//             <td>
//                 <button className='btn btn-danger'>수정</button>
//                 <button className='btn btn-primary'>삭제</button>
//             </td>
//         </tbody>
//     </table>
//     {/* 로그아웃 버튼 */}
//     <div className='mt-3'>
//         <button className='btn btn-secondary'>로그아웃</button>
//     </div>
// </div>