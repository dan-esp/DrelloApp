import { useEffect, useState } from "react";
import { useLocation, useParams } from "react-router-dom";
import boardStyle from "./style/Board.module.css";
import Button from "../components/Button";
import { ListInput } from "../components/Inputs";
import List from "../components/list/List";
import Modal from "../components/Modal";
import useModal from "../hook/useModal";
import RequestMemberModal from "../components/modals/RequestMember";
import { useWorkspace } from "../pages/LayuotWorkspace";
function Board() {
  const [isModalOpen, handleOpenModal, handleCloseModal] = useModal(false);
  const boardId = useParams();
  const location = useLocation();
  const [lists, setLists] = useState([]);
  const {currentBoard, handleCurrentBoard} = useWorkspace();
  useEffect(() => {
    console.log(boardId);
    console.log(location);
  }, []);

  const addLists = (list) => {
    if (lists.findIndex((l) => l.nameList === list) !== -1) {
      return;
    }
    setLists([...lists, list]);
  };

  const addCard = (newCard) => {
    
  }

  const addMember = (member)=>{
    console.log(member);
  }

  return (
    <div className={boardStyle.board_content}>
      <div className={boardStyle.board_options}>
        <div className={boardStyle.board_options_privacy}>
          <Button
            icon="fa-solid fa-lock"
            prompt={"Private"}
            onclick={() => {
              console.log("click");
            }}
            btnStyle="second_btn"
          />
          {currentBoard.members.map((pair) => (
            <img
              key={new Date().getUTCMilliseconds}
              src={pair.second.imageUrl}
              alt={pair.second.username}
              className="profile-card-image"
            />
          ))}

          <Button
            icon="fa-solid fa-plus"
            prompt={""}
            onclick={handleOpenModal}
          />
        </div>
        <Button
          icon="fa-solid fa-ellipsis"
          prompt={"Show Menu"}
          onclick={() => {
            console.log("click");
          }}
          btnStyle="second_btn"
        />
      </div>
      <div className={boardStyle.board_lists}>
        {lists.map((list) => (
          <List nameList={list} />
        ))}
        <ListInput addComponent={addLists} />
      </div>
      <Modal
        isModalOpen={isModalOpen}
        onCloseModal={handleCloseModal}
        hightModal={"fit-content"}
        widthModal={"380px"}
      >
        <RequestMemberModal
          boardName={currentBoard.title}
          addMember={addMember}
          closeModal={handleCloseModal}
        />
      </Modal>
    </div>
  );
}

export default Board;
