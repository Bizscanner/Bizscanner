import classnames from 'classnames';
import ReactDOM from 'react-dom';

function ModalMain({ children, isOpen }) {
  console.log(isOpen);
  if (!isOpen) {
    return null;
  }

  return ReactDOM.createPortal(<div>{children}</div>, document.body);
}

function ModalDimmed({ children, onClick }) {
  return (
    <div className="fixed left-0 top-0 w-full h-[100vh] bg-dim z-50" onClick={onClick}>
      {children}
    </div>
  );
}

function ModalContainer({ children, className, width, height }) {
  return (
    <div
      className={classnames(
        `p-10 fixed top-1/2 left-1/2 w-1/2 translate-x-[-50%] translate-y-[-50%] rounded-large z-50`,
        className,
      )}
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
      <button className="block ml-auto" onClick={onClick}>
        <img src="icons/close.svg" width={40} height={40} />
      </button>
    </div>
  );
}

function ModalTitle({ children }) {
  return <div className="text-3xl font-bold text-center">{children}</div>;
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
