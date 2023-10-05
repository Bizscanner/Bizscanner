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
import HighlightingText from '@/components/HighlightingText';
import DataNotFound from './DataNotFound';

const ReportStoreCount = forwardRef(function ReportStoreCount({ careaCode, jcategoryCode }, ref) {
  const [storeCountInfo, setStoreCountInfo] = useState();

  const fetchStoreCount = async () => {
    const { data } = await axios.get(`/report/stores/${careaCode}/${jcategoryCode}`);
    setStoreCountInfo(data.quarterlyStore);
  };

  useEffect(() => {
    fetchStoreCount();
  }, []);

  return (
    <ReportSection title="점포 수" ref={ref}>
      {storeCountInfo ? (
        <>
          <SummaryText>
            해당 상권에서 운영 중인 점포 수는 전년 동분기 대비
            <HighlightingText>
              {' ' + getLastYearDiff(storeCountInfo)}개 {getLastYearDiffText(storeCountInfo)}
            </HighlightingText>
            하였으며, 전분기 대비
            <HighlightingText>
              {' ' + getPrevQuaterDiff(storeCountInfo)}개 {getPrevQuaterDiffText(storeCountInfo)}
            </HighlightingText>
            하였습니다.
          </SummaryText>
          <div className="flex items-center justify-center">
            <div className="w-3/4">
              <Bar
                graphData={storeCountInfo}
                title="점포 수"
                config={quaterConfig}
                dataLabel="점포 수 (개)"
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

export default ReportStoreCount;
