'use client';

import React, { useState, useEffect } from 'react';
import axios from '@/api/index';
import { Card } from '@/components/Card';
import DeleteCheck from './DeleteCheck';
import jcodedata from '../../../public/jcategorycode.json';

function MyReport() {
  const [showDeleteCheck, setShowDeleteCheck] = useState(false);
  const [scrapList, setScriptList] = useState([]);
  const [reportData, setReportData] = useState({
    careaCode: '',
    jcategoryCode: '',
  });

  const fetchData = async () => {
    const {
      data: { scrapResponses },
    } = await axios.get(`/scrap`);
    setScriptList(scrapResponses);
  };

  const onClickDelete = (careaCode, jcategoryCode) => {
    setReportData({
      careaCode,
      jcategoryCode,
    });
    setShowDeleteCheck(true);
  };

  const onClickDetail = () => {};

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <div className="flex flex-col w-[80vw] translate-x-[10vw]">
        <h1 className="text-4xl font-bold pl-7 pt-7">마이 리포트</h1>
        <div className="grid grid-cols-3 justify-items-center">
          {scrapList.map(({ careaCode, careaName, jcategoryCode, createdAt }, idx) => {
            const createdDate = new Date(createdAt);
            return (
              <Card key={idx}>
                <Card.UpperButton
                  size={40}
                  onClick={() => onClickDelete(careaCode, jcategoryCode)}
                />
                <Card.Content
                  carea={careaName}
                  jcategory={jcodedata[jcategoryCode].name}
                  reportDate={createdDate.toLocaleDateString()}
                />
                <Card.LowerButton onClick={onClickDetail}>상세보기</Card.LowerButton>
              </Card>
            );
          })}
          {showDeleteCheck && (
            <DeleteCheck
              isOpen={showDeleteCheck}
              onClose={() => setShowDeleteCheck(false)}
              reportData={reportData}
              fetchData={fetchData}
            />
          )}
        </div>
      </div>
    </div>
  );
}

export default MyReport;
