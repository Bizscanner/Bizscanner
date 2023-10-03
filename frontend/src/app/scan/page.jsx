import React from 'react';
import Map from './Map';
import Controller from './Controller';
import { SearchProvider } from './SearchContext';

function page() {
  return (
    <SearchProvider>
      <div className="relative flex grow">
        <Map />
        <Controller />
      </div>
    </SearchProvider>
  );
}

export default page;
