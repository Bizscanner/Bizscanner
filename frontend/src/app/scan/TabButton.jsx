import classnames from 'classnames';
import React from 'react';

function TabButton({ Icon, title, onClick, isSelected }) {
  return (
    <button
      className={classnames(
        'flex items-center justify-start w-full gap-1 p-3 text-xl font-bold text-white hover:bg-hover rounded-small',
        isSelected ? 'bg-hover' : '',
      )}
      onClick={onClick}
    >
      <Icon /> {title}
    </button>
  );
}

export default TabButton;
