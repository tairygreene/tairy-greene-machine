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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.studentprofile.model.AdminDAO;
import com.techelevator.studentprofile.model.EmployerDAO;
import com.techelevator.studentprofile.model.Portfolio;
import com.techelevator.studentprofile.model.PortfolioDAO;
import com.techelevator.studentprofile.model.Student;
import com.techelevator.studentprofile.model.StudentDAO;
import com.techelevator.studentprofile.model.UsersDAO;

@Controller
@SessionAttributes({ "currentUser"})
@Transactional

public class StudentController {

	private UsersDAO usersDAO;
	private AdminDAO adminDAO;
	private StudentDAO studentDAO;
	private EmployerDAO employerDAO;
	private PortfolioDAO portfolioDAO;

	@Autowired
	public StudentController(UsersDAO usersDAO, EmployerDAO employerDAO, StudentDAO studentDAO, AdminDAO adminDAO, PortfolioDAO portfolioDAO) {
		this.usersDAO = usersDAO;
		this.adminDAO = adminDAO;
		this.studentDAO = studentDAO;
		this.employerDAO = employerDAO;
		this.portfolioDAO = portfolioDAO;
	}

	@RequestMapping(path = "/studentRegistration/{code}", method = RequestMethod.GET)
	public String displayStudentRegistationForm(@PathVariable String code, ModelMap model) {
		model.put("code", code);
		return "studentRegistration";
	}

	@RequestMapping(path = "/studentDashboard", method = RequestMethod.GET)
	public String displayStudentDashboard() {
		return "studentDashboard";
	}

	@RequestMapping(path = "/studentRegistration", method = RequestMethod.POST)
	public String submitStudentRegistationForm(@RequestParam String userName, @RequestParam String password,
			@RequestParam String code, @RequestParam(value="ErrorMsg", defaultValue="") String errorMsg, ModelMap model) {

		boolean checkNull = studentDAO.checkIfUserNameIsNull(userName);

		if (checkNull == false) {
			model.put("code", code);
			model.put("userName", userName);
			model.put("password", password);
			studentDAO.createStudentRegistration(userName.toLowerCase(), password, code);
			model.put("currentUser", userName);
			return "redirect:/studentDashboard";
		} else {
			errorMsg = "User name taken. Choose another one";
			model.put("errorMsg", errorMsg);
			return "studentRegistration";

		}

	}
	
	@RequestMapping(path = "/updateProfile", method = RequestMethod.GET)
	public String displayUpdateProfileForm() {
		return "updateProfile";
	}

	
	@RequestMapping(path = "/updateProfile", method = RequestMethod.POST)
	public String submitStudentProfileForm(@RequestParam String firstName,
											@RequestParam String lastName,
											@RequestParam String PhoneNumber,
											@RequestParam String email,
											@RequestParam String academicBackground,
											@RequestParam String summary,
											@RequestParam String softSkills,
											@RequestParam String fieldsOfInterest,
											@RequestParam boolean industryExperience,
											ModelMap model) {
		
		String userName = (String) model.get("currentUser");
		int id = studentDAO.getUserIdByUserName(userName);
		studentDAO.updateStudentProfile(firstName, lastName, summary, PhoneNumber, academicBackground, softSkills, fieldsOfInterest, email, id, industryExperience);

		return "redirect:/studentDashboard";
	}
	
	
	@RequestMapping(path = "/studentProfile", method = RequestMethod.GET)
	public String displayStudentProfile(ModelMap model) {
		String currentUser = (String) model.get("currentUser");
		model.put("student", studentDAO.getStudentProfileByUserName(currentUser));
		
		int studentId = portfolioDAO.getStudentIdByUserName(currentUser);
		List <Portfolio> portfolio = portfolioDAO.getPortfolioByStudentId(studentId);
		portfolio.remove(0);
		if (portfolio.size()>=1) {
			model.put("portfolios", portfolio);
		};
		// used for viewing student profile
		
		return "studentProfile";
	}
	
	@RequestMapping(path="/studentProfileById", method=RequestMethod.GET)
	public String displayParkDetailPage(@RequestParam int studentId, ModelMap model) {

		model.put("student", studentDAO.getStudentByStudentId(studentId));
		
		Student student = studentDAO.getStudentByStudentId(studentId);
		boolean publicProfile = student.getPublicProfile();
		
		List <Portfolio> portfolio = portfolioDAO.getPortfolioByStudentId(studentId);
		portfolio.remove(0);
		if (portfolio.size()>=1 && publicProfile == true) {
			model.put("portfolios", portfolio);
		};

		return "studentProfile";

	}
	
	
	
	@RequestMapping(path = "/updatePortfolio", method = RequestMethod.GET)
	public String displayStudentPortfolio() {
		return "updatePortfolio";
	}
	
	@RequestMapping(path = "/updatePortfolio", method = RequestMethod.POST)
	public String updateStudentPortfolio(@RequestParam String projectName,
			@RequestParam boolean isGroupProject,
			@RequestParam String projectDescription,
			@RequestParam String linkToSourceCode,
			@RequestParam(value="Java", defaultValue="") String Java,
			@RequestParam(value="Csharp", defaultValue="") String Csharp,
			@RequestParam(value="Html", defaultValue="") String Html,
			@RequestParam(value="Css", defaultValue="") String Css,
			@RequestParam(value="Javascript", defaultValue="") String Javascript,
			@RequestParam(value="Sql", defaultValue="") String Sql,
			
			ModelMap model) {
		String space = " ";
		String technologyUsed = Java.concat(space).concat(Csharp).concat(space).concat(Html).concat(space).concat(Css).concat(space).concat(Javascript).concat(space).concat(Sql);
		String currentUser = (String) model.get("currentUser");
		int studentId = portfolioDAO.getStudentIdByUserName(currentUser);
		portfolioDAO.updateStudentPortfolio(studentId, projectName, projectDescription, technologyUsed, linkToSourceCode, isGroupProject);
		return "redirect:/studentDashboard";
	}

	
	@RequestMapping(path = "/publicSetting", method = RequestMethod.POST)
	public String submitpublicSetting(boolean isPublic, ModelMap model, RedirectAttributes attr) {
		String currentUser = (String) model.get("currentUser");
		int userId = studentDAO.getUserIdByUserName(currentUser);
		studentDAO.setPublishProfilePreferences(isPublic, userId);
		attr.addFlashAttribute("isPublic", isPublic);
		return "redirect:/studentDashboard";
	}
}	
