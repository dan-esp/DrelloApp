import "./style/layoutWorkspace.css";
import { Outlet, Link, Navigate } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import ProfileCard from "../components/ProfileCard";
import SearchBar from "../components/SearchBar";
import { useState, useContext, createContext } from "react";
export const WorkspaceContext = createContext();

export const useWorkspace = () => {
  const context = useContext(WorkspaceContext);
  if (!context) {
    throw new Error("useWorkspace must be used within a WorkspaceProvider");
  }
  return context;
};

export const LayoutWorkspace = () => {
  const { isAuthenticated } = useAuth();
  const [currentBoard, setCurrentBoard] = useState(null);
  const [availableBoards, setAvailableBoards] = useState(null);

  const handleCurrentBoard = (board) => {
    setCurrentBoard(board);
  };

  const handleAvailableBoards = (boards) => {
    setAvailableBoards(boards);
  };

  if (!isAuthenticated) return <Navigate to="/login" replace />;

  return (
    <WorkspaceContext.Provider
      value={{
        currentBoard,
        availableBoards,
        handleCurrentBoard,
        handleAvailableBoards,
      }}
    >
      <section id="workspace-layout">
        <header id="workspace-layout-header">
          <Link to="/" className="logo-ctn">
            <img src="/icon.png" className="logo" alt="" />
            <h1>Drello</h1>
          </Link>

          <div
            className="dashboard-info"
            style={{ visibility: `${currentBoard ? "visible" : "hidden"}` }}
          >
            <h2>{currentBoard ? currentBoard.name : ""}</h2>
            <button className="all-boards-btn">
              <i className="fa-solid fa-border-none"></i> All Board
            </button>
          </div>
          <SearchBar />
          <ProfileCard />
        </header>
        <div id="workspace-layout-content">
          <Outlet />
        </div>
      </section>
    </WorkspaceContext.Provider>
  );
};
