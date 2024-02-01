// Déclaration du package dans lequel se trouve la classe

package com.sameh.service;

import java.util.List;

//Importation des annotations de Spring

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//Importation des classes BookRetard et RetardBookRepository

import com.sameh.entity.BookRetard;
import com.sameh.repository.RetardBookRepository;

//Déclaration de la classe BookRetardService comme un service Spring

@Service
public class BookRetardService {

	// Injection de dépendance de RetardBookRepository dans la classe
	// BookRetardService

	@Autowired
	private RetardBookRepository retardBook;

	// Méthode pour sauvegarder un livre en retard dans la base de données

	public void saveBookRetard(BookRetard book) {
		retardBook.save(book);
	}

	// Méthode pour récupérer tous les livres en retard de la base de données

	public List<BookRetard> getAllRetardBooks() {
		return retardBook.findAll();
	}

}
