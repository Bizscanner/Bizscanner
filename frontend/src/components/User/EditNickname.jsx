import React, { useRef } from 'react';
import { Modal } from '../Modal';
import { Input } from '../Input';
import Button from '../Button';
import axios from '@/api/index';
import { useDispatch, useSelector } from 'react-redux';
import { updateNickname } from '@/redux/userSlice';

function EditNickname({ isOpen, onClose }) {
  const inputRef = useRef();
  const { email } = useSelector((state) => state.user);
  const dispatch = useDispatch();

  const onClickClose = () => {
    onClose();
  };

  const onClickChangeNickname = async () => {
    const newNickname = inputRef.current.value;

    await axios.patch(`member`, {
      email,
      nickname: newNickname,
    });
    dispatch(updateNickname({ nickname: newNickname }));
    onClose();
  };

  return (
    <Modal isOpen={isOpen}>
      <Modal.Container className="shadow-2xl bg-background" width="30vw" height="50vh">
        <Modal.Close onClick={onClickClose} />
        <div className="flex flex-col items-center justify-between w-full h-1/2">
          <Modal.Title>닉네임 변경</Modal.Title>
          <div className="flex flex-row w-full">
            <Input>
              <Input.Wrapper className="w-full">
                <Input.Section
                  name="nickname"
                  placeholder="닉네임"
                  type="input"
                  className="w-full"
                  ref={inputRef}
                ></Input.Section>
              </Input.Wrapper>
            </Input>
            <Button
              className="text-white bg-primary rounded-small"
              width="30%"
              onClick={onClickChangeNickname}
            >
              확인
            </Button>
          </div>
        </div>
      </Modal.Container>
    </Modal>
  );
}

export default EditNickname;
