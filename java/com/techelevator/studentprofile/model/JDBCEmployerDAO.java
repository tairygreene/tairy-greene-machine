package com.techelevator.studentprofile.model;


import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.security.PasswordHasher;

@Component
@Transactional
public class JDBCEmployerDAO implements EmployerDAO{

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;

	@Autowired
	public JDBCEmployerDAO(DataSource dataSource, PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
	}
	
	@Override
	public void createEmployerRegistration(String username, String password, String code) {
		String sqlCreateNewEmployerLogin ="UPDATE users "+
					"SET login_name=?, password = ? "+
					"WHERE salt_code = ?";
		jdbcTemplate.update(sqlCreateNewEmployerLogin,username, password, code);
		
		String deleteSaltCode = 	"UPDATE users "+
				"SET salt_code=? "+
				"WHERE salt_code = ?";
	jdbcTemplate.update(deleteSaltCode,null,code);
		
	}
	
	@Override
	public boolean checkIfEmployerAlreadyExist(String userName) {
		String checkIfEmployerAlreadyExist ="SELECT * "+
				"FROM users "+
				"WHERE login_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(checkIfEmployerAlreadyExist, userName);
		boolean IfEmployerAlreadyExist = results.next();
		return IfEmployerAlreadyExist;
	}
	
	@Override
	public List<Student> submitStudentSearch(String technologiesUsed, int cohortNumber,
		String academicBackground, boolean industryExperience) {
		String sqlSubmitStudentSearch = "";
		SqlRowSet results;
		if (cohortNumber == 0) {
			sqlSubmitStudentSearch = "SELECT students.first_name, students.last_name, students.email_id, students.phone_number, students.summary, students.interests, students.student_id "+
									"FROM portfolios "+
									"JOIN students ON portfolios.student_id = students.student_id "+
									"JOIN users ON students.user_id = users.user_id "+
									"WHERE technology_used LIKE ? "+
									"AND academic_background = ? "+
									"AND cohort = 1 OR cohort = 2 OR cohort = 3 OR cohort = 4 "+
									"AND industry_experience = ? "+
									"AND public = ? "+
									"GROUP BY students.first_name, students.last_name, students.email_id, students.phone_number, students.summary, students.interests, students.student_id ";
			results = jdbcTemplate.queryForRowSet(sqlSubmitStudentSearch, "%"+technologiesUsed+"%", academicBackground, industryExperience, true);

		} else {
		
		sqlSubmitStudentSearch = "SELECT students.first_name, students.last_name, students.email_id, students.phone_number, students.summary, students.interests, students.student_id "+
										"FROM portfolios "+
										"JOIN students ON portfolios.student_id = students.student_id "+
										"JOIN users ON students.user_id = users.user_id "+
										"WHERE technology_used LIKE ? "+
										"AND academic_background = ? "+
										"AND cohort = ? "+
										"AND industry_experience = ? "+
										"AND public = ? "+
										"GROUP BY students.first_name, students.last_name, students.email_id, students.phone_number, students.summary, students.interests, students.student_id ";
			results = jdbcTemplate.queryForRowSet(sqlSubmitStudentSearch, "%"+technologiesUsed+"%", academicBackground, cohortNumber, industryExperience, true);
		}
		
		Student student = new Student();
		List<Student> students = new ArrayList<Student>();
		
		while (results.next()) {
			student = mapPortfolioToRow(results);
			students.add(student);
		}
		return students;
	}
	
	private Student mapPortfolioToRow(SqlRowSet results) {
		Student student = new Student();
		student.setFirstName(results.getString("first_name"));
		student.setLastName(results.getString("last_name"));
		student.setEmailId(results.getString("email_id"));
		student.setPhoneNumber(results.getString("phone_number"));
		student.setSummary(results.getString("summary"));
		student.setInterests(results.getString("interests"));
		student.setStudentId(results.getInt("student_id"));

		return student;
	}
		
		
	}
	
	

