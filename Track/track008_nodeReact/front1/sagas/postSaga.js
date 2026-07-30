// sagas/PostSaga.js
import {all, call, put, takeLatest } from 'redux-saga/effects';
import axios from 'axios';
import {
  fetchPostsRequest,  fetchPostsSuccess,  fetchPostsFailure,
  fetchPostRequest,         fetchPostSuccess,         fetchPostFailure,
  createPostRequest,  createPostSuccess,  createPostFailure,
  updatePostRequest,  updatePostSuccess,  updatePostFailure,
  deletePostRequest,  deletePostSuccess,  deletePostFailure,
  resetPostState, 
}from'../reducers/postReducer';
const POST_API_BASE = 'http://localhost:8080/api/posts';

// watchFetchPosts          - GET      /api/posts           전체 게시글 조회 
export const fetchPostAPI = ()=> axios.get(POST_API_BASE);
export function* fetchPosts(){
  try{
      const result = yield call(fetchPostAPI)
      yield put(fetchPostsSuccess(result.data))
  }catch(err){
    yield put( fetchPostsFailure(err.response?.data?.message||err.message));
  }  
}

// watchFetchPostDetail    - GET      /api/posts          게시글 단건  조회  
export const fetchPostDetailAPI = (id)=> axios.get(`${POST_API_BASE}/${id}`);
export function* fetchPostDetail(action){
  try{
      const result = yield call (fetchPostDetailAPI, action.payload)
      yield put(fetchPostSuccess(result.data))
  }catch(err){
    yield put(fetchPostFailure(err.response?.data?.message || err.message));
  }
}

// watchCreatePost          - POST     /api/posts           게시글 작성  
export const createPostAPI = (postData)=> axios.post(POST_API_BASE, postData);
export function* createPost(action){
  try{
      const result = yield call (createPostAPI, action.payload)
      yield put(createPostSuccess(result.data))
  }catch(err){
    yield put( createPostFailure(err.response?.data?.message||err.message));
  }
}

// watchUpdatePost          - PUT      /api/posts/{id}      게시글 수정 
export const updatePostAPI = ({postId, dto})=> axios.put(`${POST_API_BASE}/${postId}`, dto);
export function* updatePost(action){ 
   try{
      const result = yield call(updatePostAPI, action.payload)
      yield put(updatePostSuccess(result.data))
  }catch(err){
    yield put( updatePostFailure(err.response?.data?.message||err.message));
  }
}

// watchDeletePost          - DELETE   /api/posts/{id}      게시글 삭제   
export const deletePostAPI = (id)=> axios.delete(`${POST_API_BASE}/${id}`);
export function* deletePost(action){
  try{
      yield call(deletePostAPI, action.payload)
      yield put(deletePostSuccess(action.payload))
  }catch(err){
    yield put( deletePostFailure(err.response?.data?.message||err.message));
  }
}

//---- watch saga들 ----
function* watchFetchPosts(){        yield takeLatest(fetchPostsRequest.type,       fetchPosts); }
function* watchFetchPostDetail(){  yield takeLatest(fetchPostRequest.type, fetchPostDetail); }
function* watchCreatePost(){        yield takeLatest(createPostRequest.type,       createPost); }
function* watchUpdatePost(){        yield takeLatest(updatePostRequest.type,       updatePost); }
function* watchDeletePost(){        yield takeLatest(deletePostRequest.type,       deletePost); }


export default function* postSaga(){
  yield all([
    call(watchFetchPosts),
    call(watchFetchPostDetail),
    call(watchCreatePost),
    call(watchDeletePost),
    call(watchUpdatePost),

  ]);
}



// 요청 : PostRequestDto ,  응답: PostResponseDto
//- DELETE   /api/posts/{id}      게시글 삭제     
//- PUT      /api/posts/{id}      게시글 수정      

//- GET      /api/posts/{id}      게시글 단건 조회  
//- GET      /api/posts      전체 게시글 조회  
//- POST   /api/posts         게시글 작성   
