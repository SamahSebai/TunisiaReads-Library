// Ce fichier est le point d'entrée de l'application Spring Boot.
// Il est responsable du démarrage de l'application.

// Le package dans lequel se trouve cette classe. Utile pour organiser le code.

package com.sameh;

//Importation de classes de Spring Boot nécessaires pour la configuration de l'application.
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//Annotation indiquant que c'est une classe de configuration principale pour Spring Boot.
//Elle englobe les annotations @Configuration, @EnableAutoConfiguration et @ComponentScan.
@SpringBootApplication
public class ProjectBookApplication {
	// Méthode principale pour démarrer l'application Spring Boot.
	public static void main(String[] args) {
		// Cette méthode statique lance l'application en utilisant la classe principale
		// et les arguments fournis.
		SpringApplication.run(ProjectBookApplication.class, args);
	}

}
