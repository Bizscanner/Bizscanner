'use client';
import React, { useEffect, useState } from 'react';
import { useSearchState } from './SearchContext';
import axios from '@/api/index';
import ControllerTitle from './ControllerTitle';

function CDistrictSelection() {
  const [cDistricts, setCDistricts] = useState([]);

  const { dongCode } = useSearchState();

  const fetchData = async () => {
    const {
      data: { dongInfoResponseList },
    } = await axios.get(`/jcategory-recommend/dong/${dongCode}`);
    setCDistricts(dongInfoResponseList);
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      <ControllerTitle title="상권을 선택해주세요" />
      <div className="h-48 overflow-y-auto">
        <div className="grid grid-cols-3 gap-1 py-3 justify-items-center">
          {cDistricts.map(({ careaCode, careaName }) => {
            return (
              <button
                key={careaCode}
                className="text-ellipsis overflow-hidden whitespace-nowrap w-[130px] h-[50px] rounded-small border-disabled text-disabled border-2 hover:border-primary hover:text-white hover:bg-primary"
              >
                {careaName}
              </button>
            );
          })}
        </div>
      </div>
    </div>
  );
}

export default CDistrictSelection;
