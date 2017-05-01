package com.techelevator.studentprofile.model;

public class Admin {

	private int adminId; //PK
	private int userId; //FK
	private String firstName;
	private String lastName;
	
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		String fName = firstName.substring(0, 1).toUpperCase();
		fName = fName.concat(firstName.substring(1));
		this.firstName = fName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		String surName = lastName.substring(0, 1).toUpperCase();
		surName = surName.concat(lastName.substring(1));
		this.lastName = surName;
	}
	
	
}
