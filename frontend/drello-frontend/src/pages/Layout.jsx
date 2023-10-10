import { Outlet, Link, useLocation, Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";

const Layout = () => {
  const { state } = useLocation();

  const { isAuthenticated } = useAuth();

  if (isAuthenticated) return <Navigate to="/workspace" replace />;

  return (
    <>
      <header className="home-header">
        <Link to="/" className="logo-ctn">
          <img src="/icon.png" className="logo" alt="" />
          <h1>Drello</h1>
        </Link>

        <nav>
          <ul>
            <li>
              <Link to="/login" className="link-login">
                Login
              </Link>
            </li>
            <li>
              <Link to="/register" className="link-register">
                Get Trello For Free
              </Link>
            </li>
          </ul>
        </nav>
      </header>
      <Outlet />
    </>
  );
};

export default Layout;
