-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : sam. 29 août 2020 à 19:31
-- Version du serveur :  5.7.29-0ubuntu0.18.04.1
-- Version de PHP : 7.3.16-1+ubuntu18.04.1+deb.sury.org+1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `championat`
--

-- --------------------------------------------------------

--
-- Structure de la table `buts`
--

CREATE TABLE `buts` (
  `id` int(11) NOT NULL,
  `minute` int(11) DEFAULT NULL,
  `joueur_id` int(11) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `buts`
--

INSERT INTO `buts` (`id`, `minute`, `joueur_id`, `match_id`) VALUES
(1, 40, 17, 9),
(4, 47, 17, 9),
(5, 35, 14, 9),
(6, 36, 14, 9),
(7, 10, 17, 9),
(8, 30, 28, 14),
(9, 35, 36, 14),
(10, 40, 23, 13),
(11, 10, 17, 13),
(12, 90, 18, 13),
(13, 15, 27, 15),
(14, 30, 44, 16),
(15, 3, 52, 17),
(16, 35, 52, 17),
(17, 60, 54, 17);

-- --------------------------------------------------------

--
-- Structure de la table `cartons`
--

CREATE TABLE `cartons` (
  `id` int(11) NOT NULL,
  `nombrecarton` int(11) NOT NULL,
  `type` bit(1) NOT NULL,
  `joueur_id` int(11) DEFAULT NULL,
  `match_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `cartons`
--

INSERT INTO `cartons` (`id`, `nombrecarton`, `type`, `joueur_id`, `match_id`) VALUES
(1, 0, b'0', 17, 9),
(2, 0, b'0', 27, 15),
(3, 0, b'0', 14, 9),
(4, 0, b'0', 6, 9),
(5, 0, b'1', 10, 9);

-- --------------------------------------------------------

--
-- Structure de la table `championat`
--

CREATE TABLE `championat` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `championat`
--

INSERT INTO `championat` (`id`, `description`, `nom`) VALUES
(4, 'Leage des champions', 'Leage des champions'),
(2, 'ligue francaise', '2er Ligue francaise'),
(8, 'hgghgh', 'D1');

-- --------------------------------------------------------

--
-- Structure de la table `equipe`
--

CREATE TABLE `equipe` (
  `id` int(11) NOT NULL,
  `description` varchar(255) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `championat_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `equipe`
--

INSERT INTO `equipe` (`id`, `description`, `nom`, `championat_id`) VALUES
(10, 'Fc Barcelone', 'Fc Barcelone', 4),
(11, 'reals', 'Real de Madrid', 4),
(12, 'Monaco', 'Monaco', 4),
(13, 'Atletico de Madrid', 'Atletico de Madrid', 4),
(14, 'PSG', 'PSG', 4),
(15, 'PSV', 'PSV', 4),
(16, 'Chelsea', 'Chelsea', 4),
(17, 'Racing', 'Racing', 2),
(18, 'United Fc', 'United Fc', 4),
(19, 'Racing baffoussam', 'Racing baffoussam', 4),
(20, 'TKC', 'TKC', 4),
(21, 'Alaba', 'Alaba', 8),
(22, 'Bassa', 'Bassa', 8),
(23, 'Papo', 'Papo', 8),
(24, 'ool', 'Pupol', 8);

-- --------------------------------------------------------

--
-- Structure de la table `joueur`
--

CREATE TABLE `joueur` (
  `id` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL,
  `equipe_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `joueur`
--

INSERT INTO `joueur` (`id`, `age`, `nom`, `prenom`, `equipe_id`) VALUES
(6, 20, 'Nermar', 'Jr', 14),
(7, 25, 'Dibala', 'Paul', 14),
(8, 30, 'Irique', 'Ziza', 14),
(9, 26, 'Ericke izani', 'chirac', 14),
(10, 29, 'Marcus ', 'Ema', 14),
(11, 35, 'Pio', 'Puel', 14),
(12, 25, 'Finali', 'Paul', 14),
(13, 25, 'Rachi', 'jean', 14),
(14, 35, 'Messi', 'Messi', 10),
(15, 30, 'Suarez', 'suarez', 10),
(16, 20, 'Puol', 'Puol', 10),
(17, 28, 'Dibala', 'Dibala', 10),
(18, 35, 'James', 'Rodriguez', 10),
(19, 10, 'paul', 'jean', 15),
(20, 30, 'jack', 'jack', 15),
(21, 20, 'eric', 'eric', 15),
(22, 25, 'djhhhj', 'hjh', 15),
(23, 20, 'vbvbvbvb', 'bvv', 15),
(24, 30, 'tetettet', 'ttet', 15),
(25, 35, 'Ronaldo', 'Christiano', 11),
(26, 32, 'Song', 'Bahana', 11),
(27, 19, 'Ditap', 'germi', 11),
(28, 30, 'kamga', 'kamga', 20),
(29, 50, 'talomm', 'paul', 20),
(30, 40, 'marcis', 'item', 20),
(31, 45, 'jack', 'jack', 20),
(32, 20, 'Jak', 'jean', 20),
(33, 30, 'ali', 'ali', 20),
(34, 30, 'filo', 'filo', 20),
(35, 30, 'jhjh', 'jhjh3', 19),
(36, 45, 'uyy', 'yyu', 19),
(37, 50, 'ghjh', 'hhj', 19),
(38, 30, 'bh', 'jhjh', 19),
(39, 30, 'bbj', 'bjbj', 19),
(41, 30, 'ghgh', 'ghgh', 12),
(42, 52, 'jhjh', 'jhjh', 12),
(44, 30, 'ghh', 'ghgh', 21),
(45, 50, 'jhjh', 'jhj', 21),
(46, 10, 'hhjjh', 'jhjh', 21),
(47, 50, 'jjh', 'jhjh', 22),
(48, 5, 'uiui', 'jhjh', 22),
(49, 10, 'bbvbv', 'bvbv', 22),
(50, 5, 'uuu', 'uuuu', 23),
(51, 15, 'pua', 'jj', 23),
(52, 6, 'usol', 'hhh', 23),
(53, 62, 'nnmn', 'nmnmnm', 24),
(54, 4, 'vbv', 'bvbv', 24),
(55, 30, 'oopop', 'oop', 24),
(56, 55, 'vgghfg', 'fgfgfgfg', 21),
(57, 12, 'fgfgfgfgfg', 'fgfgfgfg', 21),
(58, 12, 'ssss', 'sss', 22),
(59, 12, 'jso', 'jso', 22),
(60, 12, 'ggh', 'ghgh', 23),
(61, 50, 'uiui', 'uui', 23),
(62, 12, 'hh', 'ghgh', 24),
(63, 16, 'jjhjhjh', 'jhjhjh', 24);

-- --------------------------------------------------------

--
-- Structure de la table `matchs`
--

CREATE TABLE `matchs` (
  `id` int(11) NOT NULL,
  `jourheurematch` datetime NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `status` bit(1) NOT NULL,
  `equipe_id1` int(11) DEFAULT NULL,
  `equipe_id2` int(11) DEFAULT NULL,
  `championat_id` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matchs`
--

INSERT INTO `matchs` (`id`, `jourheurematch`, `nom`, `status`, `equipe_id1`, `equipe_id2`, `championat_id`) VALUES
(9, '2020-08-28 15:00:00', NULL, b'1', 10, 14, 4),
(12, '2020-08-28 01:00:00', NULL, b'1', 12, 15, 4),
(13, '2020-08-28 23:00:00', NULL, b'1', 15, 10, 4),
(14, '2020-08-29 01:00:00', NULL, b'1', 20, 19, 4),
(15, '2020-08-28 23:00:00', NULL, b'1', 11, 19, 4),
(16, '2020-08-29 02:00:00', NULL, b'1', 21, 22, 8),
(17, '2020-08-28 01:00:00', NULL, b'1', 23, 24, 8);

-- --------------------------------------------------------

--
-- Structure de la table `matchs_joueur`
--

CREATE TABLE `matchs_joueur` (
  `matchs_id` int(11) NOT NULL,
  `MatchJoueurList_id` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `matchs_joueur`
--

INSERT INTO `matchs_joueur` (`matchs_id`, `MatchJoueurList_id`) VALUES
(9, 10),
(9, 9),
(9, 8),
(9, 7),
(9, 6),
(9, 17),
(9, 16),
(9, 15),
(9, 14),
(9, 5),
(14, 28),
(14, 29),
(14, 30),
(14, 31),
(14, 32),
(14, 35),
(14, 36),
(14, 37),
(14, 38),
(14, 39),
(13, 19),
(13, 20),
(13, 21),
(13, 23),
(13, 24),
(13, 5),
(13, 14),
(13, 16),
(13, 17),
(13, 18),
(15, 39),
(15, 38),
(15, 37),
(15, 36),
(15, 35),
(15, 27),
(15, 26),
(16, 44),
(16, 45),
(16, 46),
(16, 56),
(16, 57),
(16, 47),
(16, 48),
(16, 49),
(16, 58),
(16, 59),
(17, 50),
(17, 51),
(17, 52),
(17, 60),
(17, 61),
(17, 53),
(17, 54),
(17, 55),
(17, 62),
(17, 63);

-- --------------------------------------------------------

--
-- Structure de la table `utilisateur`
--

CREATE TABLE `utilisateur` (
  `id` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `motpasse` varchar(500) NOT NULL,
  `nom` varchar(255) NOT NULL,
  `prenom` varchar(255) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `utilisateur`
--

INSERT INTO `utilisateur` (`id`, `email`, `motpasse`, `nom`, `prenom`) VALUES
(1, 'orelien.kamga@gmail.com', 'root', 'orelien', 'kamga');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `buts`
--
ALTER TABLE `buts`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKh6l18w6c5kloki3mht7dcvod5` (`joueur_id`),
  ADD KEY `FKa7b54qjkk4f4xgaxmrb3qqeo4` (`match_id`);

--
-- Index pour la table `cartons`
--
ALTER TABLE `cartons`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKju8yiby86a8hiqjq7kpmq8t89` (`joueur_id`),
  ADD KEY `FK8uyyqkq7cbvkk549jo58foa9t` (`match_id`);

--
-- Index pour la table `championat`
--
ALTER TABLE `championat`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `equipe`
--
ALTER TABLE `equipe`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKggl7mgwvqj07u1gloo6qejita` (`championat_id`);

--
-- Index pour la table `joueur`
--
ALTER TABLE `joueur`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhrh0br197oaaly8dmduj07v3c` (`equipe_id`);

--
-- Index pour la table `matchs`
--
ALTER TABLE `matchs`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhydiq8td3mvkamdcllsf66n8` (`championat_id`),
  ADD KEY `FKd3kjy6x6hmowqj6pjub4syhyy` (`equipe_id1`),
  ADD KEY `FK8xgqbpxd6n5pwkyoqhxkadsc7` (`equipe_id2`);

--
-- Index pour la table `matchs_joueur`
--
ALTER TABLE `matchs_joueur`
  ADD KEY `FKyx6f0t17b8kiip25gtvsfj6f` (`MatchJoueurList_id`),
  ADD KEY `FKi7d1nuhqjockkct6s01o1v0ac` (`matchs_id`);

--
-- Index pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_rma38wvnqfaf66vvmi57c71lo` (`email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `buts`
--
ALTER TABLE `buts`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `cartons`
--
ALTER TABLE `cartons`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT pour la table `championat`
--
ALTER TABLE `championat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT pour la table `equipe`
--
ALTER TABLE `equipe`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT pour la table `joueur`
--
ALTER TABLE `joueur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- AUTO_INCREMENT pour la table `matchs`
--
ALTER TABLE `matchs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT pour la table `utilisateur`
--
ALTER TABLE `utilisateur`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
