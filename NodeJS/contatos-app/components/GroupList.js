import React from 'react';

const GroupList = ({ groups }) => {
  return (
    <div>
      <p>Grupos</p>
      <ul>
        {groups.map((group) => (
          <li key={group._id}>
            <button>
              <span className="iconify" data-icon=""></span>
              <p>{group.name}</p>
              <p>123</p> {/* You may want to replace this with actual data */}
            </button>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default GroupList;
