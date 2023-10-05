'use client';
import React, { useState } from 'react';
import Selection from './SearchSelection';
import PlaceSelection from './PlaceSelection';
import JobSelection from './JobSelection';
import BizSelection from './BizSelection';
import BackIcon from '@/assets/icons/undo.svg';
import CDistrictSelection from './CDistrictSelection';
import Button from '@/components/Button';
import { useSearchDispatch } from './SearchContext';

const Stage = {
  INIT: Selection,
  PLACE: PlaceSelection,
  BIZ: BizSelection,
  JOB: JobSelection,
  CDISTRICT: CDistrictSelection,
};

function Controller() {
  const dispatch = useSearchDispatch();

  const [searchType, setSearchType] = useState({
    mode: '',
    cur: 'INIT',
    history: [],
  });

  const CurStage = Stage[searchType.cur];

  const onChangeStage = (nextStage) => {
    setSearchType((prev) => ({
      ...prev,
      history: [...prev.history, prev.cur],
      ...nextStage,
    }));
  };

  const onClickPrev = () => {
    setSearchType((prev) => ({
      ...prev,
      cur: prev.history.pop(),
      history: [...prev.history],
    }));
  };

  const onClickStart = () => {
    setSearchType(() => ({
      mode: '',
      cur: 'INIT',
      history: [],
    }));
    dispatch({
      type: 'RESET',
    });
  };

  return (
    <div className="absolute z-10 p-5 bg-white left-4 rounded-medium top-1/2 translate-y-[-50%]">
      {searchType.cur !== 'INIT' ? (
        <div className="flex flex-row justify-between">
          <button onClick={onClickPrev}>
            <BackIcon width="48" height="48" />
          </button>
          <Button
            className="text-white bg-disabled rounded-medium hover:bg-primary"
            width="30%"
            onClick={onClickStart}
          >
            처음으로
          </Button>
        </div>
      ) : null}
      <CurStage onChangeStage={onChangeStage} mode={searchType.mode} />
    </div>
  );
}

export default Controller;
