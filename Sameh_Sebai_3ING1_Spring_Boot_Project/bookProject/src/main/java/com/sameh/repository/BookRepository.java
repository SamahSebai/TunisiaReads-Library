// Déclaration du package dans lequel se trouve la classe
package com.sameh.repository;

// Importation des annotations nécessaires
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Importation de l'entité Book pour définir le type de données géré par ce repository
import com.sameh.entity.Book;

// Déclaration de l'interface BookRepository comme un repository JPA
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    // Déclaration d'une requête personnalisée pour rechercher des livres par nom, auteur, prix ou genre
    @Query("SELECT b FROM Book b WHERE LOWER(b.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.price) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.gender) LIKE LOWER(CONCAT('%', :searchTerm, '%'))  OR LOWER(b.author.name) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.price) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.gender) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Book> searchBooksByNameOrAuthorOrPrice(@Param("searchTerm") String searchTerm);

    // Aucune méthode supplémentaire n'est définie ici, car JpaRepository fournit déjà des méthodes pour les opérations CRUD.
    
    // L'interface JpaRepository offre des méthodes telles que :
    // - save(Entity entity): pour sauvegarder une entité
    // - findById(ID id): pour rechercher une entité par son identifiant
    // - findAll(): pour récupérer toutes les entités
    // - deleteById(ID id): pour supprimer une entité par son identifiant
    // ... et d'autres méthodes pour effectuer des opérations CRUD.

    // Cette interface simplifie l'accès aux opérations de base de la base de données
    // pour l'entité Book, tout en fournissant une méthode personnalisée pour la recherche.

    // Il suffit d'injecter BookRepository dans d'autres composants (services, contrôleurs, etc.)
    // pour bénéficier de l'accès aux opérations CRUD et à la méthode de recherche sans avoir à écrire une seule ligne de code supplémentaire.
}

