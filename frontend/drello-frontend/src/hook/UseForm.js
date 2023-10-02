import { useState } from "react";

function UseForm(initialForm = {}) {
  const [formState, setFormsState] = useState(initialForm);

  const onInputChange = ({ target }) => {
    const { name, value } = target;
    setFormsState({
      ...formState,
      [name]: value,
    });
  };

  const onResetForm = () => {
    setFormsState(initialForm);
  };

  return {
    ...formState,
    formState,
    onInputChange,
    onResetForm,
  };
}

export default UseForm;
