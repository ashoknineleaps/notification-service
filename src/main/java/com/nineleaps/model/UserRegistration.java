package com.nineleaps.model;

import java.io.Serializable;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import com.datastax.driver.core.DataType;

@Table(value = "user_registration_by_id")
public class UserRegistration implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2470331332965891068L;

	@PrimaryKey
	@CassandraType(type = DataType.Name.UUID)
	private UUID id;
	
	@NotNull(message = "UserRegistration First Name must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	@Column(value = "first_name")
	private String firstName;
	
	@NotNull(message = "UserRegistration Last Name must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	@Column(value = "last_name")
	private String lastName;
	
	@NotNull(message = "UserRegistration User Name must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	@Column(value = "user_name")
	private String userName;
	
	@NotNull(message = "UserRegistration Password must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	@Column(value = "password")
	private String password;
	
	@NotNull(message = "UserRegistration Email must not be null")
	@CassandraType(type = DataType.Name.TEXT)
	@Column(value = "email")
	private String email;
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserRegistration [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", email=" + email + "]";
	}
}
