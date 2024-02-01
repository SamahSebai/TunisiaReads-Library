// Déclaration du package dans lequel se trouve la classe
package com.sameh.controller;

//Importation des annotations de Spring Framework
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sameh.service.UserBookService;// Importation de la classe UserBookService du package com.sameh.service

//Déclaration de la classe MyBookController qui agit en tant que contrôleur dans le modèle MVC
@Controller
public class MyBookController {
	// Injection de dépendance de la classe UserBookService via l'annotation
	// @Autowired
	@Autowired
	private UserBookService service;

	// Méthode de gestion de la requête HTTP GET pour la suppression d'un livre de
	// la liste de l'utilisateur
	@RequestMapping("/deleteMyList/{id}")
	public String deleteMyList(@PathVariable("id") int id) {
		
		// Appel de la méthode deleteById de la classe UserBookService pour supprimer un
		// livre par son identifiant
		service.deleteById(id);
		
		// Redirection vers la page "/my_books" après la suppression
		return "redirect:/my_books";
	}
}
