import React, { createContext, useContext, useState, useEffect } from 'react';

// Create the context
export const CartContext = createContext();

// Provider
export const CartProvider = ({ children }) => {
  // Load from localStorage on startup
  const [cartItems, setCartItems] = useState(() => {
    const storedCart = localStorage.getItem('cartItems');
    return storedCart ? JSON.parse(storedCart) : [];
  });

  // Save to localStorage when cart changes
  useEffect(() => {
    localStorage.setItem('cartItems', JSON.stringify(cartItems));
  }, [cartItems]);

  // Add toy to cart
  const addToCart = (toy) => {
    const alreadyAdded = cartItems.some(item => item.id === toy.id);
    if (!alreadyAdded) {
      setCartItems(prev => [...prev, toy]);
    }
  };

  // Remove toy from cart by ID
  const removeFromCart = (toyId) => {
    setCartItems(prev => prev.filter(item => item.id !== toyId));
  };

  // Clear cart after checkout
  const clearCart = () => {
    setCartItems([]);
    localStorage.removeItem('cartItems');
  };

  return (
    <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart }}>
      {children}
    </CartContext.Provider>
  );
};

// Hook for easy use
export const useCart = () => useContext(CartContext);
