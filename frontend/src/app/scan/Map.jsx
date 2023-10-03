'use client';
import Script from 'next/script';
import polyData from '../../../public/영역 통합.json';
import centerData from '../../../public/영역 통합 중심 좌표.json';
import { useSearchState } from './SearchContext';
import React, { useEffect, useRef } from 'react';

function Map() {
  const mapRef = useRef(null);
  const polyRef = useRef(null);
  const { mapSelected } = useSearchState();

  const initializeMap = () => {
    const mapOptions = {
      center: new window.naver.maps.LatLng(37.5262411, 126.99289439),
      scaleControl: false,
      mapDataControl: false,
      logoControlOptions: {
        position: naver.maps.Position.BOTTOM_RIGHT,
      },
    };

    const map = new window.naver.maps.Map('map', mapOptions);
    mapRef.current = map;
  };

  const draw = () => {
    if (!mapSelected) {
      return;
    }

    if (polyRef.current) {
      polyRef.current.setMap(null);
      polyRef.current = null;
    }

    const coordinates = polyData[mapSelected];

    const polygon = new naver.maps.Polygon({
      map: mapRef.current,
      paths: coordinates,
      fillColor: '#0064FF',
      fillOpacity: 0.3,
      strokeColor: '#0064FF',
      strokeOpacity: 0.6,
      strokeWeight: 3,
    });

    if (centerData[mapSelected]) {
      mapRef.current.panTo(centerData[mapSelected]);
    }

    polyRef.current = polygon;
  };

  useEffect(() => {
    draw();
  }, [mapSelected]);

  useEffect(() => {
    return () => {
      mapRef.current?.destroy();
    };
  }, []);

  return (
    <>
      <Script
        strategy="afterInteractive"
        type="text/javascript"
        src={`https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${process.env.NEXT_PUBLIC_NAVER_MAP_ID}`}
        onReady={initializeMap}
      />
      <Script
        strategy="afterInteractive"
        type="text/javascript"
        src={`https://oapi.map.naver.com/openapi/v3/maps.js?ncpClientId=${process.env.NEXT_PUBLIC_NAVER_MAP_ID}&submodules=drawing`}
        onReady={draw}
      />
      <div id={'map'} className="grow" />
    </>
  );
}

export default React.memo(Map);
