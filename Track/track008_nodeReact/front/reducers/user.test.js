// npx jest reducers/user.test.js

import reducer, {
    initialState,
    LOG_IN_REQUEST, LOG_IN_SUCCESS, LOG_IN_FAILURE,
    LOG_OUT_REQUEST, LOG_OUT_SUCCESS, LOG_OUT_FAILURE,
    SIGN_UP_REQUEST, SIGN_UP_SUCCESS, SIGN_UP_FAILURE,
    LOAD_USERS_REQUEST, LOAD_USERS_SUCCESS, LOAD_USERS_FAILURE,
    UPDATE_NICKNAME_REQUEST, UPDATE_NICKNAME_SUCCESS, UPDATE_NICKNAME_FAILURE,
    DELETE_USER_REQUEST, DELETE_USER_SUCCESS, DELETE_USER_FAILURE,
} from './user';

describe('user reducer', ()=>{
    it('LOG_IN_REQUEST', ()=>{
        const state = reducer(initialState, {type:LOG_IN_REQUEST });
        expect(state.isLoading).toBe(true);
    }); //return {...state, isLoding:true, error: null}
    ////////////////////////////////////////////
    it('LOG_IN_SUCCESS', ()=>{
        const fakeUser = { id:1, email:'1@1', nickname:'first'};
        const state = reducer(initialState, {type:LOG_IN_SUCCESS, data:fakeUser});
        expect(state.error).toBe(null);
        expect(state.isLoading).toBe(false);
    }); //return {...state, isLoding:true, error: null}
    ////////////////////////////////////////////
    it('LOG_IN_FAILURE', ()=>{
        const state = reducer(initialState, {type:LOG_IN_FAILURE, error:'로그인 실패'});
        expect(state.error).toBe('로그인 실패');
        expect(state.isLoading).toBe(false);
    }); //return {...state, isLoding:true, error: null}
    //////////////////////////////////////////// 
    it('LOG_OUT_REQUEST', ()=>{
        const state = reducer(initialState, {type:LOG_OUT_REQUEST });
        expect(state.isLoading).toBe(true);
    }); //return {...state, isLoding:true, error: null}
    ////////////////////////////////////////////
    it('LOG_OUT_SUCCESS', ()=>{
        const state = reducer(initialState, {type:LOG_OUT_SUCCESS });
        expect(state.isLoading).toBe(false);
        expect(state.me).toBeNull();
    }); //return {...state, isLoding:true, error: null}
    ////////////////////////////////////////////
    it('LOG_OUT_FAILURE', ()=>{
        const state = reducer(initialState, {type:LOG_OUT_FAILURE, error:'로그아웃 실패' });
        expect(state.error).toBe('로그아웃 실패');
        expect(state.isLoading).toBe(false);
    }); //return {...state, isLoding:false, error: action.error?.message || action.error };
    ////////////////////////////////////////////
    it('SING_UP_SUCCESS', ()=>{
        const state = reducer(initialState, {type:SING_UP_SUCCESS} );
        expect(state.isLoading).toBe(false);
        expect(state.signUpDone).toBe(true);
    });//return{...state,isLodinL false, signUpDone: true };

    it('LOAD_USERS_SUCCESS', ()=>{
        const fakeUsers = [{ id:1, email:'1@1', nickname:'first'}];
        const state = reducer(initialState,{ type:LOAD_USERS_SUCCESS, data: fakeUsers } );
        expect(state.users).toBe(fakeUsers);
    });

    it('UPDATE_NICKNAME_SUCCESS', ()=>{
        const prev = {...initialState, me:{id:1, nickname:'old', users:[{id:1, nickname:'old'}]} };
        const state = reducer(prev, { type:UPDATE_NICKNAME_SUCCESS, data:{id:1, nickname:'new'} });
        expect(state.users).toBe(fakeUsers);
    });

    it('LOAD_USERS_SUCCESS', ()=>{
        const fakeUsers = [{ id:1, email:'1@1', nickname:'first'}];
        const state = reducer(initialState,{ type:LOAD_USERS_SUCCESS, data: fakeUsers } );
        expect(state.users).toBe(fakeUsers);
    });

    it('UPDATE_NICKNAME_SUCCESS', ()=>{
        const prev = {...initialState, me:{id:1, nickname:'old'}, users:[{id:1, nickname:'old'}] };
        const state = reducer(prev, {type:UPDATE_NICKNAME_SUCCESS, data:{id:1, nickname:'new'}});
        expect(state.me.nickname).toBe('new');
        expect(state.users[0].nickname).toBe('new');
    });

    it('DELETE_USER_SUCCESS', ()=>{
            const prev = {...initialState, me:{id:1}, users:[{id:1}, {id:2}, {id:3}] };
            const state = reducer(prev, {type:DELETE_USER_SUCCESS, data:{id:1}});
            expect(state.me).toBeNull( );
            expect(state.users).toBeEqual( [{ id:2 }, { id:3 }] );
    });

    





});
       