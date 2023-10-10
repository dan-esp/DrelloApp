import { createContext, useContext, useEffect, useState } from "react";
import {
  registerRequest,
  loginRequest,
  verifyToken,
} from "../service/AuthService";
export const AuthContext = createContext();

export const useAuth = () => {
  const context = useContext(AuthContext);
  if (!context) {
    throw new Error("useAuth must be used within AuthProvider");
  }
  return context;
};

export const AuthProvider = ({ children }) => {
  const [user, setUser] = useState(null);
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const signup = async (userData) => {
    try {
      const res = await registerRequest(userData);
      setUser(res.data.user);
      localStorage.setItem("drellojwt", res.data.token);
      setIsAuthenticated(true);
    } catch (error) {
      setUser(null);
      setIsAuthenticated(false);
    }
  };

  const sigin = async (userData) => {
    try {
      const res = await loginRequest(userData);
      setUser(res.data.user);
      localStorage.setItem("drellojwt", res.data.token);
      setIsAuthenticated(true);
    } catch (error) {
      setUser(null);
      setIsAuthenticated(false);
    }
  };

  useEffect(() => {
    const token = localStorage.getItem("drellojwt");
    async function checkLogin() {
      if (token) {
        try {
          let res = await verifyToken(token);
          setUser(res.data.user);
          setIsAuthenticated(true);
        } catch (error) {
          setUser(null);
          setIsAuthenticated(false);
        }
      }
    }
    checkLogin();
  }, []);

  const logout = () => {
    setUser(null);
    localStorage.removeItem("drellojwt");
    setIsAuthenticated(false);
  };
  return (
    <AuthContext.Provider
      value={{ user, signup, sigin, logout, isAuthenticated }}
    >
      {children}
    </AuthContext.Provider>
  );
};
