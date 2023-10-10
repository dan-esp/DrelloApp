import Button from "../Button";
function List({ listId, nameList }) {
  return (
    <div className="list">
      <div className="list_header">
        <p>{nameList}</p>
        <Button
          icon="fa-solid fa-ellipsis"
          prompt={""}
          onclick={() => {
            console.log("click");
          }}
          btnStyle="second_btn"
        />
      </div>
      <div></div>
    </div>
  );
}

export default List;
