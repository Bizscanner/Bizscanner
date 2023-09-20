import React from 'react';

function Input({ width, height, label, type, placeholder, value, onChange }) {
  return (
    <div className="w-full h-full bg-white rounded-medium" style={{ width, height }}>
      <div className="relative flex justify-between	px-6">
        <div className="w-full h-full flex flex-col pt-8 pb-2.5">
          <label className="absolute top-1 left-6 text-disabled text-small font-semibold">
            {label}
          </label>
          <input
            className="w-full bg-transparent text-black text-xl font-bold placeholder-lightgray focus:outline-0"
            type={type}
            placeholder={placeholder}
            onChange={onChange}
            value={value}
          />
        </div>
      </div>
    </div>
  );
}

export default Input;
