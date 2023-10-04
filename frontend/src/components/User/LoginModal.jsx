import React from 'react';
import { Modal } from '../Modal';
import KakaoIcon from '@/assets/icons/kakao_logo.svg';
import GoogleIcon from '@/assets/icons/google_logo.svg';

import Button from '../Button';

function LoginModal({ isOpen, onClose }) {
  return (
    <Modal isOpen={isOpen}>
      <Modal.Dimmed onClick={onClose}>
        <Modal.Container className="w-1/4 bg-background">
          <Modal.Close onClick={onClose} />
          <Modal.Title className="mb-7">로그인</Modal.Title>
          <Button className="flex items-center justify-center w-full gap-1 p-2 mb-2 bg-kakao">
            <KakaoIcon width="36" />
            카카오톡으로 시작하기
          </Button>

          <Button className="flex items-center justify-center w-full gap-1 p-2 bg-white">
            <GoogleIcon width="36" />
            구글로 시작하기
          </Button>
        </Modal.Container>
      </Modal.Dimmed>
    </Modal>
  );
}

export default LoginModal;
