import useHover from '@/hooks/useHover';
import classnames from 'classnames';
import React from 'react';

function SearchSelectButton({ text, Icon, onClick }) {
  const [ref, hover] = useHover();

  return (
    <button
      className={classnames(
        'w-[150px] h-[150px] border-4 flex flex-col justify-center items-center text-2xl border-disabled text-disabled hover:border-primary hover:text-primary',
      )}
      ref={ref}
      onClick={onClick}
    >
      <Icon className={classnames('mb-2 fill-disabled', hover ? 'fill-primary' : '')} />
      {text}
    </button>
  );
}

export default SearchSelectButton;
