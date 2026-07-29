// sagas/postSaga.js
import { all } from 'redux-saga/effects';

// post 관련 사가들을 모아서 실행
export default function* postSaga() {
  // 빈 배열이라도 반드시 넣어야 함
  yield all([
    // 나중에 takeLatest(ADD_POST_REQUEST, addPost) 등을 추가
  ]);
}