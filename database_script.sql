-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 16 août 2018 à 07:33
-- Version du serveur :  5.7.19
-- Version de PHP :  7.1.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `schooloffice`
--
CREATE DATABASE IF NOT EXISTS `schooloffice` DEFAULT CHARACTER SET armscii8 COLLATE armscii8_general_ci;
USE `schooloffice`;

-- --------------------------------------------------------

--
-- Structure de la table `classes`
--

DROP TABLE IF EXISTS `classes`;
CREATE TABLE IF NOT EXISTS `classes` (
  `classcode` varchar(8) NOT NULL,
  `studentsnumber` int(11) NOT NULL,
  `specialty` enum('science','litheratur') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`classcode`),
  UNIQUE KEY `classcode` (`classcode`)
) ENGINE=MyISAM DEFAULT CHARSET=armscii8;

--
-- Déchargement des données de la table `classes`
--

INSERT INTO `classes` (`classcode`, `studentsnumber`, `specialty`, `created_at`, `updated_at`) VALUES
('2018/1L4', 9, 'litheratur', '2018-08-12 09:01:49', NULL),
('2018/1S1', 10, 'science', '2018-08-12 09:18:54', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `marks`
--

DROP TABLE IF EXISTS `marks`;
CREATE TABLE IF NOT EXISTS `marks` (
  `serialnumber` varchar(30) NOT NULL,
  `modulecode` varchar(30) NOT NULL,
  `attendance` float NOT NULL,
  `test_1` float NOT NULL,
  `test_2` float NOT NULL,
  `exam` float NOT NULL,
  `total` float NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`serialnumber`,`modulecode`)
) ENGINE=MyISAM DEFAULT CHARSET=armscii8;

--
-- Déchargement des données de la table `marks`
--

INSERT INTO `marks` (`serialnumber`, `modulecode`, `attendance`, `test_1`, `test_2`, `exam`, `total`, `created_at`, `updated_at`) VALUES
('201400007516', 'Math', 15, 13, 12.5, 17, 15.25, '2018-08-15 02:02:00', NULL),
('201800000828', 'Math', 13, 15, 16, 12, 13.3333, '2018-08-16 03:45:45', NULL),
('201800000828', 'NSL_s', 18, 13.5, 14.5, 16, 15.6667, '2018-08-16 03:47:11', NULL),
('201800000828', 'Phys_s', 14.5, 10.5, 16.5, 17, 15.4167, '2018-08-16 03:48:22', NULL),
('201800000828', 'HistGeo_s', 16, 15.5, 10.75, 13.25, 13.6667, '2018-08-16 03:49:51', NULL),
('201800000828', 'Eng_s', 18, 16, 15.75, 17.25, 16.9167, '2018-08-16 03:52:03', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `modules`
--

DROP TABLE IF EXISTS `modules`;
CREATE TABLE IF NOT EXISTS `modules` (
  `modulecode` varchar(15) NOT NULL,
  `module` varchar(50) NOT NULL,
  `coefficient` int(11) NOT NULL,
  `specialty` enum('science','litheratur') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP
) ENGINE=MyISAM DEFAULT CHARSET=armscii8;

--
-- Déchargement des données de la table `modules`
--

INSERT INTO `modules` (`modulecode`, `module`, `coefficient`, `specialty`, `created_at`, `updated_at`) VALUES
('Math', 'Mathematic', 7, 'science', '2018-08-10 19:44:14', '2018-08-10 21:25:38'),
('Phys_s', 'physical', 6, 'science', '2018-08-15 16:16:39', NULL),
('NSL_s', 'Natural Science and Life', 6, 'science', '2018-08-15 16:35:35', NULL),
('HistGeo_s', 'History and Geography', 3, 'science', '2018-08-15 16:40:49', NULL),
('Eng_s', 'English', 3, 'science', '2018-08-15 16:41:43', NULL),
('Eng_l', 'English literature', 6, 'litheratur', '2018-08-16 03:25:11', NULL),
('phil_l', 'philosophy', 5, 'litheratur', '2018-08-16 03:25:59', NULL),
('HistGeo_l', 'History and Geography', 5, 'litheratur', '2018-08-16 03:26:32', NULL),
('Math_l', 'Mathematic', 3, 'litheratur', '2018-08-16 03:27:05', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `serialnumber` varchar(12) NOT NULL,
  `birthDate` varchar(15) NOT NULL,
  `specialty` enum('science','litheratur') DEFAULT NULL,
  `classcode` varchar(8) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`serialnumber`)
) ENGINE=MyISAM DEFAULT CHARSET=armscii8;

--
-- Déchargement des données de la table `students`
--

INSERT INTO `students` (`firstname`, `lastname`, `serialnumber`, `birthDate`, `specialty`, `classcode`, `created_at`) VALUES
('Parto', 'Bamford', '201800000828', '2001-12-03', 'science', '2018/1S1', '2018-08-15 15:02:40'),
('Bezalel', 'Simmel', '201800001121', '2002-06-02', 'science', '2018/1S1', '2018-08-15 15:01:44'),
('Georgi', 'Facello', '201800000626', '2001-09-02', 'science', '2018/1S1', '2018-08-15 15:00:32'),
('Chirstian', 'Koblick', '201800001201', '2002-05-01', 'science', '2018/1S1', '2018-08-15 15:03:46'),
('Kyoichi', 'Maliniak', '201800001912', '2001-01-21', 'science', '2018/1S1', '2018-08-15 15:05:05'),
('Anneke', 'Preusig', '201800001602', '2001-04-20', 'science', '2018/1S1', '2018-08-15 15:06:14'),
('Tzvetan', 'Zielinski', '201800001210', '2001-05-23', 'science', '2018/1S1', '2018-08-15 15:07:11'),
('Saniya', 'Kalloufi', '201800001915', '2001-02-19', 'science', '2018/1S1', '2018-08-15 15:08:17'),
('Sumant', 'Peac', '201800001218', '2002-04-19', 'science', '2018/1S1', '2018-08-15 15:09:01'),
('Duangkaew', 'Piveteau', '201800001824', '2001-06-01', 'science', '2018/1S1', '2018-08-15 15:09:56'),
('Mary', 'Sluis', '201800001122', '2001-11-07', 'litheratur', '2018/1L4', '2018-08-15 15:54:37'),
('Patricio', 'Bridgland', '201800001418', '2001-10-04', 'litheratur', '2018/1L4', '2018-08-15 15:56:46'),
('Eberhardt', 'Terkki', '201800001020', '2001-06-07', 'litheratur', '2018/1L4', '2018-08-15 15:57:33'),
('Berni', 'Genin', '201800001311', '2002-02-12', 'litheratur', '2018/1L4', '2018-08-15 15:58:38'),
('Guoxiang', 'Nooteboom', '201800001702', '2001-08-19', 'litheratur', '2018/1L4', '2018-08-15 15:59:28'),
('Kazuhito', 'Cappelletti', '201800001127', '2002-05-02', 'litheratur', '2018/1L4', '2018-08-15 16:01:02'),
('Cristinel', 'Bouloucos', '201800001803', '2001-07-06', 'litheratur', '2018/1L4', '2018-08-15 16:01:42'),
('Kazuhide', 'Peha', '201800001403', '2001-06-19', 'litheratur', '2018/1L4', '2018-08-15 16:03:51'),
('Lillian', 'Haddadi', '201800001614', '2002-01-23', 'litheratur', '2018/1L4', '2018-08-15 16:14:06');

--
-- Déclencheurs `students`
--
DROP TRIGGER IF EXISTS `decrease_students_number`;
DELIMITER $$
CREATE TRIGGER `decrease_students_number` AFTER DELETE ON `students` FOR EACH ROW BEGIN
UPDATE classes SET studentsnumber = studentsnumber - 1 WHERE Old.classcode = classcode;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `increase_student_number`;
DELIMITER $$
CREATE TRIGGER `increase_student_number` AFTER INSERT ON `students` FOR EACH ROW BEGIN
UPDATE classes SET studentsnumber = studentsnumber + 1 WHERE New.classcode = classcode;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `updaate_students_number`;
DELIMITER $$
CREATE TRIGGER `updaate_students_number` AFTER UPDATE ON `students` FOR EACH ROW BEGIN
IF New.classcode <> Old.classcode
THEN
UPDATE classes SET studentsnumber = studentsnumber + 1 WHERE New.classcode = classcode;
UPDATE classes SET studentsnumber = studentsnumber - 1 WHERE Old.classcode = classcode;
END IF ;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Structure de la table `teachers`
--

DROP TABLE IF EXISTS `teachers`;
CREATE TABLE IF NOT EXISTS `teachers` (
  `codeteacher` varchar(50) NOT NULL,
  `firstname` varchar(50) NOT NULL,
  `lastname` varchar(50) NOT NULL,
  `modulecode` varchar(20) NOT NULL,
  `classcode` varchar(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`codeteacher`)
) ENGINE=MyISAM DEFAULT CHARSET=armscii8;

--
-- Déchargement des données de la table `teachers`
--

INSERT INTO `teachers` (`codeteacher`, `firstname`, `lastname`, `modulecode`, `classcode`, `created_at`, `updated_at`) VALUES
('2018000082', 'Mayuko', 'Warwick', 'Math', '2018/1S1', '2018-08-16 03:11:02', NULL),
('2018000072', 'Ramzi', 'Erde', 'Phys_s', '2018/1S1', '2018-08-16 03:12:02', NULL),
('2018000083', 'Shahaf', 'Famili', 'NSL_s', '2018/1S1', '2018-08-16 03:14:04', NULL),
('2018000076', 'Bojan', 'Montemayor', 'HistGeo_s', '2018/1S1', '2018-08-16 03:15:06', NULL),
('20180000931', 'Suzette', 'Pettey', 'Eng_s', '2018/1S1', '2018-08-16 03:16:31', NULL),
('201800001146', 'Prasadram', 'Heyers', 'Eng_l', '2018/1L4', '2018-08-16 03:29:46', NULL),
('201800001029', 'Yongqiao', 'Berztiss', 'phil_l', '2018/1L4', '2018-08-16 03:33:29', NULL),
('2018000086', 'Divier', 'Reistad', 'HistGeo_l', '2018/1L4', '2018-08-16 03:34:06', NULL),
('2018000070', 'Elvis', 'Demeyer', 'Math_l', '2018/1L4', '2018-08-16 03:35:00', NULL);

-- --------------------------------------------------------

--
-- Structure de la table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `code` varchar(50) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(20) NOT NULL,
  `type` varchar(10) NOT NULL DEFAULT 'user',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `code` (`code`)
) ENGINE=MyISAM DEFAULT CHARSET=armscii8;

--
-- Déchargement des données de la table `users`
--

INSERT INTO `users` (`code`, `username`, `password`, `type`, `created_at`, `updated_at`) VALUES
('201800006', 'user46', 'user123', 'user', '2018-08-10 23:42:27', NULL),
('20180000530', 'admin', 'admin123', 'user', '2018-08-10 23:55:31', NULL),
('2018000055', 'UserT', 'usert123', 'teacher', '2018-08-13 13:55:05', NULL),
('2018000082', 'Mayuko_W', 'azert1234', 'teacher', '2018-08-16 03:11:02', NULL),
('2018000072', 'Ramzi_E', 'azert1234', 'teacher', '2018-08-16 03:12:02', NULL),
('2018000083', 'Shahaf_F', 'azert1234', 'teacher', '2018-08-16 03:14:03', NULL),
('2018000076', 'Bojan_M', 'azert1234', 'teacher', '2018-08-16 03:15:06', NULL),
('20180000931', 'Suzette_P', 'azert1234', 'teacher', '2018-08-16 03:16:31', NULL),
('201800001146', 'Prasadram_H', 'azert1234', 'teacher', '2018-08-16 03:29:46', NULL),
('201800001029', 'Yongqiao_B', 'azert1234', 'teacher', '2018-08-16 03:33:29', NULL),
('2018000086', 'Divier_R', 'azert1234', 'teacher', '2018-08-16 03:34:06', NULL),
('2018000070', 'Elvis_D', 'azert1234', 'teacher', '2018-08-16 03:35:00', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



