'use client';
import React from 'react';
import SelectButton from './SearchSelectButton';
import LocationIcon from '@/assets/icons/location_on.svg';
import StoreIcon from '@/assets/icons/local_convenience_store.svg';
import ControllerTitle from './ControllerTitle';

function SearchSelection() {
  return (
    <>
      <ControllerTitle title={"무엇으로 검색하시겠어요?"} />
      <div className="flex gap-10 mb-10">
        <SelectButton text="상권" Icon={LocationIcon} />
        <SelectButton text="업종" Icon={StoreIcon} />
      </div>
    </>
  );
}

export default SearchSelection;
