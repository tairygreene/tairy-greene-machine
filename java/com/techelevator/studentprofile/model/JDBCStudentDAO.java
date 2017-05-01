package com.techelevator.studentprofile.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.InvalidResultSetAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.security.PasswordHasher;

@Component
@Transactional
public class JDBCStudentDAO implements StudentDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;

	@Autowired
	public JDBCStudentDAO(DataSource dataSource, PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
	}

	@Override
	public void createStudentRegistration(String username, String password, String code) {
		String sqlCreateNewStudentLogin = "UPDATE users " + "SET login_name=?, password = ? " + "WHERE salt_code = ?";
		jdbcTemplate.update(sqlCreateNewStudentLogin, username, password, code);

		String deleteSaltCode = "UPDATE users " + "SET salt_code=? " + "WHERE salt_code = ?";
		jdbcTemplate.update(deleteSaltCode, null, code);

	}

	@Override
	public boolean checkIfUserNameIsNull(String userName) {
		String checkIfUserNameIsNull = "SELECT * " + "FROM users " + "WHERE login_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(checkIfUserNameIsNull, userName);
		boolean checkNull = results.next();
		return checkNull;

	}

	@Override
	public void updateStudentProfile(String firstName, String lastName, String summary, String PhoneNumber,
			String academicBackground, String softSkills, String fieldsOfInterest, String email, int userId, boolean industryExperience) {
		String SqlUpdateStudentProfile = "UPDATE students " + 
				"SET first_name = ?, last_name = ?, summary = ?, email_id = ?, academic_background = ?, soft_skills = ?, phone_number = ?, interests = ?, industry_experience = ? " +
						"WHERE user_id = ?";

		jdbcTemplate.update(SqlUpdateStudentProfile, firstName, lastName, summary, email, academicBackground,
				softSkills, PhoneNumber, fieldsOfInterest, industryExperience, userId);

	}

	@Override
	public Student getStudentProfileByUserName(String user) {
		Student student = new Student();
		String studentProfile = "SELECT * " + "FROM students " + "JOIN users ON students.user_id = users.user_id "
				+ "WHERE users.login_name = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(studentProfile, user);
		if (results.next()) {
			student = mapStudentToRow(results);
		}

		return student;
	}

	private Student mapStudentToRow(SqlRowSet results) {
		Student student = new Student();
		student.setStudentId(results.getInt("student_id"));
		student.setFirstName(results.getString("first_name"));
		student.setLastName(results.getString("last_name"));
		student.setSummary(results.getString("summary"));
		try {
			student.setEmailId(results.getString("email_id"));
		} catch (InvalidResultSetAccessException ex) {
		}
		student.setAcademicBackground(results.getString("academic_background"));
		student.setSoftSkills(results.getString("soft_skills"));
		student.setPhoneNumber(results.getString("phone_number"));
		student.setInterests(results.getString("interests"));
		student.setPublicProfile(results.getBoolean("public"));

		return student;
	}

	@Override
	public int getUserIdByUserName(String userName) {
		String sqlGetUserIdByUserName = "SELECT user_id " + "FROM users " + "WHERE login_name = ? ";

		int userId = 0;
		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetUserIdByUserName, userName);
		if (result.next()) {
			userId = result.getInt("user_id");
		}

		return userId;
	}

	@Override
	public List<Student> getStudentListAnonymous() {
		Student student = new Student();
		List<Student> studentList = new ArrayList<Student>();
		String SqlGetStudentList= "SELECT first_name, cohort " +
									"FROM students " +
									"JOIN users ON students.user_id = users.user_id " +
									" WHERE cohort = ? " ;
								
		SqlRowSet results = jdbcTemplate.queryForRowSet(SqlGetStudentList, 4);						
		while(results.next()){
			student = mapStudentListToRow(results);
			studentList.add(student);
		}
		
		return studentList;
	}

	private Student mapStudentListToRow(SqlRowSet results) {
		Student student = new Student();
		student.setFirstName(results.getString("first_name"));
		student.setCohort(results.getInt("cohort"));
		return student;
	}
	
	
	@Override
	public List<Student> getStudentListForLoggedInUsers() {
		Student student = new Student();
		List<Student> studentList = new ArrayList<Student>();
		String SqlGetStudentList= "SELECT * " +
									"FROM students " +
									"JOIN users ON students.user_id = users.user_id " +
									" WHERE cohort = ? ";
								
		SqlRowSet results = jdbcTemplate.queryForRowSet(SqlGetStudentList, 4);						
		while(results.next()){
			student = mapStudentRosterToRow(results);
			studentList.add(student);
		}
		
		return studentList;
	}

	private Student mapStudentRosterToRow(SqlRowSet results) {
		Student student = new Student();
		student.setFirstName(results.getString("first_name"));
		student.setLastName(results.getString("last_name"));
		student.setCohort(results.getInt("cohort"));
		student.setEmailId(results.getString("email_id"));
		student.setPhoneNumber(results.getString("phone_number"));
		student.setStudentId(results.getInt("student_id"));
		return student;
	}
	
	@Override
	public void setPublishProfilePreferences(boolean isPublic, int userId){

		String setPublishProfilePreferences = "UPDATE students " +
											"SET public = ? " +
											"WHERE user_id = ? ";
		jdbcTemplate.update(setPublishProfilePreferences, isPublic, userId);
		
	}

	@Override
	public Student getStudentByStudentId(int studentId) {
		Student student = new Student();
		String sqlGetStudentByStudentId = "SELECT * " + "FROM students " + "WHERE student_id = ? ";

		SqlRowSet result = jdbcTemplate.queryForRowSet(sqlGetStudentByStudentId, studentId);
		if (result.next()) {
			student = mapStudentToRow(result);
		}
		return student;
	}
}
