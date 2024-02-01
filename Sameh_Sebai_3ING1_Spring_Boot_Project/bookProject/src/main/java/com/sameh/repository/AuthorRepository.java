// Déclaration du package dans lequel se trouve la classe
package com.sameh.repository;

// Importation de l'interface JpaRepository de Spring Data JPA
import org.springframework.data.jpa.repository.JpaRepository;

// Importation de l'entité Author pour définir le type de données géré par ce repository
import com.sameh.entity.Author;

// Interface définissant un repository pour l'entité Author avec une clé primaire de type Integer
public interface AuthorRepository extends JpaRepository<Author, Integer> {

	// Aucune méthode n'est définie ici, car JpaRepository fournit des méthodes CRUD
	// génériques par défaut.

	// L'interface JpaRepository offre déjà des méthodes telles que :
	// - save(Entity entity): pour sauvegarder une entité
	// - findById(ID id): pour rechercher une entité par son identifiant
	// - findAll(): pour récupérer toutes les entités
	// - deleteById(ID id): pour supprimer une entité par son identifiant
	// ... et d'autres méthodes pour effectuer des opérations CRUD.

	// Il n'est pas nécessaire de fournir une implémentation pour ces méthodes,
	// Spring Data JPA génère automatiquement le code nécessaire en fonction du nom
	// des méthodes.

	// Cette interface simplifie grandement l'accès aux opérations de base de la
	// base de données
	// pour l'entité Author, en évitant d'avoir à écrire du code spécifique pour
	// chaque opération CRUD.

	// Il suffit d'injecter AuthorRepository dans d'autres composants (services,
	// contrôleurs, etc.)
	// pour bénéficier de l'accès aux opérations CRUD sans avoir à écrire une seule
	// ligne de code supplémentaire.
}
