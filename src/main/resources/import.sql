/* Insertando roles por defecto */
INSERT INTO ROLES (NOMBRE) VALUES ('ROLE_ADMIN');
INSERT INTO ROLES (NOMBRE) VALUES ('ROLE_USUARIO');
/* Insertando usuario por defecto */
INSERT INTO USUARIOS (NOMBRE, APELLIDOS, FECHA_NACIMIENTO, DIRECCION, EMAIL, CLAVE, ROLE, ESTADO) VALUES ('Juan', 'Arboleda', NOW(), '', 'asd@asd.asd', '$2a$10$wYz2t4gtGE5YSbu5Np96MeUX.7DBHau1uT6LrY0nteNb4gCDQWjcm', 1, 1);
INSERT INTO USUARIOS (NOMBRE, APELLIDOS, FECHA_NACIMIENTO, DIRECCION, EMAIL, CLAVE, ROLE, ESTADO) VALUES ('Pepito', 'Perez', NOW(), '', 'inactive@asd.asd', '$2a$10$wYz2t4gtGE5YSbu5Np96MeUX.7DBHau1uT6LrY0nteNb4gCDQWjcm', 2, 0);