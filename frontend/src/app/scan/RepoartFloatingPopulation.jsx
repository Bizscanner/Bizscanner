import React, { useEffect, useState } from 'react';
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
import DoubleBarGraph from '@/components/Graph/DoubleBarGraph';

const radioButtons = [
  { key: 'QUATER', text: '분기별' },
  { key: 'DAY', text: '요일별' },
  { key: 'GENDER', text: '성별' },
  { key: 'TIME', text: '시간대별' },
  { key: 'AGE', text: '연령대별' },
  { key: 'GENDER_AGE', text: '성별, 연령별' },
];

function ReportFloatingPopulation() {
  const [floatPopulationInfo, setFloatPopulationInfo] = useState({
    quarterlyPopulation: [],
    dayPopulation: [],
    timePopulation: [],
    genderPopulation: [],
    agePopulation: [],
    maleAgePopulation: [],
    femaleAgePopulation: [],
  });

  const [graph, setGraph] = useState('QUATER');

  const GraphMapper = {
    QUATER: (
      <LineGraph
        graphData={floatPopulationInfo.quarterlyPopulation}
        title="분기별 유동인구"
        config={quaterConfig}
      />
    ),
    DAY: (
      <BarGraph
        graphData={floatPopulationInfo.dayPopulation}
        title="요일별 유동인구"
        config={dayConfig}
      />
    ),
    TIME: (
      <AreaGraph
        title="시간대별 유동인구"
        graphData={floatPopulationInfo.timePopulation}
        labels={timeLabel}
      />
    ),
    GENDER: (
      <PieGraph
        title="성별 유동인구"
        graphData={floatPopulationInfo.genderPopulation}
        config={genderConfig}
      />
    ),
    AGE: (
      <BarGraph
        title="연령대별 유동인구"
        graphData={floatPopulationInfo.agePopulation}
        config={ageConfig}
      />
    ),
    GENDER_AGE: (
      <DoubleBarGraph
        title="성별, 연령별 유동인구"
        firstGraphData={floatPopulationInfo.femaleAgePopulation}
        secondGraphData={floatPopulationInfo.maleAgePopulation}
        firstLabel="여성"
        secondLabel="남성"
        config={ageConfig}
      />
    ),
  };

  const { careaCode } = useSearchState();

  const fetchSalesInfo = async () => {
    const { data } = await axios.get(`/report/population/${careaCode}`);
    console.log(data);
    setFloatPopulationInfo((prev) => ({
      ...data,
      quarterlyPopulation: data.quarterlyPopulation.map(({ totalPopulation }) => totalPopulation),
    }));
  };

  const onClickRadioButton = (key) => {
    setGraph(key);
  };

  useEffect(() => {
    fetchSalesInfo();
  }, []);

  return (
    <ReportSection title="유동인구">
      <SummaryText>{`해당 상권에서 유동인구는 전년 동분기 대비 ${getLastYearDiff(
        floatPopulationInfo.quarterlyPopulation,
      )}원 ${getLastYearDiffText(
        floatPopulationInfo.quarterlyPopulation,
      )} 하였으며, 전분기 대비 ${getPrevQuaterDiff(
        floatPopulationInfo.quarterlyPopulation,
      )}원 ${getPrevQuaterDiffText(
        floatPopulationInfo.quarterlyPopulation,
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
}

export default ReportFloatingPopulation;