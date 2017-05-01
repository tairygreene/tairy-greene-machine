package com.techelevator.studentprofile.model;

public interface AdminDAO {

	
	public String createStudentLink(String firstName, String lastName, String email, int cohortNumber);
	String createEmployerLink(String employerName, String email);
	String createAdminLink(String firstName, String lastName, String email);
	void createAdminRegistration(String username, String password, String code);
	boolean checkIfAdminNameAlreadyExists(String userName);
	Integer getRoleIDOfCurrentUser(String userName);
}
