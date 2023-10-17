import axios from "axios";
const API = "http://localhost:8080";
const axiosConfig = {
  headers: {
    "Content-Type": "application/json",
    Authorization: `Bearer ${localStorage.getItem("drellojwt")}`,
  },
};
export const addBoard = (board) => {
  axios.post(`${API}/api/user`, board);
};

export const searchUser = (partial) =>
  axios.get(`${API}/api/users/search/${partial}`, axiosConfig);
