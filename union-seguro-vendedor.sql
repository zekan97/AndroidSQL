-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 11-02-2020 a las 10:03:53
-- Versión del servidor: 5.7.28-0ubuntu0.18.04.4
-- Versión de PHP: 7.2.24-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `android_seguros`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `union-seguro-vendedor`
--

CREATE TABLE `union-seguro-vendedor` (
  `id_union` int(11) NOT NULL,
  `DNI_cliente` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `DNI_vendedor` varchar(9) COLLATE utf8_spanish_ci NOT NULL,
  `id_seguro` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `union-seguro-vendedor`
--
ALTER TABLE `union-seguro-vendedor`
  ADD PRIMARY KEY (`id_union`,`id_seguro`,`DNI_cliente`),
  ADD KEY `FK-clientes-union` (`DNI_cliente`),
  ADD KEY `FK-seguro-union` (`id_seguro`),
  ADD KEY `FK-vendedores-union` (`DNI_vendedor`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `union-seguro-vendedor`
--
ALTER TABLE `union-seguro-vendedor`
  MODIFY `id_union` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `union-seguro-vendedor`
--
ALTER TABLE `union-seguro-vendedor`
  ADD CONSTRAINT `FK-clientes-union` FOREIGN KEY (`DNI_cliente`) REFERENCES `clientes` (`DNI`),
  ADD CONSTRAINT `FK-seguro-union` FOREIGN KEY (`id_seguro`) REFERENCES `seguros` (`id_seguro`),
  ADD CONSTRAINT `FK-vendedores-union` FOREIGN KEY (`DNI_vendedor`) REFERENCES `vendedores` (`DNI`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
