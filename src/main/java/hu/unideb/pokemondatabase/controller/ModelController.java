package hu.unideb.pokemondatabase.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import hu.unideb.pokemondatabase.entity.User;
import hu.unideb.pokemondatabase.service.UserServiceImpl;

@Controller
public class ModelController {

	private UserServiceImpl userServiceImpl;

	@Autowired
	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	@RequestMapping("/")
	public String index() {
		return "index.html";
	}

	@RequestMapping("/registration")
	public String registration(Model model) {
		model.addAttribute("user", new User());
		return "registration.html";
	}

	@PostMapping("/reg")
	public String greetingSubmit(@Valid User user, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "registration";
		}

		String regisztracio = userServiceImpl.registerUser(user);
		if (regisztracio.equals("UserExists")) {
			bindingResult.rejectValue("username", "error.user", "Már regisztráltak ezzel a felhasználói névvel");
			return "registration";
		} else if (regisztracio.equals("PassNotMatch")) {
			bindingResult.rejectValue("password", "error.user", "A jelszavak nem egyeznek");
			return "registration";
		}

		return "auth/login";

	}

}
