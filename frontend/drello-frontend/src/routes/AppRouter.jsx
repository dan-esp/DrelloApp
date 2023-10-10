import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "../pages/Layout";
import NoPage from "../pages/NoPage";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Register from "../pages/Register";

function AppRouter() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Layout />}>
          <Route index element={<Home />} />
        </Route>
        <Route path="login" index element={<Login />} />
        <Route path="register" index element={<Register />}/>
        <Route path="*" element={<NoPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default AppRouter;
