import React, { forwardRef, useEffect, useState } from 'react';
import axios from '@/api/index';
import { useSearchState } from './SearchContext';
import AreaGraph from '@/components/Graph/AreaGraph';
import ReportSection from './ReportSection';
import PieGraph from '@/components/Graph/PieGraph';
import {
  ageConfig,
  dayConfig,
  genderConfig,
  quaterConfig,
  timeLabel,
} from '@/components/Graph/constants';
import LineGraph from '@/components/Graph/LineGraph';
import BarGraph from '@/components/Graph/BarGraph';
import RadioButton from '@/components/RadioButton';
import SummaryText from './SummaryText';
import {
  getLastYearDiff,
  getLastYearDiffText,
  getPrevQuaterDiff,
  getPrevQuaterDiffText,
} from '@/utils/diff';

const radioButtons = [
  { key: 'QUATER', text: '분기별' },
  { key: 'DAY', text: '요일별' },
  { key: 'GENDER', text: '성별' },
  { key: 'TIME', text: '시간대별' },
  { key: 'AGE', text: '연령대별' },
];

const dataLabel = '매출액 (원)';

const ReportSales = forwardRef(function ReportSales({}, ref) {
  const [salesInfo, setSalesInfo] = useState({
    quarterlySalesAmountResponses: [],
    daySalesAmountResponses: [],
    timeSalesAmountResponses: [],
    genderSalesAmountResponses: [],
    ageSalesAmountResponses: [],
  });

  const [graph, setGraph] = useState('QUATER');

  const GraphMapper = {
    QUATER: (
      <LineGraph
        graphData={salesInfo.quarterlySalesAmountResponses}
        title="분기별 매출"
        config={quaterConfig}
        dataLabel={dataLabel}
      />
    ),
    DAY: (
      <BarGraph
        graphData={salesInfo.daySalesAmountResponses}
        title="요일별 매출"
        config={dayConfig}
        dataLabel={dataLabel}
      />
    ),
    TIME: (
      <AreaGraph
        title="시간대별 매출"
        graphData={salesInfo.timeSalesAmountResponses}
        labels={timeLabel}
        dataLabel={dataLabel}
      />
    ),
    GENDER: (
      <PieGraph
        title="성별 매출"
        graphData={salesInfo.genderSalesAmountResponses}
        config={genderConfig}
        dataLabel
      />
    ),
    AGE: (
      <BarGraph
        title="연령대별 매출"
        graphData={salesInfo.ageSalesAmountResponses}
        config={ageConfig}
        dataLabel={dataLabel}
      />
    ),
  };

  const { careaCode, jcategoryCode } = useSearchState();

  const fetchSalesInfo = async () => {
    const { data } = await axios.get(`/report/sales/amount/${careaCode}/${jcategoryCode}`);
    setSalesInfo(data);
  };

  const onClickRadioButton = (key) => {
    setGraph(key);
  };

  useEffect(() => {
    fetchSalesInfo();
  }, []);

  return (
    <ReportSection title="매출액" ref={ref}>
      <SummaryText>{`해당 상권에서 매출은 전년 동분기 대비 ${getLastYearDiff(
        salesInfo.quarterlySalesAmountResponses,
      )}원 ${getLastYearDiffText(
        salesInfo.quarterlySalesAmountResponses,
      )} 하였으며, 전분기 대비 ${getPrevQuaterDiff(
        salesInfo.quarterlySalesAmountResponses,
      )}원 ${getPrevQuaterDiffText(
        salesInfo.quarterlySalesAmountResponses,
      )}하였습니다.`}</SummaryText>
      <div className="flex justify-around gap-2">
        {radioButtons.map(({ key, text }) => (
          <RadioButton
            text={text}
            key={key}
            onClick={() => onClickRadioButton(key)}
            isClicked={key === graph}
          />
        ))}
      </div>
      <div className="flex items-center justify-center">
        <div className="w-3/4">{GraphMapper[graph]}</div>
      </div>
    </ReportSection>
  );
});

export default ReportSales;
