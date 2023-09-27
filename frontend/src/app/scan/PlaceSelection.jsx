'use client';
import React, { useState } from 'react';
import ControllerTitle from './ControllerTitle';
import { Dropdown } from '@/components/Dropdown';
import sigunguData from '../../../public/시군구.json';
import dongData from '../../../public/행정동.json';
import { useSearchDispatch, useSearchState } from './SearchContext';
import RecommendButton from './RecommendButton';
import NextButton from './NextButton';
import { searchMode } from './constant';
import CDistrictRecommendation from './CDistrictRecommendation';

function PlaceSelection({ onChangeStage, mode }) {
  const [showRecommend, setShowRecommend] = useState(false);
  const { dongName, sigunguCode, sigunguName } = useSearchState();
  const dispatch = useSearchDispatch();

  const sigungu = sigunguData['sigungu'];
  const dong = dongData;

  const onSelectSigungu = ({ code, name }) => {
    dispatch({
      type: 'SET_SIGUNGU',
      sigunguCode: code,
      sigunguName: name,
      dongCode: dong[code][0].code,
      dongName: dong[code][0].name,
    });
  };

  const onSelectDong = ({ code, name }) => {
    dispatch({ type: 'SET_DONG', dongCode: code, dongName: name });
  };

  const onClickNext = () => {
    onChangeStage({ cur: 'CDISTRICT' });
  };

  const onClickRecommend = () => {
    setShowRecommend(true);
  };

  return (
    <div>
      <ControllerTitle title="지역을 선택해주세요" />
      <Dropdown>
        <Dropdown.Container className="mb-5 w-72">
          <Dropdown.Label>지역구</Dropdown.Label>
          <Dropdown.Trigger>{sigunguName}</Dropdown.Trigger>
          <Dropdown.OptionContainer>
            {sigungu.map(({ code, name }) => (
              <Dropdown.Option
                id={code}
                code={code}
                name={name}
                onSelect={onSelectSigungu}
                key={code}
              >
                {name}
              </Dropdown.Option>
            ))}
          </Dropdown.OptionContainer>
        </Dropdown.Container>
      </Dropdown>

      <Dropdown>
        <Dropdown.Container className="w-72 mb-28">
          <Dropdown.Label>행정동</Dropdown.Label>
          <Dropdown.Trigger>{dongName}</Dropdown.Trigger>
          <Dropdown.OptionContainer>
            {dong[sigunguCode].map(({ code, name }) => (
              <Dropdown.Option id={code} code={code} name={name} onSelect={onSelectDong} key={code}>
                {name}
              </Dropdown.Option>
            ))}
          </Dropdown.OptionContainer>
        </Dropdown.Container>
      </Dropdown>
      <div className="flex flex-row align-center">
        {mode === searchMode.BIZ ? (
          <>
            <RecommendButton
              title={'추천받기'}
              className="w-1/2 h-full p-3 mx-1 mt-0"
              onClick={onClickRecommend}
            />
            <CDistrictRecommendation
              isOpen={showRecommend}
              onClose={() => setShowRecommend(false)}
            />
            <NextButton onClick={onClickNext} className="w-1/2 h-full p-3 mx-1 mt-0" />
          </>
        ) : (
          <>
            <NextButton
              onClick={onClickNext}
              className="inline-block float-right w-3/4 p-3 ml-auto"
            />
          </>
        )}
      </div>
    </div>
  );
}

export default PlaceSelection;
