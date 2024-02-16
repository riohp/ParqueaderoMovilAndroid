-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 16-02-2024 a las 16:20:30
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `personal_finances`
--
CREATE DATABASE IF NOT EXISTS `personal_finances` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE `personal_finances`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `category`
--

CREATE TABLE `category` (
  `category_id` smallint(3) NOT NULL,
  `category_name` varchar(50) DEFAULT NULL,
  `category_description` varchar(120) DEFAULT NULL,
  `category_status` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tokens`
--

CREATE TABLE `tokens` (
  `token` char(180) NOT NULL,
  `user_id` char(30) DEFAULT NULL,
  `token_status` tinyint(1) DEFAULT 1,
  `token_created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transactions`
--

CREATE TABLE `transactions` (
  `transactions_id` int(10) UNSIGNED NOT NULL,
  `user_id` char(30) DEFAULT NULL,
  `category_id` smallint(3) DEFAULT NULL,
  `amount` float(10,2) DEFAULT NULL,
  `t_description` varchar(120) DEFAULT NULL,
  `t_type` enum('revenue','expenses') DEFAULT NULL,
  `t_date` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE `users` (
  `user_id` char(30) NOT NULL,
  `full_name` varchar(80) DEFAULT NULL,
  `mail` varchar(100) DEFAULT NULL,
  `passhash` varchar(140) DEFAULT NULL,
  `user_role` enum('admin','user') DEFAULT NULL,
  `user_status` tinyint(1) DEFAULT 1,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_at` datetime DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Volcado de datos para la tabla `users`
--

INSERT INTO `users` (`user_id`, `full_name`, `mail`, `passhash`, `user_role`, `user_status`, `created_at`, `update_at`) VALUES
('04youCN0cXvm1BBevaqPcJi7RSi4fY', 'xx', 'xxx@example.com', '$2b$12$F59kNhZWK96ZwYquJoIr6e4vVfz/.bPmjk9NmW4dALjzQ0dczJbrO', 'admin', 1, '2024-02-14 20:35:25', '2024-02-14 15:35:25'),
('7y5hWaZ6F19FzdeM5oqD7g58vtB9Bp', 'jeffry', 'jeffry@gmail.com', '$2b$12$llfzyldDdtrLqQhk2t6q5.7dxvfVP4ZlZ0hAk6VZrmWsmhRJurTkq', 'user', 1, '2024-02-14 18:53:01', '2024-02-14 13:53:01'),
('a7MPWBprR8mutQmIwXnT0EJceTF34C', 'guiller', 'guiller@gmail.com', '$2b$12$hJfzCT/Yj3z1f/7ReBRVM.GRd84FJEw4SUnmE5EVuAJTEbMdbhMUW', 'admin', 1, '2024-02-14 18:49:33', '2024-02-14 13:49:33'),
('evfDOtUBQQxL37DBaK95lhGcdVWEGc', 'carlos', 'carlos@gmail.com', '$2b$12$ES8Raeg0E5gmyBAK9gb/MuX.kn2UCulQQqaN/WpQhXYH0ooMU3wqy', 'user', 1, '2024-02-14 18:26:37', '2024-02-14 10:56:55');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Indices de la tabla `tokens`
--
ALTER TABLE `tokens`
  ADD PRIMARY KEY (`token`),
  ADD KEY `user_id` (`user_id`);

--
-- Indices de la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`transactions_id`),
  ADD KEY `user_id` (`user_id`),
  ADD KEY `category_id` (`category_id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `mail` (`mail`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `category`
--
ALTER TABLE `category`
  MODIFY `category_id` smallint(3) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `transactions`
--
ALTER TABLE `transactions`
  MODIFY `transactions_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `tokens`
--
ALTER TABLE `tokens`
  ADD CONSTRAINT `tokens_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Filtros para la tabla `transactions`
--
ALTER TABLE `transactions`
  ADD CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `transactions_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`);
--
-- Base de datos: `phpmyadmin`
--
CREATE DATABASE IF NOT EXISTS `phpmyadmin` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `phpmyadmin`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__bookmark`
--

CREATE TABLE `pma__bookmark` (
  `id` int(10) UNSIGNED NOT NULL,
  `dbase` varchar(255) NOT NULL DEFAULT '',
  `user` varchar(255) NOT NULL DEFAULT '',
  `label` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `query` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Bookmarks';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__central_columns`
--

CREATE TABLE `pma__central_columns` (
  `db_name` varchar(64) NOT NULL,
  `col_name` varchar(64) NOT NULL,
  `col_type` varchar(64) NOT NULL,
  `col_length` text DEFAULT NULL,
  `col_collation` varchar(64) NOT NULL,
  `col_isNull` tinyint(1) NOT NULL,
  `col_extra` varchar(255) DEFAULT '',
  `col_default` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Central list of columns';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__column_info`
--

CREATE TABLE `pma__column_info` (
  `id` int(5) UNSIGNED NOT NULL,
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `column_name` varchar(64) NOT NULL DEFAULT '',
  `comment` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `mimetype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `transformation` varchar(255) NOT NULL DEFAULT '',
  `transformation_options` varchar(255) NOT NULL DEFAULT '',
  `input_transformation` varchar(255) NOT NULL DEFAULT '',
  `input_transformation_options` varchar(255) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Column information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__designer_settings`
--

CREATE TABLE `pma__designer_settings` (
  `username` varchar(64) NOT NULL,
  `settings_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Settings related to Designer';

--
-- Volcado de datos para la tabla `pma__designer_settings`
--

INSERT INTO `pma__designer_settings` (`username`, `settings_data`) VALUES
('root', '{\"angular_direct\":\"direct\",\"relation_lines\":\"true\",\"snap_to_grid\":\"off\"}');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__export_templates`
--

CREATE TABLE `pma__export_templates` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL,
  `export_type` varchar(10) NOT NULL,
  `template_name` varchar(64) NOT NULL,
  `template_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved export templates';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__favorite`
--

CREATE TABLE `pma__favorite` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Favorite tables';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__history`
--

CREATE TABLE `pma__history` (
  `id` bigint(20) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db` varchar(64) NOT NULL DEFAULT '',
  `table` varchar(64) NOT NULL DEFAULT '',
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp(),
  `sqlquery` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='SQL history for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__navigationhiding`
--

CREATE TABLE `pma__navigationhiding` (
  `username` varchar(64) NOT NULL,
  `item_name` varchar(64) NOT NULL,
  `item_type` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Hidden items of navigation tree';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__pdf_pages`
--

CREATE TABLE `pma__pdf_pages` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `page_nr` int(10) UNSIGNED NOT NULL,
  `page_descr` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='PDF relation pages for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__recent`
--

CREATE TABLE `pma__recent` (
  `username` varchar(64) NOT NULL,
  `tables` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Recently accessed tables';

--
-- Volcado de datos para la tabla `pma__recent`
--

INSERT INTO `pma__recent` (`username`, `tables`) VALUES
('root', '[{\"db\":\"personal_finances\",\"table\":\"users\"},{\"db\":\"preguntas_v2_db\",\"table\":\"respuestas\"},{\"db\":\"preguntas_v2_db\",\"table\":\"cuestionarios\"},{\"db\":\"preguntas_v2_db\",\"table\":\"preguntas\"},{\"db\":\"preguntas_v2_db\",\"table\":\"opciones\"},{\"db\":\"preguntas_v2_db\",\"table\":\"usuarios\"},{\"db\":\"personal_finances\",\"table\":\"transactions\"},{\"db\":\"personal_finances\",\"table\":\"category\"}]');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__relation`
--

CREATE TABLE `pma__relation` (
  `master_db` varchar(64) NOT NULL DEFAULT '',
  `master_table` varchar(64) NOT NULL DEFAULT '',
  `master_field` varchar(64) NOT NULL DEFAULT '',
  `foreign_db` varchar(64) NOT NULL DEFAULT '',
  `foreign_table` varchar(64) NOT NULL DEFAULT '',
  `foreign_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Relation table';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__savedsearches`
--

CREATE TABLE `pma__savedsearches` (
  `id` int(5) UNSIGNED NOT NULL,
  `username` varchar(64) NOT NULL DEFAULT '',
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `search_name` varchar(64) NOT NULL DEFAULT '',
  `search_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Saved searches';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_coords`
--

CREATE TABLE `pma__table_coords` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `pdf_page_number` int(11) NOT NULL DEFAULT 0,
  `x` float UNSIGNED NOT NULL DEFAULT 0,
  `y` float UNSIGNED NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table coordinates for phpMyAdmin PDF output';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_info`
--

CREATE TABLE `pma__table_info` (
  `db_name` varchar(64) NOT NULL DEFAULT '',
  `table_name` varchar(64) NOT NULL DEFAULT '',
  `display_field` varchar(64) NOT NULL DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Table information for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__table_uiprefs`
--

CREATE TABLE `pma__table_uiprefs` (
  `username` varchar(64) NOT NULL,
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `prefs` text NOT NULL,
  `last_update` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Tables'' UI preferences';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__tracking`
--

CREATE TABLE `pma__tracking` (
  `db_name` varchar(64) NOT NULL,
  `table_name` varchar(64) NOT NULL,
  `version` int(10) UNSIGNED NOT NULL,
  `date_created` datetime NOT NULL,
  `date_updated` datetime NOT NULL,
  `schema_snapshot` text NOT NULL,
  `schema_sql` text DEFAULT NULL,
  `data_sql` longtext DEFAULT NULL,
  `tracking` set('UPDATE','REPLACE','INSERT','DELETE','TRUNCATE','CREATE DATABASE','ALTER DATABASE','DROP DATABASE','CREATE TABLE','ALTER TABLE','RENAME TABLE','DROP TABLE','CREATE INDEX','DROP INDEX','CREATE VIEW','ALTER VIEW','DROP VIEW') DEFAULT NULL,
  `tracking_active` int(1) UNSIGNED NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Database changes tracking for phpMyAdmin';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__userconfig`
--

CREATE TABLE `pma__userconfig` (
  `username` varchar(64) NOT NULL,
  `timevalue` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `config_data` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User preferences storage for phpMyAdmin';

--
-- Volcado de datos para la tabla `pma__userconfig`
--

INSERT INTO `pma__userconfig` (`username`, `timevalue`, `config_data`) VALUES
('root', '2024-02-16 14:31:40', '{\"Console\\/Mode\":\"collapse\",\"lang\":\"es\"}');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__usergroups`
--

CREATE TABLE `pma__usergroups` (
  `usergroup` varchar(64) NOT NULL,
  `tab` varchar(64) NOT NULL,
  `allowed` enum('Y','N') NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='User groups with configured menu items';

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pma__users`
--

CREATE TABLE `pma__users` (
  `username` varchar(64) NOT NULL,
  `usergroup` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='Users and their assignments to user groups';

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pma__central_columns`
--
ALTER TABLE `pma__central_columns`
  ADD PRIMARY KEY (`db_name`,`col_name`);

--
-- Indices de la tabla `pma__column_info`
--
ALTER TABLE `pma__column_info`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `db_name` (`db_name`,`table_name`,`column_name`);

--
-- Indices de la tabla `pma__designer_settings`
--
ALTER TABLE `pma__designer_settings`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_user_type_template` (`username`,`export_type`,`template_name`);

--
-- Indices de la tabla `pma__favorite`
--
ALTER TABLE `pma__favorite`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__history`
--
ALTER TABLE `pma__history`
  ADD PRIMARY KEY (`id`),
  ADD KEY `username` (`username`,`db`,`table`,`timevalue`);

--
-- Indices de la tabla `pma__navigationhiding`
--
ALTER TABLE `pma__navigationhiding`
  ADD PRIMARY KEY (`username`,`item_name`,`item_type`,`db_name`,`table_name`);

--
-- Indices de la tabla `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  ADD PRIMARY KEY (`page_nr`),
  ADD KEY `db_name` (`db_name`);

--
-- Indices de la tabla `pma__recent`
--
ALTER TABLE `pma__recent`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__relation`
--
ALTER TABLE `pma__relation`
  ADD PRIMARY KEY (`master_db`,`master_table`,`master_field`),
  ADD KEY `foreign_field` (`foreign_db`,`foreign_table`);

--
-- Indices de la tabla `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `u_savedsearches_username_dbname` (`username`,`db_name`,`search_name`);

--
-- Indices de la tabla `pma__table_coords`
--
ALTER TABLE `pma__table_coords`
  ADD PRIMARY KEY (`db_name`,`table_name`,`pdf_page_number`);

--
-- Indices de la tabla `pma__table_info`
--
ALTER TABLE `pma__table_info`
  ADD PRIMARY KEY (`db_name`,`table_name`);

--
-- Indices de la tabla `pma__table_uiprefs`
--
ALTER TABLE `pma__table_uiprefs`
  ADD PRIMARY KEY (`username`,`db_name`,`table_name`);

--
-- Indices de la tabla `pma__tracking`
--
ALTER TABLE `pma__tracking`
  ADD PRIMARY KEY (`db_name`,`table_name`,`version`);

--
-- Indices de la tabla `pma__userconfig`
--
ALTER TABLE `pma__userconfig`
  ADD PRIMARY KEY (`username`);

--
-- Indices de la tabla `pma__usergroups`
--
ALTER TABLE `pma__usergroups`
  ADD PRIMARY KEY (`usergroup`,`tab`,`allowed`);

--
-- Indices de la tabla `pma__users`
--
ALTER TABLE `pma__users`
  ADD PRIMARY KEY (`username`,`usergroup`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `pma__bookmark`
--
ALTER TABLE `pma__bookmark`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__column_info`
--
ALTER TABLE `pma__column_info`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__export_templates`
--
ALTER TABLE `pma__export_templates`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__history`
--
ALTER TABLE `pma__history`
  MODIFY `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__pdf_pages`
--
ALTER TABLE `pma__pdf_pages`
  MODIFY `page_nr` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pma__savedsearches`
--
ALTER TABLE `pma__savedsearches`
  MODIFY `id` int(5) UNSIGNED NOT NULL AUTO_INCREMENT;
--
-- Base de datos: `preguntas_v2_db`
--
CREATE DATABASE IF NOT EXISTS `preguntas_v2_db` DEFAULT CHARACTER SET utf8 COLLATE utf8_spanish2_ci;
USE `preguntas_v2_db`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuestionarios`
--

CREATE TABLE `cuestionarios` (
  `id` int(11) NOT NULL,
  `id_usuario` int(11) UNSIGNED NOT NULL,
  `cant_preguntas` int(11) NOT NULL,
  `cant_ok` int(11) NOT NULL,
  `cant_error` int(11) NOT NULL,
  `fecha_inicio` datetime NOT NULL DEFAULT current_timestamp(),
  `fecha_fin` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish2_ci;

--
-- Volcado de datos para la tabla `cuestionarios`
--

INSERT INTO `cuestionarios` (`id`, `id_usuario`, `cant_preguntas`, `cant_ok`, `cant_error`, `fecha_inicio`, `fecha_fin`) VALUES
(1, 1, 0, 0, 0, '2024-01-25 20:32:56', NULL),
(2, 1, 0, 0, 0, '2024-01-25 23:41:44', '2024-01-25 23:47:44'),
(38, 1, 0, 0, 0, '2024-01-26 12:18:46', NULL),
(39, 1, 0, 0, 0, '2024-01-26 12:26:09', NULL),
(40, 1, 5, 1, 1, '2024-02-05 13:56:10', NULL),
(41, 1, 5, 1, 1, '2024-02-05 14:02:13', NULL),
(42, 1, 5, 1, 1, '2024-02-05 14:03:58', NULL),
(43, 1, 5, 1, 1, '2024-02-05 14:11:08', NULL),
(44, 1, 5, 1, 1, '2024-02-05 14:12:31', NULL),
(45, 1, 5, 1, 1, '2024-02-05 14:13:46', NULL),
(46, 1, 5, 1, 1, '2024-02-05 14:15:37', NULL),
(47, 1, 5, 1, 1, '2024-02-05 14:16:26', NULL),
(48, 1, 5, 1, 1, '2024-02-05 14:17:47', NULL),
(49, 1, 5, 1, 1, '2024-02-05 14:18:09', NULL),
(50, 1, 5, 1, 1, '2024-02-05 14:18:40', NULL),
(51, 1, 5, 1, 1, '2024-02-05 14:18:50', NULL),
(52, 1, 5, 1, 1, '2024-02-05 14:19:05', NULL),
(53, 1, 5, 1, 1, '2024-02-05 14:19:36', NULL),
(54, 1, 5, 1, 1, '2024-02-05 14:24:34', NULL),
(55, 1, 5, 1, 1, '2024-02-05 14:24:52', NULL),
(56, 1, 5, 1, 1, '2024-02-05 14:26:03', NULL),
(57, 1, 5, 1, 1, '2024-02-05 14:26:20', NULL),
(58, 1, 5, 1, 1, '2024-02-05 14:26:38', NULL),
(59, 1, 5, 1, 1, '2024-02-05 14:26:55', NULL),
(60, 1, 5, 1, 1, '2024-02-05 14:29:28', NULL),
(61, 1, 5, 1, 1, '2024-02-05 14:29:49', NULL),
(62, 1, 5, 1, 1, '2024-02-05 15:13:58', NULL),
(63, 1, 5, 1, 1, '2024-02-05 15:22:51', NULL),
(64, 1, 5, 1, 1, '2024-02-05 15:23:05', NULL),
(65, 1, 5, 1, 1, '2024-02-05 15:24:44', NULL),
(66, 1, 5, 1, 1, '2024-02-05 15:24:53', NULL),
(67, 1, 5, 1, 1, '2024-02-05 15:25:50', NULL),
(68, 1, 5, 1, 1, '2024-02-05 15:27:53', NULL),
(69, 1, 5, 1, 1, '2024-02-05 15:29:03', NULL),
(70, 1, 5, 1, 1, '2024-02-05 15:29:15', NULL),
(71, 1, 5, 1, 1, '2024-02-05 15:31:30', NULL),
(72, 1, 5, 1, 1, '2024-02-05 15:31:55', NULL),
(73, 1, 5, 1, 1, '2024-02-05 15:32:25', NULL),
(74, 1, 5, 1, 1, '2024-02-05 15:37:58', NULL),
(75, 1, 5, 1, 1, '2024-02-05 15:39:33', NULL),
(76, 1, 5, 1, 1, '2024-02-05 15:40:26', NULL),
(77, 1, 5, 1, 1, '2024-02-05 15:41:01', NULL),
(78, 1, 5, 1, 1, '2024-02-05 15:41:24', NULL),
(79, 1, 5, 1, 1, '2024-02-05 15:42:03', NULL),
(80, 1, 5, 1, 1, '2024-02-05 15:42:08', NULL),
(81, 1, 5, 1, 1, '2024-02-05 15:42:23', NULL),
(82, 1, 5, 1, 1, '2024-02-05 15:42:53', NULL),
(83, 1, 5, 1, 1, '2024-02-05 15:48:22', NULL),
(84, 1, 5, 1, 1, '2024-02-05 15:52:45', NULL),
(85, 1, 5, 1, 1, '2024-02-05 15:53:32', NULL),
(86, 1, 5, 1, 1, '2024-02-05 15:57:50', NULL),
(87, 1, 5, 1, 1, '2024-02-05 15:58:55', NULL),
(88, 1, 5, 1, 1, '2024-02-05 16:00:16', NULL),
(89, 1, 5, 1, 1, '2024-02-05 16:02:02', NULL),
(90, 1, 5, 1, 1, '2024-02-05 16:03:55', NULL),
(91, 1, 5, 1, 1, '2024-02-05 16:04:17', NULL),
(92, 1, 5, 1, 1, '2024-02-05 16:16:37', NULL),
(93, 1, 5, 1, 1, '2024-02-05 16:18:16', NULL),
(94, 1, 5, 1, 1, '2024-02-05 16:21:14', NULL),
(95, 1, 5, 1, 1, '2024-02-05 16:24:30', NULL),
(96, 1, 5, 1, 1, '2024-02-05 16:24:49', NULL),
(97, 1, 5, 1, 1, '2024-02-05 16:25:29', NULL),
(98, 1, 5, 1, 1, '2024-02-05 16:28:58', NULL),
(99, 1, 5, 1, 1, '2024-02-05 16:29:57', NULL),
(100, 1, 5, 1, 1, '2024-02-05 16:30:22', NULL),
(101, 1, 5, 1, 1, '2024-02-05 16:31:10', NULL),
(102, 1, 5, 1, 1, '2024-02-05 16:32:42', NULL),
(103, 1, 5, 1, 1, '2024-02-05 16:33:13', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opciones`
--

CREATE TABLE `opciones` (
  `id` int(10) UNSIGNED NOT NULL,
  `id_pregunta` int(10) UNSIGNED DEFAULT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `opciones`
--

INSERT INTO `opciones` (`id`, `id_pregunta`, `descripcion`) VALUES
(1, 1, 'HyperText Markup Language'),
(2, 1, 'Hyperlink '),
(3, 1, 'High-Level'),
(4, 2, 'C'),
(5, 2, 'C++'),
(6, 2, 'Ruby'),
(7, 3, 'Cascaded S'),
(8, 3, 'Cascading Style Sheets'),
(9, 3, 'Cascaded S'),
(10, 4, '->'),
(11, 4, '='),
(12, 4, '==='),
(13, 5, 'Crear las '),
(14, 5, 'Almacenar '),
(15, 5, 'Lenguaje de consulta estructurada para gestionar bases de datos'),
(16, 6, 'Modelo-Visa'),
(17, 6, 'Modelo-Vista-Controlador'),
(18, 6, 'Microservi'),
(19, 7, 'Java'),
(20, 7, 'PHP'),
(21, 7, 'C'),
(22, 8, 'Instrucción'),
(23, 8, 'Estructura de control de iteración'),
(24, 8, 'Comando de'),
(25, 9, 'SVN'),
(26, 9, 'Mercurial'),
(27, 9, 'GitHub'),
(28, 10, 'Un conjunt'),
(29, 10, 'Un disposi'),
(30, 10, 'Un conjunto de instrucciones paso a paso para realizar una tarea'),
(31, 9, 'Git');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `preguntas`
--

CREATE TABLE `preguntas` (
  `id` int(10) UNSIGNED NOT NULL,
  `descripcion` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `id_correcta` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `url_imagen` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `preguntas`
--

INSERT INTO `preguntas` (`id`, `descripcion`, `id_correcta`, `url_imagen`) VALUES
(1, '¿Qué significa HTML?', '1', 'https://www.oxfordwebstudio.com/user/pages/06.da-li-znate/sta-je-html/sta-je-html.jpg'),
(2, '¿En qué lenguaje est? escrito Python?', '4', 'https://www.solvetic.com/uploads/monthly_01_2016/tutorials-1415-0-60642300-1452279191.jpg'),
(3, '¿Qué es CSS?', '8', 'https://1000marcas.net/wp-content/uploads/2021/02/CSS-Logo.png'),
(4, '¿Cuál es el símbolo de asignación en JavaScript?', '11', 'https://upload.wikimedia.org/wikipedia/commons/thumb/9/99/Unofficial_JavaScript_logo_2.svg/1200px-Unofficial_JavaScript_logo_2.svg.png'),
(5, '¿Cuál es el propósito de SQL?', '15', 'https://www.bigbaydata.com/wp-content/uploads/2022/11/sql_ejercicios.png'),
(6, '¿Qué significa MVC en el contexto del desarrollo web?', '17', 'https://estradawebgroup.com/ImagesUpload/File_Upload_202331310241646_U_7.webp'),
(7, '¿Cuál es el lenguaje de programación más utilizado para el desarrollo de aplicaciones Android?', '19', 'https://developer.android.com/static/training/basics/firstapp/images/first_app.svg?hl=es-419'),
(8, '¿Qué es un bucle \"for\" en la programación?', '23', 'https://sistemasumma.files.wordpress.com/2012/10/ciclos.png?w=1140'),
(9, '¿Cuál es el sistema de control de versiones más utilizado?', '31', 'https://victorroblesweb.es/wp-content/uploads/2018/04/git.png'),
(10, '¿Qué es un algoritmo?', '30', 'https://cdn.openwebinars.net/media/featured_images/blog-que-es-un-algoritmo-informatico.jpg');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `respuestas`
--

CREATE TABLE `respuestas` (
  `id_respuesta` int(10) UNSIGNED NOT NULL,
  `id_cuestionario` int(11) NOT NULL,
  `id_pregunta` int(10) UNSIGNED DEFAULT NULL,
  `respuesta` varchar(100) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `estado` enum('OK','ERROR') CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL,
  `fecha` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `respuestas`
--

INSERT INTO `respuestas` (`id_respuesta`, `id_cuestionario`, `id_pregunta`, `respuesta`, `estado`, `fecha`) VALUES
(3, 1, 1, 'Hyperlink Text Markup Language', 'OK', '2024-01-25 20:38:55'),
(5, 1, 5, 'Crear las bases de datos', 'ERROR', '2024-01-25 20:40:15'),
(6, 2, 7, 'Java', 'OK', '2024-01-25 23:43:42'),
(7, 2, 9, 'Git', 'OK', '2024-01-25 23:43:42'),
(8, 2, 4, '=', 'OK', '2024-01-25 23:44:08'),
(9, 2, 10, 'Un conjunto de instrucciones paso a paso para realizar una tarea', 'OK', '2024-01-25 23:44:35'),
(44, 38, 6, 'Microservices-Virtual Container', 'ERROR', '2024-01-26 07:18:53'),
(45, 38, 10, 'Un dispositivo de almacenamiento de datos', 'ERROR', '2024-01-26 07:18:55'),
(46, 38, 8, 'InstrucciÃƒÂ³n de salida', 'ERROR', '2024-01-26 07:18:58'),
(47, 38, 5, 'Almacenar grandes cantidades de datos', 'ERROR', '2024-01-26 07:19:01'),
(48, 39, 6, 'MÃƒÂ¡quina Virtual de Control', 'ERROR', '2024-01-26 07:26:56'),
(49, 39, 10, 'Un dispositivo de almacenamiento de datos', 'ERROR', '2024-01-26 07:29:01'),
(50, 39, 4, '==', 'ERROR', '2024-01-26 07:31:30'),
(51, 39, 5, 'Almacenar grandes cantidades de datos', 'ERROR', '2024-01-26 07:32:29'),
(52, 39, 3, 'Cascading Styling Sheets', 'ERROR', '2024-01-26 07:32:31'),
(53, 39, 1, 'Hyperlink Text Markup Language', 'ERROR', '2024-01-26 07:32:33'),
(54, 39, 9, 'Mercurial', 'ERROR', '2024-01-26 07:32:48'),
(55, 39, 2, 'C++', 'ERROR', '2024-01-26 07:32:50'),
(56, 39, 8, 'Estructura de selecciÃƒÂ³n', 'OK', '2024-01-26 07:32:52'),
(57, 40, 8, 'Instrucción', 'ERROR', '2024-02-05 13:56:17'),
(58, 40, 7, 'Java', 'OK', '2024-02-05 13:56:21'),
(59, 40, 1, 'Hyperlink ', 'ERROR', '2024-02-05 13:56:23'),
(60, 67, 6, 'Modelo-Vista-Controlador', 'OK', '2024-02-05 15:27:26'),
(61, 68, 7, 'C', 'ERROR', '2024-02-05 15:27:59'),
(62, 68, 3, 'C', 'ERROR', '2024-02-05 15:28:01'),
(63, 70, 4, '===', 'ERROR', '2024-02-05 15:29:23'),
(64, 70, 7, 'PHP', 'ERROR', '2024-02-05 15:29:27'),
(65, 97, 5, 'Crear las ', 'ERROR', '2024-02-05 16:26:39'),
(66, 97, 1, 'Hyperlink ', 'ERROR', '2024-02-05 16:26:41'),
(67, 97, 6, 'Modelo-Vista-Controlador', 'OK', '2024-02-05 16:26:43'),
(68, 103, 7, 'Java', 'OK', '2024-02-05 16:33:22'),
(69, 103, 10, 'Un disposi', 'ERROR', '2024-02-05 16:33:24'),
(70, 103, 9, 'Mercurial', 'ERROR', '2024-02-05 16:33:27');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(10) UNSIGNED NOT NULL,
  `nombres` varchar(80) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `correo` varchar(120) CHARACTER SET utf8 COLLATE utf8_spanish2_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish2_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_swedish_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombres`, `correo`, `password`) VALUES
(1, 'Oscar Andres Loaiza Pabon', 'andres@gmail.com', '827ccb0eea8a706c4c34a16891f84e7b');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuestionarios`
--
ALTER TABLE `cuestionarios`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `opciones`
--
ALTER TABLE `opciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `pregunta` (`id_pregunta`);

--
-- Indices de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD PRIMARY KEY (`id_respuesta`),
  ADD KEY `pregunta` (`id_pregunta`),
  ADD KEY `id_cuestionarios` (`id_cuestionario`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `correo` (`correo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuestionarios`
--
ALTER TABLE `cuestionarios`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=104;

--
-- AUTO_INCREMENT de la tabla `opciones`
--
ALTER TABLE `opciones`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=32;

--
-- AUTO_INCREMENT de la tabla `preguntas`
--
ALTER TABLE `preguntas`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `respuestas`
--
ALTER TABLE `respuestas`
  MODIFY `id_respuesta` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=71;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `cuestionarios`
--
ALTER TABLE `cuestionarios`
  ADD CONSTRAINT `cuestionarios_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `opciones`
--
ALTER TABLE `opciones`
  ADD CONSTRAINT `opciones_ibfk_1` FOREIGN KEY (`id_pregunta`) REFERENCES `preguntas` (`id`);

--
-- Filtros para la tabla `respuestas`
--
ALTER TABLE `respuestas`
  ADD CONSTRAINT `respuestas_ibfk_3` FOREIGN KEY (`id_cuestionario`) REFERENCES `cuestionarios` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `respuestas_ibfk_4` FOREIGN KEY (`id_pregunta`) REFERENCES `preguntas` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;
--
-- Base de datos: `test`
--
CREATE DATABASE IF NOT EXISTS `test` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `test`;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
