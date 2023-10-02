import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import SummaryText from './SummaryText';
import { useSearchState } from './SearchContext';
import axios from '@/api/index';
import Bar from '@/components/Graph/BarGraph';
import {
  getLastYearDiff,
  getLastYearDiffText,
  getPrevQuaterDiff,
  getPrevQuaterDiffText,
} from '@/utils/diff';
import { quaterConfig } from '@/components/Graph/constants';

const ReportStoreCount = forwardRef(function ReportStoreCount({}, ref) {
  const [storeCountInfo, setStoreCountInfo] = useState();
  const { careaCode, jcategoryCode } = useSearchState();

  const fetchStoreCount = async () => {
    const { data } = await axios.get(`/report/stores/${careaCode}/${jcategoryCode}`);
    setStoreCountInfo(data.quarterlyStore);
  };

  useEffect(() => {
    fetchStoreCount();
  }, []);

  return (
    <ReportSection title="점포수" ref={ref}>
      <SummaryText>
        {storeCountInfo &&
          `해당 상권에서 운영 중인 한식 점포수 전년 동분기 대비 ${getLastYearDiff(
            storeCountInfo,
          )}개 ${getLastYearDiffText(storeCountInfo)}
      하였으며,전분기 대비 ${getPrevQuaterDiff(storeCountInfo)}개 ${getPrevQuaterDiffText(
            storeCountInfo,
          )}하였습니다.`}
      </SummaryText>
      <div className="flex items-center justify-center">
        <div className="w-3/4">
          <Bar
            graphData={storeCountInfo}
            title="점포수"
            config={quaterConfig}
            dataLabel="점포수 (개)"
          />
        </div>
      </div>
    </ReportSection>
  );
});

export default ReportStoreCount;
