/**
 * 
 */
package com.stackroute.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * @author 426141
 *
 */
@Entity
@Table(name="topics")
public class Topic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="topic_id")
	private int topicId;
	
	@NotNull
	@Column(name="topic")
	private String topic;
	
	@NotNull
	@Column(name="description")
	private String description;
	
	/*@OneToMany(mappedBy="topicId")
	private Set<Queries> topicReference;
*/
	public int getTopicId() {
		return topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/*public Set<Queries> getTopicReference() {
		return topicReference;
	}

	public void setTopicReference(Set<Queries> topicReference) {
		this.topicReference = topicReference;
	}*/

	@Override
	public String toString() {
		return "Topic [topicId=" + topicId + ", topic=" + topic + ", description=" + description + "]";
	}


	
}
