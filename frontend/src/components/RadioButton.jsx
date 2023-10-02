import classnames from 'classnames';
import React from 'react';

function RadioButton({ text, onClick, isClicked }) {
  return (
    <button
      className={classnames(
        'flex items-center justify-center gap-1 p-2 font-bold border-2 rounded-full border-primary',
        isClicked ? 'bg-primary text-white' : '  text-primary',
      )}
      onClick={onClick}
    >
      <div
        className={classnames(
          'w-5 h-5 border-2 rounded-full flex justify-center items-center',
          isClicked ? 'border-white' : ' border-primary',
        )}
      >
        {isClicked && <div className="w-3 h-3 bg-white rounded-full"></div>}
      </div>
      {text}
    </button>
  );
}

export default RadioButton;
