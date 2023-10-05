import React from 'react';
import classnames from 'classnames';

function RecommendationIndicatorMain({ children }) {
  return (
    <div className='flex flex-col items-center my-7'>{children}</div>
  );
}

function RecommendationIndicatorTitle({ children }) {
  return (
    <h1 className='text-lg font-semibold'>{children}</h1>
  );
}

function RecommendationIndicatorContainer({ children, className }) {
  return (
    <div className={classnames('flex flex-col items-center justify-evenly rounded-xl w-[200px] h-[100px] m-3 font-bold', className)}>
      {children}
    </div>
  )
}

export const RecommendationIndicator = Object.assign(RecommendationIndicatorMain, {
  Title: RecommendationIndicatorTitle,
  Container: RecommendationIndicatorContainer,
});