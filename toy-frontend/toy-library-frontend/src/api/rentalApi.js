import axios from 'axios';

const BASE_URL = 'http://localhost:8080/api/rentals';

export const getRentalsByUserId = async (userId) => {
  const response = await axios.get(`${BASE_URL}/user/${userId}`);
  return response.data;
};

export const rentToy = async (userId, toyId) => {
  try {
    const response = await axios.post(`${BASE_URL}/rent/${userId}/${toyId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const returnRentalById = async (rentalId) => {
  const response = await axios.post(`${BASE_URL}/return/${rentalId}`);
  return response.data;
};

export const getAllRentals = async (adminId) => {
  try {
    const response = await axios.get(`${BASE_URL}/all/${adminId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
};
