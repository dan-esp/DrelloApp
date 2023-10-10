import Button from "../Button";

function CardItem({
  title,
  labels = [],
  imageUrl,
  members,
  commets = 0,
  attachments = 0,
}) {
  return (
    <div>
      {imageUrl && <img src={imageUrl} />}
      <div className="desc_card">
        <p>{title}</p>
      </div>
      <div className="labels">
        {members.map((label) => (
          <div key={new Date().getMilliseconds()}>
            <p>{label.text}</p>
          </div>
        ))}
      </div>
      <div className="card_info">
        <div className="assigned_users">
          <img
            key={new Date().getUTCMilliseconds}
            src={"/emma.png"}
            alt={"owner"}
            className="profile-card-image"
          />

          <Button
            icon="fa-solid fa-plus"
            prompt={""}
            onclick={() => {
              console.log("click");
            }}
          />
        </div>
        <div>
          <div>
            <i className="fa-solid fa-comment"></i>
            {commets}
          </div>
          <div>
            <i className="fa-solid fa-paperclip"></i>
            {attachments}
          </div>
        </div>
      </div>
    </div>
  );
}

export default CardItem;
