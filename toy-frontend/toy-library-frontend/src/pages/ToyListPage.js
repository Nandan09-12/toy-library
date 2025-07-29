import React, { useEffect, useState } from 'react';
import { getAvailableToys } from '../api/toyApi';
import { useCart } from '../context/CartContext';


const ToyListPage = () => {
  const [toys, setToys] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  // Pagination state
  const [currentPage, setCurrentPage] = useState(1);
  const toysPerPage = 5; // You can change this

  useEffect(() => {
    const fetchToys = async () => {
      try {
        const data = await getAvailableToys();
        setToys(data);
        setLoading(false);
      } catch (err) {
        setError('Failed to load toys.');
        setLoading(false);
      }
    };

    fetchToys();
  }, []);

  const { addToCart, cartItems } = useCart();

  // Pagination logic
  const indexOfLastToy = currentPage * toysPerPage;
  const indexOfFirstToy = indexOfLastToy - toysPerPage;
  const currentToys = toys.slice(indexOfFirstToy, indexOfLastToy);

  const totalPages = Math.ceil(toys.length / toysPerPage);

  const paginate = (pageNumber) => setCurrentPage(pageNumber);

  return (
    <div style={{ padding: '20px' }}>
      <h2>Available Toys for Rent</h2>

      {loading && <p>Loading toys...</p>}
      {error && <p style={{ color: 'red' }}>{error}</p>}

      {currentToys.map((toy) => {
  const isInCart = cartItems.some(item => item.id === toy.id);

  return (
    <div key={toy.id} style={{ border: '1px solid #ccc', padding: '10px', marginBottom: '10px' }}>
      <h3>{toy.name}</h3>
      <p><strong>Description:</strong> {toy.description}</p>
      <p><strong>Point Cost:</strong> {toy.pointCost}</p>
      <p><strong>Listed by:</strong> {toy.ownerName}</p>
      <button
        onClick={() => addToCart(toy)}
        disabled={isInCart}
      >
        {isInCart ? 'Added to Cart' : 'Add to Cart'}
      </button>
    </div>
  );
    })}


      {/* Pagination Controls */}
      <div style={{ marginTop: '20px' }}>
        {Array.from({ length: totalPages }, (_, i) => (
          <button
            key={i + 1}
            onClick={() => paginate(i + 1)}
            style={{
              marginRight: '5px',
              backgroundColor: i + 1 === currentPage ? '#007bff' : '#eee',
              color: i + 1 === currentPage ? '#fff' : '#000'
            }}
          >
            {i + 1}
          </button>
        ))}
      </div>
    </div>
  );
};

export default ToyListPage;
