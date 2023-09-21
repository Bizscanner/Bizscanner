import React from 'react';
import data from '../../../public/상권특구.geojson';
import Map from './Map';

function page() {
  return (
    <div className="flex grow">
      <Map commerialDistricts={data} />
    </div>
  );
}

export default page;
