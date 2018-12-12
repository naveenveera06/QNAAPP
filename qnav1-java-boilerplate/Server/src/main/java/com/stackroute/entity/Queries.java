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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * @author 426141
 *
 */
@Entity
@Table(name = "query")
public class Queries {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "query_id", nullable=false,updatable=false)
	private int queryId;

	@NotNull
	@Column(name = "topic_id")
	private int topicId;

	@Size(min = 1, max = 200)
	@NotNull
	@Column(name = "queries")
	private String queries;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MMM dd yyyy")
	@Column(name = "row_creat_dt")
	private LocalDate  rowCreatDt;

	public int getQueryId() {
		return queryId;
	}

	public void setQueryId(int queryId) {
		this.queryId = queryId;
	}

	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getQueries() {
		return queries;
	}

	public void setQueries(String queries) {
		this.queries = queries;
	}

	public LocalDate getRowCreatDt() {
		return rowCreatDt;
	}

	public void setRowCreatDt(LocalDate rowCreatDt) {
		this.rowCreatDt = rowCreatDt;
	}


	@Override
	public String toString() {
		return "Queries [queryId=" + queryId + ", topicId=" + topicId + ", queries=" + queries + ", rowCreatDt="
				+ rowCreatDt + "]";
	}


}
