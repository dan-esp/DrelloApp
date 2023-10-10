import { Outlet, Link, useLocation } from "react-router-dom";

const Layout = () => {
  const { state } = useLocation();

  return (
    <>
      <header>
        <Link to="/" className="logo-ctn">
          <img src="/icon.png" className="logo" alt="" />
          <h1>Drello</h1> 
          </Link>

        {
          state?.logged ? (

            <div className="user">
              <span className="username">{state?.username}</span>
              <button className="btn-logout">Logout</button>
            </div>
          ) : (
            <nav>
              <ul>
                <li>
                  <Link to="/login" className="link-login">Login</Link>
                </li>
                <li>
                  <Link to="/register" className="link-register">Get Trello For Free</Link>
                </li>
              </ul>
            </nav>
          )
        }

      </header>
      <Outlet />
    </>
  );
};

export default Layout;
