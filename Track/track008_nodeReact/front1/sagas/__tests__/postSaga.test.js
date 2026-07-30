// sagas/__tests__/postSaga.test.js 

import { call, put } from 'redux-saga/effects';
import axios from 'axios';
import {fetchPostsRequest,  fetchPostsSuccess,  fetchPostsFailure, // 전체글
  fetchPostDetailRequest,   fetchPostDetailSuccess,   fetchPostDetailFailure, // 상세글
  createPostRequest,  createPostSuccess,  createPostFailure, // 글쓰기
  updatePostRequest,  updatePostSuccess,  updatePostFailure, // 글수정
  deletePostRequest,  deletePostSuccess,  deletePostFailure, // 글삭제
  resetPostState, // 초기화
} from '../../reducers/postReducer';
import { fetchPosts, fetchPostDetail,
    createPost, updatePost, deletePost  } from '../postSaga';

describe('post saga', () => {
  afterEach(() => {
    jest.clearAllMocks();
  });

  // --- 전체 게시글 조회 ---
  it('fetchPosts success', () => {
    const action = fetchPostsRequest();
    const generator = fetchPosts(action);

    expect(generator.next().value.type).toBe('CALL');

    const mockData = [{ id: 1, content: 'post 1' }];
    const putStep = generator.next({ data: mockData }).value;

    expect(putStep).toEqual(put(fetchPostsSuccess(mockData)));
    expect(generator.next().done).toBe(true);
  });

  // --- 글 수정 ---
  it('updatePost success', () => {
    const payload = { postId: 1, dto: { content: '수정된 글' } };
    const action = updatePostRequest(payload);
    const generator = updatePost(action);

    expect(generator.next().value.type).toBe('CALL');

    const mockData = { id: 1, content: '수정된 글' };  // 단건 객체
    const putStep = generator.next({ data: mockData }).value;

    expect(putStep).toEqual(put(updatePostSuccess(mockData)));
    expect(generator.next().done).toBe(true);
  });

  // --- 글 삭제 ---
  it('deletePost success', () => {
    const postId = 1;
    const action = deletePostRequest(postId);
    const generator = deletePost(action);

    expect(generator.next().value.type).toBe('CALL');

    // delete는 보통 응답 data 없이 성공 처리
    const putStep = generator.next().value;  // 또는 generator.next({ data: ... })

    expect(putStep).toEqual(put(deletePostSuccess(postId)));
    expect(generator.next().done).toBe(true);
  });
});
//npm test postSaga.test.js


