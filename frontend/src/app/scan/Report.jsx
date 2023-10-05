'use client';
import React, { useEffect, useRef, useState } from 'react';
import Tab from './Tab';
import LocationIcon from '@/assets/icons/location_on.svg';
import ReportSummary from './ReportSummary';
import ReportStoreCount from './ReportStoreCount';
import ReportOpenStore from './ReportOpenStore';
import ReportCloseStore from './ReportCloseStore';
import ReportSales from './ReportSales';
import ReportSalesCount from './ReportSalesCount';
import ReportFloatingPopulation from './RepoartFloatingPopulation';
import { createPortal } from 'react-dom';
import { useSearchState } from './SearchContext';
import CloseIcon from '@/assets/icons/close.svg';
import SaveIcon from '@/assets/icons/save.svg';
import SavedIcon from '@/assets/icons/saved.svg';
import ReportConsumptionTrend from './ReportConsumptionTrend';
import ReportCareaChange from './ReportCareaChange';
import ReportRent from './ReportRent';
import axios from '@/api/index';
import { useSelector } from 'react-redux';
import ReportComment from './ReportComment';

function Report({ onClose }) {
  const isLogin = useSelector((state) => state.user.isLogin);

  const [tabIdx, setTabIdx] = useState(0);
  const [isScrapped, setIsScrapped] = useState(false);

  const { jcategoryName, jcategoryCode, careaName, careaCode } = useSearchState();

  const mainRef = useRef();
  const summaryRef = useRef();
  const storecountRef = useRef();
  const openStoreRef = useRef();
  const closeStoreRef = useRef();
  const salesRef = useRef();
  const salesCountRef = useRef();
  const floatPopulationRef = useRef();
  const comsumptionRef = useRef();
  const careaChangeRef = useRef();
  const rentRef = useRef();

  const sectionList = [
    summaryRef,
    storecountRef,
    openStoreRef,
    closeStoreRef,
    salesRef,
    salesCountRef,
    floatPopulationRef,
    comsumptionRef,
    careaChangeRef,
    rentRef,
  ];

  useEffect(() => {
    mainRef.current.addEventListener('scroll', () => {
      const top = mainRef.current.scrollTop + window.innerHeight / 2;
      for (let i = 0; i < sectionList.length; i++) {
        const $curEl = sectionList[i].current;
        if ($curEl.offsetTop <= top && top <= $curEl.offsetTop + $curEl.clientHeight) {
          setTabIdx(i);
          break;
        }
      }

      return () => {};
    });
    if (isLogin) {
      axios.get(`/scrap/valid/${careaCode}/${jcategoryCode}`).then((response) => {
        setIsScrapped(response.data.scrapped);
      });
    }
  }, []);

  const onClickTab = (idx) => {
    setTabIdx(idx);
    mainRef.current?.scrollTo({ top: sectionList[idx].current.offsetTop, behavior: 'smooth' });
  };

  const onClickSave = async () => {
    if (isLogin) {
      if (!isScrapped) {
        await axios.post('/scrap', {
          careaCode,
          jcategoryCode,
        });
        setIsScrapped((prev) => !prev)
      } else {
        alert('이미 저장된 리포트입니다.');
      }
    } else {
      alert('로그인을 해주세요.')
    }
  };

  return createPortal(
    <div className="absolute top-0 left-0 z-50 flex w-[100vw] h-[100vh] bg-background">
      <Tab onClickTab={onClickTab} tab={tabIdx} />
      <div className="flex flex-col flex-grow p-8">
        <div className="flex items-center justify-between mb-8 text-4xl font-bold">
          <div className="flex items-center">
            <LocationIcon className="fill-primary" width="48" height="48"></LocationIcon>
            {`${jcategoryName} | ${careaName}`}
          </div>
          <div>
            {isScrapped ? (
              <button onClick={onClickSave}>
                <SavedIcon width={48} height={48} />
              </button>
            ) : (
              <button onClick={onClickSave}>
                <SaveIcon width={48} height={48} />
              </button>
            )}
            <button onClick={() => onClose()}>
              <CloseIcon width={48} height={48} />
            </button>
          </div>
        </div>
        <div className="flex flex-grow gap-8 overflow-y-auto">
          <main
            ref={mainRef}
            className="relative flex flex-col flex-grow max-h-full gap-8 overflow-y-auto"
          >
            <ReportSummary ref={summaryRef} />
            <ReportStoreCount ref={storecountRef} />
            <ReportOpenStore ref={openStoreRef} />
            <ReportCloseStore ref={closeStoreRef} />
            <ReportSales ref={salesRef} />
            <ReportSalesCount ref={salesCountRef} />
            <ReportFloatingPopulation ref={floatPopulationRef} />
            <ReportConsumptionTrend ref={comsumptionRef} />
            <ReportCareaChange ref={careaChangeRef} />
            <ReportRent ref={rentRef} />
          </main>
          <aside className="h-full w-[350px]">
            <ReportComment />
          </aside>
        </div>
      </div>
    </div>,
    document.body,
  );
}

export default Report;
