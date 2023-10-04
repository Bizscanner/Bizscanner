'use client';

import React, { useState, useEffect } from 'react';
import { useSearchState } from './SearchContext';
import axios from '@/api/index';
import { Modal } from '@/components/Modal';
import Button from '@/components/Button';
import { RecommendationIndicator } from './RecommendationIndicator';
import LocationIcon from '@/assets/icons/location_on.svg';

function Guide() {
  return (
    <div className="flex flex-col items-center mt-2 font-bold text-disabled">
      <p>LL : 다이나믹</p>
      <p>LH : 상권확장</p>
      <p>HL : 상권축소</p>
      <p>HH : 정체</p>
    </div>
  );
}

function CDistrictRecommendation({ isOpen, onClose, investmentData }) {
  const [noRecommend, setNoRecommend] = useState(false);

  const [cDistrictRecommendation, setCDistrictRecommendation] = useState({
    careaName: '',
    careaCode: '',
    averageNetProfitByJcategory: 0,
    averageSalesAmountRateByJcategory: '',
    averageTotalPopulation: 0,
    recommendedNetProfit: 0,
    recommendedSalesAmountRate: '',
    totalPopulation: 0,
    careaChange: '',
  });

  const { jcategoryCode } = useSearchState();

  const warning =
    '해당 정보는 추정 데이터를 기반으로 하고 있어 정확하지 않을 수 있기에 사용자의 책임하에 활용하시기 바랍니다.';

  const fetchData = async () => {
    try {
      const {
        data: {
          careaName,
          careaCode,
          averageNetProfitByJcategory,
          averageSalesAmountRateByJcategory,
          averageTotalPopulation,
          recommendedNetProfit,
          recommendedSalesAmountRate,
          totalPopulation,
          careaChange,
        },
      } = await axios.post('/carea-recommend/', {
        jcategoryCode,
        firstInvestmentAmount: investmentData.firstInvestmentAmount,
        storeArea: investmentData.storeArea,
      });
      setCDistrictRecommendation({
        careaName,
        careaCode,
        averageNetProfitByJcategory: averageNetProfitByJcategory.toLocaleString('ko-KR'),
        averageSalesAmountRateByJcategory,
        averageTotalPopulation: averageTotalPopulation.toLocaleString('ko-KR'),
        recommendedNetProfit: recommendedNetProfit.toLocaleString('ko-KR'),
        recommendedSalesAmountRate,
        totalPopulation: totalPopulation.toLocaleString('ko-KR'),
        careaChange,
      });
    } catch (error) {
      if (error.response && error.response.status === 404) {
        setNoRecommend(true);
      }
    }
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
          <div className="flex flex-col items-center">
            <Modal.Title>추천 상권</Modal.Title>
            <div className="text-sm text-center text-red-600">{warning}</div>
            {noRecommend ? (
              <div className="flex flex-row justify-center items-center w-[900px] h-[500px] bg-white mt-3 rounded-large">
                <div className="text-5xl text-black">추천 결과가 존재하지 않습니다.</div>
              </div>
            ) : (
              <>
                <div className="flex flex-row justify-center my-3">
                  <LocationIcon
                    className="mr-1 fill-primary"
                    style={{ width: '30px', height: '30px' }}
                  />
                  <p className="text-xl">{cDistrictRecommendation.careaName}</p>
                </div>
                <div className="flex flex-row justify-center w-[900px] h-[340px] bg-white mt-3 rounded-large">
                  <RecommendationIndicator>
                    <RecommendationIndicator.Title>추정 순이익</RecommendationIndicator.Title>
                    <RecommendationIndicator.Container className="text-white bg-primary">
                      <p>추천 상권</p>
                      <p>{cDistrictRecommendation.recommendedNetProfit} 원</p>
                    </RecommendationIndicator.Container>
                    <RecommendationIndicator.Container className="bg-background">
                      <p>평균 추정 순이익</p>
                      <p>{cDistrictRecommendation.averageNetProfitByJcategory} 원</p>
                    </RecommendationIndicator.Container>
                  </RecommendationIndicator>
                  <RecommendationIndicator>
                    <RecommendationIndicator.Title>
                      평균 매출액 변화율
                    </RecommendationIndicator.Title>
                    <RecommendationIndicator.Container className="text-white bg-primary">
                      <p>추천 상권</p>
                      <p>{cDistrictRecommendation.recommendedSalesAmountRate} %</p>
                    </RecommendationIndicator.Container>
                    <RecommendationIndicator.Container className="bg-background">
                      <p>전체 상권</p>
                      <p>{cDistrictRecommendation.averageSalesAmountRateByJcategory} %</p>
                    </RecommendationIndicator.Container>
                  </RecommendationIndicator>
                  <RecommendationIndicator>
                    <RecommendationIndicator.Title>평균 유동인구</RecommendationIndicator.Title>
                    <RecommendationIndicator.Container className="text-white bg-primary">
                      <p>추천 상권</p>
                      <p>{cDistrictRecommendation.totalPopulation} 명</p>
                    </RecommendationIndicator.Container>
                    <RecommendationIndicator.Container className="bg-background">
                      <p>전체 상권</p>
                      <p>{cDistrictRecommendation.averageTotalPopulation} 명</p>
                    </RecommendationIndicator.Container>
                  </RecommendationIndicator>
                  <RecommendationIndicator>
                    <RecommendationIndicator.Title>상권 변화지표</RecommendationIndicator.Title>
                    <RecommendationIndicator.Container className="text-white bg-primary">
                      <p>{cDistrictRecommendation.careaChange}</p>
                    </RecommendationIndicator.Container>
                    <Guide />
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
              </>
            )}
          </div>
        </Modal.Container>
      </Modal.Dimmed>
    </Modal>
  );
}

export default CDistrictRecommendation;
