package com.techelevator.studentprofile.controller;

import java.util.List;

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
import com.techelevator.studentprofile.model.Student;
import com.techelevator.studentprofile.model.StudentDAO;
import com.techelevator.studentprofile.model.UsersDAO;


@Controller
@SessionAttributes({"currentUser"})
@Transactional
public class EmployerController {


		private UsersDAO usersDAO;
		private AdminDAO adminDAO;
		private StudentDAO studentDAO;
		private EmployerDAO employerDAO;
		
		@Autowired
		public EmployerController(UsersDAO usersDAO, StudentDAO studentDAO, AdminDAO adminDAO, EmployerDAO employerDAO) {
			this.usersDAO = usersDAO;
			this.adminDAO = adminDAO;
			this.studentDAO = studentDAO;
			this.employerDAO = employerDAO;
		}
			
		@RequestMapping(path = "/employerDashboard", method = RequestMethod.GET)
		public String displayEmployerDashboard() {
			return "employerDashboard";
		}
		
		@RequestMapping(path = "/employerRegistration/{code}", method = RequestMethod.GET)
		public String displayEmployerRegistration(@PathVariable String code, ModelMap model) {
			model.put("code", code);
			return "employerRegistration";
		}
		
		@RequestMapping(path = "/employerRegistration", method = RequestMethod.POST)
		public String submitEmployerRegistrationForm(@RequestParam String userName, @RequestParam String password,
				@RequestParam String code, ModelMap model) {
			
			boolean IfEmployerAlreadyExists = employerDAO.checkIfEmployerAlreadyExist(userName);

			if (IfEmployerAlreadyExists ==false) {
				model.put("code", code);
				model.put("userName", userName);
				model.put("password", password);
				employerDAO.createEmployerRegistration(userName.toLowerCase(), password, code);
				model.put("currentUser", userName);
				return "redirect:/employerDashboard";
			} else {
				return "home";
			}
		}
		
		@RequestMapping(path = "/searchStudent", method = RequestMethod.GET)
		public String displaySearchStudentPage() {
			return "searchStudent";
		}
		
		@RequestMapping(path = "/searchStudent", method = RequestMethod.POST)
		public String submitStudentSearch(@RequestParam String technologiesUsed, @RequestParam int cohortNumber,
				@RequestParam String academicBackground, @RequestParam boolean industryExperience, ModelMap model, RedirectAttributes attr) {
			List <Student> student = employerDAO.submitStudentSearch(technologiesUsed, cohortNumber, academicBackground, industryExperience);
			System.out.println(student.size());
			if (student.size() == 0) {
				String str = "Your search criteria did not match any results";
				attr.addFlashAttribute("str", str);
				return "redirect:/studentSearchResult";
			} else {
			attr.addFlashAttribute("students", student);
			model.put("students", student);
			
			return "redirect:/studentSearchResult";
			}
		}
		
		@RequestMapping(path = "/studentSearchResult", method = RequestMethod.GET)
		public String displayStudentSearchResultsPage() {
			return "studentSearchResult";
		}
		
	
}
