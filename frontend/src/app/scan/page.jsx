import React from 'react';
import data from '../../../public/상권특구.geojson';
import Map from './Map';
import Controller from './Controller';
import { SearchProvider } from './SearchContext';

function page() {
  return (
    <>
      <SearchProvider>
        <div className="relative flex grow">
          <Map commerialDistricts={data} />
          <Controller />
        </div>
      </SearchProvider>
    </>
  );
}

export default page;
