import { createSlice } from '@reduxjs/toolkit';

export const userSlice = createSlice({
  name: 'user',
  initialState: {
    nickname: '',
    email: '',
    isLogin: false,
  },
  reducers: {
    login: (state, action) => {
      state.nickname = action.payload.nickname;
      state.email = action.payload.email;
      state.isLogin = true;
    },

    logout: (state) => {
      console.log('testsets');
      state.nickname = '';
      state.email = '';
      state.isLogin = false;

      localStorage.removeItem('at');
      localStorage.removeItem('rt');
    },
  },
});

export const { login, logout } = userSlice.actions;
export default userSlice.reducer;
