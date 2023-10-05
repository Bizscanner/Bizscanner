'use client';
import LoginButton from '@/components/User/LoginButton';
import Profile from '@/components/User/Profile';
import Link from 'next/link';
import React from 'react';
import { useSelector } from 'react-redux';

function Header() {
  const isLogin = useSelector((state) => state.user.isLogin);
  return (
    <nav className="flex items-center justify-between w-full p-4 bg-background">
      <Link href="/">
        <img src="icons/logo.svg" alt="비즈 스캐너" width={175} height={50} />
      </Link>
      {isLogin ? <Profile /> : <LoginButton />}
    </nav>
  );
}

export default Header;
