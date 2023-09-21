'use client';
import Script from 'next/script';
import { useEffect, useRef } from 'react';

function Map({ commerialDistricts }) {
  const mapRef = useRef(null);

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
    for (const d of commerialDistricts.features) {
      const coordinates = d.geometry.coordinates;

      const polygon = new naver.maps.Polygon({
        map: mapRef.current,
        paths: [coordinates[0].map(([lat, lng]) => new naver.maps.LatLng(lng, lat))],
        fillColor: '#0064FF',
        fillOpacity: 0.3,
        strokeColor: '#0064FF',
        strokeOpacity: 0.6,
        strokeWeight: 3,
      });
    }
  };

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

export default Map;
