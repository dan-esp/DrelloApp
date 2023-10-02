import { useNavigate } from "react-router-dom";
import UseForm from "../hook/UseForm";
import "./styles/Login.css";

function Login() {
  const { username, email, password, onInputChange, onResetForm } = UseForm({
    username: "",
    email: "",
    password: "",
  });

  const navigate = useNavigate();
  const onLogin = (e) => {
    e.preventDefault();
    console.log(username, email, password);
    navigate("/", {
      replace: true,
      state: {
        logged: true,
        username
      }
    });
    onResetForm();
  }

  return (
    <div className="wrapper login-page">
      <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
      </div>
      <form onSubmit={onLogin}>
        <h1>Login</h1>
        <div className="input-group">
          <label htmlFor="username" className="form-auth-label">Username :</label>
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
          <label htmlFor="password" className="form-auth-label">Password :</label>
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
        <button type="submit" className="btn-submit btn-login">Login</button>

      </form>
    </div>
  );
}

export default Login;
