package web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import web.model.User;
import web.repositories.UserRepository;
import web.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
	@Autowired
	private UserService userService;

	@GetMapping(value = "/")
	public String printWelcome(ModelMap model) {
		List<String> messages = new ArrayList<>();
		messages.add("Congratulations!");
		messages.add("You just launched a Spring MVC application :)");
		model.addAttribute("messages", messages);
		return "index";
	}

	@GetMapping("/users")
	public String listUsers(Model model) {
		model.addAttribute("users", userService.findAll());
		return "users";
	}
	@GetMapping("/add")
	public String addUserForm(Model model) {
		model.addAttribute("add", new User());
		return "user-form";
	}
	@PostMapping("/add")
	public String addUserSubmit(@ModelAttribute User user) {
		userService.addUser(user);
		return "redirect:/users";
	}
	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable Long id) {
		userService.deleteUserById(id);
		return "redirect:/users";
	}
}