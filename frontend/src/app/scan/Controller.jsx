'use client';
import React, { useState } from 'react';
import Selection from './SearchSelection';
import PlaceSelection from './PlaceSelection';
import { SearchProvider } from './SearchContext';
import JobSelection from './JobSelection';
import BizSelection from './BizSelection';
import BackIcon from '@/assets/icons/undo.svg';
import CDistrictSelection from './CDistrictSelection';

const Stage = {
  INIT: Selection,
  PLACE: PlaceSelection,
  BIZ: BizSelection,
  JOB: JobSelection,
  CDISTRICT: CDistrictSelection,
};

function Controller() {
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

  return (
    <SearchProvider>
      <div className="absolute z-10 p-5 bg-white left-4 rounded-medium top-1/2 translate-y-[-50%]">
        {searchType.cur !== 'INIT' ? (
          <button onClick={onClickPrev}>
            <BackIcon width="48" height="48" />
          </button>
        ) : null}
        <CurStage onChangeStage={onChangeStage} mode={searchType.mode} />
      </div>
    </SearchProvider>
  );
}

export default Controller;
