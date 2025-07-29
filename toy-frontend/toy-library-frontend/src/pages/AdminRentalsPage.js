import React, { useEffect, useState } from 'react';
import { useAuth } from '../context/AuthContext';
import { getAllRentals } from '../api/rentalApi';

const AdminRentalsPage = () => {
  const { user } = useAuth();
  const [rentals, setRentals] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    if (!user || user.role !== 'ADMIN') return;

    const fetchRentals = async () => {
      try {
        const data = await getAllRentals(user.id);
        setRentals(data);
        setLoading(false);
      } catch (err) {
        console.error(err);
        setError('Failed to load rentals');
        setLoading(false);
      }
    };

    fetchRentals();
  }, [user]);

  return (
    <div style={{ padding: '20px' }}>
      <h2>All Rentals</h2>

      {loading && <p>Loading...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {rentals.length === 0 ? (
        <p>No rentals found.</p>
      ) : (
        rentals.map(rental => (
          <div key={rental.rentalId} style={{ border: '1px solid #ccc', padding: '10px', marginBottom: '10px' }}>
            <h3>{rental.toyName}</h3>
            <p><strong>Renter:</strong> {rental.renterName}</p>
            <p><strong>Start Date:</strong> {rental.startDate}</p>
            <p><strong>End Date:</strong> {rental.endDate}</p>
            <p>
              <strong>Status:</strong>{' '}
              {rental.returned
                ? <span style={{ color: 'green' }}>Returned</span>
                : <span style={{ color: 'orange' }}>Active</span>}
            </p>
            {rental.returned && <p><strong>Returned On:</strong> {rental.returnDate}</p>}
          </div>
        ))
      )}
    </div>
  );
};

export default AdminRentalsPage;
