import "./style/layoutWorkspace.css";
import { Outlet, Link, Navigate, useLocation } from "react-router-dom";
import { useAuth } from "../context/AuthContext";
import ProfileCard from "../components/ProfileCard";
import SearchBar from "../components/SearchBar";
import { useState, useContext, createContext } from "react";
export const WorkspaceContext = createContext();
import { getLuminance } from "../service/ColotPicker";
import DropDownButton  from "../components/DropDownButton";

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
  const [availableBoards, setAvailableBoards] = useState([]);
  const { location } = useLocation();
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
      <section
        id="workspace-layout"
        style={
          currentBoard
            ? {
                background: `url(${currentBoard.imgUrl})`,
             
                backgroundImageSize: "cover",
                backgroundRepeat: "no-repeat",
                backgroundPosition: "center",
              }
            : { background: "" }
        }
      >
        <header
          id="workspace-layout-header"
          style={{
            backgroundColor: `${
              currentBoard ? currentBoard.headerColor.rgbColor : ""
            }`,
          }}
        >
          <Link to="/" className="logo-ctn">
            <img src="/icon.png" className="logo" alt="" />
            <h1
              style={{
                color: `${
                  currentBoard ? getLuminance(currentBoard.headerColor.rgb) : ""
                }`,
              }}
            >
              Drello
            </h1>
          </Link>

          <div
            className="dashboard-info"
            style={{ visibility: `${currentBoard ? "visible" : "hidden"}` }}
          >
            <h4
              style={{
                color: `${
                  currentBoard ? getLuminance(currentBoard.headerColor.rgb) : ""
                }`,
              }}
            >
              {currentBoard ? currentBoard.title : ""}
            </h4>
            <DropDownButton
              promptButton={"All Boards"}
              iconButton={"fa-solid fa-border-none"}
              items={availableBoards}
            />
          </div>
          <SearchBar />
          <ProfileCard
            color={
              currentBoard ? getLuminance(currentBoard.headerColor.rgb) : ""
            }
          />
        </header>
        <div
          id="workspace-layout-content"
          style={{ height: currentBoard ? "calc(100vh - 70px)" : "100%" }}
        >
          <Outlet />
        </div>
      </section>
    </WorkspaceContext.Provider>
  );
};
