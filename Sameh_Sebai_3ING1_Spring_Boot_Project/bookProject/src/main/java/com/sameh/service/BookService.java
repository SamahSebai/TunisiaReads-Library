// Déclaration du package dans lequel se trouve la classe
package com.sameh.service;

// Importation des annotations de Spring et des classes nécessaires
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sameh.entity.Book;
import com.sameh.repository.BookRepository;

// Déclaration de la classe BookService comme un service Spring
@Service
public class BookService {

    // Injection de dépendance de BookRepository dans la classe BookService
    @Autowired
    private BookRepository bRepo;

    // Méthode pour sauvegarder un livre dans la base de données
    public void save(Book b) {
        bRepo.save(b);
    }

    // Méthode pour récupérer tous les livres de la base de données
    public List<Book> getAllBook() {
        return bRepo.findAll();
    }

    // Méthode pour récupérer un livre par son ID
    public Book getBookById(int id) {
        return bRepo.findById(id).get();
    }

    // Méthode pour supprimer un livre par son ID
    public void deleteById(int id) {
        bRepo.deleteById(id);
    }

    // Méthode pour rechercher des livres par un terme de recherche
    public List<Book> searchBooks(String searchTerm) {
        // Implémenter la logique pour rechercher des livres en fonction du terme de recherche
        // Pour simplifier, supposons que vous ayez une méthode dans votre repository pour la recherche par nom, auteur ou prix
        return bRepo.searchBooksByNameOrAuthorOrPrice(searchTerm);
    }

    // Méthode pour éditer les détails d'un livre existant
    public void editBook(Book updatedBook) {
        // Vérifier si le livre avec l'ID donné existe
        Optional<Book> existingBook = bRepo.findById(updatedBook.getId());

        if (existingBook.isPresent()) {
            // Mettre à jour le livre existant avec les nouvelles données
            Book currentBook = existingBook.get();
            currentBook.setName(updatedBook.getName());
            currentBook.setAuthor(updatedBook.getAuthor());
            currentBook.setPrice(updatedBook.getPrice());
            currentBook.setAvailable(updatedBook.getAvailable());

            // Enregistrer le livre mis à jour
            bRepo.save(currentBook);
        } else {
            // Gérer le cas où le livre avec l'ID donné n'est pas trouvé
            throw new IllegalArgumentException("Livre avec l'ID " + updatedBook.getId() + " non trouvé");
        }
    }
}
