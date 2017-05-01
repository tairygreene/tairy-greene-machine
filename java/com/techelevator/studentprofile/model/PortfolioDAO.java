package com.techelevator.studentprofile.model;

import java.util.List;

public interface PortfolioDAO {

	void updateStudentPortfolio(int studentId, String projectName, String projectDescription, String technologyUsed, String sourceCodeLink, boolean isGroupProject);

	int getStudentIdCountByUserName(String currentUser);

	int getStudentIdByUserName(String currentUser);

	List<Portfolio> getPortfolioByStudentId(int studentId);
	


}
