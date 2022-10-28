INSERT INTO tb_user (name, email, password) VALUES ('Nina Brown', 'nina@gmail.com','$2a$10$NYFZ/8WaQ3Qb6FCs.00jce4nxX9w7AkgWVsQCG6oUwTAcZqP9Flqu');
INSERT INTO tb_user (name, email, password) VALUES ('Leia Red', 'leia@gmail.com', '$2a$10$Nx1W/IhbDS/E/6XDC54Bu.dyu6q07mSOlcHJ0TRF7FjAB6Ok7gXcW');

INSERT INTO tb_role (role_name) VALUES ('ROLE_OPERATION');
INSERT INTO tb_role (role_name) VALUES ('ROLE_ADMIN');

INSERT INTO tb_user_role (user_id, role_id) VALUES (1, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO tb_user_role (user_id, role_id) VALUES (2, 2);