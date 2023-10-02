import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import { useSearchState } from './SearchContext';
import axios from '@/api/index';
import { Age, Day, Gender, Time } from './constant';

function IndicatorSection({ title, children }) {
  return (
    <div className="flex-grow">
      <div className="mb-4 text-lg font-bold text-center">{title}</div>
      <div className="grid grid-cols-2 grid-rows-3 gap-2">{children}</div>
    </div>
  );
}

function Indicator({ title, value }) {
  return (
    <div className="inline p-4 font-bold text-center border-2 border-outline rounded-small">
      <div className="text-primary">{title}</div>
      {value}
    </div>
  );
}

const ReportSummary = forwardRef(function ReportSummary({}, ref) {
  const [bestJob, setBestJob] = useState();
  const [bestSales, setBestSales] = useState();
  const [bestFloatingPopulation, setBestFloatingPopulation] = useState();

  const { careaCode } = useSearchState();

  const fetchBestJob = async () => {
    const { data } = await axios.get(`/report/best-jcategory/${careaCode}`);
    setBestJob(data);
  };

  const fetchBestSales = async () => {
    const { data } = await axios.get(`/report/best-sales-amount/${careaCode}`);
    setBestSales(data);
  };

  const fetchBestFloatingPopulation = async () => {
    const { data } = await axios.get(`/report/best-population/${careaCode}`);
    setBestFloatingPopulation(data);
  };

  useEffect(() => {
    fetchBestJob();
    fetchBestSales();
    fetchBestFloatingPopulation();
  }, []);

  return (
    <ReportSection title="간략분석" ref={ref}>
      <div className="flex gap-3">
        <IndicatorSection title="BEST 업종">
          <Indicator title="점포수" value={bestJob?.bestStoreCountJcategory} />
          <Indicator title="개업 점포수" value={bestJob?.bestOpenStoreCountJcategory} />
          <Indicator title="폐업 점포수" value={bestJob?.bestCloseStoreCountJcategory} />
        </IndicatorSection>
        <IndicatorSection title="BEST 매출">
          <Indicator title="성별" value={Gender[bestSales?.bestSalesGender]} />
          <Indicator title="연령대" value={Age[bestSales?.bestSalesAge]} />
          <Indicator title="요일" value={Day[bestSales?.bestSalesDay]} />
          <Indicator title="시간대" value={Time[bestSales?.bestSalesTime]} />
          <Indicator title="업종" value={bestSales?.bestJcategoryCode} />
        </IndicatorSection>
        <IndicatorSection title="BEST 유동인구">
          <Indicator title="성별" value={Gender[bestFloatingPopulation?.bestPopulationGender]} />
          <Indicator title="연령대" value={Age[bestFloatingPopulation?.bestPopulationAge]} />
          <Indicator title="요일" value={Day[bestFloatingPopulation?.bestPopulationDay]} />
          <Indicator title="시간대" value={Time[bestFloatingPopulation?.bestPopulationTime]} />
        </IndicatorSection>
      </div>
    </ReportSection>
  );
});
export default ReportSummary;
