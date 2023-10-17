import Button from "./Button";
import styleDropdown from "./style/dropdown.module.css";
import { useRef, useState, useEffect } from "react";
import React from "react";
const DropDownButton = ({ iconButton, promptButton, items }) => {
  const dropdownRef = useRef();
  const [isOpen, setIsOpen] = useState(false);

  useEffect(() => {
    let handler = (e) => {
      if (dropdownRef.current && !dropdownRef.current.contains(e.target)) {
        setIsOpen(false);
      }
    };

    document.addEventListener("mousedown", handler);
    return () => {
      document.removeEventListener("mousedown", handler);
    };
  }, []);

  const handleIsOpen = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className={styleDropdown.dropdown_ctn} ref={dropdownRef}>
      <Button
        icon={iconButton}
        prompt={promptButton}
        onclick={handleIsOpen}
        btnStyle="second_btn"
        isDisabled={false}
      />

      <ul
        className={`${styleDropdown.dropdown} ${
          isOpen ? styleDropdown.open : styleDropdown.close
        }`}
      >
        {items.length !== 0 &&
          items.map((item) => (
            <ImageItem imageUrl={item.imgUrl} prompt={item.title} />
          ))}
      </ul>
    </div>
  );
};

const ImageItem = ({ imageUrl, prompt }) => {
  return (
    <li className={styleDropdown.dropdown_item}>
      <img src={imageUrl} loading="lazy"/>
      <p>{prompt}</p>
    </li>
  );
};

export default React.memo(DropDownButton);
