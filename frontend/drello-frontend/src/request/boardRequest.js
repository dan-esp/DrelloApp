import axios from "axios";
const API = "http://localhost:8080";
const axiosConfig = {
  headers: {
    "Content-Type": "application/json",
    Authorization: `Bearer ${localStorage.getItem("drellojwt")}`,
  },
};

export const saveBoard = (board) =>
  axios.post(`${API}/api/boards`, board, axiosConfig);

export const getBoards = (userId) =>
  axios.get(`${API}/api/boards/user/${userId}`, axiosConfig);
