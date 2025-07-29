import React from 'react';
import { useCart } from '../context/CartContext';
import { useAuth } from '../context/AuthContext';
import axios from 'axios';
import { rentToy } from '../api/rentalApi'; 

const CartPage = () => {
  const { cartItems, removeFromCart, clearCart } = useCart();
  const { user } = useAuth();

  const totalCost = cartItems.reduce((sum, toy) => sum + toy.pointCost, 0);
  const monthly = user?.monthlyPoints ?? 0;
  const used = user?.usedPoints ?? 0;
  const availablePoints = monthly - used;

  console.log("User in CartPage:", user);

  const canCheckout = totalCost <= availablePoints;

  const handleCheckout = async () => {
    if (!canCheckout) return;

    try {
      for (const toy of cartItems) {
        await axios.post(`http://localhost:8080/api/rentals/rent/${user.id}/${toy.id}`);
      }

      alert('Toys rented successfully!');
      clearCart();
    } catch (error) {
      console.error('Rental failed:', error);
      alert('Something went wrong during checkout.');
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h2>My Cart</h2>

      {cartItems.length === 0 ? (
        <p>Your cart is empty.</p>
      ) : (
        <>
          {cartItems.map((toy) => (
            <div key={toy.id} style={{ border: '1px solid #ccc', padding: '10px', marginBottom: '10px' }}>
              <h3>{toy.name}</h3>
              <p><strong>Description:</strong> {toy.description}</p>
              <p><strong>Point Cost:</strong> {toy.pointCost}</p>
              <button onClick={() => removeFromCart(toy.id)}>Remove</button>
            </div>
          ))}

          <hr />
          <p><strong>Total Points Required:</strong> {totalCost}</p>
          <p><strong>Your Available Points:</strong> {availablePoints}</p>

          {!canCheckout && <p style={{ color: 'red' }}>You don’t have enough points to rent these toys.</p>}

          <button
            onClick={handleCheckout}
            disabled={!canCheckout}
            style={{ marginTop: '10px' }}
          >
            Confirm Rental
          </button>
        </>
      )}
    </div>
  );
};

export default CartPage;
