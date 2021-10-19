package com.edutecno.imdb.controllers;

import java.security.Principal;
import java.util.ArrayList;
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

import com.edutecno.imdb.model.Rating;
import com.edutecno.imdb.model.Show;
import com.edutecno.imdb.model.User;
import com.edutecno.imdb.service.RatingService;
import com.edutecno.imdb.service.ShowService;
import com.edutecno.imdb.service.UserService;

@Controller
@RequestMapping("/shows")
public class ShowController {

	@Autowired
	private UserService userService;
	@Autowired
	private ShowService showService;
	@Autowired
	private RatingService ratingService;

	// add Rating

	@PostMapping(value = "/{id}/add")
	public String addRating(@Valid @ModelAttribute("addRating") Rating rating, BindingResult result,
			@PathVariable("id") Long id, Principal principal) {

		if (result.hasErrors()) {
			return "redirect:/shows/" + id;
		} else {
			String email = principal.getName();
			User currentUser = userService.findByEmail(email);
			Show currentShow = showService.findById(id);
			rating.setUser(currentUser);
			rating.setShow(currentShow);
			currentShow.getRatings().add(rating);
			showService.update(currentShow);
			return "redirect:/shows/" + id;
		}
	}

	// read show
	@GetMapping(value = "/{id}")
	public String getShow(@PathVariable("id") Long id, Model model, Principal principal) {
		Show show = showService.findById(id);

		User creatorShow = show.getCreatorShow();
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
		List<Rating> showRatings = show.getRatings();
		Rating newRating = new Rating();
		model.addAttribute("addRating", newRating);
		model.addAttribute("showRatings", showRatings);
		model.addAttribute("currentUser", currentUser);
		model.addAttribute("show", show);
		model.addAttribute("creatorShow", creatorShow);

		return "show";
	}

	// read all
	@GetMapping("")
	public String homePage(Principal principal, Model model) {
		String email = principal.getName();
		User currentUser = userService.findByEmail(email);
		List<Show> allShows = showService.findAll();
		model.addAttribute("allShows", allShows);
		model.addAttribute("currentUser", currentUser);

		return "index";

	}

	// Create new
	@GetMapping(value = "/new")
	public String newShow(Model model) {
		Show newShow = new Show();
		model.addAttribute("newShow", newShow);
		return "new";
	}

	// create save
	@PostMapping(value = "create")
	public String createShow(@Valid @ModelAttribute("newShow") Show show, BindingResult result, Principal principal) {

		if (result.hasErrors()) {
			return "new";
		} else {
			String email = principal.getName();
			User CreatorShow = userService.findByEmail(email);
			show.setCreatorShow(CreatorShow);
			showService.create(show);
			return "redirect:/shows";
		}
	}

	// EDit
	@GetMapping(value = "/{id}/edit")
	public String editShow(@PathVariable("id") Long id, Model model, @ModelAttribute("errors") String erros) {
		Show editShow = showService.findById(id);
		model.addAttribute("editShow", editShow);
		return "edit";
	}

	// update
	@PostMapping(value = "/{id}/edit")
	public String updateShow(@PathVariable("id") Long id, @Valid @ModelAttribute("editShow") Show editedShow,
			BindingResult result, Model model, Principal principal, HttpSession session) {
		String email = principal.getName();
		User CreatorShow = userService.findByEmail(email);
		Show show = showService.findById(id);

		if (result.hasErrors()) {
			session.setAttribute("id", show.getId());
			return "redirect:/shows/createError";
		} else {
			editedShow.setCreatorShow(CreatorShow);
			showService.update(editedShow);
			return "redirect:/shows/";
		}
	}

	// delete

	@GetMapping(value = "/{id}/delete")
	public String deleteShow(@PathVariable("id") Long id) {
		showService.delete(id);
		return "redirect:/shows/";
	}

}
