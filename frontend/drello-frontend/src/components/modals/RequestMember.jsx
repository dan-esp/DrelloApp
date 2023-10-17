import styles from "./RequestMember.module.css";
import Button from "../Button";
import { searchUser } from "../../request/UserRequest";
import { useState, useEffect } from "react";

function RequestMemberModal({ boardName, addMember, closeModal }) {
  const [partial, setPartial] = useState("");
  const [users, setUsers] = useState([]);
  const [userSelected, setSelectedUser] = useState(null);
 useEffect(() => {
   if (partial.trim() === "") {
     setUsers([]);
     return;
   }

   const requestUsers = async () => {
     const matchUsers = await searchUser(partial);
     setUsers(matchUsers.data);
   };

   requestUsers();
 }, [partial]);

  const onWrite = (e) => {
    setPartial(e.target.value);
  };

  const onUnselectUser = () => {
    setSelectedUser(null);
    setPartial("");
    setUsers([]);
  };

  const selectUser = (user) => {
    onUnselectUser();
    setSelectedUser(user);
  };
  const requestUsers = async (partial) => {
    const matchUsers = await searchUser(partial);
    setUsers(matchUsers.data);
  };

  const HandleOnAddMember = () => {
    addMember(userSelected);
    onUnselectUser();
    closeModal();
  };

  return (
    <div className={styles.modal_members_ctn}>
      <div className={styles.modal_members_header}>
        <p>
          <i className="fa-solid fa-user"></i>
        </p>
        <h4>Add a collaborator to {boardName}</h4>
      </div>
      {!userSelected ? (
        <>
          <div className={styles.modal_members_input}>
            <i className=" fa-solid fa-search"></i>
            <input type="text" value={partial} onChange={onWrite} />
          </div>
          <UserResultList usersList={users} onClickItem={selectUser} />
        </>
      ) : (
        <UserSelectedItem
          userItem={userSelected}
          unselectUser={onUnselectUser}
        />
      )}

      <Button
        icon={""}
        prompt={
          userSelected
            ? `Add ${userSelected.username} to this Board`
            : "select a new member"
        }
        onclick={HandleOnAddMember}
        isDisabled={!userSelected}
        btnStyle={userSelected ? "first_btn" : "disabled_btn"}
      />
    </div>
  );
}

const UserResultList = ({ usersList = [], onClickItem }) => {
  return (
    <>
      {usersList.length !== 0 && (
        <div
          className={`${styles.modal_members_result_ctn} ${styles.modal_member_item}`}
        >
          {usersList.map((user) => (
            <UserResultItem
              key={user.id}
              userItem={user}
              onclick={onClickItem}
            />
          ))}
        </div>
      )}
    </>
  );
};
const UserResultItem = ({ userItem, onclick }) => {
  return (
    <div
      className={`${styles.modal_members_result}`}
      onClick={() => onclick(userItem)}
    >
      <img src={userItem.profileUrl} alt={userItem.username} />
      <div className={styles.modal_members_result_info}>
        <p>{userItem.username}</p>
        <p>{userItem.email} - invite user</p>
      </div>
    </div>
  );
};

const UserSelectedItem = ({ userItem, unselectUser }) => {
  return (
    <div className={styles.modal_members_selected}>
      <UserResultItem userItem={userItem} />
      <button onClick={unselectUser}>
        <i className="fa-solid fa-x"></i>
      </button>
    </div>
  );
};
export default RequestMemberModal;
