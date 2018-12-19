import React, { Component } from "react";
import toastr from "toastr";
import "toastr/build/toastr.min.css";

class Comment extends Component {
    _isMounted = false;
    constructor(props) {
        super(props);
        this.state = {
            Comments: [],
            value: '',
            deleteStatus: ''
        }
        this.handleChange = this.handleChange.bind(this);
        this.postComment = this.postComment.bind(this);
        this.deleteComment = this.deleteComment.bind(this);
    }


    handleChange(event) {
      
        if (this._isMounted) {
            this.setState({ value: event.target.value });
            console.log(this.state.value);
        }
    }

    postComment(event) {

        event.preventDefault();

        const API = "http://localhost:8080";
        const ENDPOINT = "/postComment/";

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
                    toastr.info("Enter characters  between 1 and 200");
                }
                else if (response.status == 417) {
                    toastr.error("Error in posting comment");
                }
                return response.json();
            })
            .then(

                result => {
                    toastr.success("Comment posted successfully")
                    let newComments = [...this.state.Comments, result];
                    this.setState({
                        Comments: newComments,
                        value: ''
                    });
                }
            ).catch(error => console.log(error));

    }


    deleteComment(deleteId) {

        var myJSON = JSON.stringify(deleteId);

        const API = "http://localhost:8080";
        const ENDPOINT = "/deleteComment";

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
                    toastr.success("Comment deleted successfully")
                    this.getCommentList();
                }
                else {
                    toastr.error("Comment deletion is unsuccessful")
                }
                return response.json();
            }).catch(error => console.log(error));
    }

    getCommentList() {
        this._isMounted = true;

        const API = "http://localhost:8080";
        const ENDPOINT = "/commentList/";

        fetch(API + ENDPOINT + this.props.match.params.id)
            .then(response => {
                if (response.status == 204) {
                    toastr.info("No Comments available");
                    return [];
                }
                return response.json();
            }).then(result => {
                this.setState({
                    Comments: result
                });
                
            })
            .catch(error => console.log(error));
    }

    componentDidMount() {
        this.getCommentList()
    }

    componentWillUnmount() {

        this._isMounted = false;
    }

    render() {
        return (
            <div className="container-fluid mt-0">

                {this.state.Comments.map(comment => (
                    <div key={comment.commentId}>
                        <div className="card mb-3">
                            <div className="card-body comment-card">
                                <p className="card-text comment-description">{comment.comment}</p>
                                <button className="btn btn-sm btn-primary mr-2 float-right delete-comment " onClick={() => this.deleteComment(comment.commentId)}>Delete Comment</button>
                            </div>
                            <div >
                                <p className="card-text text-muted mr-2 float-right comment-date">Posted: {comment.rowCreatDt}</p>
                            </div>
                        </div>
                    </div>
                ))}


                <div className="row">
                    <div className="col-md-6 col-md-offset-3">
                        <div className="panel panel-info">
                            <div className="panel-body">
                                <form className="form-inline" >
                                    <textarea placeholder="Write your comments here!"
                                        id="comment-field"
                                        className="pb-cmnt-textarea"
                                        value={this.state.value}
                                        onChange={this.handleChange}></textarea>

                                    <button className="btn btn-primary pull-right" id="comment-button" type="button" onClick={this.postComment} disabled={!this.state.value}> Comment</button>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}


export default Comment;