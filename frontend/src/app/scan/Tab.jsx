'use client';
import React from 'react';
import ArticleIcon from '@/assets/icons/article.svg';
import StoreIcon from '@/assets/icons/Store.svg';
import DoorOpenIcon from '@/assets/icons/openIcon.svg';
import DoorClosedIcon from '@/assets/icons/CloseIcon.svg';
import PieIcon from '@/assets/icons/PieIcon.svg';
import MoneyIcon from '@/assets/icons/MoneyIcon.svg';
import MoneyCountIcon from '@/assets/icons/MoneyCountIcon.svg';
import PersonIcon from '@/assets/icons/PersonIcon.svg';
import StatisticsIcon from '@/assets/icons/StatisticsIcon.svg';
import TrendIcon from '@/assets/icons/TrendIcon.svg';
import RealEstateIcon from '@/assets/icons/RealEstateIcon.svg';
import Logo from '@/assets/icons/logo.svg';

import TabButton from './TabButton';

const tabMenu = [
  { title: '간략분석', icon: ArticleIcon },
  { title: '점포 수', icon: StoreIcon },
  { title: '개업 현황', icon: DoorOpenIcon },
  { title: '폐업 현황', icon: DoorClosedIcon },
  { title: '매출액', icon: MoneyIcon },
  { title: '매츨건수', icon: MoneyCountIcon },
  { title: '유동인구 수', icon: PersonIcon },
  { title: '소비트렌드', icon: StatisticsIcon },
  { title: '상권변화지표', icon: TrendIcon },
  { title: '임대료', icon: RealEstateIcon },
];

function Tab({ onClickTab, tab }) {
  return (
    <div className="bg-primary h-full w-[300px] p-3">
      <Logo className="mt-5 mb-9 fill-white" />
      {tabMenu.map(({ title, icon }, idx) => (
        <TabButton
          Icon={icon}
          title={title}
          key={title}
          onClick={() => onClickTab(idx)}
          isSelected={tab === idx}
        />
      ))}
    </div>
  );
}

export default Tab;
