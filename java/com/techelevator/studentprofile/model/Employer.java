package com.techelevator.studentprofile.model;

public class Employer {

	private int employerId; 
	private String name;
	private int userId; 
	
	
	
	public int getEmployerId() {
		return employerId;
	}
	public void setEmployerId(int employerId) {
		this.employerId = employerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		String employerName = name.substring(0, 1).toUpperCase();
		employerName = employerName.concat(name.substring(1));
		this.name = employerName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
	
}
