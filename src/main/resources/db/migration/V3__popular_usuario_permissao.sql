INSERT INTO usuario(ativo, nome, email, senha)
	VALUES ('true', 'Administrador', 'admin@estacao.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario(ativo, nome, email, senha)
	VALUES ('true','Usuario', 'usuario@estacao.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');
INSERT INTO permissao(ativo, descricao)
	VALUES ('true','ROLE_CADASTRAR_EMPRESA');
INSERT INTO permissao(ativo, descricao)
	VALUES ('true','ROLE_PESQUISAR_EMPRESA');
INSERT INTO permissao(ativo, descricao)
	VALUES ('true', 'ROLE_CADASTRAR_ESTACAO');
INSERT INTO permissao(ativo, descricao)
	VALUES ('true', 'ROLE_REMOVER_ESTACAO');
INSERT INTO permissao(ativo, descricao)
	VALUES ('true', 'ROLE_PESQUISAR_ESTACAO');
INSERT INTO permissao(ativo, descricao)
	VALUES ('true', 'ROLE_CADASTRAR_LEITURA');
INSERT INTO permissao(ativo, descricao)
	VALUES ('true', 'ROLE_REMOVER_LEITURA');
INSERT INTO permissao(ativo, descricao)
	VALUES ('true','ROLE_PESQUISAR_LEITURA');
-- admin
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (1, 1);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (1, 2);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (1, 3);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (1, 4);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (1, 5);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (1, 6);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (1, 7);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (1, 8);

-- maria
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (2, 2);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (2, 5);
INSERT INTO usuario_permissao(usuario_id, permissao_id) 
    VALUES (2, 8);
