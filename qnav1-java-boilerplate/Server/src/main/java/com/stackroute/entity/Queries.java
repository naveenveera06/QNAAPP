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
@Table(name = "query")
public class Queries {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "query_id", nullable=false,updatable=false)
	private int queryId;

	@Column(name = "topic_id")
	private int topicId;

	@Column(name = "queries")
	private String queries;

	@Column(name = "row_creat_dt")
	private LocalDate  rowCreatDt;

	@Transient
	private String rowCreatDtQuery;

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

	public String getRowCreatDtQuery() {
		return rowCreatDtQuery;
	}

	public void setRowCreatDtQuery(String rowCreatDtQuery) {
		this.rowCreatDtQuery = rowCreatDtQuery;
	}

	@Override
	public String toString() {
		return "Queries [queryId=" + queryId + ", topicId=" + topicId + ", queries=" + queries + ", rowCreatDt="
				+ rowCreatDt + ", rowCreatDtQuery=" + rowCreatDtQuery + "]";
	}

	
}
