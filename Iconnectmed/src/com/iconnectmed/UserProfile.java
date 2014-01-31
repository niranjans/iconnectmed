package com.iconnectmed;

import com.parse.ParseUser;

public class UserProfile {
	
	private String firstName;
	private String LastName;
	private String department;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	private String username;

	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	private String email;


	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	public String getFullName(){
		return firstName + " " + LastName;
	}

	
	

}
