function DropDownItem({ icon, promp, onclick}) {
  return (
    <li
      className="dropdown-item"
      style={{ listStyle: "none", cursor: "pointer" }}
      onClick={onclick}
    >
      <i className={icon}></i>
      {` ${promp}`}
    </li>
  );
}

export default DropDownItem;
