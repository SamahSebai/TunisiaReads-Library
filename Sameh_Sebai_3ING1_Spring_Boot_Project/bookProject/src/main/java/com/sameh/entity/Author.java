// Déclaration du package dans lequel se trouve la classe
package com.sameh.entity;

// Importation de l'annotation pour le formatage de la date et de la classe Date
import org.springframework.format.annotation.DateTimeFormat;

// Importation des annotations JPA pour la gestion des entités
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.util.Date; // Importation de la classe Date pour représenter la date en Java

// Déclaration de la classe Author comme une entité persistante
@Entity
public class Author {

	// Annotation indiquant que l'attribut suivant est la clé primaire de l'entité
	@Id
	// Annotation spécifiant la stratégie de génération de la clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; // Identifiant unique de l'auteur

	private String name; // Nom de l'auteur
	private String nationality; // Nationalité de l'auteur

	// Annotation indiquant le format de la date lors de la conversion depuis une
	// chaîne
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date_birth; // Date de naissance de l'auteur

	// Constructeur par défaut (obligatoire pour les entités JPA)
	public Author() {
		super();
	}

	// Constructeur avec paramètres pour initialiser les attributs de l'objet
	public Author(int id, String name, String nationality, Date date_birth) {
		super();
		this.id = id;
		this.name = name;
		this.nationality = nationality;
		this.date_birth = date_birth;
	}

	// Getter pour l'identifiant
	public int getId() {
		return id;
	}

	// Setter pour l'identifiant
	public void setId(int id) {
		this.id = id;
	}

	// Getter pour le nom de l'auteur
	public String getName() {
		return name;
	}

	// Setter pour le nom de l'auteur
	public void setName(String name) {
		this.name = name;
	}

	// Getter pour la nationalité de l'auteur
	public String getNationality() {
		return nationality;
	}

	// Setter pour la nationalité de l'auteur
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	// Getter pour la date de naissance de l'auteur
	public Date getDate_birth() {
		return date_birth;
	}

	// Setter pour la date de naissance de l'auteur
	public void setDate_birth(Date date_birth) {
		this.date_birth = date_birth;
	}

	// Méthode de conversion de l'objet en chaîne pour l'affichage ou le débogage
	@Override
	public String toString() {
		return "Author [id=" + id + ", name=" + name + ", nationality=" + nationality + ", date_birth=" + date_birth
				+ "]";
	}
}
