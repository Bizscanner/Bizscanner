import React from 'react';
import Selection from './SearchSelection';
import PlaceSelection from './PlaceSelection';
import { SearchProvider } from './SearchContext';

function Controller() {
  return (
    <SearchProvider>
      <div className="absolute z-10 p-5 bg-white left-4 rounded-medium top-1/2 translate-y-[-50%]">
        {/* <Selection /> */}
        <PlaceSelection />
      </div>
    </SearchProvider>
  );
}

export default Controller;
