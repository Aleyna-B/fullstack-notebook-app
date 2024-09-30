import axios from 'axios';

// Create an Axios instance with default configurations
const axiosInstanceforLogin = axios.create({
  baseURL: 'http://localhost:8080/v1/notebook', //Spring Boot backend URL
  headers: {
    'Content-Type': 'application/x-www-form-urlencoded',
  },
  withCredentials: true, // Include credentials (cookies, authorization headers, etc.) if needed
});

// Export the Axios instance
export default axiosInstanceforLogin ;