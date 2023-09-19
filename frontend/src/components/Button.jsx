'use client';

import React from 'react';

function Button({ width, height, variant, radius, onClick, children }) {
  const buttonConfig = {
    // Colors
    primary: {
      bgColor: 'bg-primary',
      color: 'text-white',
    },
    disabled: {
      bgColor: 'bg-disabled',
      color: 'text-white',
    },
    //Border-radius
    small: 'rounded-small',
    medium: 'rounded-medium',
    large: 'rounded-large',
  };

  return (
    <div>
      <button
        className={`
          ${buttonConfig[variant].bgColor} 
          ${buttonConfig[variant].color} 
          ${buttonConfig[radius]} 
          font-bold
        `}
        style={{ width: `${width}`, height: `${height}` }}
        onClick={onClick}
      >
        {children}
      </button>
    </div>
  );
}

export default Button;
