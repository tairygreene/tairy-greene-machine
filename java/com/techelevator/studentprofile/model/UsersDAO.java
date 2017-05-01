package com.techelevator.studentprofile.model;

import java.util.List;


public interface UsersDAO {
	
	public List<Users> searchForLoginnameAndPassword(String loginName, String password);
	public void saveUser(String userName, String password);
	int searchForRoleIdByUserName(String userName);

}
