// Déclaration du package dans lequel se trouve la classe
package com.sameh.service;

// Importation des annotations de Spring et des classes nécessaires
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sameh.entity.Comment;
import com.sameh.repository.CommentRepository;

// Déclaration de la classe CommentService comme un service Spring
@Service
public class CommentService {

	// Injection de dépendance de CommentRepository dans la classe CommentService
	@Autowired
	private CommentRepository commentRepo;

	// Méthode pour sauvegarder un commentaire dans la base de données
	public void save(Comment c) {
		commentRepo.save(c);
	}

	// Méthode pour récupérer tous les commentaires de la base de données
	public List<Comment> getAllComments() {
		return commentRepo.findAll();
	}

	// Méthode pour récupérer un commentaire par son ID
	public Comment getCommentById(Long id) {
		return commentRepo.findById(id).get();
	}

	// Méthode pour supprimer un commentaire par son ID
	public void deleteById(Long id) {
		commentRepo.deleteById(id);
	}
}
