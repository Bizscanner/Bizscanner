import Button from '@/components/Button';
import React from 'react';
import classnames from 'classnames';

function NextButton({ onClick, className }) {
  return (
    <Button
      className={classnames("text-white bg-primary rounded-small", className)}
      onClick={onClick}
    >
      다음
    </Button>
  );
}

export default NextButton;
