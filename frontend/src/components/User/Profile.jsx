'use client';
import React, { useState } from 'react';
import ProfileMenu from './ProfileMenu';

function Profile() {
  const [showMenu, setMenu] = useState(false);

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
          gs
        </span>
      </button>
      {showMenu && <ProfileMenu />}
    </div>
  );
}

export default Profile;
