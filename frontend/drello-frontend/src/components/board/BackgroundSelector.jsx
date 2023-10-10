import Button from "../Button";
import selector from "./BackgroundSelector.module.css";
import { useEffect, useState } from "react";
import styleButton from "../../components/style/Button.module.css";
import { uploadFile } from "../../service/FileService";
const backgrounds = [
  {
    id: "YQ4vknTXgfI",
    url: "https://images.unsplash.com/photo-1679678691006-0ad24fecb769?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0ODU1MjV8MXwxfHNlYXJjaHwxfHxuYXR1cmV8ZW58MHx8fHwxNjkxMzc4OTU0fDA&ixlib=rb-4.0.3&q=85",
  },
  {
    id: "78A265wPiO4",
    url: "https://images.unsplash.com/photo-1469474968028-56623f02e42e?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0ODU1MjV8MHwxfHNlYXJjaHwyfHxuYXR1cmV8ZW58MHx8fHwxNjkxMzc4OTU0fDA&ixlib=rb-4.0.3&q=85",
  },
  {
    id: "_RBcxo9AU-U",
    url: "https://images.unsplash.com/photo-1472214103451-9374bd1c798e?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0ODU1MjV8MHwxfHNlYXJjaHwzfHxuYXR1cmV8ZW58MHx8fHwxNjkxMzc4OTU0fDA&ixlib=rb-4.0.3&q=85",
  },
  {
    id: "01_igFr7hd4",
    url: "https://images.unsplash.com/photo-1501854140801-50d01698950b?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0ODU1MjV8MHwxfHNlYXJjaHw0fHxuYXR1cmV8ZW58MHx8fHwxNjkxMzc4OTU0fDA&ixlib=rb-4.0.3&q=85",
  },
  {
    id: "ndN00KmbJ1c",
    url: "https://images.unsplash.com/photo-1426604966848-d7adac402bff?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0ODU1MjV8MHwxfHNlYXJjaHw1fHxuYXR1cmV8ZW58MHx8fHwxNjkxMzc4OTU0fDA&ixlib=rb-4.0.3&q=85",
  },
  {
    id: "cssvEZacHvQ",
    url: "https://images.unsplash.com/photo-1433086966358-54859d0ed716?crop=entropy&cs=srgb&fm=jpg&ixid=M3w0ODU1MjV8MHwxfHNlYXJjaHw2fHxuYXR1cmV8ZW58MHx8fHwxNjkxMzc4OTU0fDA&ixlib=rb-4.0.3&q=85",
  },
];

function BackgroundSelector(props) {
  const { onclick, setBackground } = props;

  const handleClickImage = (e) => {
    const backgroundId = e.target.getAttribute("data-background-id");
    const obj = backgrounds.find((element) => element.id === backgroundId);
    setBackground(obj.url);
  };
  const uploadImage = async (e) => {
    try {
      let loadResult = await uploadFile(e.target.files[0]);
      console.log(loadResult);
    } catch (error) {
      console.log(error);
    }
  };

  const handleClickColor = (e) => {
    const backgroundId = e.target.getAttribute("data-background-id");
    setBackground(backgroundId);
  };
  return (
    <div className={selector.selectorContainer}>
      <p className={selector.title}>Background Photos</p>
      <p>Photos</p>
      <div className={selector.gridContainer}>
        {backgrounds.map((el) => (
          <div
            key={el.id}
            data-background-id={el.id}
            className={selector.item}
            onClick={handleClickImage}
            style={{
              backgroundImage: `url(${el.url})`,
              backgroundSize: "cover",
            }}
          ></div>
        ))}
      </div>
      <p>Color</p>
      <div className={selector.gridContainer}>
        <div
          onClick={handleClickColor}
          data-background-id="blue"
          className={`${selector.item} ${selector.itemColor1}`}
        ></div>
        <div
          onClick={handleClickColor}
          data-background-id="skyblue"
          className={`${selector.item} ${selector.itemColor2}`}
        ></div>
        <div
          onClick={handleClickColor}
          data-background-id="green"
          className={`${selector.item} ${selector.itemColor3}`}
        ></div>
        <div
          onClick={handleClickColor}
          data-background-id="greenyellow"
          className={`${selector.item} ${selector.itemColor4}`}
        ></div>
        <div
          onClick={handleClickColor}
          data-background-id="palevioletred"
          className={`${selector.item} ${selector.itemColor5}`}
        ></div>
        <div
          onClick={handleClickColor}
          data-background-id="black"
          className={`${selector.item} ${selector.itemColor6}`}
        ></div>
      </div>
      <p>custom</p>
      <div className={selector.upload_image}>
        <label htmlFor="file-upload" className={styleButton.first_btn}>
          <i className="fa fa-cloud-upload"></i> Custom Upload
        </label>
        <input type="file" id="file-upload" onChange={uploadImage} />
      </div>
      <div className={selector.go_back} onClick={onclick}>
        <i className="fa-solid fa-arrow-left"></i>
      </div>
    </div>
  );
}

export default BackgroundSelector;
