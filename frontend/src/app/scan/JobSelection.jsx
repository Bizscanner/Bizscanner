import React, { useState } from 'react';
import jdata from '../../../public/jcategory.json';
import { useSearchState, useSearchDispatch } from './SearchContext';
import RecommendButton from './RecommendButton';
import { searchMode } from './constant';
import JobRecommendation from './JobRecommendation';
import Report from './Report';

function JobSelection({ onChangeStage, mode }) {
  const [showRecommend, setShowRecommend] = useState(false);
  const [showReport, setShowReport] = useState(false);
  const { jcategoryName, jcategoryCode, careaName, careaCode, bizCode, bizName } = useSearchState();
  const jList = jdata[bizCode];
  const dispatch = useSearchDispatch();

  const onClickJob = (jcategoryCode, jcategoryName) => {
    dispatch({
      type: 'SET_JCATEGORY',
      jcategoryCode,
      jcategoryName,
    });

    if (mode === searchMode.BIZ) {
      onChangeStage({ cur: 'PLACE' });
      return;
    }

    setShowReport(true);
  };

  const onClickRecommend = () => {
    setShowRecommend(true);
  };

  return (
    <div className="flex flex-col">
      <div className="py-4 text-2xl text-center">{bizName} 내 세부 업종을 선택해주세요.</div>
      <div className="h-48 overflow-y-auto">
        <div className="grid grid-cols-3 gap-1 py-3 justify-items-center">
          {jList.map(({ code, name }, idx) => {
            return (
              <button
                key={idx}
                className="w-[130px] h-[50px] rounded-small border-disabled text-disabled border-2 hover:border-primary hover:text-white hover:bg-primary"
                onClick={() => onClickJob(code, name)}
              >
                {name}
              </button>
            );
          })}
        </div>
      </div>
      {mode === searchMode.PLACE && (
        <RecommendButton
          title={'추천받기'}
          className="w-full p-4 mt-4"
          onClick={onClickRecommend}
        />
      )}
      {showReport && (
        <Report
          jcategoryName={jcategoryName}
          jcategoryCode={jcategoryCode}
          careaName={careaName}
          careaCode={careaCode}
          onClose={() => setShowReport(false)}
        />
      )}
      {showRecommend && (
        <JobRecommendation isOpen={showRecommend} onClose={() => setShowRecommend(false)} />
      )}
    </div>
  );
}

export default JobSelection;
