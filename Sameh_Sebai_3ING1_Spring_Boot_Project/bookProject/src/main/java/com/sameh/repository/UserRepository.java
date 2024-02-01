// Déclaration du package dans lequel se trouve la classe
package com.sameh.repository;

// Importation de l'interface JpaRepository de Spring Data JPA, qui offre des méthodes de base pour les opérations CRUD
import org.springframework.data.jpa.repository.JpaRepository;

// Importation de la classe User pour définir le type de données géré par ce repository
import com.sameh.entity.User;

// Importation de l'interface Optional pour représenter un conteneur pouvant ou non contenir une valeur
import java.util.Optional;

// Interface définissant un repository pour l'entité User avec une clé primaire de type Long
public interface UserRepository extends JpaRepository<User, Long> {

	// Déclaration d'une méthode personnalisée permettant de rechercher un
	// utilisateur par son nom d'utilisateur
	User findByUsername(String username);

	// Déclaration d'une méthode personnalisée permettant de rechercher un
	// utilisateur par son identifiant
	Optional<User> findById(Long id);

	// Ces méthodes personnalisées sont automatiquement implémentées par Spring Data
	// JPA
	// en fonction du nom de la méthode et des propriétés de l'entité User.

	// L'utilisation de JpaRepository offre également des méthodes génériques telles
	// que :
	// - save(Entity entity): pour sauvegarder une entité
	// - findAll(): pour récupérer toutes les entités
	// - deleteById(ID id): pour supprimer une entité par son identifiant
	// ... et d'autres méthodes pour effectuer des opérations CRUD.

	// L'interface Optional est utilisée pour indiquer que la méthode peut ne pas
	// renvoyer de résultat.
	// Cela permet de traiter les cas où aucun utilisateur correspondant à
	// l'identifiant donné n'est trouvé.

	// Cette interface simplifie grandement l'accès aux opérations de base de la
	// base de données
	// pour l'entité User, en évitant d'avoir à écrire du code spécifique pour
	// chaque opération CRUD.

	// Il suffit d'injecter UserRepository dans d'autres composants (services,
	// contrôleurs, etc.)
	// pour bénéficier de l'accès aux opérations CRUD sans avoir à écrire une seule
	// ligne de code supplémentaire.
}
