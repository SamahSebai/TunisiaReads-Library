// Déclaration du package dans lequel se trouve la classe
package com.sameh.entity;

//Importation des annotations JPA pour la gestion des entités
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Déclaration de la classe Book comme une entité persistante
@Entity
public class Book {

	// Annotation indiquant que l'attribut suivant est la clé primaire de l'entité
	@Id
	// Annotation spécifiant la stratégie de génération de la clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;// Identifiant unique du livre
	private String name; // Nom du livre
	private String price;// Prix du livre
	private String gender;// Genre du livre
	private Boolean available;// Disponibilité du livre

	// Annotation indiquant une relation Many-to-One avec l'entité Author
	@ManyToOne
	// Annotation spécifiant la colonne qui fait référence à l'entité Author dans la
	// base de données
	@JoinColumn(name = "author_id")
	
	private Author author;// Auteur du livre

	// Constructeur par défaut (obligatoire pour les entités JPA)
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructeur avec paramètres pour initialiser les attributs de l'objet
	public Book(int id, String name, String price, String gender, Boolean available, Author author) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.gender = gender;
		this.available = available;
		this.author = author;
	}
	// Getter pour l'identifiant
	public int getId() {
		return id;
	}

	// Setter pour l'identifiant
	public void setId(int id) {
		this.id = id;
	}
	// Getter pour le nom du livre
	public String getName() {
		return name;
	}
	// Setter pour le nom du livre
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPrice() {
		return price;
	}
	
	public void setPrice(String price) {
		this.price = price;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	// Getter pour la disponibilité du livre
	public Boolean getAvailable() {
		return available;
	}
	// Setter pour la disponibilité du livre
	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}
	// Méthode de conversion de l'objet en chaîne pour l'affichage ou le débogage
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", price=" + price + ", gender=" + gender + ", available="
				+ available + ", author=" + author + "]";
	}

}
