import axios from 'axios';

export const BASE_URL = `${process.env.NEXT_PUBLIC_API}`;

const instance = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Request 🧑
instance.interceptors.request.use();

// Response 🧑
instance.interceptors.response.use();

export default instance;
