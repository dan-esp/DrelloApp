import inputStyle from "./style/Input.module.css";
import Button from "./Button";
import { useState } from "react";

export function CardInput() {
  return <div>Inputs</div>;
}

export function ListInput() {
  const [openMenu, setOpenMenu] = useState(false);
  const [content, setContent] = useState("");
  const handleCloseMenu = () => {
    setOpenMenu(false);
    resetContent();
  };

  const handleOpenMenu = () => {
    setOpenMenu(true);
  };

  const handleOnClick = () => {
    if (content === "") {
      return;
    }
    console.log(content);
    resetContent();
    handleCloseMenu();
  };

  const handleContentChange = (e) => {
    setContent(e.target.value);
  };

  const resetContent = () => {
    setContent("");
  };

  return (
    <div className={inputStyle.input_ctn}>
      <button
        className={inputStyle.add_list_btn}
        onClick={handleOpenMenu}
        style={{ visibility: openMenu ? "hidden" : "visible" }}
      >
        Add another card
        <i className="fa-solid fa-plus"></i>
      </button>
      <div
        className={`${inputStyle.input} ${`${
          openMenu ? inputStyle.active : inputStyle.inactive
        }`}`}
      >
        <input type="text" value={content} onChange={handleContentChange} max={30}/>
        <div className={inputStyle.add_list_btns}>
          <Button icon="" prompt={"Add List"} onclick={handleOnClick} />
          <Button
            icon="fa-solid fa-x"
            prompt={""}
            onclick={handleCloseMenu}
            btnStyle="second_btn"
          />
        </div>
      </div>
    </div>
  );
}
