import Button from "../Button";
import listStyle from "./style/List.module.css";
import { CardInput } from "../Inputs";
import { useState } from "react";
function List({ nameList }) {
  const [card, cards] = useState([]);

  const addCard = (newCard) => {
    cards([...card, newCard]);
  };

  const startDrag = (event, item) => {
    event.dataTransfer.setData("itemID", item);
    event.dataTransfer.dropEffect = "move";
  };

  const draggingOver = (event) => {
    event.preventDefault();
    event.dataTransfer.dropEffect = "move";
    console.log();
  }

  const onDrop = (event , list) => {
    
  }
  return (
    
    <div className={listStyle.list_ctn}>
      <div className={listStyle.list_header}>
        {nameList}
        <Button
          icon="fa-solid fa-ellipsis"
          prompt={""}
          onclick={() => {
            console.log("click");
          }}
          btnStyle="second_btn"
        />
      </div>
      {card.length !== 0 && (
        <div className={listStyle.list_body}  onDrop={draggingOver} onDragOver={draggingOver}>
          {card.length !== 0 && (
            <>
              {card.map((el, index) => (
                <div
                 draggable onDragStart={(event)=>startDrag(event, index)}
                  key={new Date().getUTCMilliseconds}
                  style={{
                    background: "white",
                    padding: "10px",
                    borderRadius: "10px",
                    cursor: "pointer",
                  }}
                >
                  {el.title}
                </div>
              ))}
            </>
          )}
        </div>
      )}

      <CardInput addComponent={addCard} />
    </div>
  );
}

export default List;
