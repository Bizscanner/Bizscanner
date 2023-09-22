import React from 'react';

function BizSelection() {
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
  return (
    <div className="flex flex-col">
      <div className="text-2xl text-center py-4">희망하는 업종을 선택해주세요.</div>
      <div className="flex flex-row justify-center py-3">
        {Jtype.map(( {name, code}, idx ) => {
          return (
            <button
              key={idx}
              className="w-[100px] h-20 m-1  border-disabled text-disabled border-2 rounded-sm hover:border-primary hover:text-white hover:bg-primary"
            >
              {name}
            </button>
          );
        })}
      </div>
    </div>
  );
}

export default BizSelection;
