import UseForm from "../hook/UseForm";
import { useNavigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import { useEffect } from "react";
function Register() {
  const { username, email, password, onInputChange, onResetForm } = UseForm({
    username: "",
    email: "",
    password: "",
  });

  const { signup, isAuthenticated } = useAuth();

  const navigate = useNavigate();

  useEffect(() => {
    if (isAuthenticated) {
      navigate("/workspace", {
        replace: true,
        state: {
          logged: true,
          username,
        },
      });
    }
  }, [isAuthenticated]);

  const onRegister = (e) => {
    e.preventDefault();
    signup({ username, email, password });
    onResetForm();
  };

  return (
    <div className="wrapper">
      <div className="background">
        <div className="shape"></div>
        <div className="shape"></div>
      </div>
      <form onSubmit={onRegister}>
        <h1>Sign Up</h1>
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
          <label className="form-auth-label" htmlFor="email">
            Email :
          </label>
          <input
            className="form-auth-input"
            type="email"
            placeholder="Email"
            name="email"
            value={email}
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
        <button type="submit" className="btn-submit">
          Signup
        </button>
      </form>
    </div>
  );
}

export default Register;
