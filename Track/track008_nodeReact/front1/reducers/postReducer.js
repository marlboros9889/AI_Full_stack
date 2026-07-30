// reducer/postReducer.js
import { createSlice } from '@reduxjs/toolkit';

// 게시글 관련 초기 상태
const initialState = {
  posts: [],           // 게시글 목록
  currentPost: null,   // 단건 게시글 (상세보기용)
  loading: false,      // 요청 중 여부
  error: null,         // 에러 메시지
  success: false,      // 요청 성공 여부
};

const postReducer = createSlice({
  name: 'post',
  initialState,
  reducers: {
    // ========================================
    // 전체 게시글 목록 조회
    // ========================================
    fetchPostsRequest: (state) => {
      state.loading = true;
      state.error = null;
    },
    fetchPostsSuccess: (state, action) => {
      state.loading = false;
      state.posts = action.payload; // 서버에서 받은 게시글 배열
      state.error = null;
    },
    fetchPostsFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
    },

    // ========================================
    // 단건 게시글 조회
    // ========================================
    fetchPostRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.currentPost = null;
    },
    fetchPostSuccess: (state, action) => {
      state.loading = false;
      state.currentPost = action.payload; // 단건 게시글 저장
      state.error = null;
    },
    fetchPostFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.currentPost = null;
    },

    // ========================================
    // 게시글 작성
    // ========================================
    createPostRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = false;
    },
    createPostSuccess: (state, action) => {
      state.loading = false;
      // 새 글을 목록 맨 앞에 추가
      state.posts = [action.payload, ...state.posts];
      state.success = true;
    },
    createPostFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.success = false;
    },

    // ========================================
    // 게시글 수정
    // ========================================
    updatePostRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = false;
    },
    updatePostSuccess: (state, action) => {
      state.loading = false;
      // 목록에서 해당 게시글 교체
      state.posts = state.posts.map((post) =>
        post.id === action.payload.id ? action.payload : post
      );
      // 단건 조회 중이던 글도 같이 업데이트
      state.currentPost = action.payload;
      state.success = true;
    },
    updatePostFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.success = false;
    },

    // ========================================
    // 게시글 삭제
    // ========================================
    deletePostRequest: (state) => {
      state.loading = true;
      state.error = null;
      state.success = false;
    },
    deletePostSuccess: (state, action) => {
      state.loading = false;
      // action.payload 에는 삭제할 게시글 id가 들어옴
      state.posts = state.posts.filter(
        (post) => post.id !== action.payload
      );
      state.success = true;
    },
    deletePostFailure: (state, action) => {
      state.loading = false;
      state.error = action.payload;
      state.success = false;
    },

    // ========================================
    // 상태 초기화
    // ========================================
    resetPostState: (state) => {
      state.posts = [];
      state.currentPost = null;
      state.loading = false;
      state.error = null;
      state.success = false;
    },
  },
});

// 액션 생성자들 export (컴포넌트 / 사가에서 사용)
export const {
  fetchPostsRequest,  fetchPostsSuccess,  fetchPostsFailure,
  fetchPostRequest,    fetchPostSuccess,   fetchPostFailure,
  fetchPostDetailRequest,   fetchPostDetailSuccess,   fetchPostDetailFailure,
  createPostRequest,  createPostSuccess,  createPostFailure,
  updatePostRequest,  updatePostSuccess,  updatePostFailure,
  deletePostRequest,  deletePostSuccess,  deletePostFailure,
  resetPostState,
} = postReducer.actions;

// 리듀서 export (store에 등록)
export default postReducer.reducer;