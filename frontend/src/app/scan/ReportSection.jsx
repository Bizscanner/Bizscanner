import classnames from 'classnames';
import React, { forwardRef } from 'react';

const ReportSection = forwardRef(function ReportSection({ children, title, className }, ref) {
  return (
    <div className={classnames('p-8 bg-white rounded-small', className)} ref={ref}>
      {title && <div className="mb-8 text-2xl font-bold">{title}</div>}
      {children}
    </div>
  );
});

export default ReportSection;
