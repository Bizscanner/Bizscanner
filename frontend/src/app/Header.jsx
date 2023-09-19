import LoginButton from '@/components/User/LoginButton';
import Profile from '@/components/User/Profile';
import Link from 'next/link';
import React from 'react';

function Header() {
  return (
    <nav className="bg-background w-full p-4 flex justify-between items-center">
      <Link href="/">
        <img src="icons/logo.svg" />
      </Link>
      <Profile />
    </nav>
  );
}

export default Header;
