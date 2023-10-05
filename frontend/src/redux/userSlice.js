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
      state.nickname = '';
      state.email = '';
      state.isLogin = false;

      localStorage.removeItem('at');
      localStorage.removeItem('rt');
    },

    updateNickname: (state, action) => {
      state.nickname = action.payload.nickname;
    },
  },
});

export const { login, logout, updateNickname } = userSlice.actions;
export default userSlice.reducer;
