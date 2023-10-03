'use client';

import React, { useState } from 'react';
import LoginModal from './LoginModal';

function LoginButton() {
  const [showLoginModal, setLoginModal] = useState(false);

  const closeLoginModal = () => {
    setLoginModal(false);
  };

  const openLoginModal = () => {
    setLoginModal(true);
  };

  return (
    <>
      <button
        className="flex items-center self-stretch justify-center gap-1 border-2 border-outline rounded-3xl w-28"
        onClick={openLoginModal}
      >
        <img src="icons/account.svg" style={{ width: '30px', height: '30px' }}></img>
        로그인
      </button>
      <LoginModal isOpen={showLoginModal} onClose={closeLoginModal} />
    </>
  );
}

export default LoginButton;
