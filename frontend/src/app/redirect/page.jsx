'use client';
import React, { useEffect } from 'react';
import { useSearchParams, redirect } from 'next/navigation';
import { useDispatch } from 'react-redux';
import { login } from '@/redux/userSlice';

function page() {
  const params = useSearchParams();
  const dispatch = useDispatch();

  useEffect(() => {
    const accessToken = params.get('accessToken');
    const refreshToken = params.get('refreshToken');
    const email = params.get('email');
    const nickname = params.get('nickname');

    localStorage.setItem('at', accessToken);
    localStorage.setItem('rt', refreshToken);

    dispatch(
      login({
        email,
        nickname,
        isLogin: true,
      }),
    );

    redirect('/');
  }, []);
  return <div>로그인 중.....</div>;
}

export default page;
