import { storage } from "../firebase/config";
import { ref, uploadBytes, getDownloadURL } from "firebase/storage";

export const uploadFile = async (file) => {
  const storageRef = ref(storage, "some-child");
  await uploadBytes(storageRef, file);
  const url = await getDownloadURL(storageRef, "some-child");
  return url;
};
