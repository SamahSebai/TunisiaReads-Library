// Déclaration du package dans lequel se trouve la classe
package com.sameh.service;

import java.util.List;

//Importation de l'annotation Service de Spring pour indiquer que cette classe est un service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Importation des classes Author et AuthorRepository

import com.sameh.entity.Author;
import com.sameh.repository.AuthorRepository;

//Déclaration de la classe AuthorService comme un service Spring
@Service
public class AuthorService {

	// Injection de dépendance de AuthorRepository dans la classe AuthorService
	@Autowired
	private AuthorRepository aRepo;

	// Méthode pour sauvegarder un auteur dans la base de données
	public void save(Author a) {
		aRepo.save(a);
	}

	// Méthode pour récupérer tous les auteurs de la base de données
	public List<Author> getAllAuthor() {
		return aRepo.findAll();
	}

	// Méthode pour récupérer un auteur par son identifiant
	public Author getAuthorById(int id) {
		return aRepo.findById(id).get();
	}
	// Méthode pour supprimer un auteur par son identifiant

	public void deleteById(int id) {
		aRepo.deleteById(id);
	}

}
