import React, { createContext, useState, useContext } from 'react';

// Create the context
export const AuthContext = createContext();

// Create a provider component
export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null); // Will hold the logged-in user

  const logout = () => setUser(null); // Simple logout

  return (
    <AuthContext.Provider value={{ user, setUser, logout }}>
      {children}
    </AuthContext.Provider>
  );
};

// Optional: easy hook
export const useAuth = () => useContext(AuthContext);
