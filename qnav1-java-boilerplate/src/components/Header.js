import React from "react";
import logo from "../car_logo.svg";

const Header = () => {
  return (
    <div className="container-fluid">
      <nav className="navbar navbar-inverse">
        <a className="navbar-brand" href="#">
          {<img
            src={logo}
            className="App-logo d-inline-block align-top h-15"
            alt=""
          />}
          <p className="display-3 text-white" >Car Xplore</p>
        </a>
      </nav>
    </div>
  );
};

export default Header;
