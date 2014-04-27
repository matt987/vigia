CREATE DATABASE  IF NOT EXISTS `vigia`;
USE `vigia`;


DROP TABLE IF EXISTS `Clientes`;

CREATE TABLE `Clientes` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Id del cliente',
  `nombre` varchar(255) NOT NULL COMMENT 'nombre del cliente',
  `apellido` varchar(255) DEFAULT NULL COMMENT 'apellido del cliente',
  `ci` varchar(255) NOT NULL COMMENT 'cedula de identidad del cliente',
  `tipo_cliente` varchar(255) NOT NULL COMMENT 'tipo de cliente',
  `ruc`varchar(255) COMMENT 'RUC del cliente',
  `direccion` varchar(255) COMMENT 'direccion del cliente',
  `telefono` varchar(255) COMMENT 'telefono del cliente',
  `eliminado` int(1) DEFAULT '0' COMMENT 'bit de eliminado debe ser 1 si esta eliminado y cero si aun no esta eliminado',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_clientes_UNIQUE` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;


INSERT INTO `Clientes` VALUES (1,'Carlos','Wozniak','33126879', 'preferencial', '504806', 'Eva Peron y Blas Parera', '+549354343223', 0),
    (2,'Cristina','Gates','28567843', 'preferencial', '504806', 'Mcal Estigarribia 1', '+549354343223', 0),
    (3,'Ariel','Renly','4123765', 'preferencial', '504806', 'Eva Peron y Blas Parera 2', '+549354343223', 0),
    (4,'Juan','Grisson','4123987', 'preferencial', '504806', 'Mcal Estigarribia 2', '+549354343223', 0),
    (5,'Yanina','Wincroft','2456763', 'preferencial', '504806', 'Eva Peron y Blas Parera 3', '+549354343223', 1),
    (6,'Pedro','Mycroft','25765234', 'preferencial', '504806', 'Mcal Estigarribia 3', '+549354343223', 0),
    (7,'Milagros','Alvarez','1234432', 'preferencial', '504806', 'Eva Peron y Blas Parera 4', '+549354343223', 0),
    (8,'Esmilce','Delvalle','12321123', 'preferencial', '504806', 'Mcal Estigarribia 4', '+549354343223', 1),
    (9,'Hugo','Sendoa','321123654', 'preferencial', '504806', 'Eva Peron y Blas Parera 5', '+549354343223', 1),
    (10,'Nestor','Tapia','87645234', 'preferencial', '504806', 'Mcal Estigarribia 5', '+549354343223', 0)
