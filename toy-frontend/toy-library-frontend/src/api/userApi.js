import axios from 'axios';


const BASE_URL = 'http://localhost:8080/api/users'; 

// Register user (member only for now)
export const registerUser = async (userData) => {
  try {
    const response = await axios.post(`${BASE_URL}/register`, userData);
    return response.data; // Should return the UserDTO
  } catch (error) {
    throw error; // Let the component handle the error
  }

};


export const getUserByEmail = async (email) => {
  const response = await axios.get(`${BASE_URL}/email`, {
    params: { email: email }
  });
  return response.data;
};

export const getAllUsers = async () => {
  const response = await axios.get('http://localhost:8080/api/users');
  return response.data;
};



