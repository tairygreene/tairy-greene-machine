package com.techelevator.studentprofile.model;

public class Portfolio {
	
	private int portfolioId;
	private int studentId;
	private String projectName;
	private String projectDescription;
	private String technologyUsed;
	private String sourceCodeLink;
	private boolean isGroupProject;
	
	public int getPortfolioId() {
		return portfolioId;
	}
	public void setPortfolioId(int portfolioId) {
		this.portfolioId = portfolioId;
	}
	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public String getTechnologyUsed() {
		return technologyUsed;
	}
	public void setTechnologyUsed(String technologyUsed) {
		this.technologyUsed = technologyUsed;
	}
	public String getSourceCodeLink() {
		return sourceCodeLink;
	}
	public void setSourceCodeLink(String sourceCodeLink) {
		this.sourceCodeLink = sourceCodeLink;
	}
	public boolean isGroupProject() {
		return isGroupProject;
	}
	public void setGroupProject(boolean isGroupProject) {
		this.isGroupProject = isGroupProject;
	}
	 

}
