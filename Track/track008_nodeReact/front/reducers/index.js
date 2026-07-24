/**
 * reducers/index,js
 * ---------------------------------
 * 루트리듀서(rootReducer) 설정파일
 * - 여러개의 리듀서를 하나로 합쳐서 Redux 스토어에 전달
 * - 현재는 user 리듀서만 포함 
*/

import { HYDRATE } from 'next-redux-wrapper';
import { combineReducers } from 'redux';
import user from './user';
// import post from './post';

const rootReducer = (state, action) => {
  switch (action.type) {
    // Next.js 서버 사이드 렌더링(SSR) 시 동작하는 HYDRATE 액션 처리
    case HYDRATE:
      console.log('HYDRATE 상태 병합:', action);
      return {
        ...state,
        ...action.payload,
      };

    // 일반적인 Redux 상태 변화 처리
    default: {
      const combinedReducer = combineReducers({
        user,
        // post,
      });
      return combinedReducer(state, action);
    }
  }
};

export default rootReducer;