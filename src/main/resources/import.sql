/* Insertando roles por defecto */
INSERT INTO `roles` (ID, NOMBRE) VALUES (1, 'ROLE_ADMIN');
INSERT INTO `roles` (ID, NOMBRE) VALUES (2, 'ROLE_USUARIO');
/* Insertando usuario por defecto */
INSERT INTO `usuarios` (NOMBRE, APELLIDOS, FECHA_NACIMIENTO, DIRECCION, EMAIL, CLAVE, ROLE, ESTADO) VALUES ('Estefany', 'Duque', NOW(), '', 'd.estefany.g@outlook.com', '$2a$10$ibYGGYyyd3W0rE/.QVU9TeviVc5oJUlkr2cAE8WHRTji2/9YwNl7i', 1, 1);
INSERT INTO `usuarios` (NOMBRE, APELLIDOS, FECHA_NACIMIENTO, DIRECCION, EMAIL, CLAVE, ROLE, ESTADO) VALUES ('Pepito', 'Perez', NOW(), '', 'inactive@asd.asd', '$2a$10$ibYGGYyyd3W0rE/.QVU9TeviVc5oJUlkr2cAE8WHRTji2/9YwNl7i', 2, 0);
/* Insertando causas por defecto */
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (1, '001', 'Accesorio de espalda y/o cargadera');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (2, '002', 'Broche');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (3, '003', 'Soporte accesorio');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (4, '004', 'Falta cargadera sencilla');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (5, '005', 'Zetas');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (6, '006', 'Taco');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (7, '007', 'Migración color');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (8, '008', 'Roto');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (9, '009', 'Descosido');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (10, '0010', 'Sucio');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (11, '0011', 'Varilla de la copa expuesta');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (12, '0012', 'Cargaderas torcidas');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (13, '0013', 'Desdoble de sesgo copa');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (14, '0014', 'Cambiar almohadillas removibles del brasier');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (15, '0015', 'Logo al revés de la marca');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (16, '0016', 'Extensor malo');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (17, '0017', 'Recortar o ampliar contorno');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (18, '0018', 'Solto presilla');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (19, '0019', 'Copa imperfecta');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (20, '0020', 'Cambiar cargadera sencilla');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (21, '0021', 'Recortar cargadera');
INSERT INTO `causas` (ID, CODIGO, NOMBRE) VALUES (22, 'OTRO', 'Otro');
/* Insertando tipos de documentos por defecto */
INSERT INTO `tipo_documentos` (ID, CODIGO, NOMBRE) VALUES (1, 'CC', 'Cédula de ciudadanía');
INSERT INTO `tipo_documentos` (ID, CODIGO, NOMBRE) VALUES (2, 'TI', 'Tarjeta de identidad');
INSERT INTO `tipo_documentos` (ID, CODIGO, NOMBRE) VALUES (3, 'TP', 'Tarjeta pasaporte');

INSERT INTO `clientes` (ID, TIPO_DOCUMENTO, NRO_DOCUMENTO, NOMBRE, APELLIDOS, EMAIL, TELEFONO, CELULAR, DIRECCION, PAIS, CIUDAD, FECHA_INGRESO, ESTADO) VALUES (1, 1, '123456', 'asd', 'asd', 'asd@asd.asd', '123456', '123456', '', '', '', NOW(), 1);
INSERT INTO `clientes` (ID, TIPO_DOCUMENTO, NRO_DOCUMENTO, NOMBRE, APELLIDOS, EMAIL, TELEFONO, CELULAR, DIRECCION, PAIS, CIUDAD, FECHA_INGRESO, ESTADO) VALUES (2, 1, '654321', 'asd', 'asd', 'asd@asd.asd', '123456', '123456', '', '', '', NOW(), 1);
