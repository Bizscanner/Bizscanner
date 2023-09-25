import React from 'react';
import data from '../../../public/상권특구.geojson';
import Map from './Map';
import Controller from './Controller';

function page() {
  return (
    <div className="relative flex grow">
      <Map commerialDistricts={data} />
      <Controller />
    </div>
  );
}

export default page;
