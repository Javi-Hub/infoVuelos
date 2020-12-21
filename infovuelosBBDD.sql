-- phpMyAdmin SQL Dump
-- version 5.0.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 21-12-2020 a las 12:40:12
-- Versión del servidor: 10.4.16-MariaDB
-- Versión de PHP: 7.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `infovuelos`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `nombre_usuario` varchar(30) NOT NULL,
  `email` varchar(50) NOT NULL,
  `password` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `nombre`, `apellidos`, `nombre_usuario`, `email`, `password`) VALUES
(1, 'Javier', 'Rodrigo', 'javi', 'javi@mail.com', 'javi'),
(36, 'María', 'López', 'maria', 'maria@mail.com', 'maria'),
(48, 'Sonia', 'García', 'son', 'son@mail.com', 'son'),
(49, 'pedro', 'oros', 'pedro', 'pedro@mail.com', 'pedro'),
(50, 'Ana', 'López', 'ana', 'ana@mail.com', 'ana');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vuelos`
--

CREATE TABLE `vuelos` (
  `codigo` varchar(6) NOT NULL,
  `origen` varchar(50) NOT NULL,
  `destino` varchar(50) NOT NULL,
  `operadora` varchar(50) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `clase` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `vuelos`
--

INSERT INTO `vuelos` (`codigo`, `origen`, `destino`, `operadora`, `fecha`, `clase`) VALUES
('06LG7R', 'Firenze', 'Munich', 'Lufthansa', '2021-04-13', 'First Class'),
('1LBI4C', 'Glasgow', 'Newcastle', 'British Airways', '2021-02-25', 'Economy'),
('1MENL9', 'Barcelona', 'Zaragoza', 'Iberia', '2020-12-24', 'Premium Economy'),
('2K0Y9S', 'Alicante', 'Tenerife', 'Iberia', '2021-06-16', 'First Class'),
('2YK86Y', 'Londres', 'El Cairo', 'Emirates Airlines', '2021-05-20', 'Bussiness'),
('3R6RNL', 'Barcelona', 'Viena', 'Iberia', '2022-12-16', 'First Class'),
('45XF51', 'Helsinki', 'Oslo', 'American Airlines', '2021-02-26', 'Bussiness'),
('4I58Y3', 'Moscu', 'Stalingrado', 'Emirates Airlines', '2021-03-26', 'First Class'),
('4N872J', 'Valencia', 'Munich', 'Lufthansa', '2021-03-11', 'First Class'),
('4XOH2O', 'Alicante', 'Tenerife', 'Iberia', '2021-06-17', 'First Class'),
('59A6OI', 'Lisboa', 'Oporto', 'TAP Portugal', '2021-03-26', 'Economy'),
('60BVYH', 'Zaragoza', 'Alicante', 'Iberia', '2021-12-31', 'Economy'),
('6P4YS9', 'Barcelona', 'Viena', 'Emirates Airlines', '2021-02-19', 'First Class'),
('77150D', 'Sevilla', 'Tenerife', 'Lufthansa', '2021-03-11', 'First Class'),
('7E2D0G', 'Zaragoza', 'Londres', 'Iberia', '2020-12-19', 'Economy'),
('86IXJ4', 'Frankfurt', 'Estocolmo', 'Lufthansa', '2021-06-23', 'First Class'),
('89Z8Q1', 'Glasgow', 'Bath', 'Lufthansa', '2021-06-09', 'First Class'),
('8MODMI', 'Barcelona', 'New York', 'Norwegian', '2021-02-11', 'Bussiness'),
('948LE5', 'Bristol', 'Roma', 'British Airways', '2021-08-12', 'First Class'),
('9BD89X', 'Madrid', 'Barcelona', 'Alitalia', '2021-08-11', 'Bussiness'),
('9G34C0', 'Roma', 'Berna', 'Swissair', '2021-02-26', 'Bussiness'),
('A86JHN', 'Barcelona', 'Estocolmo', 'Iberia', '2021-07-06', 'First Class'),
('DB6H66', 'Tokyo', 'Osaka', 'Qatar Airways', '2021-03-23', 'First Class'),
('DI96EH', 'Lima', 'Brasilia', 'Iberia', '2021-07-07', 'First Class'),
('F73EX0', 'Madrid', 'Los Angeles', 'American Airlines', '2021-03-25', 'Premium Economy'),
('FQAJC6', 'Paris', 'New York', 'American Airlines', '2021-10-13', 'First Class'),
('G4AO0J', 'Barcelona', 'Madrid', 'Iberia', '2020-12-11', 'Bussiness'),
('HAO519', 'Xeraco', 'Gran Canarias', 'Iberia', '2021-06-29', 'First Class'),
('JAJ27F', 'Sanghai', 'Londres', 'British Airways', '2021-07-14', 'First Class'),
('K2NWRV', 'Madrid', 'Mallorca', 'Iberia', '2021-04-16', 'Premium Economy'),
('P4CA41', 'Dublin', 'Zaragoza', 'Iberia', '2021-07-01', 'First Class'),
('P4L680', 'San Francisco', 'Springfield', 'American Airlines', '2021-08-12', 'First Class'),
('S30N2W', 'Sanghai', 'Tokyo', 'Japan Airlines', '2021-03-26', 'Premium Economy'),
('S3FKP3', 'Barcelona', 'Madrid', '', '2020-12-05', 'Seleccione Tipo'),
('U488T9', 'Tokyo', 'Sydney', 'Japan Airlines', '2021-04-15', 'First Class'),
('X452RX', 'Buenos Aires', 'Madrid', 'Iberia', '2021-03-24', 'Premium Economy'),
('Y26GHU', 'Budapest', 'Munich', 'Lufthansa', '2021-03-17', 'First Class');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`);

--
-- Indices de la tabla `vuelos`
--
ALTER TABLE `vuelos`
  ADD PRIMARY KEY (`codigo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=51;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
