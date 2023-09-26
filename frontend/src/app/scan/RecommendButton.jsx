import Button from '@/components/Button';
import React from 'react';

function RecommendButton({ title, onClick }) {
  return (
    <Button className="w-full p-4 mt-4 text-white rounded-small bg-primary" onClick={onClick}>
      {title}
    </Button>
  );
}

export default RecommendButton;
