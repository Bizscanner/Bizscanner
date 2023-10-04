'use client';

import React, { useState } from 'react';
import { Card } from '@/components/Card';
import DeleteCheck from './DeleteCheck';

function MyReport() {
  const [showDeleteCheck, setShowDeleteCheck] = useState(false);

  const ScrapList = [
    {
      careaCode: '1',
      careaName: '망원시장',
      jcategoryCode: '제과점',
      reportDate: '2023-09-16',
    },
    {
      careaCode: '1',
      careaName: '망원시장',
      jcategoryCode: '제과점',
      reportDate: '2023-09-16',
    },
    {
      careaCode: '1',
      careaName: '망원시장',
      jcategoryCode: '제과점',
      reportDate: '2023-09-16',
    },
  ];

  const onClickDelete = () => {
    setShowDeleteCheck(true);
  };

  const onClickDetail = () => {};

  return (
    <div>
      <div className="flex flex-col w-[80vw] translate-x-[10vw]">
        <h1 className="text-4xl font-bold pl-7 pt-7">마이 리포트</h1>
        <div className="grid grid-cols-3 justify-items-center">
          {ScrapList.map(({ careaName, jcategoryCode, reportDate }, idx) => {
            return (
              <Card key={idx}>
                <Card.UpperButton size={40} onClick={onClickDelete} />
                <Card.Content carea={careaName} jcategory={jcategoryCode} reportDate={reportDate} />
                <Card.LowerButton onClick={onClickDetail}>상세보기</Card.LowerButton>
              </Card>
            );
          })}
          {showDeleteCheck && (
            <DeleteCheck isOpen={showDeleteCheck} onClose={() => setShowDeleteCheck(false)} />
          )}
        </div>
      </div>
    </div>
  );
}

export default MyReport;
