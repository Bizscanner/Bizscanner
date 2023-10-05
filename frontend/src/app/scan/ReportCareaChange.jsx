import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import SummaryText from './SummaryText';
import axios from '@/api/index';
import { useSearchState } from './SearchContext';
import { CareaChangeDesc } from './constant';
import DataNotFound from './DataNotFound';
import HighlightingText from '@/components/HighlightingText';

const ReportCareaChange = forwardRef(function ReportCareaChange({ careaCode }, ref) {
  const [careaChange, setCareaChange] = useState({ careaChange: '', careaChangeName: '' });

  const fetchCareaChange = async () => {
    const { data } = await axios.get(`/report/cchange/${careaCode}`);
    setCareaChange(data);
  };

  useEffect(() => {
    fetchCareaChange();
  }, []);

  return (
    <ReportSection title="상권 변화 지표" ref={ref}>
      {careaChange.careaChange ? (
        <>
          <SummaryText>
            해당 상권의 변화 지표는{' '}
            <HighlightingText>{careaChange.careaChangeName}</HighlightingText>입니다.
            {`${CareaChangeDesc[careaChange.careaChange]}입니다.`}
          </SummaryText>
        </>
      ) : (
        <DataNotFound />
      )}
    </ReportSection>
  );
});

export default ReportCareaChange;
