import boardCardStyles from "./BoardCardCreation.module.css";
import { useState } from "react";
import Button from "../Button";
import BackgroundSelector from "./BackgroundSelector";
const PUBLIC_ICON = <i className="fa-solid fa-unlock"></i>;
const PRIVATE_ICON = <i className="fa-solid fa-lock"></i>;
const initialCardBoard = {
  imgUrl:
    "https://images.unsplash.com/photo-1469474968028-56623f02e42e?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0ODU1MjV8MHwxfHNlYXJjaHwyfHxuYXR1cmV8ZW58MHx8fHwxNjkxMzc4OTU0fDA&ixlib=rb-4.0.3&q=85",
  title: "",
  privacy: false,
};

function BoardCardCreation({ hide, create }) {
  const [boardForm, setBoardForm] = useState(initialCardBoard);
  const [openBackground, setOpenBackground] = useState(false);
  const [titleError, setTittleError] = useState({});

  const getVisibilityContent = () => {
    return boardForm.privacy ? (
      <>{PUBLIC_ICON} Public</>
    ) : (
      <>{PRIVATE_ICON} Private</>
    );
  };

  const setBackground = (newImageSource) => {
    setBoardForm({
      ...boardForm,
      imgUrl: newImageSource,
    });
  };

  const setVisibility = () => {
    setBoardForm({
      ...boardForm,
      privacy: !boardForm.privacy,
    });
  };

  const openBackgroundEditor = () => {
    setOpenBackground(!openBackground);
  };

  const handleChange = (e) => {
    setBoardForm({
      ...boardForm,
      [e.target.name]: e.target.value,
    });
  };

  const cancel = () => {
    setBoardForm(initialCardBoard);
    hide();
  };

  const createBoard = async () => {
    try {
      await create(boardForm);
      cancel();
    } catch (error) {
      titleError.message = error;
      setTittleError({
        ...titleError,
        message: error,
      });
      setTimeout(() => {
        titleError.message = null;
        setTittleError(titleError);
      }, 1400);
    }
  };

  return (
    <>
      <div className={boardCardStyles.boardCreator}>
        <div
          style={{ backgroundImage: `url(${boardForm.imgUrl})` }}
          className={boardCardStyles.boardImage}
        >
          <div className={boardCardStyles.scheme}></div>
        </div>

        <input
          type="text"
          placeholder="Add board tittle"
          className={boardCardStyles.titleInput}
          name="title"
          value={boardForm.title}
          onChange={handleChange}
        />
        <a
          href="#"
          className={boardCardStyles.coverButton}
          name="img"
          value={boardForm.img}
          onClick={openBackgroundEditor}
        >
          <i className="fa-solid fa-image"></i> Background
        </a>
        <a
          href="#"
          className={boardCardStyles.visibilityButton}
          onClick={() => {
            setVisibility();
          }}
        >
          {getVisibilityContent()}
        </a>
        <div className={boardCardStyles.createButton}>
          <Button
            icon={"fa-solid fa-plus"}
            prompt={"Create"}
            onclick={createBoard}
          />
        </div>

        <div className={boardCardStyles.tooltip}>
          <p
            className={boardCardStyles.tooltiptext}
            style={{
              visibility: `${titleError.message ? "visible" : "hidden"}`,
            }}
          >
            <span>{titleError.message}</span>
          </p>
        </div>
      </div>

      {openBackground && (
        <BackgroundSelector
          onclick={openBackgroundEditor}
          setBackground={setBackground}
        />
      )}
    </>
  );
}
export default BoardCardCreation;
