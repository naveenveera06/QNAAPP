/**
 * 
 */
package com.stackroute.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author 426141
 *
 */
@Entity
@Table(name="comment")
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="comment_id", nullable=false,updatable=false)
	private int commentId;
	
	@Column(name="query_id")
	private int queryId;
	
	@Column(name="comments")
	private String comment;
	
	@Column(name="row_creat_dt")
	private LocalDate rowCreatDt;
	
	@Transient	
	private String rowCreatDtComment;
	
	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDate getRowCreatDt() {
		return rowCreatDt;
	}

	public void setRowCreatDt(LocalDate rowCreatDt) {
		this.rowCreatDt = rowCreatDt;
	}

	public String getRowCreatDtComment() {
		return rowCreatDtComment;
	}

	public void setRowCreatDtComment(String rowCreatDtComment) {
		this.rowCreatDtComment = rowCreatDtComment;
	}

	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", queryId=" + queryId + ", comment=" + comment + ", rowCreatDt="
				+ rowCreatDt + "]";
	}

}
