import React, { createContext, useContext, useState } from 'react';

// Create the context
export const CartContext = createContext();

// Provider
export const CartProvider = ({ children }) => {
  const [cartItems, setCartItems] = useState([]);

  // Add toy to cart
  const addToCart = (toy) => {
    // Prevent duplicate toy in cart
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
  const clearCart = () => setCartItems([]);

  return (
    <CartContext.Provider value={{ cartItems, addToCart, removeFromCart, clearCart }}>
      {children}
    </CartContext.Provider>
  );
};

// Easy custom hook
export const useCart = () => useContext(CartContext);
