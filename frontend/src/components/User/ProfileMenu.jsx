import React from 'react';

function ProfileMenu() {
  return (
    <div className="absolute py-4 top-16 right-7 bg-background w-[370px] rounded-small shadow-lg z-50">
      <div className="mb-4 text-center">ssafy@ssafy.com</div>
      <div className="flex items-center justify-center mb-4">
        <div
          className="flex items-center justify-center w-16 h-16 text-xl font-bold text-white rounded-full"
          style={{
            background: '#8D6E63',
          }}
        >
          <span
            className="text-3xl"
            style={{
              position: 'relative',
              bottom: '0.2em',
            }}
          >
            gs
          </span>
        </div>
      </div>
      <div className="mb-4">
        <div className="text-lg text-center">안녕하세요. GS님</div>
      </div>
      <div>
        <button className="w-full p-2 hover:bg-outline">닉네임 변경</button>
        <button className="w-full p-2 hover:bg-outline">마이 레포트</button>
        <button className="w-full p-2 hover:bg-outline">로그아웃</button>
      </div>
    </div>
  );
}

export default ProfileMenu;
