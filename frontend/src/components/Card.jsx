'use client';

import React from 'react';
import Button from './Button';

function CardMain({ children }) {
  return (
    <div className="flex flex-col w-5/6 p-6 m-4 h-[250px] bg-background rounded-large">
      {children}
    </div>
  );
}

function CardUpperButton({ size, onClick }) {
  return (
    <div className="flex flex-row-reverse">
      <button className="block ml-auto" onClick={onClick}>
        <img src="icons/trash.svg" width={size} height={size} />
      </button>
    </div>
  );
}

function CardContent({ carea, jcategory, reportDate }) {
  return (
    <>
      <div className="my-1 overflow-hidden text-2xl font-bold whitespace-nowrap overflow-ellipsis">
        {carea}
      </div>
      <div className="text-2xl font-bold ">{jcategory}</div>
      <div className="my-1 text-xl font-bold text-disabled">{reportDate}</div>
    </>
  );
}

function CardLowerButton({ children, onClick }) {
  return (
    <Button
      className="w-full py-3 mt-1 text-xl text-white align-middle h-1/4 bg-primary rounded-medium"
      onClick={onClick}
    >
      {children}
    </Button>
  );
}

export const Card = Object.assign(CardMain, {
  UpperButton: CardUpperButton,
  Content: CardContent,
  LowerButton: CardLowerButton,
});
