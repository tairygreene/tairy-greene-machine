package com.techelevator.studentprofile.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.techelevator.security.PasswordHasher;


@Component
@Transactional
public class JDBCUsersDAO implements UsersDAO{
	
	private JdbcTemplate jdbcTemplate;
	private PasswordHasher passwordHasher;

	@Autowired
	public JDBCUsersDAO(DataSource dataSource, PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.passwordHasher = passwordHasher;
	}
	@Override
	public void saveUser(String userName, String password) {
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		jdbcTemplate.update("INSERT INTO app_user(user_name, password, salt) VALUES (?, ?, ?)", userName, hashedPassword, saltString);
	}

	
	@Override
	public List<Users> searchForLoginnameAndPassword(String loginName, String password) {
		String sqlSearchForLoginnameAndPassword = 	"SELECT * "+
			      									"FROM users "+
			      									"WHERE login_name = ? "+
			      									"AND password = ? ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForLoginnameAndPassword, loginName, password);
		List <Users> userList =  new ArrayList<>();
		if(results.next()) {
		Users users = mapToList(results);
		userList.add(users);
		} return userList;
	}
	
	@Override
	public int searchForRoleIdByUserName(String userName) {
		int roleId = 0;
		String sqlSearchForUserName = 	"SELECT role_id "+
			      						"FROM users "+
			      						"WHERE login_name = ? ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUserName, userName);
		if(results.next()) {
			roleId = results.getInt("role_id");
		} return roleId;
	}

	private Users mapToList(SqlRowSet results) {
		Users users = new Users();
		users.setLoginName(results.getString("login_name"));
		users.setPassword(results.getString("password"));
		users.setUserId(results.getInt("user_id"));
		users.setRoleId(results.getInt("role_id"));
		users.setEmailID(results.getString("email_id"));
		return users;
	}
	
//	@Override
//	public boolean searchForUsernameAndPassword(String userName, String password) {
//		String sqlSearchForUser = "SELECT * "+
//							      "FROM app_user "+
//							      "WHERE UPPER(user_name) = ?";
//		
//		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
//		if(results.next()) {
//			String storedSalt = results.getString("salt");
//			String storedPassword = results.getString("password");
//			String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
//			return storedPassword.equals(hashedPassword);
//		} else {
//			return false;
//		}
//	}
//
//	@Override
//	public void updatePassword(String userName, String password) {
//		byte[] salt = passwordHasher.generateRandomSalt();
//		String hashedPassword = passwordHasher.computeHash(password, salt);
//		String saltString = new String(Base64.encode(salt));
//		jdbcTemplate.update("UPDATE app_user SET password = ?, salt = ? WHERE user_name = ?", hashedPassword, saltString, userName);
//	}



}
