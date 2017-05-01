package com.techelevator.studentprofile.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.studentprofile.model.AdminDAO;
import com.techelevator.studentprofile.model.EmployerDAO;
import com.techelevator.studentprofile.model.PortfolioDAO;
import com.techelevator.studentprofile.model.Student;
import com.techelevator.studentprofile.model.StudentDAO;
import com.techelevator.studentprofile.model.UsersDAO;



@Controller
@SessionAttributes({"currentUser"})
@Transactional
public class HomeController {
	
	
	private UsersDAO usersDAO;
	private AdminDAO adminDAO;
	private StudentDAO studentDAO;
	private EmployerDAO employerDAO;
	private PortfolioDAO portfolioDAO;

	@Autowired
	public HomeController(UsersDAO usersDAO, EmployerDAO employerDAO, StudentDAO studentDAO, AdminDAO adminDAO, PortfolioDAO portfolioDAO) {
		this.usersDAO = usersDAO;
		this.adminDAO = adminDAO;
		this.studentDAO = studentDAO;
		this.employerDAO = employerDAO;
		this.portfolioDAO = portfolioDAO;
	}

	@RequestMapping("/")
	public String showHomePage() {
		return "home";
	}
	
	@RequestMapping(path = "/currentCohort", method = RequestMethod.GET)
	public String showCurrentCohortPage(ModelMap model) {
		String currentUser = (String) model.get("currentUser");
		int roleId = usersDAO.searchForRoleIdByUserName(currentUser);
		
		if (roleId == 1 || roleId ==2 || roleId ==3) {
			List<Student> studentList = studentDAO.getStudentListForLoggedInUsers();
			
			model.put("studentList", studentList);
			return "currentCohort";
		} else {
			List<Student> studentList = studentDAO.getStudentListAnonymous();
			model.put("studentList", studentList);
			return "currentCohort";
		}
		
		
	}
	
}
