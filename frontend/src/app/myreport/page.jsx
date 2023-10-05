'use client';

import React from 'react';
import MyReport from './MyReport';
import { useSelector } from 'react-redux';
import { redirect } from 'next/navigation';

function page() {
  const { isLogin } = useSelector((state) => state.user);

  if (!isLogin) {
    redirect('/');
  }

  return <MyReport />;
}

export default page;
