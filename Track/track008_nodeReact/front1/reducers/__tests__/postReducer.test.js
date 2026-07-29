// reducers/__tests__/postReducer.test.js
import postReducer, {
  fetchPostsRequest,
  fetchPostsSuccess,
  fetchPostRequest,
  fetchPostSuccess,
  createPostSuccess,
  updatePostSuccess,
  deletePostSuccess,
  resetPostState,
} from '../postReducer';

describe('post', () => {
  const initialState = {
    posts: [],
    currentPost: null,
    loading: false,
    error: null,
    success: false,
  };

  // 1. 전체 목록 조회
  it('fetchPostsRequest & fetchPostsSuccess', () => {
    let state = postReducer(initialState, fetchPostsRequest());
    expect(state.loading).toBe(true);

    const posts = [{ id: 1, content: '첫 번째 글' }]; // 반드시 액션 호출 전에 선언
    state = postReducer(initialState, fetchPostsSuccess(posts));
    expect(state.loading).toBe(false);
    expect(state.posts).toEqual(posts);
  });

  // 2. 단건 조회
  it('fetchPostSuccess', () => {
    const post = { id: 1, content: '첫 번째 글' };
    const state = postReducer(initialState, fetchPostSuccess(post));
    expect(state.currentPost).toEqual(post);
    expect(state.loading).toBe(false);
  });

  // 3. 글 작성
  it('createPostSuccess', () => {
    const newPost = { id: 1, content: '새 글' };
    const state = postReducer(initialState, createPostSuccess(newPost));
    expect(state.posts).toEqual([newPost]);
    expect(state.success).toBe(true);
  });

  // 4. 글 수정
  it('updatePostSuccess', () => {
    const prev = {
      ...initialState,
      posts: [{ id: 1, content: '원래 글' }],
    };
    const updated = { id: 1, content: '수정된 글' };
    const state = postReducer(prev, updatePostSuccess(updated));
    expect(state.posts[0].content).toBe('수정된 글');
    expect(state.currentPost).toEqual(updated);
    expect(state.success).toBe(true);
  });

  // 5. 글 삭제  ← 점(.) 말고 쉼표(,) 사용!
  it('deletePostSuccess', () => {
    const prev = {
      ...initialState,
      posts: [{ id: 1, content: '새 글' }],
    };
    const state = postReducer(prev, deletePostSuccess(1)); // ✅ 쉼표
    expect(state.posts).toHaveLength(0);
    expect(state.success).toBe(true);
  });

  // 6. 상태 초기화  ← 이름도 resetPostState, 쉼표 사용!
  it('resetPostState', () => {
    const prev = {
      ...initialState,
      loading: true,
      error: 'error',
      success: true,
    };
    const state = postReducer(prev, resetPostState()); // ✅ 쉼표 + 올바른 이름
    expect(state.loading).toBe(false);
    expect(state.error).toBeNull();
    expect(state.success).toBe(false);
  });
});