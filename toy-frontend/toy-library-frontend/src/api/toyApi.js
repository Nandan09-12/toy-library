import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/toys';

// Fetch only available toys
export const getAvailableToys = async () => {
  try {
    const response = await axios.get(`${BASE_URL}/available`);
    return response.data;
  } catch (error) {
    throw error;
  }
};
