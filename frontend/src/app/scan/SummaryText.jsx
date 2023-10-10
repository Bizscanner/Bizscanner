import React from 'react';

function SummaryText({ children }) {
  return (
    <div className="flex items-center justify-center">
      <div className="py-4 mb-8 text-center px-7 bg-background max-w-[800px]">{children}</div>
    </div>
  );
}

export default SummaryText;
