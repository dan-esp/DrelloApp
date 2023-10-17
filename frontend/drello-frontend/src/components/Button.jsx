import styles from "./style/Button.module.css";
import React from "react";
function Button({
  icon = "",
  prompt,
  onclick,
  btnStyle = "first_btn",
  isDisabled = false,
}) {
  return (
    <button
      onClick={onclick}
      className={styles[btnStyle]}
      disabled={isDisabled}
    >
      <i className={icon}></i>
      {` ${prompt}`}
    </button>
  );
}


export default Button;