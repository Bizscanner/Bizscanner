import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import SummaryText from './SummaryText';
import axios from '@/api/index';
import { useSearchState } from './SearchContext';
import { CareaChangeDesc } from './constant';

const ReportCareaChange = forwardRef(function ReportCareaChange({}, ref) {
  const [careaChange, setCareaChange] = useState({ careaChange: '', careaChangeName: '' });
  const { careaCode } = useSearchState();

  const fetchCareaChange = async () => {
    const { data } = await axios.get(`/report/cchange/${careaCode}`);
    setCareaChange(data);
  };

  useEffect(() => {
    fetchCareaChange();
  }, []);

  return (
    <ReportSection title="상권 변화 지표" ref={ref}>
      <SummaryText>
        {`해당 상권의 변화 지표는 ${careaChange.careaChangeName}입니다. `}
        {`${CareaChangeDesc[careaChange.careaChange]}입니다.`}
      </SummaryText>
    </ReportSection>
  );
});

export default ReportCareaChange;
