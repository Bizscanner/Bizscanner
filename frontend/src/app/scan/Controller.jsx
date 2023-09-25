'use client';
import React, { useState } from 'react';
import Selection from './SearchSelection';
import PlaceSelection from './PlaceSelection';
import { SearchProvider } from './SearchContext';
import JobSelection from './JobSelection';
import BizSelection from './BizSelection';
import BackIcon from '@/assets/icons/undo.svg';

const Stage = {
  INIT: Selection,
  PLACE: PlaceSelection,
  BIZ: BizSelection,
  JOB: JobSelection,
};

function Controller() {
  const [searchType, setSearchType] = useState({
    cur: 'INIT',
    history: [],
  });

  const CurStage = Stage[searchType.cur];

  const onChangeStage = (nextStage) => {
    setSearchType((prev) => ({
      cur: nextStage,
      history: [...prev.history, prev.cur],
    }));
  };

  const onClickPrev = () => {
    setSearchType((prev) => ({
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
        <CurStage onChangeStage={onChangeStage} />
      </div>
    </SearchProvider>
  );
}

export default Controller;
