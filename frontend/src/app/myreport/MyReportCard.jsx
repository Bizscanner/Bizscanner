import { Card } from '@/components/Card';
import React, { useState } from 'react';
import DeleteCheck from './DeleteCheck';
import jcodedata from '../../../public/jcategorycode.json';
import Report from '../scan/Report';

function MyReportCard({ careaCode, careaName, jcategoryCode, createdAt, fetchScrapList }) {
  const [showDeleteCheck, setShowDeleteCheck] = useState(false);
  const [showReport, setShowReport] = useState(false);

  const createdDate = new Date(createdAt);
  const onClickDelete = () => {
    setShowDeleteCheck(true);
  };

  const onClickDetail = () => {
    setShowReport(true);
  };

  const onCloseReport = () => {
    setShowReport(false);
  };

  return (
    <>
      <Card>
        <Card.UpperButton size={40} onClick={() => onClickDelete()} />
        <Card.Content
          carea={careaName}
          jcategory={jcodedata[jcategoryCode].name}
          reportDate={createdDate.toLocaleDateString()}
        />
        <Card.LowerButton onClick={onClickDetail}>상세보기</Card.LowerButton>
      </Card>
      {showDeleteCheck && (
        <DeleteCheck
          isOpen={showDeleteCheck}
          onClose={() => setShowDeleteCheck(false)}
          careaCode={careaCode}
          jcategoryCode={jcategoryCode}
          fetchData={fetchScrapList}
        />
      )}
      {showReport && (
        <Report
          onClose={onCloseReport}
          careaCode={careaCode}
          careaName={careaName}
          jcategoryCode={jcategoryCode}
          jcategoryName={jcodedata[jcategoryCode].name}
        />
      )}
    </>
  );
}

export default MyReportCard;
