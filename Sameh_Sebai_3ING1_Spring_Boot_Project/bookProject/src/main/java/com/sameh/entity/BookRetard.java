// Déclaration du package dans lequel se trouve la classe
package com.sameh.entity;

//Importation des annotations JPA pour la gestion des entités
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

//Déclaration de la classe BookRetard comme une entité persistante
@Entity
public class BookRetard {

	// Annotation indiquant que l'attribut suivant est la clé primaire de l'entité
	@Id
	private int id;// Identifiant unique du retard de livre
	private int nbr_days_to_return; // Nombre de jours de retard

	// Annotation indiquant une relation Many-to-One avec l'entité UserBook
	@ManyToOne
	// Annotation spécifiant la colonne qui fait référence à l'entité UserBook dans
	// la base de données
	@JoinColumn(name = "my_list_book_id")
	private UserBook userBook;// Livre associé au retard

	// Constructeur par défaut (obligatoire pour les entités JPA)
	public BookRetard() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Constructeur avec paramètres pour initialiser les attributs de l'objet
	public BookRetard(int id, int nbr_days_to_return, UserBook userBook) {
		super();
		this.id = id;
		this.nbr_days_to_return = nbr_days_to_return;
		this.userBook = userBook;
	}

	// Getter pour l'identifiant du retard de livre
	public int getId() {
		return id;
	}

	// Setter pour l'identifiant du retard de livre
	public void setId(int id) {
		this.id = id;
	}

	// Getter pour le nombre de jours de retard
	public int getNbr_days_to_return() {
		return nbr_days_to_return;
	}

	// Setter pour le nombre de jours de retard
	public void setNbr_days_to_return(int nbr_days_to_return) {
		this.nbr_days_to_return = nbr_days_to_return;
	}

	// Getter pour le livre associé au retard
	public UserBook getUserBook() {
		return userBook;
	}

	// Setter pour le livre associé au retard
	public void setUserBook(UserBook userBook) {
		this.userBook = userBook;
	}

	// Méthode de conversion de l'objet en chaîne pour l'affichage ou le débogage
	@Override
	public String toString() {
		return "BookRetard [id=" + id + ", nbr_days_to_return=" + nbr_days_to_return + ", userBook=" + userBook + "]";
	}

}
