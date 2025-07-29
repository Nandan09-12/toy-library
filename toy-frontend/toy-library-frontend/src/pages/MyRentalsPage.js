import React, { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { getRentalsByUserId, returnRentalById } from '../api/rentalApi';

const MyRentalsPage = () => {
  const { user } = useAuth();
  const [rentals, setRentals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  const fetchRentals = async () => {
    try {
      const data = await getRentalsByUserId(user.id);
      setRentals(data);
      setLoading(false);
    } catch (err) {
      setError('Failed to load rentals');
      setLoading(false);
    }
  };

  
  const handleReturn = async (rentalId) => {
    try {
      await returnRentalById(rentalId);
      setRentals(prev =>
        prev.map(r =>
          r.rentalId === rentalId
            ? { ...r, returned: true, returnDate: new Date().toISOString().split('T')[0] }
            : r
        )
      );
    } catch (err) {
      alert('Return failed.');
      console.error(err);
    }
  };

 
  useEffect(() => {
    if (user) {
      fetchRentals();
    }
  }, [user]);

  return (
    <div style={{ padding: '20px' }}>
      <h2>My Rentals</h2>

      {loading && <p>Loading...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {rentals.length === 0 ? (
        <p>You haven’t rented any toys yet.</p>
      ) : (
        rentals.map(rental => (
          <div
            key={rental.rentalId}
            style={{ border: '1px solid #ccc', padding: '10px', marginBottom: '10px' }}
          >
            <h3>{rental.toyName}</h3>
            <p><strong>Rented On:</strong> {rental.startDate}</p>
            <p><strong>Due By:</strong> {rental.endDate}</p>
            <p>
              <strong>Status:</strong>{' '}
              {rental.returned
                ? <span style={{ color: 'green' }}>Returned</span>
                : <span style={{ color: 'orange' }}>Active</span>}
            </p>

            {!rental.returned && (
              <button onClick={() => handleReturn(rental.rentalId)}>Return Toy</button>
            )}

            {rental.returned && (
              <p><strong>Returned On:</strong> {rental.returnDate}</p>
            )}
          </div>
        ))
      )}
    </div>
  );
};

export default MyRentalsPage;
