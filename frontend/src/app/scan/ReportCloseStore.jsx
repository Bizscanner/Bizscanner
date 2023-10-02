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

const ReportCloseStore = forwardRef(function ReportCloseStore({}, ref) {
  const [closeStoreInfo, setCloseStoreInfo] = useState([]);
  const { careaCode, jcategoryCode } = useSearchState();

  const fetchCloseStoreInfo = async () => {
    const {
      data: { quarterlyCloseStore },
    } = await axios.get(`/report/stores/close-status/${careaCode}/${jcategoryCode}`);
    setCloseStoreInfo(quarterlyCloseStore);
  };
  useEffect(() => {
    fetchCloseStoreInfo();
  }, []);

  return (
    <ReportSection title="폐업 현황" ref={ref}>
      <SummaryText>{`해당 상권에서 폐업한 점포수는 전년 동분기 대비 ${getLastYearDiff(
        closeStoreInfo,
      )}개 ${getLastYearDiffText(closeStoreInfo)} 하였으며, 전분기 대비 ${getPrevQuaterDiff(
        closeStoreInfo,
      )}개 ${getPrevQuaterDiffText(closeStoreInfo)}하였습니다.`}</SummaryText>
      <div className="flex items-center justify-center">
        <div className="w-3/4">
          <Bar
            graphData={closeStoreInfo}
            title="폐업 현황"
            config={quaterConfig}
            dataLabel="점포수 (개)"
          />
        </div>
      </div>
    </ReportSection>
  );
});

export default ReportCloseStore;
