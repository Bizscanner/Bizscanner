'use client'

import React from "react";
import Button from "./Button";

function Card({ carea, jcategory, reportDate }) {
  const deleteOnClick = () => {};

  return (
    <article className="w-1/4 h-full flex flex-col m-4 p-6 bg-background rounded-large">
      <div className="flex flex-row-reverse">
        <button className="block ml-auto" onClick={deleteOnClick}>
          <img src="icons/trash.svg" width={40} height={40} />
        </button>
      </div>
      <div className="text-2xl font-bold my-1">
        {carea}
      </div>
      <div className="text-2xl font-bold">
        {jcategory}
      </div>
      <div className="text-xl font-bold text-disabled my-1">
        {reportDate}
      </div>
      <Button className="w-full h-full mt-5 py-3 bg-primary rounded-medium text-white text-xl">상세보기</Button>
    </article>
  )
}

export default Card;