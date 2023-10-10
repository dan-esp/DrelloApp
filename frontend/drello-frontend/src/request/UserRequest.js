import axios from "axios";
const API = "http://localhost:8080";

export const addBoard = (board) => {
  axios.post(`${API}/api/user`, board);
};
