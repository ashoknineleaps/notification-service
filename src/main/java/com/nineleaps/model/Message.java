package com.nineleaps.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Table(value = "messages_by_id")
public class Message implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6709559705559089909L;

	@PrimaryKey
	@CassandraType(type = DataType.Name.UUID)
	private UUID id = UUID.randomUUID();
	
	@NotNull(message = "Message Title must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	@Column(value = "title")
	private String title;
	
	@NotNull(message = "Message Text must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	@Column(value = "text")
	private String text;
	
	@NotNull(message = "Message URL must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	@Column(value = "url")
	private String url;
	
	@NotNull(message = "Message Read must not be null")
	@CassandraType(type = DataType.Name.BOOLEAN)
	@Column(value = "read")
	private boolean read;
	
	@NotNull(message = "Message Time must not be null")
	@CassandraType(type = DataType.Name.TIMESTAMP)
	@Column(value = "time")
	@JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime time;

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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}


	public LocalDateTime getTime() {
		return time;
	}

	public void setTime(LocalDateTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", title=" + title + ", text=" + text + ", url=" + url + ", read=" + read
				+ ", time=" + time + "]";
	}
	
}
