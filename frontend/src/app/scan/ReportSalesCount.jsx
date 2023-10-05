import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import SummaryText from './SummaryText';
import axios from '@/api/index';
import { useSearchState } from './SearchContext';
import {
  getLastYearDiff,
  getLastYearDiffText,
  getPrevQuaterDiff,
  getPrevQuaterDiffText,
} from '@/utils/diff';
import { quaterConfig } from '@/components/Graph/constants';
import LineGraph from '@/components/Graph/LineGraph';
import DataNotFound from './DataNotFound';
import HighlightingText from '@/components/HighlightingText';

const ReportSalesCount = forwardRef(function ReportSalesCount({ careaCode, jcategoryCode }, ref) {
  const [salesCountInfo, setSalesCountInfo] = useState([]);

  const fetchSalesCountInfo = async () => {
    const {
      data: { quarterlySalesCountResponseList },
    } = await axios.get(`/report/sales/count/${careaCode}/${jcategoryCode}`);
    setSalesCountInfo(
      quarterlySalesCountResponseList.map(({ quarterSalesCount }) => quarterSalesCount),
    );
  };
  useEffect(() => {
    fetchSalesCountInfo();
  }, []);

  return (
    <ReportSection title="매출건수" ref={ref}>
      {salesCountInfo.length ? (
        <>
          <SummaryText>
            해당 상권에서 매출 건수는 전년 동분기 대비{' '}
            <HighlightingText>
              {getLastYearDiff(salesCountInfo)}개 {getLastYearDiffText(salesCountInfo)}
            </HighlightingText>{' '}
            하였으며, 전분기 대비{' '}
            <HighlightingText>
              {getPrevQuaterDiff(salesCountInfo)}개 {getPrevQuaterDiffText(salesCountInfo)}
            </HighlightingText>
            하였습니다.
          </SummaryText>
          <div className="flex items-center justify-center">
            <div className="w-3/4">
              <LineGraph
                graphData={salesCountInfo}
                title="매출건수"
                config={quaterConfig}
                dataLabel="매출건수 (개)"
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
export default ReportSalesCount;
