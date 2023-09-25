import React from 'react';
import jdata from '../../../public/jcategory.json';
import { useSearchState } from './SearchContext';

function JobSelection({ onChangeStage }) {
  const { bizCode, bizName } = useSearchState();
  const jList = jdata[bizCode];

  return (
    <div className="flex flex-col">
      <div className="py-4 text-2xl text-center">{bizName} 내 세부 업종을 선택해주세요.</div>
      <div className="h-48 overflow-y-auto">
        <div className="grid grid-cols-3 gap-1 py-3 justify-items-center">
          {jList.map((job, idx) => {
            return (
              <button
                key={idx}
                className="w-[130px] h-[50px] rounded-small border-disabled text-disabled border-2 hover:border-primary hover:text-white hover:bg-primary"
              >
                {job.name}
              </button>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default JobSelection;
