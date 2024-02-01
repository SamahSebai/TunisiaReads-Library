// Déclaration du package dans lequel se trouve la classe
package com.sameh.entity;

//Importation des annotations nécessaires pour la gestion des entités JPA
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Déclaration de la classe UserBook comme une entité persistante
@Entity
public class UserBook {

	// Annotation indiquant que l'attribut suivant est la clé primaire de l'entité
	@Id
	// Identifiant unique du livre emprunté par l'utilisateur
	private int id;
	private String name;// Nom du livre
	private String price;// Prix du livre
	private Date date_take;// Date à laquelle l'utilisateur a emprunté le livre

	// Annotation indiquant une relation Many-to-One avec l'entité Author
	@ManyToOne

	// Annotation spécifiant la colonne qui fait référence à l'entité Author dans la
	// base de données
	@JoinColumn(name = "author_id")
	private Author author;// Auteur du livre emprunté

	// Annotation indiquant une relation Many-to-One avec l'entité User
	@ManyToOne
	// Annotation spécifiant la colonne qui fait référence à l'entité User dans la
	// base de données
	@JoinColumn(name = "user_id")
	private User user;// Utilisateur qui a emprunté le livre

	// Constructeur par défaut (obligatoire pour les entités JPA)
	public UserBook() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructeur avec paramètres pour initialiser les attributs de l'objet
	public UserBook(int id, String name, String price, Date date_take, Author author, User user) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.date_take = date_take;
		this.author = author;
		this.user = user;
	}

	// Getter pour l'identifiant du livre emprunté
	public int getId() {
		return id;
	}

	// Setter pour l'identifiant du livre emprunté
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

	// Getter pour le prix du livre
	public String getPrice() {
		return price;
	}

	// Setter pour le prix du livre
	public void setPrice(String price) {
		this.price = price;
	}

	// Getter pour la date à laquelle le livre a été emprunté
	public Date getDate_take() {
		return date_take;
	}

	// Setter pour la date à laquelle le livre a été emprunté
	public void setDate_take(Date date_take) {
		this.date_take = date_take;
	}

	// Getter pour l'auteur du livre emprunté
	public Author getAuthor() {
		return author;
	}

	// Setter pour l'auteur du livre emprunté
	public void setAuthor(Author author) {
		this.author = author;
	}

	// Getter pour l'utilisateur qui a emprunté le livre
	public User getUser() {
		return user;
	}

	// Setter pour l'utilisateur qui a emprunté le livre
	public void setUser(User user) {
		this.user = user;
	}

	 // Méthode de conversion de l'objet en chaîne pour l'affichage ou le débogage
	@Override
	public String toString() {
		return "UserBook [id=" + id + ", name=" + name + ", price=" + price + ", date_take=" + date_take + ", author="
				+ author + ", user=" + user + "]";
	}

}