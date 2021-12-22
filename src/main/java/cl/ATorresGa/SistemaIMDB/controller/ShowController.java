package cl.ATorresGa.SistemaIMDB.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import cl.ATorresGa.SistemaIMDB.model.Rating;
import cl.ATorresGa.SistemaIMDB.model.Show;
import cl.ATorresGa.SistemaIMDB.model.User;
import cl.ATorresGa.SistemaIMDB.service.RatingService;
import cl.ATorresGa.SistemaIMDB.service.ShowService;
import cl.ATorresGa.SistemaIMDB.service.UserService;

@Controller
@RequestMapping("/shows")
public class ShowController {
	@Autowired
	private UserService userService;// intanciacion para usar los metodos de los servicios
	@Autowired
	private ShowService showService;// intanciacion para usar los metodos de los servicios
	@Autowired
	private RatingService ratingService;// intanciacion para usar los metodos de los servicios

	// CREATE NEW
	@GetMapping(value = "/new")
	public ModelAndView newShow(Model model) {
		Show newShow = new Show();// se instancia un show

		ModelAndView modelAndView = new ModelAndView();// se envia el objeto vacio para que este se pueda rellenar en el
														// postmaping
		modelAndView.addObject("newShow", newShow);
		modelAndView.setViewName("new");

		return modelAndView;
	}

	// CREATE SAVE
	@PostMapping(value = "/create")
	public RedirectView createShow(@ModelAttribute("newShow") @Valid Show show, BindingResult result,
			Principal principal) {
		if (result.hasErrors()) {
			return new RedirectView("new");
		} else {
			String email = principal.getName();// se obtiene el mail del usuario en sesion
			User creatorShow = userService.findUserByEmail(email);// se busca al usuario por su email
			show.setCreatorShow(creatorShow);// se le setea el usuario al show como el creador del show
			showService.saveShow(show); // se guarda el show en la bd
			return new RedirectView("/shows");// se redirecciona a la vista de los shows
		}
	}

	// add show
	@PostMapping(value = "/{id}/add") // se obtiene el valor de la id y se agrega
	public RedirectView addRating(@Valid @ModelAttribute("addRating") Rating rating, BindingResult result,
			@PathVariable("id") Long id, Principal principal) {
		if (result.hasErrors()) {
			return new RedirectView("/shows/" + id);// se redirecciona al metodo
		} else {
			String email = principal.getName();// se obtiene el email del usuario registrado
			User currentUser = userService.findUserByEmail(email);// se busca a ese usuario
			Rating lastRating = ratingService.findFirtsByOrderByDesc();// se busca el ultimo rating ingresado
			Rating ratingUser = ratingService.findRatingByUserId(id);// se busca el rating del usuario
			if (lastRating != null) {
				Show currentShow = showService.findById(id);// busca el show por su id
				Rating ratingDB = ratingService.findFirtsByOrderByDesc();// se busca el ultimo rating ingresado
				if (ratingDB != null) {
					rating.setId(ratingDB.getId() + 1);// se le asigna una id al rating
				}
				rating.setUser(currentUser);// se le agrega el usuario para que este pueda saber quien hizo el rating
				rating.setShowId(currentShow.getId()); // se le setea el show al cual se le esta agregando rating
				ratingService.addRating(rating);// se agrega el rating
				currentShow.setRatings(ratingService.findAllRatingByShowId(currentShow.getId()));// busca todos los
																									// rating de ese
																									// show y se le
																									// agregan
				showService.updateShow(currentShow);// se agregan los datos nuevos al show
			} else if (ratingUser == null) {
				Show currentShow = showService.findById(id);// se busca el show
				Rating ratingDB = ratingService.findFirtsByOrderByDesc(); // se busca el ultimo rating
				if (ratingDB != null) {
					rating.setId(ratingDB.getId() + 1);// se agrega un nuevo rating
				}
				rating.setUser(currentUser);// se le entrega al rating el usuario que esta dando el rating
				rating.setShowId(currentShow.getId());// se obtiene la id del show actual
				ratingService.addRating(rating);// se agrega el rating a ese show
				currentShow.setRatings(ratingService.findAllRatingByShowId(currentShow.getId()));// se obtienen todos
																									// los rating del
																									// show actual y se
																									// le setean a este
				showService.updateShow(currentShow);// se actualiza el show que no tiene todos los datos
			}
			return new RedirectView("/shows/" + id);// se redirige a la pagina con el id del show actual
		}
	}

	// READ ONE
	@GetMapping(value = "/{id}")
	public ModelAndView getShow(@PathVariable("id") Long id, Model model, Principal principal) {
		Show show = showService.findById(id);// se busca el show con el id
		User creatorShow = show.getCreatorShow();// se obtiene el creador del rating
		String email = principal.getName(); // se obtiene el email del usuario en sesion
		User currentUser = userService.findUserByEmail(email);// se obtiene el usuario por el email
		List<Rating> showRatings = show.getRatings(); // obtiene los datos de los rating de la bd
		Rating newRating = new Rating();// se instancia un new rating

		ModelAndView modelAndView = new ModelAndView();// se envian los datos en el modelo
		modelAndView.addObject("addRating", newRating);
		modelAndView.addObject("showRatings", showRatings);
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.addObject("show", show);
		modelAndView.addObject("creatorShow", creatorShow);

		modelAndView.setViewName("show");

		return modelAndView;// se redirecciona a la pagina show
	}

	// READ ALL
	@GetMapping("")
	public ModelAndView homePage(Principal principal, Model model) {
		String email = principal.getName();// se obtiene el mail del usuario en sesion
		User currentUser = userService.findUserByEmail(email);// se setean los datos del usuario por su email
		List<Show> allShows = showService.findAllShows();// se buscan todos los shows para que el usuario elija cual
															// ratear

		ModelAndView modelAndView = new ModelAndView();// se agregan los atributos al modelo
		modelAndView.addObject("allShows", allShows);
		modelAndView.addObject("currentUser", currentUser);
		modelAndView.setViewName("home");

		return modelAndView;// se redirecciona a la pagina home
	}

	// EDIT
	@GetMapping(value = "/{id}/edit")
	public ModelAndView editShow(@PathVariable("id") Long id, Model model, @ModelAttribute("errors") String errors) {
		Show editShow = showService.findById(id);// se obtiene el show por su id

		ModelAndView modelAndView = new ModelAndView();// se agregan los datos al modelo para editar el show encontrado
		modelAndView.addObject("editShow", editShow);
		modelAndView.setViewName("edit");
		return modelAndView;// se redirecciona a la vista
	}

	// UPDATE
	@PostMapping(value = "/{id}/edit")
	public RedirectView updateShow(@PathVariable("id") Long id, @Valid @ModelAttribute("editShow") Show editedShow,
			BindingResult result, Model model, Principal principal, HttpSession session) {
		String email = principal.getName();// se obtiene el usuario en sesion
		Show show = showService.findById(id);// se obtiene el show por el id
		User creatorShow = userService.findUserByEmail(email);// se busca al usuario por su email
		if (result.hasErrors()) {
			session.setAttribute("id", show.getId());//se obtiene la id del usuario para obtener todos los shows de ese usuario
			return new RedirectView("/shows/createError");
		} else {
			editedShow.setCreatorShow(creatorShow);// se cambia el usuario
			showService.updateShow(editedShow);// se envia el show editado
			return new RedirectView("/shows/");// se redirecciona
		}
	}

	@RequestMapping("/createError")
	public String flashMessages(RedirectAttributes redirectAttributes, HttpSession session) {
		redirectAttributes.addFlashAttribute("errors", "Title and Network must be present");
		return "redirect:/shows/" + session.getAttribute("id") + "/edit";
	}

	// DELETE / DESTROY
	@GetMapping(value = "/{id}/delete")
	public RedirectView deleteShow(@PathVariable("id") Long id) {
		showService.deleteShow(id);// se elimina el show con esa id
		return new RedirectView("/shows/");// se redirecciona a la vista 
	}
}
