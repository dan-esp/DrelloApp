import inputStyle from "./style/Input.module.css";
import Button from "./Button";
import { useState, useRef, useEffect } from "react";

export function CardInput({ addComponent }) {
  const [openMenu, setOpenMenu] = useState(false);
  const [content, setContent] = useState("");
  const inputRef = useRef();

  useEffect(() => {
    let handler = (e) => {
      if (inputRef.current && !inputRef.current.contains(e.target)) {
        handleCloseMenu();
      }
    };

    document.addEventListener("mousedown", handler);
    return () => {
      document.removeEventListener("mousedown", handler);
    };
  }, []);

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
    try {
      let card = {
        title: content,
      };
      addComponent(card);
      resetContent();
      handleCloseMenu();
    } catch (error) {
      return;
    }
  };

  const handleContentChange = (e) => {
    setContent(e.target.value);
  };

  const resetContent = () => {
    setContent("");
  };

  return (
    <div className={inputStyle.input_ctn} ref={inputRef}>
      <label
        htmlFor="cardTitle"
        className={inputStyle.add_card_btn}
        onClick={handleOpenMenu}
        style={{ display: openMenu ? "none" : "block" }}
      >
        <i className="fa-solid fa-plus"></i> Add another Card
      </label>
      <div
        className={inputStyle.add_card_input}
        style={{ display: openMenu ? "flex" : "none" }}
      >
        <input
          type="text"
          value={content}
          onChange={handleContentChange}
          max={30}
          placeholder="Enter a title for this card..."
          id="cardTitle"
        />
        <div className={inputStyle.add_list_btns}>
          <Button icon="" prompt={"Add Card"} onclick={handleOnClick} />
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

export function ListInput({ addComponent }) {
  const [openMenu, setOpenMenu] = useState(false);
  const [content, setContent] = useState("");
  const inputRef = useRef();

  useEffect(() => {
    let handler = (e) => {
      if (inputRef.current && !inputRef.current.contains(e.target)) {
        handleCloseMenu();
      }
    };

    document.addEventListener("mousedown", handler);
    return () => {
      document.removeEventListener("mousedown", handler);
    };
  }, []);
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
    try {
      addComponent(content);
      resetContent();
      handleCloseMenu();
    } catch (error) {
      return;
    }
  };

  const handleContentChange = (e) => {
    setContent(e.target.value);
  };

  const resetContent = () => {
    setContent("");
  };

  return (
    <div className={inputStyle.input_ctn} ref={inputRef}>
      <button
        className={inputStyle.add_list_btn}
        onClick={handleOpenMenu}
        style={{ visibility: openMenu ? "hidden" : "visible" }}
      >
        Add another List
        <i className="fa-solid fa-plus"></i>
      </button>
      <div
        className={`${inputStyle.input} ${`${
          openMenu ? inputStyle.active : inputStyle.inactive
        }`}`}
      >
        <input
          type="text"
          value={content}
          onChange={handleContentChange}
          max={30}
        />
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
