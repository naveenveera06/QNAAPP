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
        console.log(this._isMounted);
        console.log(this.state.value);
        if (this._isMounted) {
            this.setState({ value: event.target.value });
            console.log(this.state.value);
        }
    }

    postComment(event) {

        event.preventDefault();

        var postCommentJSON = { "queryId": this.props.match.params.id, "comment": this.state.value }

        var myCommentJSON = JSON.stringify(postCommentJSON);

        const API = "http://localhost:8080";
        const DEFAULT_QUERY = "/postComment";

        fetch(API + DEFAULT_QUERY
            , {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: myCommentJSON
            })
            .then(response => {
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


        console.log(deleteId);
        const API = "http://localhost:8080";
        const DEFAULT_QUERY = "/deleteComment";

        fetch(API + DEFAULT_QUERY
            , {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: myJSON
            })
            .then(response => {
                return response.json();
            }).then(deleteResult => {
                this.setState({
                    deleteStatus: deleteResult
                });
                if (this.state.deleteStatus = 1) {
                    toastr.success("Comment deleted successfully")
                    console.log(this.state.deleteStatus);
                    this.getCommentList();
                }
                else {
                    toastr.error("Comment deleted is unsuccessful")
                }
            }).catch(error => console.log(error));
    }

    getCommentList() {
        this._isMounted = true;

        const API = "http://localhost:8080";
        const DEFAULT_QUERY = "/commentList";

        fetch(API + DEFAULT_QUERY
            , {
                headers: {
                    'Accept': 'application/json',
                    'Content-Type': 'application/json'
                },
                method: 'POST',
                body: this.props.match.params.id
            })
            .then(response => {
                return response.json();
            }).then(result => {
                this.setState({
                    Comments: result
                });
                console.log(this.state.Comments);
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
                                <p className="card-text text-muted mr-2 float-right comment-date">Posted: {comment.rowCreatDtComment}</p>
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