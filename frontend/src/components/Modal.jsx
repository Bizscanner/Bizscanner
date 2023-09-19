import ReactDOM from 'react-dom';

function ModalMain({ children, isOpen }) {
  if (!isOpen) {
    return null;
  }

  return ReactDOM.createPortal(<div>{children}</div>, document.body);
}

function ModalDimmed({ children, onClick }) {
  return <div className="fixed left-0 top-0 w-full h-[100vh] bg-dim" onClick={onClick}>{children}</div>;
}

function ModalContainer({ children, bgColor, width, height }) {
  const backGround = {
    white: 'bg-white',
    background: 'bg-background',
  };

  return (
    <div
      className={`${backGround[bgColor]} p-10 fixed top-1/2 left-1/2 w-1/2 translate-x-[-50%] translate-y-[-50%] rounded-large`}
      style={{
        width,
        height,
      }}
    >
      {children}
    </div>
  );
}

function ModalClose({ onClick }) {
  return (
    <div className="flex flex-row-reverse">
      <button className="ml-auto block" onClick={onClick}>
        <img src="icons/close.svg" width={40} height={40} />
      </button>
    </div>
  );
}

function ModalTitle({ children }) {
  return <div className="font-bold text-center text-3xl">{children}</div>;
}

function ModalButtonContainer({ children }) {
  return <div className="flex flex-row-reverse justify-between mt-9">{children}</div>;
}

export const Modal = Object.assign(ModalMain, {
  Title: ModalTitle,
  ButtonList: ModalButtonContainer,
  Dimmed: ModalDimmed,
  Container: ModalContainer,
  Close: ModalClose,
});
