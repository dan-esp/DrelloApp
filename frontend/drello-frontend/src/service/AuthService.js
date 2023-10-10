import axios from "axios";
const API = "http://localhost:8080";

export const loginRequest = (user) => axios.post(`${API}/auth/login`, user);

export const registerRequest = (user) =>
  axios.post(`${API}/auth/signup`, user);

export const verifyToken = (token) =>
  axios.post(`${API}/auth/verify-token`, { token });
