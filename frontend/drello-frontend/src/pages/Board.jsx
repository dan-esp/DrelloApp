import { useEffect } from "react";
import { useLocation, useParams } from "react-router-dom";
import boardStyle from "./style/Board.module.css";
import Button from "../components/Button";
import {ListInput} from "../components/Inputs";
function Board() {
  const boardId = useParams();
  const location = useLocation();
  useEffect(() => {
    console.log(boardId);
    console.log(location);
  }, []);
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
          <img
            key={new Date().getUTCMilliseconds}
            src={"/emma.png"}
            alt={"owner"}
            className="profile-card-image"
          />
          <Button
            icon="fa-solid fa-plus"
            prompt={""}
            onclick={() => {
              console.log("click");
            }}
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
        <ListInput />
        <ListInput />
      </div>
    </div>
  );
}

export default Board;
