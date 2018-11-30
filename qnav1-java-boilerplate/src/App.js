import React, { Component } from "react";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Home from "./components/Home";
import Header from "./components/Header";
import Query from "./components/Query";
import Comment from "./components/Comment";
import "./App.css";

class App extends Component {
  render() {
    return (
      <Router>
        <div className="container-fluid mt-3">
          <Header />
          <br />
          <Route exact path="/" component={Home} />
          <Route path="/topic-details/:id" component={Query} />
          <Route path="/question-details/:id" component={Comment} />
        </div>
      </Router>
    );
  }
}

export default App;
