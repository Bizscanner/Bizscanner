'use client';

import React, { useState } from 'react';
import RecommendButton from './RecommendButton';
import { Modal } from '@/components/Modal';
import { Input } from '@/components/Input';

function InvestInput({ isOpen, onClose, onUpdateInvestment, onShowCDistrictRecommendation }) {
  const [investInput, setInvestInput] = useState({
    firstInvestmentAmount: 0,
    storeArea: 0,
  });

  const Notice = '상권 추천을 받기 위해 초기 투자금과 업장 희망 면적을 입력해주세요.';

  const onClickClose = () => {
    onClose();
  };

  const onClickRecommend = () => {
    const { firstInvestmentAmount, storeArea } = investInput;

    const firstInvestmentAmountValue = parseInt(firstInvestmentAmount) * 10000;
    const storeAreaValue = parseInt(storeArea);

    if (firstInvestmentAmountValue >= 30000000 && storeAreaValue >= 1) {
      onUpdateInvestment(firstInvestmentAmountValue, storeAreaValue);
      onShowCDistrictRecommendation();
      onClose();
    } else if (
      (firstInvestmentAmountValue === 0 && storeAreaValue === 0) ||
      storeAreaValue === 0 ||
      firstInvestmentAmountValue === 0
    ) {
      alert('값을 입력해 주세요.');
    } else if (firstInvestmentAmountValue < 30000000) {
      alert('초기 투자금은 3천만원부터 입력 가능합니다.');
    } else if (storeAreaValue < 1) {
      alert('업장 희망 면적은 1부터 입력 가능합니다.');
    }
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    if (!isNaN(value)) {
      setInvestInput((prevState) => ({
        ...prevState,
        [name]: value,
      }));
    } else {
      alert('숫자를 입력해주세요.');
    }
  };

  return (
    <Modal isOpen={isOpen}>
      <Modal.Dimmed>
        <Modal.Container className="bg-background" width="1000px" height="85vh">
          <Modal.Close onClick={onClickClose} />
          <div className="flex flex-col items-center">
            <Modal.Title>초기 투자 입력</Modal.Title>
            <div className="mt-2">{Notice}</div>
            <div className="flex flex-col items-center justify-center w-11/12 h-[340px] bg-white mt-3 rounded-large">
              <Input className="w-1/2">
                <Input.Title>초기 투자금</Input.Title>
                <Input.Wrapper className="justify-between">
                  <Input.Section
                    name="firstInvestmentAmount"
                    placeholder="초기 투자금 입력"
                    type="input"
                    onChange={handleChange}
                  />
                  <Input.Unit>만 원</Input.Unit>
                </Input.Wrapper>
              </Input>
              <Input className="w-1/2 mt-10">
                <Input.Title>희망 면적</Input.Title>
                <Input.Wrapper className="justify-between">
                  <Input.Section
                    name="storeArea"
                    placeholder="희망 면적 입력"
                    type="input"
                    onChange={handleChange}
                  />
                  <Input.Unit>
                    <p>
                      m<sup>2</sup>
                    </p>
                  </Input.Unit>
                </Input.Wrapper>
              </Input>
            </div>
            <Modal.ButtonList>
              <RecommendButton
                title={'추천받기'}
                className="w-[300px] p-3 text-xl tracking-wide mx-1 mt-0"
                onClick={onClickRecommend}
              />
            </Modal.ButtonList>
          </div>
        </Modal.Container>
      </Modal.Dimmed>
    </Modal>
  );
}

export default InvestInput;
