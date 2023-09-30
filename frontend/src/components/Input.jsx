import React from 'react';
import classnames from 'classnames';

function InputMain({ children, className }) {
  return (
    <div className={classnames('bg-transparent', className)}>
      <div className="flex flex-col px-5 py-1">{children}</div>
    </div>
  );
}

function InputTitle({ children, className }) {
  return <div className={classnames('text-sm text-black', className)}>{children}</div>;
}

function InputWrapper({ children, className }) {
  return (
    <div className={classnames('flex flex-row mt-1 border-b-2 border-black', className)}>
      {children}
    </div>
  );
}

function InputSection({ name, placeholder, type, value, onChange, className }) {
  return (
    <input
      className={classnames(
        'pb-1 mr-1 text-xl font-semibold text-black bg-transparent placeholder-lightgray focus:outline-0 focus:placeholder-transparent',
        className,
      )}
      name={name}
      placeholder={placeholder}
      type={type}
      value={value}
      onChange={onChange}
    ></input>
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
  Wraaper: InputWrapper,
  Section: InputSection,
  Unit: InputUnit,
});
