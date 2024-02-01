// Package définissant la structure du projet
package com.sameh.config;

//Annotation indiquant que cette classe est une configuration Spring
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//Importation de classes nécessaires de Spring Security pour la gestion des mots de passe
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//Configuration de la sécurité dans l'application
@Configuration
public class SecurityConfig {
	// Déclaration d'un bean de type PasswordEncoder a Spring pour la gestion des
	// mots de passe
	@Bean
	public PasswordEncoder passwordEncoder() {
		// Utilisation de l'algorithme de hachage bcrypt pour le chiffrement des mots de
		// passe et retour d'une instance de BCryptPasswordEncoder
		return new BCryptPasswordEncoder();
	}
}
