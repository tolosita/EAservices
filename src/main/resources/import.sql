/* Insertando roles por defecto */
INSERT INTO ROLES (NOMBRE) VALUES ('ADMIN');
INSERT INTO ROLES (NOMBRE) VALUES ('USUARIO');
/* Insertando usuario por defecto */
INSERT INTO USUARIOS (NOMBRE, APELLIDOS, FECHA_NACIMIENTO, DIRECCION, EMAIL, CLAVE, ROLE, ESTADO) VALUES ('Juan', 'Arboleda', NOW(), '', 'asdasdd@asd', 'asd', 1, 1);