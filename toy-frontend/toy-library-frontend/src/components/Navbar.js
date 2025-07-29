import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthContext';

const Navbar = () => {
  const { user, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  
  if (!user) return null;

  return (
    <nav style={{
      padding: '10px',
      backgroundColor: '#333',
      color: 'white',
      display: 'flex',
      justifyContent: 'space-between',
      alignItems: 'center'
    }}>
      <div>
        <Link to="/toys" style={{ color: 'white', marginRight: '15px' }}>Toy Listings</Link>
        <Link to="/cart" style={{ color: 'white', marginRight: '15px' }}>My Cart</Link>
        <Link to="/my-rentals" style={{ color: 'white', marginRight: '15px' }}>My Rentals</Link>
      </div>
      <button onClick={handleLogout} style={{ backgroundColor: 'red', color: 'white', border: 'none', padding: '5px 10px', cursor: 'pointer' }}>
        Logout
      </button>
    </nav>
  );
};

export default Navbar;
