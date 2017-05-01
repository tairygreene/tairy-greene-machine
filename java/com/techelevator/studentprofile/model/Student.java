package com.techelevator.studentprofile.model;

public class Student {

	
	private int studentId; //PK
	private int userId; //FK
	private String firstName;
	private String lastName;
	private String summary;
	private String careerBackground;
	private String academicBackground;
	private String softSkills;
	private String contactPreferences;
	private String interests;
	private String emailId;
	private String phoneNumber;	
	private String technologiesUsed; 
	private int cohort;
	private boolean industryExperience;
	private String projectName;
	private boolean publicProfile;

	
	

	public boolean getPublicProfile() {
		return publicProfile;
	}
	public void setPublicProfile(boolean publicProfile) {
		this.publicProfile = publicProfile;
	}
	public String getTechnologiesUsed() {
		return technologiesUsed;
	}
	public void setTechnologiesUsed(String technologiesUsed) {
		this.technologiesUsed = technologiesUsed;
	}
	public int getCohort() {
		return cohort;
	}
	public void setCohort(int cohort) {
		this.cohort = cohort;
	}
	public boolean isIndustryExperience() {
		return industryExperience;
	}
	public void setIndustryExperience(boolean industryExperience) {
		this.industryExperience = industryExperience;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
		fName = fName.concat(firstName.substring(1).toLowerCase());
		this.firstName = fName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		String surName = lastName.substring(0, 1).toUpperCase();
		surName = surName.concat(lastName.substring(1).toLowerCase());
		this.lastName = surName;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getCareerBackground() {
		return careerBackground;
	}
	public void setCareerBackground(String careerBackground) {
		this.careerBackground = careerBackground;
	}
	public String getAcademicBackground() {
		return academicBackground;
	}
	public void setAcademicBackground(String academicBackground) {
		this.academicBackground = academicBackground;
	}
	public String getSoftSkills() {
		return softSkills;
	}
	public void setSoftSkills(String softSkills) {
		this.softSkills = softSkills;
	}
	public String getContactPreferences() {
		return contactPreferences;
	}
	public void setContactPreferences(String contactPreferences) {
		this.contactPreferences = contactPreferences;
	}
	public String getInterests() {
		return interests;
	}
	public void setInterests(String interests) {
		this.interests = interests;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	
	
}
