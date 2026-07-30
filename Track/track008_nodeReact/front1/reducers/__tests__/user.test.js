import userReducer, { resetUserState } from '../authReducer';

describe('user reducer', () => {
  it('resets user state', () => {
    const state = userReducer(
      { user: { id: 1 }, loading: true, error: 'error', success: true },
      resetUserState()
    );

    expect(state).toEqual({ user: null, loading: false, error: null, success: false });
  });
});
