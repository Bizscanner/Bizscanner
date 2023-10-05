import React from 'react';
import axios from '@/api/index';
import { Modal } from '@/components/Modal';
import Button from '@/components/Button';

function DeleteCheck({ isOpen, onClose, careaCode, jcategoryCode, fetchData }) {
  const onClickClose = () => {
    onClose();
  };

  const onClickConfirm = async () => {
    await axios.delete(`/scrap/${careaCode}/${jcategoryCode}`);
    fetchData();
    onClose();
  };

  return (
    <Modal isOpen={isOpen}>
      <Modal.Dimmed>
        <Modal.Container className="bg-white" width="40vw" height="40vh">
          <Modal.Close onClick={onClickClose} />
          <div className="flex flex-col items-center justify-around h-full">
            <Modal.Title>리포트를 삭제하시겠습니까 ?</Modal.Title>
            <Modal.ButtonList className="w-full h-1/4">
              <Button
                className="text-2xl text-white mx-7 rounded-large bg-disabled"
                width="35%"
                height="100%"
                onClick={onClickClose}
              >
                취소
              </Button>
              <Button
                className="text-2xl text-white mx-7 bg-primary rounded-large"
                width="35%"
                height="100%"
                onClick={onClickConfirm}
              >
                확인
              </Button>
            </Modal.ButtonList>
          </div>
        </Modal.Container>
      </Modal.Dimmed>
    </Modal>
  );
}

export default DeleteCheck;
