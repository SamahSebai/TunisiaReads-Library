// Déclaration du package dans lequel se trouve la classe de service
package com.sameh.service;

// Importation des classes nécessaires
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sameh.entity.Book;
import com.sameh.entity.UserBook;
import com.sameh.repository.UserBookRepository;

// Annotation Spring indiquant que cette classe est un service
@Service
public class UserBookService {

	// Injection de dépendance pour le repository UserBook
	@Autowired
	private UserBookRepository mybook;

	// Méthode pour enregistrer un UserBook dans la base de données
	public void saveMyBooks(UserBook book) {
		mybook.save(book);
	}

	// Méthode pour récupérer tous les UserBooks de la base de données
	public List<UserBook> getAllMyBooks() {
		return mybook.findAll();
	}

	// Méthode pour supprimer un UserBook par son identifiant
	public void deleteById(int id) {
		mybook.deleteById(id);
	}

	// Méthode pour récupérer un UserBook par son identifiant
	public UserBook getMyBookById(int id) {
		return mybook.findById(id).get();
	}
}
