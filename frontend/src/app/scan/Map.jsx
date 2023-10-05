'use client';
import Script from 'next/script';
import { useSearchState } from './SearchContext';
import React, { useEffect, useRef } from 'react';

function Map() {
  const mapRef = useRef(null);
  const polyRef = useRef([]);
  const { mapCenter, mapCoordinates, mapZoom } = useSearchState();

  const initializeMap = () => {
    const mapOptions = {
      center: new window.naver.maps.LatLng(37.5262411, 126.99289439),
      zoom: 13,
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
    if (polyRef.current) {
      for (const p of polyRef.current) {
        p.setMap(null);
      }
    }

    if (!mapCoordinates) {
      return;
    }

    polyRef.current = [];

    for (const coordinate of mapCoordinates) {
      const polygon = new naver.maps.Polygon({
        map: mapRef.current,
        paths: coordinate,
        fillColor: '#0064FF',
        fillOpacity: 0.3,
        strokeColor: '#0064FF',
        strokeOpacity: 0.6,
        strokeWeight: 3,
      });

      polyRef.current.push(polygon);
    }
    mapRef.current.setZoom(mapZoom, true);

    if (mapCenter) {
      mapRef.current.panTo(mapCenter);
    }
  };

  useEffect(() => {
    draw();
  }, [mapCenter, mapCoordinates, mapZoom]);

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
