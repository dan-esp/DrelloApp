import { initializeApp } from "firebase/app";
import { getStorage } from "firebase/storage";
const firebaseConfig = {
  apiKey: "AIzaSyCu5uKyztuoZikI03p-xAfsa1A6Ijf_3Fg",
  authDomain: "drello-9dd0f.firebaseapp.com",
  projectId: "drello-9dd0f",
  storageBucket: "drello-9dd0f.appspot.com",
  messagingSenderId: "621121155954",
  appId: "1:621121155954:web:44d55b4448e6e9e839a3eb",
};

const app = initializeApp(firebaseConfig);
export const storage = getStorage(app);

