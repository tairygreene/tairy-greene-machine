package com.techelevator.studentprofile.model;

import java.util.List;

public interface StudentDAO {

	
	void createStudentRegistration(String username, String password, String code);

	public boolean checkIfUserNameIsNull(String userName);

	Student getStudentProfileByUserName(String user);

	int getUserIdByUserName(String userName);


	void updateStudentProfile(String firstName, String lastName, String summary, String PhoneNumber,
			String academicBackground, String softSkills, String fieldsOfInterest, String email, int userId,
			boolean industryExperience);

	List<Student> getStudentListAnonymous();

	List<Student> getStudentListForLoggedInUsers();

	void setPublishProfilePreferences(boolean isPublic, int userId);

	Student getStudentByStudentId(int studentId);
}
