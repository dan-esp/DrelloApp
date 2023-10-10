import { BrowserRouter, Routes, Route } from "react-router-dom";
import Layout from "../pages/Layout";
import NoPage from "../pages/NoPage";
import Home from "../pages/Home";
import Login from "../pages/Login";
import Register from "../pages/Register";
import { AuthProvider } from "../context/AuthContext";
import Workspace from "../pages/Workspace";
import {LayoutWorkspace} from "../pages/LayuotWorkspace";
import Board from "../pages/Board";
function AppRouter() {
  return (
    <BrowserRouter>
      <AuthProvider>
        <Routes>
          <Route path="/" element={<Layout />}>
            <Route element={<Home />} />
          </Route>
          
          <Route element={<LayoutWorkspace />}>
            <Route path="/workspace" element={<Workspace />} />
            <Route path="/workspace/:boardId" element={<Board />} />
          </Route>
          <Route path="/login" element={<Login />} />
          <Route path="/register" element={<Register />} />
          <Route path="*" element={<NoPage />} />
        </Routes>
      </AuthProvider>
    </BrowserRouter>
  );
}

export default AppRouter;
