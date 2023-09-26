'use client';
import React, { useEffect, useState } from 'react';
import { useSearchState } from './SearchContext';
import axios from '@/api/index';
import ControllerTitle from './ControllerTitle';

function CDistrictSelection({ onChangeStage, mode }) {
  const [cDistricts, setCDistricts] = useState([]);

  const { dongCode } = useSearchState();

  const fetchData = async () => {
    const {
      data: { dongInfoResponseList },
    } = await axios.get(`/jcategory-recommend/dong/${dongCode}`);
    setCDistricts(dongInfoResponseList);
  };

  const onClickCDistrict = () => {
    if (mode === 'PLACE') {
      onChangeStage({ cur: 'BIZ' });
      return;
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  return (
    <div>
      {cDistricts.length > 0 ? (
        <>
          <ControllerTitle title="상권을 선택해주세요" />
          <div className="h-48 overflow-y-auto">
            <div className="grid grid-cols-3 gap-1 py-3 justify-items-center">
              {cDistricts.map(({ careaCode, careaName }) => {
                return (
                  <button
                    key={careaCode}
                    className="text-ellipsis overflow-hidden whitespace-nowrap w-[130px] h-[50px] rounded-small border-disabled text-disabled border-2 hover:border-primary hover:text-white hover:bg-primary"
                    onClick={() => onClickCDistrict()}
                  >
                    {careaName}
                  </button>
                );
              })}
            </div>
          </div>
        </>
      ) : (
        <div className="mt-8 mb-8 text-2xl font-bold text-center">상권이 존재하지 않아요!</div>
      )}
    </div>
  );
}

export default CDistrictSelection;
