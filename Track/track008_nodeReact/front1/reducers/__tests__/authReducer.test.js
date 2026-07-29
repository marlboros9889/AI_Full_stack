// __tests__/authReducer.test.js
import userReducer, {
  signupRequest,
  signupSuccess,
  signupFailure,
  fetchUserRequest,
  fetchUserSuccess,
  fetchUserFailure,
  resetUserState,
} from '../authReducer';

describe('user slice reducer', ()=>{
    const initialState = {
        user: null,        // 로그인한 사용자 정보 (또는 회원가입된 유저 정보)
        loading: false,    // 요청 중인지 여부
        error: null,       // 에러 메시지
        success: false,    // 요청 성공 여부  (기존 suceess 오타 수정)
    };
    it(' signupRequest ', ()=>{
        const state = userReducer(initialState, signupRequest() );
        expect(state.loading).toBe(true);
        expect(state.error).toBeNull();
        expect(state.success).toBe(false);
    });
    
    it(' signupSuccess ', ()=>{ 
        const userData = {id:1, email:'1@1'};
        const state = userReducer(initialState, signupSuccess(userData) );
        //1. signupSuccess(userData) - {id:1, email:'1@1'};
        //2. 리듀서톨킷에서 {type: signupSuccess, payload:userData }   객체만들기
        //3. 리듀서의 signupSuccess(state,action)=>{}
        expect(state.loading).toBe(false);    // state.loading = false
        expect(state.user).toEqual(userData); // state.user  = action.payload
        expect(state.success).toBe(true);
    });

    it(' signupFailure ', ()=>{ 
        const state = userReducer(initialState, signupFailure('회원가입 실패') );
        expect(state.loading).toBe(false);
        expect(state.error).toBe('회원가입 실패');
    });

    it( 'fetchUserSuccess', ()=>{ 
        const userData = {id:1, email:'1@1'};
        const state    = userReducer(initialState, fetchUserSuccess(userData));
        //1. fetchUserSuccess(userData) - {id:1, email:'1@1'} 전달
        //2. 리듀서 툴킷 - {type:fetchUserSuccess, payload:userData } 객체만들기
        //3. 리듀서의 fetchUserSuccess : (state, action)=>{} 액션받아서 처리
        // action = { type:fetchUserSuccess , payload:userData }
        expect(state.user).toEqual(userData); // state.user = action.payload
        expect(state.loading).toBe(false);
    });

    it(' resetUserState ', ()=>{ 
        const prev = {user:{id:1}, loading:true, error:'err', success:true }; // 당태꼬임
        const state = userReducer(prev, resetUserState());
        //1. resetUserState() 실행 - 인자없음
        //2. 리듀서의 툴킷 - { type:resetUserState, payload:undefined } 객체 만들기
        //3. 리듀서의 resetUserState: (state,action)=>{} 액션받아서 처리 - 상태 초기화
        // action = { type:resetUserState, payload:undefined }
        expect(state.loading).toBe(false);
        expect(state.error).toBe(null);
        expect(state.success).toBe(false);
    });
});
// npm test authReducer