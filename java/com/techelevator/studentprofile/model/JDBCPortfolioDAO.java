package com.techelevator.studentprofile.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.security.CodeGenerator;
import com.techelevator.security.PasswordHasher;

@Component
@Transactional

public class JDBCPortfolioDAO implements PortfolioDAO{
	
	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;
	private UsersDAO usersDAO;
	private StudentDAO studentDAO;
	private EmployerDAO employerDAO;
	
	@Autowired
	public JDBCPortfolioDAO(DataSource dataSource, PasswordHasher passwordHasher, UsersDAO usersDAO, StudentDAO studentDAO, EmployerDAO employerDAO) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
		this.usersDAO = usersDAO;
		this.studentDAO = studentDAO;
		this.employerDAO = employerDAO;
	}
	
	
	@Override
	public int getStudentIdCountByUserName(String currentUser) {
		int studentIdCount = 0;
		String countStudentID = "select count(students.student_id) " +  
				"from portfolios " + 
				"JOIN students ON portfolios.student_id = students.student_id " + 
				" JOIN users ON students.user_id = users.user_id " +
				" WHERE users.login_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(countStudentID, currentUser);

		if (results.next()) {
			studentIdCount = results.getInt("student_id");
		}
		return studentIdCount;
	}
	
	@Override
	public void updateStudentPortfolio(int studentId, String projectName, String projectDescription, String technologyUsed, String sourceCodeLink, boolean isGroupProject) {
			
		String updateStudentPortfolio = "INSERT INTO portfolios (student_id, project_name, project_description, technology_used, source_code_link, Is_group_project) " +
										"VALUES (?, ?, ?, ?, ?, ?)";
		
		jdbcTemplate.update(updateStudentPortfolio,studentId, projectName, projectDescription, technologyUsed, sourceCodeLink, isGroupProject);

		//		
//		String updateStudentPortfolio = "UPDATE portfolios " +
//									"SET project_name = ?, project_description = ?, " + 
//									"technology_used = ?, source_code_link = ?, is_group_project = ? " + 
//									"FROM students, users " +
//									"WHERE portfolios.student_id = students.student_id AND  " +
//									"students.user_id = users.user_id AND " +
//									"users.login_name = ? ";
		
							
	}
	
	@Override
	public int getStudentIdByUserName(String currentUser){
		int studentId = 0;
		String getStudentIdByUserName = "SELECT student_id " +
										" FROM students " +
										" JOIN users ON users.user_id = students.user_id " +
										" WHERE login_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getStudentIdByUserName, currentUser);

		if (results.next()) {
			studentId = results.getInt("student_id");
		}
		return studentId;
	}
	
	@Override
	public List<Portfolio> getPortfolioByStudentId(int studentId) {
		Portfolio portfolio = new Portfolio();
		List <Portfolio> portfolioList =  new ArrayList<>();
		String getPortfolioByUserId = "SELECT * " +
									"FROM portfolios " +
									"WHERE student_id = ? ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(getPortfolioByUserId, studentId);
		
		while (results.next()) {
			portfolio = mapPortfolioToRow(results);
			portfolioList.add(portfolio);
		}
		return portfolioList;
	}


	private Portfolio mapPortfolioToRow(SqlRowSet results) {
		Portfolio portfolio = new Portfolio();
		portfolio.setStudentId(results.getInt("student_id"));
		portfolio.setProjectName(results.getString("project_name"));
		portfolio.setProjectDescription(results.getString("project_description"));
		portfolio.setTechnologyUsed(results.getString("technology_used"));
		portfolio.setSourceCodeLink(results.getString("source_code_link"));
		portfolio.setGroupProject(results.getBoolean("Is_group_project"));
		return portfolio;
	}

}
