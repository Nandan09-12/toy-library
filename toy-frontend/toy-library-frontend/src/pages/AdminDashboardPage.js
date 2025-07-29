import React from 'react';
import { useNavigate } from 'react-router-dom';

const AdminDashboardPage = () => {
  const navigate = useNavigate();

  return (
    <div style={{ padding: '20px' }}>
      <h2>Admin Dashboard</h2>
      <p>Welcome! Choose what you’d like to view:</p>

      <div style={{ display: 'flex', flexDirection: 'column', gap: '10px', maxWidth: '300px' }}>
        <button onClick={() => navigate('/admin/members')}>
          View All Members
        </button>
        <button onClick={() => navigate('/admin/toys')}>
          View All Toys
        </button>
        <button onClick={() => navigate('/admin/rentals')}>
          View All Rentals
        </button>
      </div>
    </div>
  );
};

export default AdminDashboardPage;
