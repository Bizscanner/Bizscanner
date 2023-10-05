'use client';

import React, { useState, useEffect } from 'react';
import axios from '@/api/index';
import MyReportCard from './MyReportCard';

function MyReport() {
  const [scrapList, setScriptList] = useState([]);

  const fetchScrapList = async () => {
    const {
      data: { scrapResponses },
    } = await axios.get(`/scrap`);
    setScriptList(scrapResponses);
  };

  useEffect(() => {
    fetchScrapList();
  }, []);

  return (
    <div>
      <div className="flex flex-col w-[80vw] translate-x-[10vw]">
        <h1 className="text-4xl font-bold pl-7 pt-7">마이 리포트</h1>
        <div className="grid grid-cols-3 justify-items-center">
          {scrapList.map(({ careaCode, careaName, jcategoryCode, createdAt }, idx) => {
            return (
              <MyReportCard
                key={idx}
                careaCode={careaCode}
                careaName={careaName}
                jcategoryCode={jcategoryCode}
                createdAt={createdAt}
                fetchScrapList={fetchScrapList}
              />
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default MyReport;
