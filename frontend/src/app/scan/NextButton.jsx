import Button from '@/components/Button';
import React from 'react';

function NextButton({ onClick }) {
  return (
    <Button
      className="inline-block float-right w-3/4 p-3 ml-auto text-white bg-primary rounded-small"
      onClick={onClick}
    >
      다음
    </Button>
  );
}

export default NextButton;
