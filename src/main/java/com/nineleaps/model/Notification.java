package com.nineleaps.model;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

@Table(value = "notifications_by_id")
public class Notification implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2042106008261994813L;

	@PrimaryKey
	@CassandraType(type = DataType.Name.UUID)
	private UUID id;
	
	@NotNull(message = "Notification title must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	private String title;
	
	@NotNull(message = "Notification body must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	private String body;
	
	public Notification() {
	}

	public Notification(UUID id, @NotNull(message = "Notification title must not be null") String title,
			@NotNull(message = "Notification body must not be null") String body) {
		this.id = id;
		this.title = title;
		this.body = body;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Notification [id=" + id + ", title=" + title + ", body=" + body + "]";
	}

}
