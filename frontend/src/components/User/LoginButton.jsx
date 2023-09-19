'use client';

import React from 'react';

function LoginButton() {
  const onLogin = () => {};

  return (
    <button
      className="flex justify-center items-center border-outline border-2 rounded-3xl w-28 gap-1 self-stretch"
      onClick={onLogin}
    >
      <img src="icons/account.svg" style={{ width: '30px', height: '30px' }}></img>
      로그인
    </button>
  );
}

export default LoginButton;
