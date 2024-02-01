package com.bookStore;

// Importation de la classe de test de JUnit 5
import org.junit.jupiter.api.Test;

// Importation de l'annotation SpringBootTest
import org.springframework.boot.test.context.SpringBootTest;

// Annotation indiquant que c'est une classe de test pour Spring Boot
@SpringBootTest
class BookStoreApplicationTests {

	// Méthode de test
	@Test
	void contextLoads() {
		// Ce test vérifie simplement que le contexte de l'application peut être chargé
		// avec succès.
		// Il ne contient actuellement aucune logique de test spécifique.
	}
}
