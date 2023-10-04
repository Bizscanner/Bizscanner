import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import axios from '@/api/index';
import { useSearchState } from './SearchContext';
import DataNotFound from './DataNotFound';

const ReportRent = forwardRef(function ReportRend({}, ref) {
  const [rent, setRent] = useState({
    rentAmount: 0,
    rentIncreaseRate: 0,
    depositAmount: 0,
    monthlyAmount: 0,
    maintenanceAmount: 0,
    firstInvestmentAmount: 0,
  });
  const { careaCode } = useSearchState();

  const fetchRent = async () => {
    const { data } = await axios.get(`/report/rent/${careaCode}`);
    setRent(data);
  };

  useEffect(() => {
    fetchRent();
  }, []);
  return (
    <ReportSection title="임대료" ref={ref}>
      {rent.ReportRend !== 0 ? (
        <>
          <div className="mb-5">
            <div className="mb-4 text-lg font-bold">통상 임대료</div>
            {rent.rentAmount.toLocaleString('ko-kr')}(원 / m<sup>2</sup>)
          </div>
          <div className="mb-5">
            <div className="mb-4 text-lg font-bold">통상 임대료 증감율</div>
            {rent.rentIncreaseRate}%
          </div>
          <div className="mb-5">
            <div className="mb-4 text-lg font-bold">보증금</div>
            {rent.depositAmount.toLocaleString('ko-kr')}(원 / m<sup>2</sup>)
          </div>
          <div className="mb-5">
            <div className="mb-4 text-lg font-bold">월세</div>
            {rent.monthlyAmount.toLocaleString('ko-kr')}(원 / m<sup>2</sup>)
          </div>
          <div className="mb-5">
            <div className="mb-4 text-lg font-bold">공용관리비</div>
            {rent.maintenanceAmount.toLocaleString('ko-kr')}(원 / m<sup>2</sup>)
          </div>
          <div className="mb-5">
            <div className="mb-4 text-lg font-bold">초기투자비</div>
            {rent.firstInvestmentAmount.toLocaleString('ko-kr')}(원 / m<sup>2</sup>)
          </div>
        </>
      ) : (
        <DataNotFound />
      )}
    </ReportSection>
  );
});

export default ReportRent;
