package cl.ATorresGa.SistemaIMDB.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import cl.ATorresGa.SistemaIMDB.model.User;
import cl.ATorresGa.SistemaIMDB.service.UserService;
import cl.ATorresGa.SistemaIMDB.validator.UserValidator;

@Controller
public class UserController {
	@Autowired
	private UserValidator userValidator;//instanciacion para verificar los usuarios y los patrones de texto
	@Autowired
	private UserService userService; //instanciacion del crud
	
	@RequestMapping("/login")
	public ModelAndView login(@RequestParam(value = "error", required = false)String error,
			@RequestParam(value = "logout" , required = false ) String logout, Model model,
			@Valid @ModelAttribute("user") User user, BindingResult result) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");//se le da el nombre a de la vista
		
		if (error !=null) {
			modelAndView.addObject("errorMessage", "Invalid credentials, please try again");// en el caso de que falle el el login envia el error
		}
		if (logout !=null) {
			modelAndView.addObject("logoutMessage", "Logout Sucessful!"); // en el caso de que sea logout envia el mensaje de despedida
		}
		return modelAndView;
	}
	@GetMapping("/registration")
	public ModelAndView registerForm(@Valid @ModelAttribute("user") User user) {
		return new ModelAndView("registration");//envia al usuario a la vista de registro
	}
	@PostMapping("/registration")
	public RedirectView registration(@Valid @ModelAttribute("user") User user, BindingResult result) {
		userValidator.validate(user, result);// se usa la instancia para validar al usuario
		if (result.hasErrors()) {
			return new RedirectView("registration");// se redirecciona a registro si el usuario no cumple con las validaciones
		} else {
			userService.saveWithUserRole(user);//se guarda al usuario con el rol de usuario
			// userService.saveUserWithAdminRole(user); para guardar con el rol de admin descomentar esta linea
			return new RedirectView("/login");// se le redirecciona a la vista
		}
		
	}
	@RequestMapping(value = { "/", "/home" })
	public RedirectView home(Principal principal) {
		return new RedirectView("/shows");
	}
}
