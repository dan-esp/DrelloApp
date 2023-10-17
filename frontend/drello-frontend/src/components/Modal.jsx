import styles from "./style/Modal.module.css";
import ReactDOM from "react-dom";
import Button from "./Button";
const $modal = document.querySelector("#modal");
import { useEffect ,useRef} from "react";
function Modal({
  children,
  isModalOpen,
  onCloseModal,
  hightModal,
  widthModal,
}) {
  const modalRef = useRef();

   useEffect(() => {
     let handler = (e) => {
       if (
         modalRef.current &&
         !modalRef.current.contains(e.target)
       ) {
         onCloseModal();
       }
     };

     document.addEventListener("mousedown", handler);
     return () => {
       document.removeEventListener("mousedown", handler);
     };
   }, []);

  return (
    isModalOpen &&
    ReactDOM.createPortal(
      <div className="modal">
        <div className={styles.modal_font}>
          <div
            className={styles.modal_ctn}
            style={{ height: hightModal, width: widthModal }}
            ref={modalRef}
          >
            <div className={styles.modal_header}>
              <Button
                icon={"fa-solid fa-x"}
                prompt={""}
                onclick={onCloseModal}
              />
            </div>
            <div className={styles.modal_content}>{children}</div>
          </div>
        </div>
      </div>,
      $modal
    )
  );
}

export default Modal;
