// Déclaration du package dans lequel se trouve la classe
package com.sameh.entity;

//Importation des classes nécessaires pour la gestion des entités JPA et les annotations associées
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//Déclaration de la classe Comment comme une entité persistante
@Entity
public class Comment {
	// Annotation indiquant que l'attribut suivant est la clé primaire de l'entité
	@Id
	// Annotation spécifiant la stratégie de génération de la clé primaire
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;// Identifiant unique du commentaire

	private String texte;// Contenu textuel du commentaire
	private Date created_at;// Date de création du commentaire
	private Date updated_at;// Date de mise à jour du commentaire

	// Annotation indiquant une relation One-to-One avec l'entité User
	@OneToOne
	// Annotation spécifiant la colonne qui fait référence à l'entité User dans la
	// base de données
	@JoinColumn(name = "user_id")
	private User user; // Utilisateur associé au commentaire

	// Annotation indiquant une relation One-to-One avec l'entité Book
	@OneToOne
	// Annotation spécifiant la colonne qui fait référence à l'entité Book dans la
	// base de données
	@JoinColumn(name = "book_id")
	private Book book;// Livre associé au commentaire

	// Constructeur par défaut (obligatoire pour les entités JPA)
	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructeur avec paramètres pour initialiser les attributs de l'objet
	public Comment(Long id, String texte, Date created_at, Date updated_at, User user, Book book) {
		super();
		this.id = id;
		this.texte = texte;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.user = user;
		this.book = book;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexte() {
		return texte;
	}

	public void setTexte(String texte) {
		this.texte = texte;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	public Date getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Date updated_at) {
		this.updated_at = updated_at;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	// Méthode de conversion de l'objet en chaîne pour l'affichage ou le débogage
	@Override
	public String toString() {
		return "Comment [id=" + id + ", texte=" + texte + ", created_at=" + created_at + ", updated_at=" + updated_at
				+ ", user=" + user + ", book=" + book + "]";
	}

}
