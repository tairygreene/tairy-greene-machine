package com.techelevator.studentprofile.model;

import java.util.List;

public interface EmployerDAO {

	void createEmployerRegistration(String username, String password, String code);

	boolean checkIfEmployerAlreadyExist(String userName);

	List<Student> submitStudentSearch(String technologiesUsed, int cohortNumber, String academicBackground,
			boolean industryExperience);

}
