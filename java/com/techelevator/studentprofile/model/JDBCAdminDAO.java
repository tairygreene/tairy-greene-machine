package com.techelevator.studentprofile.model;


import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techelevator.security.CodeGenerator;
import com.techelevator.security.PasswordHasher;

@Component
@Transactional
public class JDBCAdminDAO implements AdminDAO{

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;
	private UsersDAO usersDAO;
	private StudentDAO studentDAO;
	private EmployerDAO employerDAO;
	private PortfolioDAO portfolioDAO;
	
	@Autowired
	public JDBCAdminDAO(DataSource dataSource, PasswordHasher passwordHasher, UsersDAO usersDAO, StudentDAO studentDAO, EmployerDAO employerDAO,
			PortfolioDAO portfolioDAO) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
		this.usersDAO = usersDAO;
		this.studentDAO = studentDAO;
		this.employerDAO = employerDAO;
		this.portfolioDAO = portfolioDAO;
	}

	@Override
	public String createStudentLink(String firstName, String lastName, String email, int cohortNumber) {
		String studentURL = "http://localhost:8080/capstone/studentRegistration/";
		String code = CodeGenerator.getCode();
		String studentLink = studentURL.concat(code);
		
		String addStudentUser = 	"insert into users (email_id, role_id, salt_code, cohort) VALUES (?, ?, ?, ?) "
				+ "RETURNING user_id";
		Integer id = jdbcTemplate.queryForObject(addStudentUser, Integer.class, email, 1, code, cohortNumber);
		
		String addStudentName = 	"insert into students (first_name, last_name, user_id) VALUES (?, ?, ?) " 
				+ "RETURNING student_id";
		Integer studentId = jdbcTemplate.queryForObject(addStudentName, Integer.class, firstName, lastName, id);
		
		String SqlAddStudentId = "insert into portfolios (student_id) VALUES (?)";
					jdbcTemplate.update(SqlAddStudentId, studentId);
				return studentLink;
	}
	
	@Override
	public String createEmployerLink(String employerName,String email) {
		String employerURL = "http://localhost:8080/capstone/employerRegistration/";
		String code = CodeGenerator.getCode();
		String employerLink = employerURL.concat(code);
		
		String addEmployerUser = 	"insert into users (email_id, role_id, salt_code) VALUES (?, ?, ?) "
				+ "RETURNING user_id";
		Integer id = jdbcTemplate.queryForObject(addEmployerUser, Integer.class, email, 3, code);
		
		String addEmployerName = 	"insert into employers (name, user_id) VALUES (?, ?)";
		jdbcTemplate.update(addEmployerName, employerName, id);

		return employerLink;
	}
	
	@Override
	public String createAdminLink(String firstName, String lastName, String email) {
		String adminURL = "http://localhost:8080/capstone/adminRegistration/";
		String code = CodeGenerator.getCode();
		String adminLink = adminURL.concat(code);
		
		String addAdminUser = 	"insert into users (email_id, role_id, salt_code) VALUES (?, ?, ?) "
				+ "RETURNING user_id";
		Integer id = jdbcTemplate.queryForObject(addAdminUser, Integer.class, email, 2, code);
		
		String addAdminName = 	"insert into admins (first_name, last_name, user_id) VALUES (?, ?, ?)";
		jdbcTemplate.update(addAdminName, firstName, lastName, id);

		return adminLink;
	}

	@Override
	public void createAdminRegistration(String username, String password, String code) {
		String SqlCreateAdminRegistration ="UPDATE users "+
					"SET login_name=?, password = ? "+
					"WHERE salt_code = ?";
		jdbcTemplate.update(SqlCreateAdminRegistration,username, password, code);
		
		String deleteSaltCode = 	"UPDATE users "+
				"SET salt_code=? "+
				"WHERE salt_code = ?";
	jdbcTemplate.update(deleteSaltCode,null,code);
		
	}
	
	@Override
	public boolean checkIfAdminNameAlreadyExists(String userName) {
		String checkIfAdminNameAlreadyExists ="SELECT * "+
				"FROM users "+
				"WHERE login_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(checkIfAdminNameAlreadyExists, userName);
		boolean IfAdminAlreadyExists = results.next();
		return IfAdminAlreadyExists;
		
	}
	
	@Override
	public Integer getRoleIDOfCurrentUser(String userName) {
		Integer currentUserRoleID = 0;
		String checkIfCurrentUserIsAdmin ="SELECT role_id "+
				"FROM users "+
				"WHERE login_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(checkIfCurrentUserIsAdmin, userName);
		
		if (results.next()) {
		 currentUserRoleID = results.getInt("role_id");
		}
	 return currentUserRoleID;
	}
	
}
