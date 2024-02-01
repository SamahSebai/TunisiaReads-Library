// Déclaration du package auquel appartient la classe
package com.sameh.controller;

//Importation des classes nécessaires de Java et de Spring Framework
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sameh.entity.Author;
import com.sameh.entity.User;
import com.sameh.service.AuthorService;
import com.sameh.service.UserService;

//Annotation indiquant que cette classe est un contrôleur Spring MVC
@Controller
public class AuthorController {

	// Injection de dépendance pour les services AuthorService et UserService
	@Autowired
	private AuthorService authorService;
	@Autowired
	private UserService userService;

	// Gestion de la requête pour le lien "/author_register"
	// ici on appelle ce lien lors de la clique sur le lien new author register du
	// page authorList.html
	@GetMapping("/author_register")
	public ModelAndView authorRegister(HttpSession session) {

		// ici on le récupère id d'user de la session
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// si l'utilisateur n'est pas connecté Redirection vers la page
			// notConnected.html
			return new ModelAndView("redirect:/notConnected");
		}

		// Récupération de l'utilisateur à partir de son ID grâce au service UserService
		// ici on le récupère le user avec la méthode findById(userId) dans le service
		// UserService
		// on le récupère id d'user de la session
		User user = userService.findById(userId);

		if (user != null && "ADMIN".equals(user.getRole())) {
			// L'utilisateur est un administrateur, autorise l'accès à la page
			// authorRegister.html
			return new ModelAndView("authorRegister");

		} else {

			// Si l'utilisateur n'existe pas ou n'est pas un administrateur, redirigez vers
			// la page noAccess.html
			return new ModelAndView("redirect:/noAccess");
		}
	}

	// Gestion de la requête pour le lien "/list_authors"
	// ici on appelle ce lien lors de la clique sur le lien authors du page
	// homeAdmin.html
	@GetMapping("/list_authors")
	public ModelAndView getAllAuthors(HttpSession session) {

		// Récupération de l'ID de l'utilisateur à partir de la session
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {

			// Redirection vers la page notConnected.html si l'utilisateur n'est pas
			// connecté
			return new ModelAndView("redirect:/notConnected");
		}

		// Récupération de l'utilisateur à partir de son ID grâce au service UserService
		// ici on le récupère le user avec la méthode findById(userId) dans le service
		// UserService
		// on le récupère id d'user de la session
		User user = userService.findById(userId);

		if (user != null && "ADMIN".equals(user.getRole())) {
			// L'utilisateur est un administrateur, autorise l'accès à la page
			// authorList.html on charge la liste des authors
			List<Author> list = authorService.getAllAuthor();
			return new ModelAndView("authorList", "author", list);
		} else {
			// Si l'utilisateur n'existe pas ou n'est pas un administrateur, redirigez vers
			// la page noAccess.html
			return new ModelAndView("redirect:/noAccess");
		}
	}

	// Gestion de la requête pour le lien "/show_author_register"
	// ici on appelle ce lien lors de la clique sur le lien new book register pour
	// récupérer la liste des auteurs et le redirecter vers la page
	// bookRegister.HTML
	@GetMapping("/show_author_register")
	public ModelAndView showAuthorRegisterPage(HttpSession session) {

		// ici on le récupère id d'user de la session
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// Redirection vers la page notConnected.html si l'utilisateur n'est pas
			// connecté
			return new ModelAndView("redirect:/notConnected");
		}

		// Récupération de l'utilisateur à partir de son ID grâce au service UserService
		// ici on le récupère le user avec la méthode findById(userId) dans le service
		// UserService
		// on le récupère id d'user de la session
		User user = userService.findById(userId);

		// ici si user != null donc user est connecté et id enregistré dans la session
		// on teste aussi sur le rôle
		if (user != null && "ADMIN".equals(user.getRole())) {
			// L'utilisateur est un administrateur, autorise l'accès à la page
			// bookregister.html on charge la liste des authors
			List<Author> list = authorService.getAllAuthor();
			return new ModelAndView("bookRegister", "authors", list);
		} else {

			// Si l'utilisateur n'existe pas ou n'est pas un administrateur, redirigez vers
			// la page noAccess.html
			return new ModelAndView("redirect:/noAccess");
		}
	}

	// Gestion de la requête POST pour le lien "/save_author"
	// du page authorRegister.html on fait appel à ce lien /save_author
	// pour enregistrer le nouveau auteur passent en paramètre de la méthode
	@PostMapping("/save_author")
	public String addAuthor(@ModelAttribute Author a) {
		// Enregistrement du nouvel auteur en appelant la méthode save(a) dans le
		// service AuthorService
		authorService.save(a);
		// Redirection vers le lien "/list_authors", ce qui conduit à la page
		// authorList.html
		return "redirect:/list_authors";
	}

	// Gestion de la requête pour le lien "/editAuthor/{id}"
	// du page authorlist.html on fait appel à ce lien /editAuthor/{id}
	// en passent id de l'auteur pour le modifier
	@RequestMapping("/editAuthor/{id}")
	public String editAuthor(@PathVariable("id") int id, Model model) {
		// Récupération de l'auteur par ID en appelant la méthode getAuthorById(id) du
		// service AuthorService
		Author a = authorService.getAuthorById(id);

		// Ajout de l'auteur à la page authorEdit.html
		model.addAttribute("author", a);
		// ici on le redirecter vers la page authorEdit.html
		return "authorEdit";
	}

	// Gestion de la requête pour le lien "/deleteAuthor/{id}"
	// du page authorlist.html on fait appel à ce lien /deleteAuthor/{id}
	// en passent id de l'auteur pour le supprimer
	@RequestMapping("/deleteAuthor/{id}")
	public String deleteAuthor(@PathVariable("id") int id) {
		// Suppression de l'auteur par ID en appelant la méthode deleteById(id) du
		// service AuthorService
		authorService.deleteById(id);
		// ici on redirect vers le lien list_authors ce qui redirecter nous a la page
		// authorList.html
		return "redirect:/list_authors";
	}
}
