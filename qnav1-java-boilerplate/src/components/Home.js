import { NavLink } from "react-router-dom";
import React, { Component } from "react";
import PropTypes from "prop-types";

class Home extends Component {
  constructor(props) {
    super(props);
    this.state = {
      topics: []
    }
  }

  componentDidMount() {

    const API = "http://localhost:8080";
    const DEFAULT_QUERY = "/getTopics";

    fetch(API + DEFAULT_QUERY)
      .then(response => {
        if (response.status == 204) {
          toastr.info("No Topics available");
          return [];
        }
        return response.json();
      }).then(result => {
        this.setState({
          topics: result
        });
        console.log(this.state.topics);
      })
      .catch(error => console.log(error));
  }

  render() {
    return (
      <div className="container-fluid mt-0">

        {this.state.topics.map(topic => (
          <div key={topic.topicId}>
            <div className="card mb-3">
              <div className="card-body">
                <h5 className="card-title">{topic.topic}</h5>
                <p className="card-text">{topic.description}</p>
                <NavLink
                  to={`/topic-details/${topic.topicId}`}

                  className="btn btn-sm btn-primary mb-2 float-right view-topic-details"
                >
                  View details
                </NavLink>
              </div>
            </div>
          </div>
        ))}
      </div>
    );
  }
};

Home.propTypes = {
  topics: PropTypes.array,
};

export default Home;
