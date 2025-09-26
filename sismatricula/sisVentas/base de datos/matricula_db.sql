-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 26-09-2025 a las 09:34:13
-- Versión del servidor: 8.4.3
-- Versión de PHP: 8.3.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `matricula_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `curso`
--

CREATE TABLE `curso` (
  `id` bigint NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `cupos` int DEFAULT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `curso`
--

INSERT INTO `curso` (`id`, `codigo`, `cupos`, `fecha_creacion`, `fecha_modificacion`, `nombre`) VALUES
(1, '001', 4, '2025-09-25 21:09:39.000000', '2025-09-25 21:09:41.000000', 'cáculo'),
(2, '002', 8, '2025-09-25 21:10:24.000000', '2025-09-25 21:10:26.000000', 'fisica II');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_pago`
--

CREATE TABLE `detalle_pago` (
  `id` bigint NOT NULL,
  `fecha_pago` datetime(6) NOT NULL,
  `monto` double NOT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `matricula_id` bigint NOT NULL,
  `metodo_pago_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `detalle_pago`
--

INSERT INTO `detalle_pago` (`id`, `fecha_pago`, `monto`, `observacion`, `matricula_id`, `metodo_pago_id`) VALUES
(2, '2025-09-25 21:54:40.000000', 200, 'cancelado', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudiante`
--

CREATE TABLE `estudiante` (
  `id` bigint NOT NULL,
  `apellido_materno` varchar(255) DEFAULT NULL,
  `apellido_paterno` varchar(255) DEFAULT NULL,
  `codigo` varchar(255) NOT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) NOT NULL,
  `nombre` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `estudiante`
--

INSERT INTO `estudiante` (`id`, `apellido_materno`, `apellido_paterno`, `codigo`, `fecha_creacion`, `fecha_modificacion`, `nombre`) VALUES
(1, 'Quispe', 'Huaman', '20987664', '2025-09-25 21:11:14.000000', '2025-09-25 21:11:16.000000', 'Juna'),
(2, 'Guitierres', 'Condori', '20093736', '2025-09-25 21:11:58.000000', '2025-09-25 21:12:00.000000', 'Luz');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matriculas`
--

CREATE TABLE `matriculas` (
  `id` bigint NOT NULL,
  `estado` varchar(50) NOT NULL,
  `fecha` datetime(6) NOT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) DEFAULT NULL,
  `numero` int DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `estudiante_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `matriculas`
--

INSERT INTO `matriculas` (`id`, `estado`, `fecha`, `fecha_creacion`, `fecha_modificacion`, `numero`, `observaciones`, `estudiante_id`) VALUES
(1, 'true', '2025-09-25 21:12:29.000000', '2025-09-25 21:12:31.000000', '2025-09-25 21:12:32.000000', 2, 'vigente', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `matricula_detalles`
--

CREATE TABLE `matricula_detalles` (
  `id` bigint NOT NULL,
  `curso_id` bigint NOT NULL,
  `matricula_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `matricula_detalles`
--

INSERT INTO `matricula_detalles` (`id`, `curso_id`, `matricula_id`) VALUES
(1, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodo_pago`
--

CREATE TABLE `metodo_pago` (
  `id` bigint NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `nombre` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `metodo_pago`
--

INSERT INTO `metodo_pago` (`id`, `descripcion`, `nombre`) VALUES
(1, 'al contado', 'yape');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `curso`
--
ALTER TABLE `curso`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKk6ib5nb7vu5tjgf54k15eudk` (`codigo`);

--
-- Indices de la tabla `detalle_pago`
--
ALTER TABLE `detalle_pago`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK9tlg8smsjod5kma8odkxdglmo` (`matricula_id`),
  ADD KEY `FKm4nqw89pf4umoaa5pulil6s0r` (`metodo_pago_id`);

--
-- Indices de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKavxayayk9kknjc7bycli4gw58` (`codigo`);

--
-- Indices de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKd6jlehn3bphyp1gcscvbvxunm` (`numero`),
  ADD KEY `FK_matricula_estudiante` (`estudiante_id`);

--
-- Indices de la tabla `matricula_detalles`
--
ALTER TABLE `matricula_detalles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_matricula_detalle_curso` (`curso_id`),
  ADD KEY `FK_matricula_detalle_matricula` (`matricula_id`);

--
-- Indices de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKnrrsdg1qhbpakbl54c769qs64` (`nombre`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `curso`
--
ALTER TABLE `curso`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `detalle_pago`
--
ALTER TABLE `detalle_pago`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `estudiante`
--
ALTER TABLE `estudiante`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `matriculas`
--
ALTER TABLE `matriculas`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `matricula_detalles`
--
ALTER TABLE `matricula_detalles`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `metodo_pago`
--
ALTER TABLE `metodo_pago`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_pago`
--
ALTER TABLE `detalle_pago`
  ADD CONSTRAINT `FK9tlg8smsjod5kma8odkxdglmo` FOREIGN KEY (`matricula_id`) REFERENCES `matriculas` (`id`),
  ADD CONSTRAINT `FKm4nqw89pf4umoaa5pulil6s0r` FOREIGN KEY (`metodo_pago_id`) REFERENCES `metodo_pago` (`id`);

--
-- Filtros para la tabla `matriculas`
--
ALTER TABLE `matriculas`
  ADD CONSTRAINT `FK_matricula_estudiante` FOREIGN KEY (`estudiante_id`) REFERENCES `estudiante` (`id`);

--
-- Filtros para la tabla `matricula_detalles`
--
ALTER TABLE `matricula_detalles`
  ADD CONSTRAINT `FK_matricula_detalle_curso` FOREIGN KEY (`curso_id`) REFERENCES `curso` (`id`),
  ADD CONSTRAINT `FK_matricula_detalle_matricula` FOREIGN KEY (`matricula_id`) REFERENCES `matriculas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
