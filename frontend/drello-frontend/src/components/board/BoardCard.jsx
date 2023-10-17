import boardCardStyle from "./BoardCard.module.css";
function BoardCard({ title, boardForm, onclick }) {
  const handleOnClick = () => {
    onclick();
  };
  return (
    <div className={boardCardStyle.boardCard} onClick={handleOnClick}>
      <div className={boardCardStyle.imageContainer}>
        <img
          src={boardForm.imgUrl}
          alt={boardForm.title}
          className={boardCardStyle.image}
          id={boardForm.id}
          crossOrigin="anonymous"
          loading="lazy"
        />
        <div className={boardCardStyle.scheme}></div>
      </div>

      <p className={boardCardStyle.title}>{title}</p>
      <div className={boardCardStyle.members}>
        {boardForm.members.map((el) => (
          <img
            key={new Date().getUTCMilliseconds}
            src={el.second.imageUrl}
            alt={el.second.username}
            className="profile-card-image"
          />
        ))}
      </div>
    </div>
  );
}

export default BoardCard;
