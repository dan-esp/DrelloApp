import Button from "../components/Button";
import BoardCard from "../components/board/BoardCard";
import useModal from "../hook/useModal";
import { useState, useEffect, useCallback } from "react";
import Modal from "../components/Modal";
import BoardCardCreation from "../components/board/BoardCardCreation";
import { saveBoard, getBoards } from "../request/boardRequest";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";
import { useWorkspace } from "./LayuotWorkspace";
import { averageColor } from "../service/ColotPicker";

function Workspace() {
  const [isModalOpen, handleOpenModal, handleCloseModal] = useModal(false);
  const [boards, setBoards] = useState([]);
  const { user } = useAuth();
  const { currentBoard, handleCurrentBoard, handleAvailableBoards } =
    useWorkspace();

  const navigate = useNavigate();

   useEffect(() => {
     const fetchBoards = async () => {
       const result = await getBoards(user.userId);
       setBoards(result.data);
     };

     fetchBoards();
   }, []);

  const createBoard = async (newBoard) => {
    if (newBoard.title === "") {
      throw "Title cannot be empty";
    }
    boards.forEach((el) => {
      if (el.title === newBoard.title) {
        throw "Board already exists";
      }
    });

    let objt = {
      board: {
        title: newBoard.title,
        imgUrl: newBoard.imgUrl,
        privacy: newBoard.privacy ? "PUBLIC" : "PRIVATE",
      },
      ownerId: user.userId,
    };
    const result = await saveBoard(objt);
    newBoard = result.data;
    setBoards([...boards, newBoard]);
  }

 

  const openBoard = (title) => {
    const selectedBoard = boards.find((el) => {
      if (el.title === title) {
        return el;
      }
    });
    var rgb = averageColor(selectedBoard.id);

    console.log(rgb);
    selectedBoard.headerColor = {
      rgb: rgb,
      rgbColor: `rgb(${rgb.r},${rgb.g},${rgb.b})`,
    };

    handleCurrentBoard(selectedBoard);
    handleAvailableBoards(boards);

    navigate(`/workspace/${selectedBoard.title}`, {
      replace: true,
      state: {
        board: selectedBoard,
      },
    });
  };

  const openModal = () => {
    handleOpenModal();
  };

  return (
    <div className="workspace-ctn">
      <div className="workspace-menu">
        <h3>All Boards</h3>
        <Button icon={"fa-solid fa-plus"} prompt={"New"} onclick={openModal} />
      </div>
      <div className="workspace-content">
        {boards.map((el, index) => (
          <BoardCard
            key={index}
            title={el.title}
            boardForm={el}
            onclick={() => {
              openBoard(el.title);
            }}
          />
        ))}
      </div>
      <Modal
        isModalOpen={isModalOpen}
        onCloseModal={handleCloseModal}
        hightModal={"380px"}
        widthModal={"380px"}
      >
        <BoardCardCreation hide={handleCloseModal} create={createBoard} />
      </Modal>
    </div>
  );
}

export default Workspace;
