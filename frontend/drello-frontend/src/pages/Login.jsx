import { useNavigate } from "react-router-dom";
import UseForm from "../hook/UseForm";
import "./style/Login.css";
import { useAuth } from "../context/AuthContext";
import { useEffect } from "react";
function Login() {
  const { username, password, onInputChange, onResetForm } = UseForm({
    username: "",
    password: "",
  });
  const { sigin, isAuthenticated } = useAuth();


  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) {
      navigate("/workspace", {
        replace: true,
        state: {
          logged: true,
          username
        }
      });
    }
  }, [isAuthenticated]);

  const onLogin = async (e) => {
    e.preventDefault();
    sigin({ username, password });
    onResetForm();
  };

  return (
    <div className="wrapper login-page">
      <div className="background">
        <div className="shape"></div>
        <div className="shape"></div>
      </div>
      <form onSubmit={onLogin}>
        <h1>Login</h1>
        <div className="input-group">
          <label htmlFor="username" className="form-auth-label">
            Username :
          </label>
          <input
            className="form-auth-input"
            type="text"
            placeholder="Username"
            name="username"
            value={username}
            onChange={onInputChange}
            required
            autoComplete="off"
          />
        </div>
        <div className="input-group">
          <label htmlFor="password" className="form-auth-label">
            Password :
          </label>
          <input
            className="form-auth-input"
            type="password"
            placeholder="Password"
            name="password"
            value={password}
            onChange={onInputChange}
            required
            autoComplete="off"
          />
        </div>
        <button type="submit" className="btn-submit btn-login">
          Login
        </button>
      </form>
    </div>
  );
}

export default Login;
