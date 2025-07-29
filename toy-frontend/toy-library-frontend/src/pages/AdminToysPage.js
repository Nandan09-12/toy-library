import React, { useEffect, useState } from 'react';
import { getAllToys } from '../api/toyApi';

const AdminToysPage = () => {
  const [toys, setToys] = useState([]);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetchToys = async () => {
      try {
        const data = await getAllToys();
        setToys(data);
      } catch (err) {
        setError('Failed to fetch toys');
      }
    };

    fetchToys();
  }, []);

  return (
    <div style={{ padding: '20px' }}>
      <h2>All Toys in Library</h2>
      {error && <p style={{ color: 'red' }}>{error}</p>}

      <table border="1" cellPadding="8" cellSpacing="0">
        <thead>
          <tr>
            <th>Name</th>
            <th>Description</th>
            <th>Point Cost</th>
            <th>Rented?</th>
            <th>Owner Name</th>
            <th>Owner ID</th>
          </tr>
        </thead>
        <tbody>
          {toys.map(toy => (
            <tr key={toy.id}>
              <td>{toy.name}</td>
              <td>{toy.description}</td>
              <td>{toy.pointCost}</td>
              <td>{toy.rented ? 'Yes' : 'No'}</td>
              <td>{toy.ownerName}</td>
              <td>{toy.ownerId}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default AdminToysPage;
