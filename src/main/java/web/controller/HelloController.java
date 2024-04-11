package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import javax.transaction.Transactional;
import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

@Controller
@Transactional
public class HelloController {
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public ModelAndView listUsers() throws SQLException {
		userService.getConnection();
		ModelAndView mav = new ModelAndView("users");
		mav.addObject("users", userService.getAllUsers());
		userService.closeConnection();
		return mav;
	}
	@GetMapping("/add")
	public String addUserForm(Model model) {
		userService.getConnection();
		User user = new User();
		model.addAttribute("add",user);
		userService.closeConnection();
		return "user-form";
	}
	@PostMapping("/add")
	public String addUserSubmit(@ModelAttribute User user) throws SQLException {
		userService.addUser(user);
		return "redirect:/";
	}
	@GetMapping("/edit")
	public String editUserForm(Model model) {
		User user = new User();
		model.addAttribute("edit",user);
		return "user-form";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id, Model model) throws SQLException {
		User user = userService.deleteUserById(id);
		model.addAttribute("delete", user);
		return "redirect:/";
	}
}