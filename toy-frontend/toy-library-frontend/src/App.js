import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import RegisterPage from './pages/RegisterPage';
import LoginPage from './pages/LoginPage';
import ToyListPage from './pages/ToyListPage';
import CartPage from './pages/CartPage';
import MyRentalsPage from './pages/MyRentalsPage';
import Navbar from './components/Navbar';
import AdminDashboardPage from './pages/AdminDashboardPage';
import AdminMembersPage from './pages/AdminMembersPage';
import AdminToysPage from './pages/AdminToysPage';
import AdminRentalsPage from './pages/AdminRentalsPage'; 

function App() {
  return (
    <Router>
      <Navbar /> 
      <Routes>
        {/* Member Routes */}
        <Route path="/login" element={<LoginPage />} />
        <Route path="/register" element={<RegisterPage />} />
        <Route path="/toys" element={<ToyListPage />} />
        <Route path="/cart" element={<CartPage />} />
        <Route path="/my-rentals" element={<MyRentalsPage />} />

        {/* Admin Routes */}
        <Route path="/admin" element={<AdminDashboardPage />} />
        <Route path="/admin/members" element={<AdminMembersPage />} />
        <Route path="/admin/toys" element={<AdminToysPage />} />
        <Route path="/admin/rentals" element={<AdminRentalsPage />} /> 

        {/* Fallback */}
        <Route path="*" element={<Navigate to="/login" />} /> 
      </Routes>
    </Router>
  );
}

export default App;
