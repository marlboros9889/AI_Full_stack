/**
 * sagas/user.js
 * ----------------------------------------------
 * 사용자 관련 비동기 작업을 처리하는 saga
 * - 로그인, 로그아웃, 회원가입, 사용자 조회, 닉네임 수정, 사용자 삭제
 * - axios로 API를 호출 >> 성공/실패에 따라 reducer로 액션 전달
 */

import { all, fork, call, put, takeLatest } from 'redux-saga/effects';  // saga 기본함수
import axios from 'axios'; // http 요청 라이브러리

import {
    LOG_IN_REQUEST, LOG_IN_SUCCESS, LOG_IN_FAILURE,
    LOG_OUT_REQUEST, LOG_OUT_SUCCESS, LOG_OUT_FAILURE,
    SIGN_UP_REQUEST, SIGN_UP_SUCCESS, SIGN_UP_FAILURE,
    LOAD_USERS_REQUEST, LOAD_USERS_SUCCESS, LOAD_USERS_FAILURE,
    UPDATE_NICKNAME_REQUEST, UPDATE_NICKNAME_SUCCESS, UPDATE_NICKNAME_FAILURE,
    DELETE_USER_REQUEST, DELETE_USER_SUCCESS, DELETE_USER_FAILURE,
    CHECK_EMAIL_REQUEST, CHECK_EMAIL_SUCCESS, CHECK_EMAIL_FAILURE,
} from '../reducers/user';    // 액션타입 불러오기

const client = axios.create({
    baseURL: 'http://localhost:3065',     // API 서버 주소
    withCredentials: true,                // 쿠키, 세션 인증 포함
});

//--------------로그인-------------------------------watchLogin
// post : /user/login    (requestBody)
export function loginApi(data) {
    return client.post('/user/login', data);
}
export function* login(action) {
    try {
        const result = yield call(loginApi, action.data);
        const user = {
            id: result.data.APP_USER_ID,
            email: result.data.EMAIL,
            nickname: result.data.NICKNAME
        };
        yield put({ type: LOG_IN_SUCCESS, data: user });     // 성공 액션 dispatch
    } catch (err) {
        yield put({ type: LOG_IN_FAILURE, error: err.response?.data || err.message });
    }
}
function* watchLogin() {
    yield takeLatest(LOG_IN_REQUEST, login);
}

//--------------로그아웃-----------------------------watchLogout
// post : /user/logout   
export function logoutApi() {
    return client.post('/user/logout');
}
export function* logout(action) {
    try {
        yield call(logoutApi);
        yield put({ type: LOG_OUT_SUCCESS });
    } catch (err) {
        yield put({ type: LOG_OUT_FAILURE, error: err.response?.data || err.message });
    }
}
function* watchLogout() {
    yield takeLatest(LOG_OUT_REQUEST, logout);
}

//--------------회원가입-----------------------------watchSignUp
// post : /user/register (requestBody)
export function signUpApi(data) {
    return client.post('/user/register', data);
}
export function* signUp(action) {
    try {
        yield call(signUpApi, action.data);
        yield put({ type: SIGN_UP_SUCCESS });
    } catch (err) {
        yield put({ type: SIGN_UP_FAILURE, error: err.response?.data || err.message });
    }
}
function* watchSignUp() {
    yield takeLatest(SIGN_UP_REQUEST, signUp);
}

//--------------사용자 조회-----------------------------watchLoadUsers
// get  : /user/
export function loadUsersApi() {
    return client.get('/user/');
}
export function* loadUsers() {
    try {
        const result = yield call(loadUsersApi);
        const users = result.data.map((u) => ({
            id: u.APP_USER_ID,
            email: u.EMAIL,
            nickname: u.NICKNAME
        }));
        yield put({ type: LOAD_USERS_SUCCESS, data: users });
    } catch (err) {
        yield put({ type: LOAD_USERS_FAILURE, error: err.response?.data || err.message });
    }
}
function* watchLoadUsers() {
    yield takeLatest(LOAD_USERS_REQUEST, loadUsers);
}

//--------------닉네임 수정-----------------------------watchUpdateNickname
// patch: /user/{id}/nickname 
export function updateNicknameApi(data) {
    return client.patch(`/user/${data.id}/nickname`, { nickname: data.nickname });
}
export function* updateNickname(action) {
    try {
        yield call(updateNicknameApi, action.data);
        yield put({
            type: UPDATE_NICKNAME_SUCCESS,
            data: { id: action.data.id, nickname: action.data.nickname }
        });
    } catch (err) {
        yield put({ type: UPDATE_NICKNAME_FAILURE, error: err.response?.data || err.message });
    }
}
function* watchUpdateNickname() {
    yield takeLatest(UPDATE_NICKNAME_REQUEST, updateNickname);
}

//--------------사용자 삭제-----------------------------watchDeleteUser
// delete: /user/{id} 
export function deleteUserApi(id) {
    return client.delete(`/user/${id}`);
}
export function* deleteUser(action) {
    try {
        yield call(deleteUserApi, action.data.id);
        yield put({ type: DELETE_USER_SUCCESS, data: { id: action.data.id } });
    } catch (err) {
        yield put({ type: DELETE_USER_FAILURE, error: err.response?.data || err.message });
    }
}
function* watchDeleteUser() {
    yield takeLatest(DELETE_USER_REQUEST, deleteUser);
}

//---------------이메일 중복확인-------------------------------
// get  : /user/check-email?email=xxx
export function checkEmailApi(email) {
    return client.get(`/user/check-email?email=${email}`);
}
export function* checkEmail(action) {
    try {
        const result = yield call(checkEmailApi, action.data.email);
        yield put({
            type: CHECK_EMAIL_SUCCESS,
            data: result.data
        });
    } catch (err) {
        yield put({
            type: CHECK_EMAIL_FAILURE,
            error: err.response?.data?.message || err.message
        });
    }
}
function* watchCheckEmail() {
    yield takeLatest(CHECK_EMAIL_REQUEST, checkEmail);
}

//--------------Root Saga-----------------------------
export default function* userSaga() {
    yield all([
        fork(watchLogin),
        fork(watchLogout),
        fork(watchSignUp),
        fork(watchLoadUsers),
        fork(watchUpdateNickname),
        fork(watchDeleteUser),
        fork(watchCheckEmail),       
    ]);
}