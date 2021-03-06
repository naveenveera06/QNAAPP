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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonFormat;


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
	
	
	@ManyToOne
	@OnDelete(action=OnDeleteAction.CASCADE)
	@NotNull
	@JoinColumn(name="query_id")
	private Queries queryId;
	
	@Size(min = 1, max = 200)
	@NotNull
	@Column(name="comments")
	private String comment;
	
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd yyyy")
	@Column(name="row_creat_dt")
	private LocalDate rowCreatDt;

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public Queries getQueryId() {
		return queryId;
	}

	public void setQueryId(Queries queryId) {
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


	@Override
	public String toString() {
		return "Comment [commentId=" + commentId + ", queryId=" + queryId + ", comment=" + comment + ", rowCreatDt="
				+ rowCreatDt + "]";
	}

}
