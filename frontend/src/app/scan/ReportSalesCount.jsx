import React, { useEffect, useState } from 'react';
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

function ReportSalesCount() {
  const [salesCountInfo, setSalesCountInfo] = useState([]);
  const { careaCode, jcategoryCode } = useSearchState();

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
    <ReportSection title="매출건수">
      <SummaryText>{`해당 상권에서 매출 건수는 전년 동분기 대비 ${getLastYearDiff(
        salesCountInfo,
      )}개 ${getLastYearDiffText(salesCountInfo)} 하였으며, 전분기 대비 ${getPrevQuaterDiff(
        salesCountInfo,
      )}개 ${getPrevQuaterDiffText(salesCountInfo)}하였습니다.`}</SummaryText>
      <div className="flex items-center justify-center">
        <div className="w-3/4">
          <LineGraph graphData={salesCountInfo} title="매출건수" config={quaterConfig} />
        </div>
      </div>
    </ReportSection>
  );
}

export default ReportSalesCount;