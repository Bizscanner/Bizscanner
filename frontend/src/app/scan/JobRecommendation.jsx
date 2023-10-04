'use client';

import React, { useState, useEffect } from 'react';
import { useSearchState } from './SearchContext';
import axios from '@/api/index';
import { Modal } from '@/components/Modal';
import Button from '@/components/Button';
import { RecommendationIndicator } from './RecommendationIndicator';
import StoreIcon from '@/assets/icons/local_convenience_store.svg';
import jcodedata from '../../../public/jcategorycode.json';


function JobRecommendation({ isOpen, onClose }) {
  const [jobRecommendation, setJobRecommendation] = useState({
    jcategoryName: '',
    jcategoryCode: '',
    averageNetProfitByJcategory: 0,
    averageSalesAmountRateByJcategory: '',
    recommendedNetProfitByJcategory: 0,
    recommendedCareaSalesAmountRateByJcategory: '',
    closeRate: '',
  });

  const { careaCode } = useSearchState();

  const warning =
    '해당 정보는 추정 데이터를 기반으로 하고 있어 정확하지 않을 수 있기에 사용자의 책임하에 활용하시기 바랍니다.';

  const fetchData = async () => {
    const {
      data: {
        jcategoryCode,
        averageNetProfitByJcategory,
        averageSalesAmountRateByJcategory,
        recommendedNetProfitByJcategory,
        recommendedCareaSalesAmountRateByJcategory,
        closeRate,
      },
    } = await axios.get(`/jcategory-recommend/${careaCode}`);
    setJobRecommendation({
      jcategoryName: jcodedata[jcategoryCode].name,
      jcategoryCode,
      averageNetProfitByJcategory: averageNetProfitByJcategory.toLocaleString('ko-KR'),
      averageSalesAmountRateByJcategory,
      recommendedNetProfitByJcategory: recommendedNetProfitByJcategory.toLocaleString('ko-KR'),
      recommendedCareaSalesAmountRateByJcategory,
      closeRate,
    });
  };

  const onClickClose = () => {
    onClose();
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <Modal isOpen={isOpen}>
      <Modal.Dimmed>
        <Modal.Container className="bg-background" width="1000px" height="90vh">
          <Modal.Close onClick={onClickClose} />
          <div className="flex flex-col items-center h-full">
            <Modal.Title>추천 업종</Modal.Title>
            <div className="text-sm text-center text-red-600">{warning}</div>
            <div className="flex flex-row justify-center my-3">
              <StoreIcon className="mr-1 fill-primary" style={{ width: '30px', height: '30px' }} />
              <p className="text-xl">{jobRecommendation.jcategoryName}</p>
            </div>
            <div className="flex flex-row justify-center w-full mt-3 bg-white h-3/5 rounded-large">
              <RecommendationIndicator>
                <RecommendationIndicator.Title>평균 매출액</RecommendationIndicator.Title>
                <RecommendationIndicator.Container className="text-white bg-primary">
                  <p>추천 업종</p>
                  <p>{jobRecommendation.recommendedNetProfitByJcategory} 원</p>
                </RecommendationIndicator.Container>
                <RecommendationIndicator.Container className="bg-background">
                  <p>전체 업종</p>
                  <p>{jobRecommendation.averageNetProfitByJcategory} 원</p>
                </RecommendationIndicator.Container>
              </RecommendationIndicator>
              <RecommendationIndicator>
                <RecommendationIndicator.Title>평균 매출액 변화율</RecommendationIndicator.Title>
                <RecommendationIndicator.Container className="text-white bg-primary">
                  <p>추천 업종</p>
                  <p>{jobRecommendation.recommendedCareaSalesAmountRateByJcategory} %</p>
                </RecommendationIndicator.Container>
                <RecommendationIndicator.Container className="bg-background">
                  <p>전체 업종</p>
                  <p>{jobRecommendation.averageSalesAmountRateByJcategory} %</p>
                </RecommendationIndicator.Container>
              </RecommendationIndicator>
              <RecommendationIndicator>
                <RecommendationIndicator.Title>폐업률</RecommendationIndicator.Title>
                <RecommendationIndicator.Container className="text-white bg-primary">
                  <p>추천 업종</p>
                  <p>{jobRecommendation.closeRate} %</p>
                </RecommendationIndicator.Container>
              </RecommendationIndicator>
            </div>
            <Modal.ButtonList>
              <Button
                className="text-xl tracking-wide text-white bg-primary rounded-small"
                width="400px"
                height="50px"
              >
                리포트 보기
              </Button>
            </Modal.ButtonList>
          </div>
        </Modal.Container>
      </Modal.Dimmed>
    </Modal>
  );
}

export default JobRecommendation;
