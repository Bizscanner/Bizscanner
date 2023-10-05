import Button from '@/components/Button';
import React from 'react';
import classnames from 'classnames';

function RecommendButton({ title, onClick, className }) {
  return (
    <Button className={classnames("text-white rounded-small bg-primary", className)} onClick={onClick}>
      {title}
    </Button>
  );
}

export default RecommendButton;
