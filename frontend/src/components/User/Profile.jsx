'use client';
import React, { useState } from 'react';
import ProfileMenu from './ProfileMenu';
import { convertNickName } from '@/utils/nickname';
import { useSelector } from 'react-redux';

function Profile() {
  const [showMenu, setMenu] = useState(false);
  const { nickname } = useSelector((state) => state.user);

  const onClick = () => {
    setMenu((prev) => !prev);
  };

  return (
    <div>
      <button
        className="w-10 h-10 text-xl font-bold text-white rounded-full"
        style={{
          background: '#8D6E63',
        }}
        onClick={onClick}
      >
        <span
          style={{
            position: 'relative',
            bottom: '0.2em',
          }}
        >
          {convertNickName(nickname)}
        </span>
      </button>
      {showMenu && <ProfileMenu />}
    </div>
  );
}

export default Profile;
