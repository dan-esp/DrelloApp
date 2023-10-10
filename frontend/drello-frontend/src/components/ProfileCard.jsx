import "./style/profileCard.css";
import DropDownItem from "./DropDownItem";
import { useAuth } from "../context/AuthContext";
import { useLocation, useNavigate } from "react-router-dom";
import { useState, useEffect, useRef } from "react";
function ProfileCard() {
  const [open, setOpen] = useState(false);
  const { user } = useAuth();
  const { logout } = useAuth();
  const navigate = useNavigate();
  let menuRef = useRef();
  let profileRef = useRef();
  useEffect(() => {
    let handler = (e) => {
      if (
        menuRef.current &&
        !menuRef.current.contains(e.target) &&
        !profileRef.current.contains(e.target)
      ) {
        setOpen(false);
      }
    };

    document.addEventListener("mousedown", handler);
    return () => {
      document.removeEventListener("mousedown", handler);
    };
  }, []);

  const onOpen = () => {
    console.log("clicked");
    setOpen(!open);
  };

  const onLogout = () => {
    logout();
    navigate("/", {
      replace: true,
    });
  };

  return (
    <>
      <div className="profile-card" onClick={onOpen} ref={profileRef}>
        <img src={user.imageUrl} alt="user" className="profile-card-image" />
        <p className="profile-card-username">{user.username}</p>
        <a className="profile-card-button">
          <i className={`fa-solid fa-caret-${open ? "up" : "down"}`}></i>
        </a>
      </div>
      <div
        className={`profile-dropdown ${open ? "active" : "inactive"}`}
        ref={menuRef}
      >
        <ul>
          <DropDownItem
            icon={"fa-solid fa-user"}
            promp={"My Profile"}
            onclick={() => {
              console.log("profile clicked");
            }}
          ></DropDownItem>
          <br />
          <DropDownItem
            icon={"fa-solid fa-droplet"}
            promp={"Theme"}
            onclick={() => {
              console.log("theme clicked");
            }}
          ></DropDownItem>
        </ul>
        <hr />
        <ul>
          <DropDownItem
            icon={"fa-solid fa-right-from-bracket"}
            promp={"Logout"}
            onclick={onLogout}
          ></DropDownItem>
        </ul>
      </div>
    </>
  );
}

export default ProfileCard;
