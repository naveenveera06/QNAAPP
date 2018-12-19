import React, { Component } from "react";
import { NavLink } from "react-router-dom";
import toastr from "toastr";
import "toastr/build/toastr.min.css";

class Query extends Component {

  _isMounted = false;


  constructor(props) {
    super(props);
    this.state = {
      Queries: [],
      QuestionResult: [],
      value: ''

    };
    this.handleChange = this.handleChange.bind(this);
    this.postQuestion = this.postQuestion.bind(this);
    this.deleteQuestion = this.deleteQuestion.bind(this);
    this.getQuestionList = this.getQuestionList.bind(this);
  }


  handleChange(event) {
    if (this._isMounted) {
      this.setState({ value: event.target.value });

    }
  }


  postQuestion(event) {
    event.preventDefault();

    const API = "http://localhost:8080";
    const ENDPOINT = "/postQuery/";

    fetch(API + ENDPOINT + this.props.match.params.id
      , {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        method: 'POST',
        body: this.state.value
      })
      .then(response => {
        if (response.status == 400) {
          toastr.info("Enter characters between 1 and 200");
        }
        else if (response.status == 417) {
          toastr.error("Error in posting query");
        }

        return response.json();
      })
      .then(

        result => {
          console.log("Result", result)
          toastr.success("Query posted successfully")
          let newQueries = [...this.state.Queries, result];
          this.setState({
            Queries: newQueries,
            value: ''
          });
        }
      ).catch(error => console.log(error));

  }


  getQuestionList() {
    this._isMounted = true;

    const API = "http://localhost:8080";
    const ENDPOINT = "/queryList/";

    fetch(API + ENDPOINT + this.props.match.params.id)
      .then(response => {
        if (response.status == 204) {
          toastr.info("No Queries available");
          return [];
        }
        return response.json();
      }).then(result => {
        this.setState({
          Queries: result
        });
        console.log(this.state.Queries);
      }).catch(error => toastr.error(error),
        error => console.log(error));
  }


  deleteQuestion(deleteId) {

    var myJSON = JSON.stringify(deleteId);

    const API = "http://localhost:8080";
    const ENDPOINT = "/deleteQuery";

    fetch(API + ENDPOINT
      , {
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json'
        },
        method: 'DELETE',
        body: myJSON
      })
      .then(response => {
        if (response.status == 200) {
          toastr.success("Query deleted successfully")
          this.getQuestionList();
        }
        else {
          toastr.error("Query deletion is unsuccessful")
        }
        return response.json();
      }).catch(error => console.log(error));
  }


  componentDidMount() {
    this.getQuestionList()

  }

  componentWillUnmount() {

    this._isMounted = false;
  }

  render() {
    return (
      <div className="container-fluid mt-0">

        {this.state.Queries.map(query => (
          <div key={query.queryId}>
            <div className="card mb-12">
              <div className="card-body">
                <p className="card-text question-card">{query.queries}</p>


                <NavLink
                  to={`/question-details/${query.queryId}`}

                  className="btn btn-sm btn-primary mb-2  float-right view-comments "
                >View Comments
                  </NavLink>
                <button className="btn btn-sm btn-primary mr-2 float-right delete-question " onClick={() => this.deleteQuestion(query.queryId)} >Delete Question</button>
              </div>
              <div >
                <p className="card-text text-muted mr-2 float-right question-date">Posted: {query.rowCreatDt}</p>
              </div>
            </div>
          </div>
        ))}

        <div className="row">
          <div className="col-md-6 col-md-offset-3">
            <div className="panel panel-info">
              <div className="panel-body">
                <form className="form-inline" >

                  <textarea placeholder="Post your questions here!"
                    id="question-field"
                    className="pb-cmnt-textarea"
                    value={this.state.value}
                    onChange={this.handleChange} required></textarea>
                  <button className="btn btn-primary pull-right" id="question-button" type="button" onClick={this.postQuestion} disabled={!this.state.value}>POST</button>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}

export default Query;
