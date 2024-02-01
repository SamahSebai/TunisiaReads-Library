// Déclaration du package dans lequel se trouve la classe
package com.sameh.entity;

//Importation des classes nécessaires pour la gestion des entités JPA et les annotations associées
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

//Déclaration de la classe Note comme une entité persistante
@Entity
public class Note {

	// Annotation indiquant que l'attribut suivant est la clé primaire de l'entité
	@Id
	private int id;// Identifiant unique de la note

	private Float note;// Note attribuée
	private Float average;// Moyenne des notes
	private Date created_at;// Date de création de la note
	private Date updated_at;// Date de mise à jour de la note

	// Annotation indiquant une relation One-to-One avec l'entité User
	@OneToOne
	// Annotation spécifiant la colonne qui fait référence à l'entité User dans la
	// base de données
	@JoinColumn(name = "user_id")
	private User user; // Utilisateur associé à la note

	// Annotation indiquant une relation One-to-One avec l'entité Book
	@OneToOne
	// Annotation spécifiant la colonne qui fait référence à l'entité Book dans la
	// base de données
	@JoinColumn(name = "book_id")// Livre associé à la note
	private Book book;

	 // Constructeur par défaut (obligatoire pour les entités JPA)
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	 // Constructeur avec paramètres pour initialiser les attributs de l'objet
	public Note(int id, Float note, Float average, Date created_at, Date updated_at, User user, Book book) {
		super();
		this.id = id;
		this.note = note;
		this.average = average;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.user = user;
		this.book = book;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Float getNote() {
		return note;
	}

	public void setNote(Float note) {
		this.note = note;
	}

	public Float getAverage() {
		return average;
	}

	public void setAverage(Float average) {
		this.average = average;
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
		return "Note [id=" + id + ", note=" + note + ", average=" + average + ", created_at=" + created_at
				+ ", updated_at=" + updated_at + ", user=" + user + ", book=" + book + "]";
	}

}
