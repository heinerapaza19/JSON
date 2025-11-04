-- phpMyAdmin SQL Dump
-- version 5.2.2
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 22-09-2025 a las 09:36:04
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
-- Base de datos: `ventas_db`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` bigint NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `codigo`, `descripcion`, `fecha_creacion`, `fecha_modificacion`, `nombre`) VALUES
(1, 'ELEC01', 'Productos tecnológicos como laptops y celulares', '2025-09-17 06:04:53.225000', '2025-09-17 06:04:53.225000', 'Electrónicos'),
(2, 'ROP01', 'Prendas de vestir para hombres y mujeres', '2025-09-17 06:06:01.960000', '2025-09-17 06:06:01.960000', 'Ropa');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id` bigint NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `dni` varchar(15) NOT NULL,
  `nombres` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`id`, `apellidos`, `dni`, `nombres`) VALUES
(1, 'Apaza', '76377628', 'Luz'),
(2, 'Apaza', '73360821', 'Heiner'),
(3, 'Apaza', '76377628', 'Luz'),
(4, 'Apaza', '73360821', 'Pablo'),
(5, 'Apaza', '73360821', 'Pablo'),
(6, 'Apaza', '73360821', 'Pablo');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id` bigint NOT NULL,
  `fecha` datetime(6) DEFAULT NULL,
  `metodo` varchar(50) DEFAULT NULL,
  `monto` decimal(38,2) DEFAULT NULL,
  `venta_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`id`, `fecha`, `metodo`, `monto`, `venta_id`) VALUES
(1, '2025-09-20 15:00:00.000000', 'TARJETA', 137.70, 2),
(2, '2025-09-20 15:35:00.000000', 'TARJETA', 185.20, 8);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `id` bigint NOT NULL,
  `activo` bit(1) NOT NULL,
  `codigo` varchar(255) NOT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) DEFAULT NULL,
  `nombre` varchar(255) NOT NULL,
  `precio` decimal(10,2) NOT NULL,
  `stock` int NOT NULL,
  `categoria_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`id`, `activo`, `codigo`, `descripcion`, `fecha_creacion`, `fecha_modificacion`, `nombre`, `precio`, `stock`, `categoria_id`) VALUES
(1, b'1', 'MOUSE01', 'Mouse óptico con luces LED', '2025-09-17 06:13:49.399000', '2025-09-20 13:18:57.310000', 'Mouse Gamer', 120.50, 0, 1),
(2, b'1', 'TEC02', 'Teclado mecánico retroiluminado RGB para gamers', '2025-09-17 06:14:17.110000', '2025-09-20 15:31:14.351000', 'Teclado Mecánico', 250.00, 12, 1),
(3, b'1', 'ROP01', 'Polo de algodón transpirable para actividades físicas', '2025-09-17 06:15:35.213000', '2025-09-20 15:31:14.351000', 'Polo Deportivo', 45.90, 99, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ventas`
--

CREATE TABLE `ventas` (
  `id` bigint NOT NULL,
  `estado` varchar(50) NOT NULL,
  `fecha` datetime(6) NOT NULL,
  `fecha_creacion` datetime(6) NOT NULL,
  `fecha_modificacion` datetime(6) DEFAULT NULL,
  `numero_factura` varchar(255) DEFAULT NULL,
  `observaciones` varchar(500) DEFAULT NULL,
  `serie` varchar(255) DEFAULT NULL,
  `total` decimal(10,2) NOT NULL,
  `cliente_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `ventas`
--

INSERT INTO `ventas` (`id`, `estado`, `fecha`, `fecha_creacion`, `fecha_modificacion`, `numero_factura`, `observaciones`, `serie`, `total`, `cliente_id`) VALUES
(2, 'COMPLETADA', '2025-09-20 15:00:00.000000', '2025-09-17 06:20:20.804000', '2025-09-20 15:04:48.557000', '2', 'Venta de polos deportivos actualizada', 'F001', 137.70, 1),
(8, 'COMPLETADA', '2025-09-20 15:31:14.095293', '2025-09-20 15:31:14.095000', '2025-09-20 15:31:14.095000', '2', 'Venta de polos y camisetas', 'F003', 185.20, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `venta_detalles`
--

CREATE TABLE `venta_detalles` (
  `id` bigint NOT NULL,
  `cantidad` int NOT NULL,
  `precio_unitario` decimal(10,2) NOT NULL,
  `subtotal` decimal(10,2) NOT NULL,
  `producto_id` bigint NOT NULL,
  `venta_id` bigint NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `venta_detalles`
--

INSERT INTO `venta_detalles` (`id`, `cantidad`, `precio_unitario`, `subtotal`, `producto_id`, `venta_id`) VALUES
(9, 3, 45.90, 137.70, 2, 2),
(10, 3, 45.90, 137.70, 2, 8),
(11, 1, 47.50, 47.50, 3, 8);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKgqotsute2hs9urwkx0rxtrbb3` (`codigo`),
  ADD UNIQUE KEY `UK35t4wyxqrevf09uwx9e9p6o75` (`nombre`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKr2gojgwqy0othfw6qnrncedj3` (`venta_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKkxdt4u9c4w6vveo7ylph4pd09` (`codigo`),
  ADD KEY `FK_producto_categoria` (`categoria_id`);

--
-- Indices de la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_ventas_cliente` (`cliente_id`);

--
-- Indices de la tabla `venta_detalles`
--
ALTER TABLE `venta_detalles`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_venta_detalle_producto` (`producto_id`),
  ADD KEY `FK_venta_detalle_venta` (`venta_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `cliente`
--
ALTER TABLE `cliente`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `ventas`
--
ALTER TABLE `ventas`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `venta_detalles`
--
ALTER TABLE `venta_detalles`
  MODIFY `id` bigint NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `FKr2gojgwqy0othfw6qnrncedj3` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`);

--
-- Filtros para la tabla `producto`
--
ALTER TABLE `producto`
  ADD CONSTRAINT `FK_producto_categoria` FOREIGN KEY (`categoria_id`) REFERENCES `categoria` (`id`);

--
-- Filtros para la tabla `ventas`
--
ALTER TABLE `ventas`
  ADD CONSTRAINT `fk_ventas_cliente` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`);

--
-- Filtros para la tabla `venta_detalles`
--
ALTER TABLE `venta_detalles`
  ADD CONSTRAINT `FK_venta_detalle_producto` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  ADD CONSTRAINT `FK_venta_detalle_venta` FOREIGN KEY (`venta_id`) REFERENCES `ventas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
