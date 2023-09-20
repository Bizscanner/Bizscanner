'use client';

import classnames from 'classnames';
import React from 'react';

function Button({ width, height, className, onClick, children }) {
  return (
    <div>
      <button
        className={classnames(`font-bold`, className)}
        style={{ width: `${width}`, height: `${height}` }}
        onClick={onClick}
      >
        {children}
      </button>
    </div>
  );
}

export default Button;
