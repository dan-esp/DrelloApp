import { useRef, useEffect, useState } from "react";
import "./style/SearchBar.css";

function SearchBar() {
  const searchBarRef = useRef();
  const [folded, setFolded] = useState(false);

  useEffect(() => {
    let handler = (e) => {
      if (searchBarRef.current && !searchBarRef.current.contains(e.target)) {
        setFolded(false);
      }
    };

    document.addEventListener("mousedown", handler);
    return () => {
      document.removeEventListener("mousedown", handler);
    };
  }, []);

  return (
    <div
      className={`searchBar ${folded ? "folded" : "unfolded"}`}
      ref={searchBarRef}
    >
      <input
        placeholder="Keyword..."
        type="text"
        onClick={() => setFolded(true)}
      />
      <button>Search</button>
    </div>
  );
}

export default SearchBar;
