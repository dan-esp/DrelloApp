import styles from "./style/Button.module.css";

function Button({ icon = "", prompt, onclick, btnStyle = "first_btn" }) {
  return (
    <button onClick={onclick} className={styles[btnStyle]}>
      <i className={icon}></i>
      {` ${prompt}`}
    </button>
  );
}

export default Button;
