import React, { useEffect, useState } from 'react';
import { getAllUsers } from '../api/userApi';

const AdminMembersPage = () => {
  const [users, setUsers] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const data = await getAllUsers();
        setUsers(data);
      } catch (err) {
        setError('Failed to fetch users');
      }
    };

    fetchUsers();
  }, []);

  return (
    <div style={{ padding: '20px' }}>
      <h2>All Registered Users</h2>

      {error && <p style={{ color: 'red' }}>{error}</p>}

      <table border="1" cellPadding="8" cellSpacing="0">
        <thead>
          <tr>
            <th>Name</th>
            <th>Email</th>
            <th>Role</th>
            <th>Member Type</th>
            <th>Monthly Points</th>
            <th>Used Points</th>
          </tr>
        </thead>
        <tbody>
          {users.map(user => (
            <tr key={user.id}>
              <td>{user.name}</td>
              <td>{user.email}</td>
              <td>{user.role}</td>
              <td>{user.memberType || '-'}</td>
              <td>{user.monthlyPoints}</td>
              <td>{user.usedPoints}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminMembersPage;
