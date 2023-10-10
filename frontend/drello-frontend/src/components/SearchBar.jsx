import "./style/SearchBar.css";
function SearchBar() {
  return (
    <div className="searchBar">
        <input placeholder="Keyword..." type="text" />
        <button>Search</button>
    </div>
  )
}

export default SearchBar