'use clinet';
import classnames from 'classnames';
import { createContext, useState, useContext, useRef, useEffect } from 'react';
import ArrowDown from '@/assets/icons/arrowDown.svg';

const DropdownContext = createContext(null);

function DropdownProvider({ children, className }) {
  const [isOpen, setIsOpen] = useState(false);

  const onChange = () => {
    setIsOpen((prev) => !prev);
  };

  return (
    <DropdownContext.Provider
      value={{
        isOpen,
        onChange,
      }}
    >
      {children}
    </DropdownContext.Provider>
  );
}

function DropdownContainer({ children, className }) {
  const dropdownContext = useContext(DropdownContext);
  const ref = useRef(null);

  const { isOpen, onChange } = dropdownContext;

  const onClick = () => {
    onChange();
  };

  useEffect(() => {
    // 임시로 비활성화
    // const handleClick = (e) => {
    //   if (ref.current && !ref.current.contains(e.target)) {
    //     onChange();
    //   }
    // };

    // console.log(isOpen);
    // if (isOpen) {
    //   window.addEventListener('mousedown', handleClick);
    // } else {
    //   window.removeEventListener('mousedown', handleClick);
    // }

    return;
  }, [ref, isOpen]);

  return (
    <div
      className={classnames('relative', className, isOpen ? 'z-30' : '')}
      ref={ref}
      onClick={onClick}
    >
      {children}
    </div>
  );
}

function DropdownLabel({ children }) {
  return <div className="text-disabled">{children}</div>;
}

function DropdownTrigger({ children }) {
  const dropdownContext = useContext(DropdownContext);

  const { isOpen } = dropdownContext;

  return (
    <button
      className={classnames(
        'flex justify-between w-full text-left border-b-2 ',
        isOpen ? 'border-primary' : 'border-underline',
      )}
    >
      <span className="text-2xl">{children}</span>
      <ArrowDown
        className={classnames(
          'duration-100 ease-linear',
          isOpen ? 'fill-primary rotate-180' : 'fill-black',
        )}
      />
    </button>
  );
}

function DropdownOptionContainer({ children }) {
  const dropdownContext = useContext(DropdownContext);

  const { isOpen } = dropdownContext;

  return (
    isOpen && (
      <div className="absolute z-30 w-full mt-2 overflow-hidden overflow-y-scroll bg-white h-44 rounded-small text-disabled shadow-dropdown">
        {children}
      </div>
    )
  );
}

function DropdownOption({ children, onSelect, ...props }) {
  const onClick = () => {
    onSelect?.(props);
  };

  return (
    <div className="py-2 pl-2 hover:bg-outline hover:text-black" onClick={onClick}>
      {children}
    </div>
  );
}

export const Dropdown = Object.assign(DropdownProvider, {
  Trigger: DropdownTrigger,
  OptionContainer: DropdownOptionContainer,
  Option: DropdownOption,
  Label: DropdownLabel,
  Container: DropdownContainer,
});
