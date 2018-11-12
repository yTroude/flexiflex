-- --------------------------------------------------------
-- Hôte :                        127.0.0.1
-- Version du serveur:           8.0.12 - MySQL Community Server - GPL
-- SE du serveur:                Win64
-- HeidiSQL Version:             9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


# -- Export de la structure de la base pour flexiflex
# CREATE DATABASE IF NOT EXISTS `flexiflex` /*!40100 DEFAULT CHARACTER SET utf8 */;
# USE `flexiflex`;
#
# -- Export de la structure de la table flexiflex. acteur
# CREATE TABLE IF NOT EXISTS `acteur` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `nom` varchar(255) NOT NULL,
#   `prenom` varchar(255) NOT NULL,
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# -- Les données exportées n'étaient pas sélectionnées.
# -- Export de la structure de la table flexiflex. genre
# CREATE TABLE IF NOT EXISTS `genre` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `genre` varchar(50) NOT NULL DEFAULT '0',
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table flexiflex. produit
# CREATE TABLE IF NOT EXISTS `produit` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `url` varchar(255) NOT NULL,
#   `titre` varchar(255) NOT NULL,
#   `synopsis` mediumtext NOT NULL,
#   `age_minimum` int(11) NOT NULL,
#   `date_sortie` timestamp NOT NULL,
#   `date_ajout` timestamp NOT NULL,
#   `duree` time NOT NULL,
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# -- Les données exportées n'étaient pas sélectionnées.
# -- Export de la structure de la table flexiflex. produit_acteur
# CREATE TABLE IF NOT EXISTS `produit_acteur` (
#   `acteur_id` int(11) NOT NULL,
#   `produit_id` int(11) NOT NULL,
#   KEY `FK_produit_acteur_produit` (`produit_id`),
#   KEY `FK_produit_acteur_acteur` (`acteur_id`),
#   CONSTRAINT `FK_produit_acteur_acteur` FOREIGN KEY (`acteur_id`) REFERENCES `acteur` (`id`),
#   CONSTRAINT `FK_produit_acteur_produit` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# -- Les données exportées n'étaient pas sélectionnées.
# -- Export de la structure de la table flexiflex. produit_genre
# CREATE TABLE IF NOT EXISTS `produit_genre` (
#   `produit_id` int(11) NOT NULL,
#   `genre_id` int(11) NOT NULL,
#   KEY `FK_produit_genre_produit` (`produit_id`),
#   KEY `FK_produit_genre_genre` (`genre_id`),
#   CONSTRAINT `FK_produit_genre_genre` FOREIGN KEY (`genre_id`) REFERENCES `genre` (`id`),
#   CONSTRAINT `FK_produit_genre_produit` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# -- Les données exportées n'étaient pas sélectionnées.
# -- Export de la structure de la table flexiflex. produit_realisateur
# CREATE TABLE IF NOT EXISTS `produit_realisateur` (
#   `produit_id` int(11) NOT NULL,
#   `realisateur_id` int(11) NOT NULL,
#   KEY `FK_produit_realisateur_produit` (`produit_id`),
#   KEY `FK_produit_realisateur_realisateur` (`realisateur_id`),
#   CONSTRAINT `FK_produit_realisateur_produit` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`),
#   CONSTRAINT `FK_produit_realisateur_realisateur` FOREIGN KEY (`realisateur_id`) REFERENCES `realisateur` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# -- Les données exportées n'étaient pas sélectionnées.
# -- Export de la structure de la table flexiflex. realisateur
# CREATE TABLE IF NOT EXISTS `realisateur` (
#   `id` int(11) NOT NULL AUTO_INCREMENT,
#   `nom` varchar(255) NOT NULL,
#   `prenom` varchar(255) NOT NULL,
#   PRIMARY KEY (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Les données exportées n'étaient pas sélectionnées.
-- Export de la structure de la table flexiflex. utilisateur
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) NOT NULL,
  `password` char(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# -- Les données exportées n'étaient pas sélectionnées.
# -- Export de la structure de la table flexiflex. utilisateur_produit
# CREATE TABLE IF NOT EXISTS `utilisateur_produit` (
#   `utilisateur_id` int(11) NOT NULL,
#   `produit_id` int(11) NOT NULL,
#   `date` timestamp NOT NULL,
#   KEY `FK_utilisateur_produit_utilisateur` (`utilisateur_id`),
#   KEY `FK_utilisateur_produit_produit` (`produit_id`),
#   CONSTRAINT `FK_utilisateur_produit_produit` FOREIGN KEY (`produit_id`) REFERENCES `produit` (`id`),
#   CONSTRAINT `FK_utilisateur_produit_utilisateur` FOREIGN KEY (`utilisateur_id`) REFERENCES `utilisateur` (`id`)
# ) ENGINE=InnoDB DEFAULT CHARSET=utf8;
#
# -- Les données exportées n'étaient pas sélectionnées.
# /*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
# /*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
# /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
