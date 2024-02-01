// Déclaration du package dans lequel se trouve la classe
package com.sameh.controller;

//Importation des classes nécessaires pour la gestion des sessions, l'injection de dépendance, le contrôleur Spring, le modèle, etc.
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import com.sameh.entity.User;// Importation de la classe User du package com.sameh.entity
import com.sameh.service.UserService;// Importation de la classe UserService du package com.sameh.service
//Déclaration de la classe UserController qui agit en tant que contrôleur dans le modèle MVC

@Controller
public class UserController {
	// Injection de dépendance de la classe UserService via l'annotation @Autowired
	@Autowired
	private UserService userService;

	// Déclaration de l'encodeur de mots de passe
	private final PasswordEncoder passwordEncoder;

	// Injection de l'encodeur de mots de passe dans la classe via le constructeur
	// Inject the PasswordEncoder into your class (e.g., via the constructor)
	public UserController(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	// Gestion de la requête HTTP GET pour afficher le formulaire d'inscription
	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		// Retourne le nom de la vue "register"
		return "register";
	}

	// Gestion de la requête HTTP GET pour la page "notConnected"
	@GetMapping("/notConnected")
	public String notConnected() {
		// Retourne le nom de la vue "notConnected"
		return "notConnected";
	}

	// Gestion de la requête HTTP GET pour la page "noAccess"
	@GetMapping("/noAccess")
	public String noAccess() {
		// Retourne le nom de la vue "noAccess"
		return "noAccess";
	}

	// Gestion de la requête HTTP GET pour afficher le formulaire de connexion
	@GetMapping("/login")
	public String showLoginForm() {
		// Retourne le nom de la vue "login"
		return "login";
	}

	// Gestion de la requête HTTP POST pour l'inscription d'un utilisateur
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") User user) {
		// Chiffrement du mot de passe avec le PasswordEncoder
		String hashedPassword = passwordEncoder.encode(user.getPassword());

		// Configuration du rôle et du mot de passe chiffré pour l'utilisateur
		user.setRole("USER");
		user.setPassword(hashedPassword);

		// Sauvegarde de l'utilisateur dans la base de données
		userService.saveUser(user);

		// Redirection vers la page de connexion après l'inscription
		return "redirect:/login";
	}

	// Gestion de la requête HTTP POST pour la connexion d'un utilisateur
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, HttpSession session,
			Model model) {
		// Recherche de l'utilisateur dans la base de données par son nom d'utilisateur
		User user = userService.findByUsername(username);

		
		if (user != null) {
			// Vérification du mot de passe avec l'encodeur de mots de passe
			if (passwordEncoder.matches(password, user.getPassword())) {
				// L'utilisateur est authentifié avec succès
				// Stocker l'ID de l'utilisateur dans la session
				session.setAttribute("userId", user.getId());
				
				// Redirection en fonction du rôle de l'utilisateur
				if ("ADMIN".equals(user.getRole())) {
					return "redirect:/homeAdmin";
				} else {
					return "redirect:/homeUser";
				}
			}
		}

		// Échec de l'authentification
		model.addAttribute("error", "Invalid username or password");
		// Retourne le nom de la vue "login" avec un message d'erreur
		return "login";
	}

	
	 // Gestion de la requête HTTP GET pour la déconnexion d'un utilisateur
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		// Invalider la session
		session.invalidate();
		// Rediriger vers la page de connexion
		return "redirect:/login";
	}
}
