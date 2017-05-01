package com.techelevator.studentprofile.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.studentprofile.model.Users;
import com.techelevator.studentprofile.model.UsersDAO;

@Transactional
@Controller
@SessionAttributes("currentUser")
public class AuthenticationController {

	private UsersDAO usersDAO;

	@Autowired
	public AuthenticationController(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String displayLoginForm() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(ModelMap model, @RequestParam String userName, @RequestParam String password, HttpSession session) {
		userName = userName.toLowerCase();
		List<Users> userValidationList = usersDAO.searchForLoginnameAndPassword(userName, password);

		if (userValidationList.isEmpty()) {
			session.invalidate();
			return "redirect:/login";
		} else if (userValidationList.get(0).getRoleId() == 1) {
			session.invalidate();
			model.put("currentUser", userName);
			return "redirect:/studentDashboard";
		} else if (userValidationList.get(0).getRoleId() == 2) {
			session.invalidate();
			model.put("currentUser", userName);
			return "redirect:/administratorDashboard";
		} else if (userValidationList.get(0).getRoleId() == 3) {
			session.invalidate();
			model.put("currentUser", userName);
			return "redirect:/employerDashboard";
		} else {
			session.invalidate();
			return "redirect:/login";
		}
	}
	
	
	@RequestMapping(path = "/dashboard", method = RequestMethod.GET)
	public String login(ModelMap model) {
		String currentUser = (String) model.get("currentUser");
		int roleId = usersDAO.searchForRoleIdByUserName(currentUser);

		if (roleId == 1) {
			return "redirect:/studentDashboard";
		} else if (roleId == 2) {
			return "redirect:/administratorDashboard";
		} else if (roleId == 3) {
			return "redirect:/employerDashboard";
		} else {
			return "redirect:/";
		}
	}

	@RequestMapping(path = "/logout", method = RequestMethod.POST)
	public String logout(Map<String, Object> model, HttpSession session) {
		model.remove("currentUser");
		session.removeAttribute("currentUser");
		return "redirect:/";
	}
}
