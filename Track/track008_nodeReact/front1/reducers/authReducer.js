// Redux Toolkit의 createSlice 불러오기
import { createSlice } from '@reduxjs/toolkit';

// 초기 상태 정의
const initialState = {
  user: null,        // 로그인한 사용자 정보 (또는 회원가입된 유저 정보)
  loading: false,    // 요청 중인지 여부
  error: null,       // 에러 메시지
  success: false,    // 요청 성공 여부  (기존 suceess 오타 수정)
};

// auth(user) 관련 상태와 액션을 관리하는 슬라이스
const authReducer = createSlice({
  name: 'user',      // 이 슬라이스의 이름 (store에서 state.user 로 접근)
  initialState,      // 위에서 정의한 초기 상태
  reducers: {
    
    // ========================================
    // 1. 회원가입 관련
    // ========================================

    // 회원가입 요청 시작
    signupRequest: (state) => {
      state.loading = true;   // 로딩 시작
      state.error = null;     // 이전 에러 초기화
      state.success = false;  // 성공 상태 초기화
    },

    // 회원가입 성공
    signupSuccess: (state, action) => {
      state.loading = false;          // 로딩 끝
      state.user = action.payload;    // 서버에서 받은 회원정보 저장
      state.success = true;           // 성공 표시
      state.error = null;
    },

    // 회원가입 실패
    signupFailure: (state, action) => {
      state.loading = false;          // 로딩 끝
      state.error = action.payload;   // 에러 메시지 저장
      state.success = false;          // 실패 표시
    },

    // ========================================
    // 2. 사용자 단건 조회 관련
    // ========================================

    // 사용자 정보 요청 시작
    fetchUserRequest: (state) => {
      state.loading = true;
      state.error   = null;
      state.success = false;
    },

    // 사용자 정보 조회 성공
    fetchUserSuccess: (state, action) => {
      state.loading = false;
      state.user    = action.payload;    // 조회된 사용자 정보 저장
      state.success = true;
    },

    // 사용자 정보 조회 실패  (기존 fatchUserFailure 오타 수정)
    fetchUserFailure: (state, action) => {
      state.loading = false;
      state.error   = action.payload;   // 에러 메시지 저장
      state.success = false;            
    },

    // ========================================
    // 3. 상태 초기화
    // ========================================

    // 유저 관련 상태를 처음 상태로 되돌림 (로그아웃 후나 페이지 이동 시 사용)
    resetUserState: (state) => {
      state.user = null;
      state.loading = false;
      state.error = null;
      state.success = false;
    },
  },
});

// 액션 생성자들 export (컴포넌트나 사가에서 사용)
export const {
  signupRequest,
  signupSuccess,
  signupFailure,
  fetchUserRequest,
  fetchUserSuccess,
  fetchUserFailure,
  resetUserState,
} = authReducer.actions;

// 리듀서 자체 export (store에 등록할 때 사용)
export default authReducer.reducer;