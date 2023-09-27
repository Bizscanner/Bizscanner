import React from 'react';
import classnames from 'classnames';

function InputMain({ children, className }) {
  return (
    <div className={classnames('w-full h-full bg-transparent', className)}>
      <div className="flex flex-col px-5 py-1">{children}</div>
    </div>
  );
}

function InputTitle({ children, className }) {
  return <label className={classnames('text-sm text-black', className)}>{children}</label>;
}

function InputSection({ placeholder, type, value, children, wrapperStyle, inputStyle }) {
  return (
    <div className={classnames('flex flex-row mt-1 border-b-2 border-black', wrapperStyle)}>
      <input
        className={classnames(
          'pb-1 mr-1 text-xl font-semibold text-black bg-transparent placeholder-lightgray focus:outline-0 focus:placeholder-transparent',
          inputStyle,
        )}
        placeholder={placeholder}
        type={type}
        value={value}
      />
      {children}
    </div>
  );
}

function InputUnit({ children, className }) {
  return (
    <div className={classnames('pb-1 text-xl font-semibold text-black bg-transparent', className)}>
      {children}
    </div>
  );
}

export const Input = Object.assign(InputMain, {
  Title: InputTitle,
  Section: InputSection,
  Unit: InputUnit,
});
