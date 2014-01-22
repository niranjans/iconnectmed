package com.iconnectmed;

import com.parse.ParseUser;

public class UserProfile {
	
	private String firstName;
	private String LastName;
	private String department;
	private ParseUser user;

	
	public ParseUser getUser() {
		return user;
	}
	public void setUser(ParseUser user) {
		this.user = user;
	}
	
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
