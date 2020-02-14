
CREATE TABLE `clientes` (
  `DNI` varchar(9) NOT NULL,
  `nombre` varchar(20) NOT NULL,
  `apellidos` varchar(50) NOT NULL,
  `telefono` varchar(15) NOT NULL
);

CREATE TABLE `poliza` (
  `id_poliza` int(11) NOT NULL,
  `id_seguro` int(11) NOT NULL,
  `comentario` varchar(255) NOT NULL
);


CREATE TABLE `seguros` (
  `id_seguro` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio` double NOT NULL
);

CREATE TABLE `union-seguro-vendedor` (
  `id_union` int(11) NOT NULL,
  `DNI_cliente` varchar(9) NOT NULL,
  `DNI_vendedor` varchar(9) NOT NULL,
  `id_poliza` int(11) NOT NULL
);


CREATE TABLE `vendedores` (
  `DNI` varchar(9) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `apellidos` varchar(30) NOT NULL,
  `telefono` int(11) NOT NULL,
  `es_admin` tinyint(1) NOT NULL,
  `activo` tinyint(1) NOT NULL,
  `password` varchar(255) NOT NULL
);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`DNI`);

--
-- Indices de la tabla `poliza`
--
ALTER TABLE `poliza`
  ADD PRIMARY KEY (`id_poliza`);

--
-- Indices de la tabla `seguros`
--
ALTER TABLE `seguros`
  ADD PRIMARY KEY (`id_seguro`);

--
-- Indices de la tabla `union-seguro-vendedor`
--
ALTER TABLE `union-seguro-vendedor`
  ADD PRIMARY KEY (`id_union`,`DNI_cliente`) USING BTREE,
  ADD KEY `FK-clientes-union` (`DNI_cliente`),
  ADD KEY `FK-vendedores-union` (`DNI_vendedor`),
  ADD KEY `FK-poliza-union` (`id_poliza`);

--
-- Indices de la tabla `vendedores`
--
ALTER TABLE `vendedores`
  ADD PRIMARY KEY (`DNI`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `poliza`
--
ALTER TABLE `poliza`
  MODIFY `id_poliza` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `seguros`
--
ALTER TABLE `seguros`
  MODIFY `id_seguro` int(11) NOT NULL AUTO_INCREMENT;
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
  ADD CONSTRAINT `FK-poliza-union` FOREIGN KEY (`id_poliza`) REFERENCES `poliza` (`id_poliza`),
  ADD CONSTRAINT `FK-vendedores-union` FOREIGN KEY (`DNI_vendedor`) REFERENCES `vendedores` (`DNI`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
