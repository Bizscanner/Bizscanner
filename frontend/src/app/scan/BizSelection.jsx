import React, { useState } from 'react';
import { useSearchDispatch } from './SearchContext';
import RecommendButton from './RecommendButton';
import { searchMode } from './constant';
import JobRecommendation from './JobRecommendation';

function BizSelection({ onChangeStage, mode }) {
  const [showRecommend, setShowRecommend] = useState(false);

  const Jtype = [
    {
      name: '외식업',
      code: 'restaurant',
    },
    {
      name: '서비스업',
      code: 'service',
    },
    {
      name: '소매업',
      code: 'retail',
    },
  ];

  const dispatch = useSearchDispatch();

  const onClickBiz = ({ name, code }) => {
    dispatch({ type: 'SET_BIZ', bizCode: code, bizName: name });
    onChangeStage({ cur: 'JOB' });
  };

  const onClickRecommend = () => {
    setShowRecommend(true);
  };

  return (
    <div className="flex flex-col">
      <div className="py-4 text-2xl text-center">희망하는 업종을 선택해주세요.</div>
      <div className="flex flex-row justify-center py-3">
        {Jtype.map(({ name, code }, idx) => {
          return (
            <button
              key={idx}
              className="w-[100px] h-20 m-1  border-disabled text-disabled border-2 rounded-sm hover:border-primary hover:text-white hover:bg-primary"
              onClick={() => onClickBiz({ name, code })}
            >
              {name}
            </button>
          );
        })}
      </div>
      {mode === searchMode.PLACE && (
        <RecommendButton
          title={'추천받기'}
          className="w-full p-4 mt-4"
          onClick={onClickRecommend}
        />
      )}
      <JobRecommendation isOpen={showRecommend} onClose={() => setShowRecommend(false)} />
    </div>
  );
}

export default BizSelection;
