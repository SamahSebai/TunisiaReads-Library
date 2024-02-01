// Déclaration du package dans lequel se trouve la classe de service
package com.sameh.service;

// Importation des classes nécessaires
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sameh.entity.User;
import com.sameh.repository.UserRepository;

// Annotation Spring indiquant que cette classe est un service
@Service
public class UserService {

	// Injection de dépendance pour le repository User
	@Autowired
	private UserRepository userRepository;

	// Méthode pour trouver un utilisateur par son nom d'utilisateur
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	// Méthode pour enregistrer un utilisateur dans la base de données
	public void saveUser(User user) {
		userRepository.save(user);
	}

	// Méthode pour trouver un utilisateur par son identifiant
	public User findById(Long userId) {
		// Affiche le résultat dans la console à des fins de débogage
		System.out.println("user : " + userRepository.findById(userId).orElse(null));

		// Renvoie l'utilisateur trouvé ou null s'il n'existe pas
		return userRepository.findById(userId).orElse(null);
	}
}
