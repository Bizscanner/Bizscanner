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
import Bar from '@/components/Graph/BarGraph';
import { quaterConfig } from '@/components/Graph/constants';
import HighlightingText from '@/components/HighlightingText';
import DataNotFound from './DataNotFound';

const ReportOpenStore = forwardRef(function ReportOpenStore({ careaCode, jcategoryCode }, ref) {
  const [openStoreInfo, setOpenStoreInfo] = useState([]);

  const fetchOpenStoreInfo = async () => {
    const {
      data: { quarterlyOpenStore },
    } = await axios.get(`/report/stores/open-status/${careaCode}/${jcategoryCode}`);
    setOpenStoreInfo(quarterlyOpenStore);
  };

  useEffect(() => {
    fetchOpenStoreInfo();
  }, []);
  return (
    <ReportSection title="개업 현황" ref={ref}>
      {openStoreInfo.length ? (
        <>
          <SummaryText>
            해당 상권에서 개업한 점포 수는 전년 동분기 대비{' '}
            <HighlightingText>
              {getLastYearDiff(openStoreInfo)}개 {getLastYearDiffText(openStoreInfo)}
            </HighlightingText>
            하였으며, 전분기 대비{' '}
            <HighlightingText>
              {getPrevQuaterDiff(openStoreInfo)}개 {getPrevQuaterDiffText(openStoreInfo)}
            </HighlightingText>
            하였습니다.
          </SummaryText>
          <div className="flex items-center justify-center">
            <div className="w-3/4">
              <Bar
                graphData={openStoreInfo}
                title="개업 현황"
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

export default ReportOpenStore;
