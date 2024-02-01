// Déclaration du package dans lequel se trouve la classe
package com.sameh.controller;

//Importations des classes pour la manipulation des dates, listes, et la gestion de sessions
import java.time.Instant;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

//Importations des annotations de Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

//Importations des entités et services nécessaires
import com.sameh.entity.BookRetard;
import com.sameh.entity.User;
import com.sameh.entity.UserBook;
import com.sameh.service.BookRetardService;
import com.sameh.service.UserBookService;
import com.sameh.service.UserService;

//Déclaration de la classe RetardBookController qui agit en tant que contrôleur dans le modèle MVC
@Controller
public class RetardBookController {

	// Injection de dépendance des services nécessaires
	@Autowired
	private BookRetardService service;
	@Autowired
	private UserBookService userBookservice;

	@Autowired
	private UserService userService;

	// Gestion de la requête HTTP GET pour récupérer la liste des livres en retard
	@GetMapping("/list_retard_books")
	public ModelAndView getAllRetard_Books(HttpSession session) {
		// Récupération de l'identifiant de l'utilisateur depuis la session
		Long userId = (Long) session.getAttribute("userId");

		// Vérification de l'authentification de l'utilisateur
		if (userId == null) {
			// Si l'utilisateur n'est pas connecté, redirection vers la page de connexion
			return new ModelAndView("redirect:/notConnected");
		}
		// Récupération de l'utilisateur à partir de son identifiant
		User user = userService.findById(userId);

		// Vérification du rôle de l'utilisateur (doit être ADMIN)
		if (user != null && "ADMIN".equals(user.getRole())) {

			// Récupération de la liste des livres en retard
			// on recupere la liste de les books en retaerd ==> si elle ajoute le nomber des
			// jour de renvoi le book en rempli le tableaux book retard et on charge la
			// liste de retad book
			List<BookRetard> list = service.getAllRetardBooks();

			// Initialisation de la liste de resultList
			List<Map<String, Object>> resultList = new ArrayList<>();

			// Boucle sur la liste des livres en retard
			for (BookRetard bookRetard : list) {

				// Récupération du livre associé au retard
				UserBook bookList = bookRetard.getUserBook();
				Date date_take = bookList.getDate_take();

				// Conversion de la date en Instant
				Instant instant = date_take.toInstant();

				// Conversion de l'Instant en LocalDate
				LocalDate localDate = instant.atZone(ZoneId.systemDefault()).toLocalDate();

				// Incrémentation de la date_take par nbr_days_to_return
				int nbr_days_to_return = bookRetard.getNbr_days_to_return();
				/// incrimenter la date de revoi le livre ==> date system + nbr_days_to_return
				LocalDate dueDate = localDate.plus(nbr_days_to_return, ChronoUnit.DAYS);

				// Calculate the number of days of delay (nombre de jours de retard)
				long daysOfDelay = Math.max(0, ChronoUnit.DAYS.between(LocalDate.now(), dueDate));

				// Création d'une carte pour contenir l'objet BookRetard original et des
				// informations supplémentaires
				// Create a map to hold the original BookRetard object and additional
				// information
				Map<String, Object> resultMap = new HashMap<>();
				resultMap.put("bookRetard", bookRetard);
				resultMap.put("dueDate", dueDate);
				resultMap.put("currentDate", LocalDate.now());
				resultMap.put("daysOfDelay", daysOfDelay);

				// Ajout de la carte à la liste resultList
				// Add the map to the resultList
				resultList.add(resultMap);
			}
			// Retourne la vue "retardBookList" avec la liste des livres en retard
			return new ModelAndView("retardBookList", "retard_books", resultList);
		} else {
			// Si l'utilisateur n'existe pas ou n'est pas un administrateur, redirection
			// vers la page d'accès refusé
			return new ModelAndView("redirect:/noAccess");
		}
	}

	// Gestion de la requête HTTP POST pour ajouter un livre en retard
	@PostMapping("/save_retard_book/{id}")
	public String addRetardBooks(@PathVariable("id") int id, @ModelAttribute BookRetard a) {
		// Récupération du livre associé à l'identifiant
		UserBook b = userBookservice.getMyBookById(id);
		// Association du livre au retard
		a.setUserBook(b);
		// Sauvegarde du livre en retard
		service.saveBookRetard(a);
		// Redirection vers la page des livres
		return "redirect:/books";
	}

}