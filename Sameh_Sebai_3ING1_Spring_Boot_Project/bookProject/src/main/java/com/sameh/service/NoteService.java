// Déclaration du package dans lequel se trouve la classe de service
package com.sameh.service;

// Importation des classes nécessaires
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sameh.entity.Note;
import com.sameh.repository.NoteRepository;

// Annotation Spring indiquant que cette classe est un service
@Service
public class NoteService {

	// Injection de dépendance pour le repository de Note
	@Autowired
	private NoteRepository noteRepo;

	// Méthode pour enregistrer une note dans la base de données
	public void save(Note n) {
		noteRepo.save(n);
	}

	// Méthode pour récupérer toutes les notes de la base de données
	public List<Note> getAllNotes() {
		return noteRepo.findAll();
	}

	// Méthode pour récupérer une note par son identifiant
	public Note getNoteById(int id) {
		return noteRepo.findById(id).get();
	}

	// Méthode pour supprimer une note par son identifiant
	public void deleteById(int id) {
		noteRepo.deleteById(id);
	}
}
