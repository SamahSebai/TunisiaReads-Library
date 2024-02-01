-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : mer. 17 jan. 2024 à 18:38
-- Version du serveur : 10.4.32-MariaDB
-- Version de PHP : 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `bookdb`
--

-- --------------------------------------------------------

--
-- Structure de la table `author`
--

CREATE TABLE `author` (
  `id` int(11) NOT NULL,
  `date_birth` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `author`
--

INSERT INTO `author` (`id`, `date_birth`, `name`, `nationality`) VALUES
(5, '1965-07-31 00:00:00.000000', 'J.K. Rowling', 'Britannique'),
(6, '1949-01-12 00:00:00.000000', 'Haruki Murakami', 'Japonaise'),
(7, '1977-09-15 00:00:00.000000', 'Chimamanda Ngozi Adichie', 'Nigériane');

-- --------------------------------------------------------

--
-- Structure de la table `book`
--

CREATE TABLE `book` (
  `id` int(11) NOT NULL,
  `available` bit(1) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `book`
--

INSERT INTO `book` (`id`, `available`, `gender`, `name`, `price`, `author_id`) VALUES
(4, b'1', 'Fantasy', 'Harry Potter and the Philosopher\'s Stone', '20.99 DT', 5),
(5, b'0', 'Fiction gender', 'Norwegian Wood', '18.50 DT', 5),
(6, b'1', 'Historical Fiction', 'Half of a Yellow Sun', '22.95 DT', 7);

-- --------------------------------------------------------

--
-- Structure de la table `book_retard`
--

CREATE TABLE `book_retard` (
  `id` int(11) NOT NULL,
  `nbr_days_to_return` int(11) NOT NULL,
  `my_list_book_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `book_retard`
--

INSERT INTO `book_retard` (`id`, `nbr_days_to_return`, `my_list_book_id`) VALUES
(5, 1, 5);

-- --------------------------------------------------------

--
-- Structure de la table `comment`
--

CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `texte` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `comment`
--

INSERT INTO `comment` (`id`, `created_at`, `texte`, `updated_at`, `book_id`, `user_id`) VALUES
(5, '2024-01-17 18:26:52.000000', 'good', NULL, 5, 2),
(6, '2024-01-17 18:27:03.000000', 'nice', NULL, 6, 2);

-- --------------------------------------------------------

--
-- Structure de la table `note`
--

CREATE TABLE `note` (
  `id` int(11) NOT NULL,
  `average` float DEFAULT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `note` float DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `book_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `note`
--

INSERT INTO `note` (`id`, `average`, `created_at`, `note`, `updated_at`, `book_id`, `user_id`) VALUES
(1, 5, '2024-01-17 18:26:46.000000', 5, NULL, 4, 2),
(2, 11.5, '2024-01-17 18:26:57.000000', 18, NULL, 5, 2),
(3, 12.6667, '2024-01-17 18:27:09.000000', 15, NULL, 6, 2);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `password`, `role`, `username`) VALUES
(1, '$2a$10$LbkaC3d/c1bEBpo3sQhzSO3wHb2lnQ4DpXPPR2ahFpneMPZQMt7Fy', 'ADMIN', 'sameh'),
(2, '$2a$10$ctUrNu4SsSLDY9aQSY6atOlFgc4odH6xCRmlH6LIWNNwH.Nz2aWIG', 'USER', 'asma');

-- --------------------------------------------------------

--
-- Structure de la table `user_book`
--

CREATE TABLE `user_book` (
  `id` int(11) NOT NULL,
  `date_take` datetime(6) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` varchar(255) DEFAULT NULL,
  `author_id` int(11) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user_book`
--

INSERT INTO `user_book` (`id`, `date_take`, `name`, `price`, `author_id`, `user_id`) VALUES
(5, '2024-01-17 18:36:00.000000', 'Norwegian Wood', '18.50 DT', 5, 2);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `author`
--
ALTER TABLE `author`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `book`
--
ALTER TABLE `book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKklnrv3weler2ftkweewlky958` (`author_id`);

--
-- Index pour la table `book_retard`
--
ALTER TABLE `book_retard`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKqve1cw1i7wfwonvicist0s5oj` (`my_list_book_id`);

--
-- Index pour la table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkko96rdq8d82wm91vh2jsfak7` (`book_id`),
  ADD KEY `FK8kcum44fvpupyw6f5baccx25c` (`user_id`);

--
-- Index pour la table `note`
--
ALTER TABLE `note`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKrussm9y4vwyp0x6gl8n288ovv` (`book_id`),
  ADD KEY `FKmoddtnuw3yy6ct34xnw6u0boh` (`user_id`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `user_book`
--
ALTER TABLE `user_book`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK3l227fjm11nk8nvatm2vmw7xs` (`author_id`),
  ADD KEY `FKbc0bwdnndnxhct38sinbem0n0` (`user_id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `author`
--
ALTER TABLE `author`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `book`
--
ALTER TABLE `book`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `comment`
--
ALTER TABLE `comment`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `book`
--
ALTER TABLE `book`
  ADD CONSTRAINT `FKklnrv3weler2ftkweewlky958` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`);

--
-- Contraintes pour la table `book_retard`
--
ALTER TABLE `book_retard`
  ADD CONSTRAINT `FKqve1cw1i7wfwonvicist0s5oj` FOREIGN KEY (`my_list_book_id`) REFERENCES `user_book` (`id`);

--
-- Contraintes pour la table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKkko96rdq8d82wm91vh2jsfak7` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);

--
-- Contraintes pour la table `note`
--
ALTER TABLE `note`
  ADD CONSTRAINT `FKmoddtnuw3yy6ct34xnw6u0boh` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKrussm9y4vwyp0x6gl8n288ovv` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`);

--
-- Contraintes pour la table `user_book`
--
ALTER TABLE `user_book`
  ADD CONSTRAINT `FK3l227fjm11nk8nvatm2vmw7xs` FOREIGN KEY (`author_id`) REFERENCES `author` (`id`),
  ADD CONSTRAINT `FKbc0bwdnndnxhct38sinbem0n0` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
