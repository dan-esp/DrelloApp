import { useState } from "react";

function useModal(initialState) {
  const [isModalOpen, setShowModal] = useState(initialState);
  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handleOpenModal = () => {
    setShowModal(true);
  };

  return [isModalOpen, handleOpenModal, handleCloseModal];
}

export default useModal;
