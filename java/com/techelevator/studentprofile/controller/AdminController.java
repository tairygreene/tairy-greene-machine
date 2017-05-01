package com.techelevator.studentprofile.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.techelevator.studentprofile.model.AdminDAO;
import com.techelevator.studentprofile.model.EmployerDAO;
import com.techelevator.studentprofile.model.StudentDAO;
import com.techelevator.studentprofile.model.Users;
import com.techelevator.studentprofile.model.UsersDAO;

@Controller
@SessionAttributes({"currentUser"})
@Transactional
public class AdminController {

	
	private UsersDAO usersDAO;
	private AdminDAO adminDAO;
	private StudentDAO studentDAO;
	private EmployerDAO employerDAO;
	
	@Autowired
	public AdminController(UsersDAO usersDAO, EmployerDAO employerDAO, StudentDAO studentDAO, AdminDAO adminDAO) {
		this.usersDAO = usersDAO;
		this.adminDAO = adminDAO;
		this.studentDAO = studentDAO;
		this.employerDAO = employerDAO;
	}
	
	@RequestMapping(path="/createStudent", method=RequestMethod.GET)
	public String displayNewUserForm(ModelMap model) {
		String currentUser = (String) model.get("currentUser");
		Integer currentUserRoleId = adminDAO.getRoleIDOfCurrentUser(currentUser);
		
		if (currentUserRoleId == 2) {
			return "createStudent";
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path = "/administratorDashboard", method = RequestMethod.GET)
	public String displayAdminDashboard() {
		return "administratorDashboard";
	}
	
	@RequestMapping(path="/createStudent", method=RequestMethod.POST)
	public String createStudentLink(ModelMap model,
									@RequestParam String firstName, 
									@RequestParam String lastName,
									@RequestParam String email,
									@RequestParam int cohortNumber) {	
										
									
		String studentLink = adminDAO.createStudentLink(firstName, lastName, email, cohortNumber);
		firstName = changeNameCasing(firstName);
		lastName = changeNameCasing(lastName);
		model.put("studentLink", studentLink);
		model.put("firstName", firstName);
		model.put("lastName", lastName);
		
		return "studentLink";
	}
	@RequestMapping(path="/createEmployer", method=RequestMethod.GET)
	public String displayNewEmployerForm(ModelMap model) {

		String currentUser = (String) model.get("currentUser");
		Integer currentUserRoleId = adminDAO.getRoleIDOfCurrentUser(currentUser);
		
		if (currentUserRoleId == 2) {
			return "createEmployer";
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path="/createAdmin", method=RequestMethod.GET)
	public String displayNewAdminForm(ModelMap model) {
		String currentUser = (String) model.get("currentUser");
		Integer currentUserRoleId = adminDAO.getRoleIDOfCurrentUser(currentUser);
		
		if (currentUserRoleId == 2) {
			return "createAdmin";
		} else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path="/createAdmin", method=RequestMethod.POST)
	public String createAdminLink(ModelMap model,
									@RequestParam String firstName, 
									@RequestParam String lastName,
									@RequestParam String email) {
		
		String adminLink = adminDAO.createAdminLink(firstName, lastName, email);
		firstName = changeNameCasing(firstName);
		lastName = changeNameCasing(lastName);
		
		model.put("adminLink", adminLink);
		model.put("firstName", firstName);
		model.put("lastName", lastName);
		
		return "adminLink";
	}
	@RequestMapping(path="/createEmployer", method=RequestMethod.POST)
	public String createEmployerLink(ModelMap model,
									@RequestParam String employerName, 
									@RequestParam String email) {
		
		String employerLink = adminDAO.createEmployerLink(employerName, email);
		String companyName = changeNameCasing(employerName);
		
		model.put("employerLink", employerLink);
		model.put("employerName", companyName);
		
		return "employerLink";
	}
	
	@RequestMapping(path = "/adminRegistration/{code}", method = RequestMethod.GET)
	public String displayAdminRegistration(@PathVariable String code, ModelMap model) {
		model.put("code", code);
		return "adminRegistration";
	}
	
	@RequestMapping(path = "/adminRegistration", method = RequestMethod.POST)
	public String displayAdminRegistration(@RequestParam String userName,
											@RequestParam String password,
											@RequestParam String code, ModelMap model) {
		
		boolean IfAdminAlreadyExists = adminDAO.checkIfAdminNameAlreadyExists(userName);

		if (IfAdminAlreadyExists == false) {
			model.put("code", code);
			model.put("userName", userName);
			model.put("password", password);
			adminDAO.createAdminRegistration(userName.toLowerCase(), password, code);
			model.put("currentUser", userName);
			return "redirect:/administratorDashboard";
		} else {
			return "home";

		}
	}
	
	private String changeNameCasing(String name) {
		String nameOutput = name.substring(0, 1).toUpperCase();
		nameOutput = nameOutput.concat(name.substring(1));
		return nameOutput;
	}
	
}
