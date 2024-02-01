// Déclaration du package dans lequel se trouve la classe
package com.sameh.entity;

//Importation des annotations nécessaires pour la gestion des entités JPA
import javax.persistence.*;

//Déclaration de la classe User comme une entité persistante
@Entity
public class User {
	// Annotation indiquant que l'attribut suivant est la clé primaire de l'entité
	@Id
	// Annotation spécifiant la stratégie de génération de la clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// Identifiant unique de l'utilisateur

	private String username;// Nom d'utilisateur
	private String password;// Mot de passe de l'utilisateur
	private String role; // Rôle de l'utilisateur, peut être "ADMIN" ou "USER"

	
	// Constructeur par défaut (obligatoire pour les entités JPA)
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	 // Constructeur avec paramètres pour initialiser les attributs de l'objet
	public User(Long id, String username, String password, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	 // Méthode de conversion de l'objet en chaîne pour l'affichage ou le débogage
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", role=" + role + "]";
	}
}
