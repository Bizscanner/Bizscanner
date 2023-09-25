import React from 'react';
import jdata from '../../../public/jcategory.json';

function JobSelection({ jcategory }) {
  const jList = jdata[jcategory.code];

  return (
    <div className="flex flex-col">
      <div className="text-2xl text-center py-4">{jcategory.name} 내 세부 업종을 선택해주세요.</div>
      <div className="h-48 overflow-y-auto">
        <div className="grid grid-cols-3 gap-1 justify-items-center py-3">
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
