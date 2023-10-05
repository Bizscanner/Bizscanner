'use client';

import React, { useState, useEffect } from 'react';
import { useSearchDispatch, useSearchState } from './SearchContext';
import axios from '@/api/index';
import { Modal } from '@/components/Modal';
import Button from '@/components/Button';
import { RecommendationIndicator } from './RecommendationIndicator';
import LocationIcon from '@/assets/icons/location_on.svg';
import Report from './Report';

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
  const [showReport, setShowReport] = useState(false);

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

  const { jcategoryName, jcategoryCode, careaName, careaCode } = useSearchState();
  const dispatch = useSearchDispatch();

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

      dispatch({ type: 'SET_CAREA', careaCode, careaName });
    } catch (error) {
      if (error.response && error.response.status === 404) {
        setNoRecommend(true);
      }
    }
  };

  const onClickClose = () => {
    onClose();
  };

  const onClickShowReport = () => {
    setShowReport(true);
  };

  const onCloseReport = () => {
    setShowReport(false);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <>
      <Modal isOpen={isOpen}>
        <Modal.Dimmed>
          <Modal.Container className="bg-background" width="1000px" height="90vh">
            <Modal.Close onClick={onClickClose} />
            <div className="flex flex-col items-center h-full">
              <Modal.Title className="mb-2">추천 상권</Modal.Title>
              <div className="text-sm text-center text-red-600">{warning}</div>
              {noRecommend ? (
                <div className="flex flex-row items-center justify-center w-full mt-3 bg-white h-4/5 rounded-large">
                  <div className="text-5xl text-black">추천 결과가 존재하지 않습니다.</div>
                </div>
              ) : (
                <>
                  <div className="flex flex-row items-center justify-center my-3">
                    <LocationIcon
                      className="mr-1 fill-primary"
                      style={{ width: '44px', height: '44px' }}
                    />
                    <p className="text-4xl font-bold">{cDistrictRecommendation.careaName}</p>
                  </div>
                  <div className="flex flex-row justify-center w-full mt-3 mb-3 bg-white h-3/5 rounded-large">
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
                    <div className="flex flex-col items-center justify-center h-full">
                      <div className="border-2 border-r border-black h-3/4"></div>
                    </div>
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
                    <div className="flex flex-col items-center justify-center h-full">
                      <div className="border-2 border-r border-black h-3/4"></div>
                    </div>
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
                    <div className="flex flex-col items-center justify-center h-full">
                      <div className="border-2 border-r border-black h-3/4"></div>
                    </div>
                    <RecommendationIndicator>
                      <RecommendationIndicator.Title>상권 변화지표</RecommendationIndicator.Title>
                      <RecommendationIndicator.Container className="text-white bg-primary">
                        <p>{cDistrictRecommendation.careaChange}</p>
                      </RecommendationIndicator.Container>
                      <Guide />
                    </RecommendationIndicator>
                  </div>
                  <Button
                    className="text-xl tracking-wide text-white bg-primary rounded-small"
                    width="400px"
                    height="50px"
                    onClick={onClickShowReport}
                  >
                    리포트 보기
                  </Button>
                </>
              )}
            </div>
          </Modal.Container>
        </Modal.Dimmed>
      </Modal>
      {showReport && (
        <Report
          jcategoryName={jcategoryName}
          jcategoryCode={jcategoryCode}
          careaName={careaName}
          careaCode={careaCode}
          onClose={onCloseReport}
        />
      )}
    </>
  );
}

export default CDistrictRecommendation;
