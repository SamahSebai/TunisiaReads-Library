// Déclaration du package dans lequel se trouve la classe
package com.sameh.controller;

//Importation des classes nécessaires de Spring Framework
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.RequestMapping;

import com.sameh.entity.Comment;// Importation de la classe Comment du package com.sameh.entity
import com.sameh.service.CommentService;// Importation de la classe CommentService du package com.sameh.service

//Déclaration de la classe CommentController qui agit en tant que contrôleur dans le modèle MVC
public class CommentController {
	// Injection de dépendance de la classe CommentService via l'annotation
	// @Autowired
	@Autowired
	private CommentService commentService;

	// Méthode de gestion de la requête HTTP GET pour l'édition d'un commentaire
	@RequestMapping("/editComment/{id}")
	public String editComment(@PathVariable("id") Long id, Model model) {

		// Appel de la méthode getCommentById de la classe CommentService pour obtenir
		// un commentaire par son identifiant
		Comment c = commentService.getCommentById(id);
		// Ajout du commentaire au modèle pour qu'il soit accessible dans la vue
		model.addAttribute("comment", c);

		// Retourne le nom de la vue associée à l'édition des commentaires (commentEdit)
		return "commentEdit";
	}

}
