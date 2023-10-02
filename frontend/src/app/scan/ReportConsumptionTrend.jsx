import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import SummaryText from './SummaryText';
import axios from '@/api/index';
import { useSearchState } from './SearchContext';
import PieGraph from '@/components/Graph/PieGraph';
import { expenditureConfig } from '@/components/Graph/constants';

const ReportConsumptionTrend = forwardRef(function ReportConsumptionTrend({}, ref) {
  const [consumptionTrend, setConsumptionTrend] = useState({});
  const { careaCode } = useSearchState();

  const fetchConsumptionTrend = async () => {
    const { data } = await axios.get(`/report/expenditure/${careaCode}`);
    setConsumptionTrend(data);
  };

  useEffect(() => {
    fetchConsumptionTrend();
  }, []);

  return (
    <ReportSection title="소비트렌드" ref={ref}>
      <SummaryText>{`해당 상권의 전체 소비금액은 ${consumptionTrend.totalExpenditure}원이며 소득 분위는 ${consumptionTrend.earningDecile}분위 입니다.`}</SummaryText>
      <div className="flex items-center justify-center">
        <div className="w-3/4">
          <PieGraph
            title="소비트렌드"
            graphData={[
              consumptionTrend.groceryExpenditure,
              consumptionTrend.clothExpenditure,
              consumptionTrend.householdExpenditure,
              consumptionTrend.medicalExpenditure,
              consumptionTrend.transportationExpenditure,
              consumptionTrend.leisureExpenditure,
              consumptionTrend.cultureExpenditure,
              consumptionTrend.educationExpenditure,
              consumptionTrend.pleasureExpenditure,
            ]}
            config={expenditureConfig}
          />
        </div>
      </div>
    </ReportSection>
  );
});

export default ReportConsumptionTrend;
