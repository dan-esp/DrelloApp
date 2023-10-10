import UseForm from "../hook/UseForm";

function Register() {
  const { username, email, password, onInputChange, onResetForm } = UseForm({
    username: "",
    email: "",
    password: "",
  });

  const onRegister = (e) => {
    e.preventDefault();
    console.log(username, email, password);
    onResetForm();
  }

  return (
    <div className="wrapper">
      <div class="background">
        <div class="shape"></div>
        <div class="shape"></div>
      </div>
      <form onSubmit={onRegister}>
        <h1>Sign Up</h1>
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
          <label className="form-auth-label" htmlFor="email">Email :</label>
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
          <label htmlFor="password" className="form-auth-label" >Password :</label>
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
        <button type="submit" className="btn-submit">Signup</button>
      </form>
    </div>
  );
}

export default Register;
