import React, { useState, useContext } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import { getUserByEmail } from '../api/userApi';
import { AuthContext } from '../context/AuthContext';

const LoginPage = () => {
  const navigate = useNavigate();
  const { setUser } = useContext(AuthContext);

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState(''); // Ignored for now
  const [error, setError] = useState(null);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError(null);

    try {
      const user = await getUserByEmail(email);
      console.log("Logged in user object:", user);
      if (user) {
    setUser(user);

    if (user.role === 'ADMIN') {
        navigate('/admin');
    } else {
        navigate('/toys');
    }
    }
    else {
            setError('User not found.');
        }
        } catch (err) {
        setError('Invalid login. Check your email.');
        }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Email:</label><br />
          <input
            type="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
          />
        </div>
        <div>
          <label>Password:</label><br />
          <input
            type="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            required
          />
        </div>
        <br />
        <button type="submit">Login</button>
      </form>
      <p>
        Not registered? <Link to="/register">Register here</Link>
      </p>
      {error && <p style={{ color: 'red' }}>{error}</p>}
    </div>
  );
};

export default LoginPage;
