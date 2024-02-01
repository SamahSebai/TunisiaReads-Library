# TunisiaReads Library

## Project Overview

The TunisiaReads Library project is a Spring Boot-based application that allows users to manage information about books, authors, and loans in a library.

## Project Features

### Book Management

- Create, update, and delete book information (title, author, publication year, genre, etc.).
- Display a list of all available books.

### Book Search

- Implement search functionality for users to find books by title, author, or genre.

### Author Management

- Add, update, and delete author information (name, date of birth, nationality, etc.).
- Display a list of all available authors.

### Loan Management

- Record book loans by library users.
- Mark books as borrowed or available.
- Display loan history for each user.

### Late Returns and Fines

- Implement a system to track late returns for books not returned on time.
- Automatically calculate fines based on the duration of the delay.

### Security Management

- Add a security layer with Spring Security to protect access to management features.
- Configure roles such as "USER" and "ADMINISTRATOR."

### Data Validation

- Implement validations to ensure that entered data complies with rules (e.g., a book must have a title, an author, etc.).

### Documentation

- Document the source code using clear Java comments.
- Add Swagger comments for automatic API documentation generation.

### Evaluation and Comments

- Allow users to rate and comment on books.
- Display average ratings and comments on each book's page.

## Technologies Used

- Spring Boot: for creating a Java-based application using Spring.
- Thymeleaf: for HTML templates.
- Hibernate (JPA): for data persistence.
- Spring Security: for authentication and authorization.
- Spring MVC: for handling web requests.
- Bootstrap: for design and layout.
- Database: MySQL or H2 to store information about books, users, and reservations.

## Database Configuration

- XAMPP is used for the database. Set up your database using the provided `application.properties` file

**Home User page**

![MakeSense image selection page](https://github.com/SamahSebai/TunisiaReads-Library/blob/main/capture%20d'ecran/home%20user.png)

**Home Admin page**

![MakeSense image selection page](https://github.com/SamahSebai/TunisiaReads-Library/blob/main/capture%20d'ecran/page%20home%20admin.png)

**book Admin page**

![MakeSense image selection page](https://github.com/SamahSebai/TunisiaReads-Library/blob/main/capture%20d'ecran/page%20book%20admin.png)


