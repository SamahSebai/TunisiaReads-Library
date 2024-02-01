package com.sameh.controller;

//Import des dépendances nécessaires de Spring Boot
//et des classes du projet

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.sameh.entity.Author;
import com.sameh.entity.Book;
import com.sameh.entity.Comment;
import com.sameh.entity.Note;
import com.sameh.entity.User;
import com.sameh.entity.UserBook;
import com.sameh.service.AuthorService;
import com.sameh.service.BookService;
import com.sameh.service.CommentService;
import com.sameh.service.NoteService;
import com.sameh.service.UserBookService;
import com.sameh.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

//Déclaration du contrôleur
@Controller
public class BookController {

	// Injection des services nécessaires
	@Autowired
	private BookService bookService;

	@Autowired
	private UserBookService userBookService;

	@Autowired
	private UserService userService;

	@Autowired
	private CommentService commentService;

	@Autowired
	private NoteService noteService;

	@Autowired
	private AuthorService authorService;

	// Méthode associée à l'URL "/homeAdmin" pour les utilisateurs de rôle ADMIN
	@GetMapping("/homeAdmin")
	public String homeAdmin(HttpSession session, Model model) {

		// on le récupère id d'user de la session
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// Redirection vers la page notConnected.html si l'utilisateur n'est pas
			// connecté
			return "redirect:/notConnected";
		}

		// Récupérer l'objet User à partir de l'id d'user de la session et ajout des
		// informations au modèle
		User user = userService.findById(userId);

		if (user != null) {

			// Ajouter le nom et le rôle de l'utilisateur au modèle au modèle du page html
			model.addAttribute("username", user.getUsername());
			model.addAttribute("role", user.getRole());

			// redirigez vers la page homeAdmin.html
			return "homeAdmin";
		} else {
			// Si l'utilisateur n'existe pas, rediriger vers la page de login.html
			return "redirect:/login";
		}
	}

	// Méthode associée à l'URL "/homeUser" pour les utilisateurs de rôle USER
	/// on fait appel a ce lien si l'utilisateur de role USER fait un login correct
	@GetMapping("/homeUser")
	public String homeUser(HttpSession session, Model model) {
		// on le récupère id d'user de la session
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// Utilisateur non connecté, rediriger vers la page notConnected.html
			return "redirect:/notConnected";
		}

		// Récupérer l'objet User à partir de l'id d'user de la session
		User user = userService.findById(userId);

		if (user != null) {
			// Ajouter le nom et le rôle de l'utilisateur au modèle du page html
			model.addAttribute("username", user.getUsername());
			model.addAttribute("role", user.getRole());
			// rediriger vers la page de homeUser.html
			return "homeUser";
		} else {
			// Si l'utilisateur n'existe pas, rediriger vers la page de login
			return "redirect:/login";
		}
	}

	// Méthode associée à l'URL "/books" pour obtenir la liste de tous les livres
	@GetMapping("/books")
	public String getAllBook(HttpSession session, Model model) {
		// on le récupère id d'user de la session
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// Redirection vers la page notConnected.html si l'utilisateur n'est pas
			// connecté
			return "redirect:/notConnected";
		}

		// Récupération de l'objet User à partir de l'ID de l'utilisateur
		// ici on le récupère le user avec la méthode findById(userId) dans le service
		// UserService
		// on le récupère id d'user de la session
		User user = userService.findById(userId);

		if (user != null) {

			// Ajouter le nom et le rôle de l'utilisateur au modèle du page html
			model.addAttribute("username", user.getUsername());
			model.addAttribute("role", user.getRole());

			// Récupération de la liste de tous les livres
			List<Book> list = bookService.getAllBook();
			// on Ajoute list au modèle du page html
			model.addAttribute("book", list);
		}

		// ici on redirect vers la page bookList.html
		return "bookList";
	}

	// Méthode associée à l'URL "/save" pour enregistrer un nouveau livre
	// du page bookRegister.html on fait appel à ce lien /save
	// pour enregistrer le nouveau book passent en paramètre de la méthode
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b) {
		// Initialisation de la disponibilité du livre à true par défaut
		// on set le variable available a true par défaut
		b.setAvailable(true);

		// Appel de la méthode save(b) dans le service BookService pour enregistrer le
		// livre
		bookService.save(b);

		// Redirection vers le lien "/books" qui affiche la liste des livres
		return "redirect:/books";
	}

	// Méthode associée à l'URL "/my_books" pour obtenir la liste des livres de
	// l'utilisateur connecté
	// du page listBook.html on fait appel à ce lien /my_books
	// pour ajouter le book dans UserBook ==> my list of books
	@GetMapping("/my_books")
	public String getMyBooks(Model model, HttpSession session) {

		// Récupération de l'ID de l'utilisateur depuis la session
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// Redirection vers la page notConnected.html si l'utilisateur n'est pas
			// connecté
			return "redirect:/notConnected";
		}

		if (userId != null) {

			// Récupération de la liste de tous les livres de l'utilisateur
			List<UserBook> myBookList = userBookService.getAllMyBooks();

			// Filtrage pour obtenir la liste finale des livres de l'utilisateur
			/// créé une liste finale qui prend en valeur les éléments de la liste
			/// mybooklist filter par user
			List<UserBook> finalList = myBookList.stream().filter(myBook -> myBook.getUser().getId().equals(userId))
					.collect(Collectors.toList());

			/// on ajoute la liste finale au modele de la page html
			model.addAttribute("books", finalList);
		}
		// on redirect vers la page myBooks.html
		return "myBooks";
	}

	// Méthode associée à l'URL "/mylist/{id}" pour ajouter un livre à la liste de
	// l'utilisateur
	// du page myBooks.html on fait appel à ce lien /mylist/{id} en passent id du
	// book en parametre
	// pour enregistrer le nouveau book dans UserBook ==> my books list

	@RequestMapping("/mylist/{id}")
	public String getMyList(@PathVariable("id") int id, HttpSession session) {
		// Récupérer le book par id
		Book b = bookService.getBookById(id);
		Long userId = (Long) session.getAttribute("userId");

		if (userId != null) {
			// Récupération de l'utilisateur par ID
			User user = userService.findById(userId);

			// Récupération de la date actuelle pour enregistrer la date de prise du livre
			// on prend la date du system pour enregistrer la date dans la quelle elle prend
			// le livre
			Date date_take = new Date();

			// Création d'un objet UserBook pour enregistrer le livre dans la liste de
			// l'utilisateur
			UserBook mb = new UserBook(b.getId(), b.getName(), b.getPrice(), date_take, b.getAuthor(), user);

			// Enregistrement du livre dans la liste de l'utilisateur
			userBookService.saveMyBooks(mb);

			// Modification de la disponibilité du livre à false
			// ici on modifie le variable Available par false
			b.setAvailable(false);

			// Modification du livre dans la base de données
			bookService.editBook(b);

		}

		// Redirection vers la page /my_books qui affiche la liste des livres de
		// l'utilisateur
		return "redirect:/my_books";
	}

	// Méthode associée à l'URL "/editBook/{id}" pour modifier les informations d'un
	// livre
	// du page mybooks.html on fait appel à ce /editBook/{id} en passent id du book
	// en paramètre pour modifier le book

	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {

		// Récupérer le book par id par la méthode getbookbyid(id) dans service Book
		// Service
		Book book = bookService.getBookById(id);

		// Récupération de la liste des auteurs
		// on charge la liste des authors par la méthode getallauthor() dans le service
		// author Service
		List<Author> list = authorService.getAllAuthor();

		// Ajout du livre et de la liste des auteurs au modèle du HTML
		/// on ajoute la liste des books en modele du html
		model.addAttribute("book", book);
		/// on ajoute la liste des authors en modele du html
		model.addAttribute("authors", list);

		/// on redirige vers la page bookEdit.html
		return "bookEdit";
	}

	// Méthode associée à l'URL "/deleteBook/{id}" pour supprimer un livre par ID
	@RequestMapping("/deleteBook/{id}")
	public String deleteBook(@PathVariable("id") int id) {

		// Suppression du livre par ID
		bookService.deleteById(id);
		// Redirection vers la page /books qui affiche la liste des livres
		return "redirect:/books";
	}

	// Méthode associée à l'URL "/search" pour chercher des livres en fonction d'un
	// terme de recherche
	// du page bookList.html on fait appel à ce lien /search
	// pour chercher un livre en passant en paramètrer le terme
	@GetMapping("/search")
	public String searchBooks(@RequestParam(name = "searchTerm", required = false) String searchTerm, Model model) {

		// Création d'une liste vide pour les résultats de la recherche
		List<Book> searchResults;

		// Si le terme de recherche n'est pas vide ou null
		if (searchTerm != null && !searchTerm.isEmpty()) {

			// Appel de la méthode searchBooks(searchTerm) dans le service BookService
			// pour obtenir les résultats de la recherche
			searchResults = bookService.searchBooks(searchTerm);
		} else {

			// Si aucun terme de recherche n'est fourni, récupération de tous les livres
			searchResults = bookService.getAllBook();
		}
		// Ajout des résultats de la recherche au modèle du HTML
		model.addAttribute("book", searchResults);
		/// redirige vers la page bookList.html
		return "bookList"; // Return the same view that displays the table
	}

	// Méthode associée à l'URL "/history_books" pour obtenir la liste des livres
	// empruntés
	@GetMapping("/history_books")
	public String getHistoryBooks(Model model, HttpSession session) {
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// Redirection vers la page notConnected.html si l'utilisateur n'est pas
			// connecté
			return "redirect:/notConnected";
		}

		User user = userService.findById(userId);

		if (user != null && "ADMIN".equals(user.getRole())) {
			// Récupération de la liste de tous les livres empruntés
			List<UserBook> list = userBookService.getAllMyBooks();

			// Ajout de la liste des livres empruntés au modèle du HTML
			model.addAttribute("books", list);

			// Redirection vers la page historyBooks.html
			return "historyBooks";
		}
		// Redirection vers la page noAccess.html si l'utilisateur n'a pas le rôle ADMIN
		return "redirect:/noAccess";
	}

	// Méthode associée à l'URL "/save_comment/{id}" pour ajouter un commentaire à
	// un livre
	@PostMapping("/save_comment/{id}")
	public String addComment(@ModelAttribute Comment c, @PathVariable("id") int id, HttpSession session) {

		// Récupération de l'ID de l'utilisateur depuis la session
		Long userId = (Long) session.getAttribute("userId");
		// System.out.println("userId: " + userId);

		try {
			if (userId != null) {
				// Récupération du livre par ID
				Book b = bookService.getBookById(id);

				if (b != null) {
					// Récupération de l'utilisateur par ID
					User user = userService.findById(userId);

					// Conversion de LocalDateTime à Date
					LocalDateTime created_at = LocalDateTime.now();
					Date dateCreatedAt = java.sql.Timestamp.valueOf(created_at);

					// Configuration des attributs du commentaire
					c.setCreated_at(dateCreatedAt);
					c.setBook(b);
					c.setUser(user);

					// Enregistrement du commentaire
					commentService.save(c);
				}
			}
		} catch (Exception e) {
			// Gestion des exceptions (journalisation ou rejet)
			e.printStackTrace();
			// Vous voudrez peut-être renvoyer une vue/page d'erreur ici
			return "error";
		}
		// Redirection vers la page /books qui affiche la liste des livres
		return "redirect:/books";
	}

	// Méthode associée à l'URL "/list_comments" pour obtenir la liste de tous les
	// commentaires
	@GetMapping("/list_comments")
	public ModelAndView getAllComments(HttpSession session) {

		// Récupération de la liste de tous les commentaires
		List<Comment> list = commentService.getAllComments();
		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// Redirection vers la page notConnected.html si l'utilisateur n'est pas
			// connecté
			return new ModelAndView("redirect:/notConnected");
		}

		// Configuration du modèle avec la liste des commentaires
		ModelAndView modelAndView = new ModelAndView("commentList");
		modelAndView.addObject("comments", list);
		// Pour tester dans commentList.html si l'ID de l'utilisateur est égal à
		// comment.user.id, alors autoriser le bouton de suppression
		modelAndView.addObject("userId", userId);

		return modelAndView;
	}

	// Méthode associée à l'URL "/deleteComment/{id}" pour supprimer un commentaire
	// par ID
	@RequestMapping("/deleteComment/{id}")
	public String deleteComment(@PathVariable("id") Long id) {
		// Suppression du commentaire par ID
		commentService.deleteById(id);
		// Redirection vers la page /list_comments qui affiche la liste des commentaires
		return "redirect:/list_comments";
	}

	// Méthode associée à l'URL "/deleteNote/{id}" pour supprimer une note par ID
	@RequestMapping("/deleteNote/{id}")
	public String deleteNote(@PathVariable("id") int id) {
		// Suppression de la note par ID
		noteService.deleteById(id);
		// Redirection vers la page /list_notes qui affiche la liste des notes
		return "redirect:/list_notes";
	}

	// Méthode associée à l'URL "/save_note/{id}" pour ajouter une note à un livre
	@PostMapping("/save_note/{id}")
	public String addNote(@ModelAttribute Note n, @PathVariable("id") int id, HttpSession session) {
		// Récupération de la liste de toutes les notes
		List<Note> list = noteService.getAllNotes();
		// Synchronisation de l'ID de la note, sinon elle fait une mise à jour
		n.setId(list.size() + 1);
		// Récupération de l'ID de l'utilisateur depuis la session
		Long userId = (Long) session.getAttribute("userId");
		System.out.println("userId: " + userId);

		try {
			if (userId != null) {
				// Récupération du livre par ID
				Book b = bookService.getBookById(id);

				if (b != null) {
					// Récupération de l'utilisateur par ID
					User user = userService.findById(userId);

					// Conversion de LocalDateTime à Date
					LocalDateTime created_at = LocalDateTime.now();
					Date dateCreatedAt = java.sql.Timestamp.valueOf(created_at);
					// Configuration des attributs de la note
					n.setCreated_at(dateCreatedAt);
					n.setBook(b);
					n.setUser(user);

					System.out.println(list);

					// Calcul de la note moyenne
					int nbr_notes = 0;
					float somme_notes = 0;

					for (int i = 0; i < list.size(); i++) {
						nbr_notes += 1;
						somme_notes = somme_notes + list.get(i).getNote();
					}
					// Initialisation de la moyenne

					float moyen = 0;

					// Pour une nouvelle note
					nbr_notes += 1;

					somme_notes = somme_notes + n.getNote();
					if (nbr_notes > 0) {
						moyen = somme_notes / nbr_notes;
						n.setAverage(moyen);
					}

					// Enregistrement de la note
					noteService.save(n);
				}
			}
		} catch (Exception e) {
			// Gestion des exceptions (journalisation ou rejet)
			e.printStackTrace();
			// Vous voudrez peut-être renvoyer une vue/page d'erreur ici
			return "error";
		}
		// Redirection vers la page /books qui affiche la liste des livres
		return "redirect:/books";
	}

	// Méthode associée à l'URL "/list_notes" pour obtenir la liste de toutes les
	// notes
	@GetMapping("/list_notes")
	public ModelAndView getAllNotess(HttpSession session) {

		Long userId = (Long) session.getAttribute("userId");

		if (userId == null) {
			// Redirection vers la page notConnected.html si l'utilisateur n'est pas
			// connecté
			return new ModelAndView("redirect:/notConnected");
		}
		// Récupération de la liste de toutes les notes
		List<Note> list = noteService.getAllNotes();

		System.out.println(list);

		// Configuration du modèle avec la liste des notes
		ModelAndView modelAndView = new ModelAndView("noteList");
		modelAndView.addObject("notes", list);

		return modelAndView;
	}

}
