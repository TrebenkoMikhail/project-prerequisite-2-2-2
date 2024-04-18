package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.sql.SQLException;


@Controller
public class HelloController {

	private final UserService userService;

	@Autowired
	public HelloController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public String listUsers(Model model) throws SQLException {
		model.addAttribute("users", userService.getAllUsers());
		return "user-list";
	}

	@GetMapping("/add")
	public String addUserForm(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "user-form";
	}
	@PostMapping(value = "/add")
	public String addUserSubmit(@ModelAttribute User user)  {
		userService.addUser(user);
		return "redirect:/";
	}
	@GetMapping("/edit/{id}")
	public String editUserForm(@PathVariable Long id, Model model) throws SQLException {
		User user =new User();
		user.setId(id);
		model.addAttribute("user", user);
		return "user-edit";
	}
	@PostMapping("/edit")
	public String editUserSubmit(@ModelAttribute User user, Long id) throws SQLException {
		userService.updateUser(user);
		user.setId(id);
		return "redirect:/";
	}

//	@GetMapping("/find/{id}")
//	public String findUserForm(Model model, @PathVariable Long id) throws SQLException {
//		model.addAttribute("user",userService.getUserById(id));
//		return "user-details";
//	}
//	@PostMapping("/find/{id}")
//	public String findUserSubmit(@ModelAttribute User user, @PathVariable Long id) throws SQLException {
//		userService.updateUser(user);
//		return "redirect:/";
//	}
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) throws SQLException {
		userService.deleteUserById(id);
		return "redirect:/";
	}
}