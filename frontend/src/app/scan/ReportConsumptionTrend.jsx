import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import SummaryText from './SummaryText';
import axios from '@/api/index';
import { useSearchState } from './SearchContext';
import PieGraph from '@/components/Graph/PieGraph';
import { expenditureConfig } from '@/components/Graph/constants';
import DataNotFound from './DataNotFound';
import HighlightingText from '@/components/HighlightingText';

const ReportConsumptionTrend = forwardRef(function ReportConsumptionTrend({ careaCode }, ref) {
  const [consumptionTrend, setConsumptionTrend] = useState({});

  const fetchConsumptionTrend = async () => {
    const { data } = await axios.get(`/report/expenditure/${careaCode}`);
    setConsumptionTrend(data);
  };

  useEffect(() => {
    fetchConsumptionTrend();
  }, []);

  return (
    <ReportSection title="소비트렌드" ref={ref}>
      {consumptionTrend.groceryExpenditure ? (
        <>
          <SummaryText>
            해당 상권의 전체 소비금액은{' '}
            <HighlightingText>
              {consumptionTrend.totalExpenditure.toLocaleString('ko-kr')}원
            </HighlightingText>
            이며 소득 분위는{' '}
            <HighlightingText>{consumptionTrend.earningDecile}분위</HighlightingText> 입니다.
          </SummaryText>
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
        </>
      ) : (
        <DataNotFound />
      )}
    </ReportSection>
  );
});

export default ReportConsumptionTrend;
