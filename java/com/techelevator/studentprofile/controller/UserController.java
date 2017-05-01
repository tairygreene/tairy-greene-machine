package com.techelevator.studentprofile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.studentprofile.model.UsersDAO;

@Controller
@SessionAttributes({"currentUser"})
@Transactional
public class UserController {

	private UsersDAO usersDAO;
	

	@Autowired
	public UserController(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	@RequestMapping(path="/users/new", method=RequestMethod.GET)
	public String displayNewUserForm() {
		return "newUser";
	}
	
	@RequestMapping(path="/users", method=RequestMethod.POST)
	public String createUser(@RequestParam String userName, @RequestParam String password) {
		usersDAO.saveUser(userName, password);
		return "redirect:/login";
	}
	
//	@RequestMapping(path="/users/{userName}", method=RequestMethod.GET)
//	public String displayDashboard(Map<String, Object> model, @PathVariable String userName) {
//		model.put("conversations", messageDAO.getConversationsForUser(userName));
//		return "userDashboard";
//	}
	
	
	@RequestMapping(path="/users/{userName}/changePassword", method=RequestMethod.GET)
	public String displayChangePasswordForm(Map<String, Object> model, @PathVariable String userName) {
		model.put("currentUser", userName); 
		return "changePassword";
	}
	
//	@RequestMapping(path="/users/{userName}/changePassword", method=RequestMethod.POST)
//	public String changePassword(@PathVariable String userName, @RequestParam String password) {
//		usersDAO.updatePassword(userName, password);
//		return "userDashboard";
//	}
}



