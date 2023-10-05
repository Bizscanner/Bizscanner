import React, { forwardRef, useEffect, useState } from 'react';
import ReportSection from './ReportSection';
import { useSearchState } from './SearchContext';
import axios from '@/api/index';
import { Age, Day, Gender, Time } from './constant';

function IndicatorSection({ title, children }) {
  return (
    <div className="flex flex-col justify-center flex-grow mb-20">
      <div className="mb-4 text-lg font-bold text-center">{title}</div>
      <div className="flex gap-2">{children}</div>
    </div>
  );
}

function Indicator({ title, value }) {
  return (
    <div className="flex flex-col items-center justify-center p-4 text-2xl font-bold text-center border-2 text-primary grow border-outline rounded-small">
      <div className="text-base text-disabled">{title}</div>
      {value}
    </div>
  );
}

const ReportSummary = forwardRef(function ReportSummary({ careaCode }, ref) {
  const [bestJob, setBestJob] = useState();
  const [bestSales, setBestSales] = useState();
  const [bestFloatingPopulation, setBestFloatingPopulation] = useState();

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
      <div>
        <IndicatorSection title="BEST 업종">
          <Indicator title="점포 수" value={bestJob?.bestStoreCountJcategory} />
          <Indicator title="개업 점포 수" value={bestJob?.bestOpenStoreCountJcategory} />
          <Indicator title="폐업 점포 수" value={bestJob?.bestCloseStoreCountJcategory} />
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
