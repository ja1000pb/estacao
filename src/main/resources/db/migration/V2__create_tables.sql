create table usuario (
    id  bigserial not null,
    ativo boolean,
	nome varchar(100),
	email varchar(100),
	senha varchar(100),
    primary key (id)
);

create table permissao (
    id  bigserial not null,
    ativo boolean,
    descricao varchar(100),
    primary key (id)
);

create table usuario_permissao (
	usuario_id bigserial not null,
	permissao_id bigserial not null,
    primary key (usuario_id, permissao_id),
    CONSTRAINT fk_usuariopermissao  foreign key (usuario_id) 
    references usuario (id),
    CONSTRAINT fk_permissaousuario  foreign key (permissao_id) 
    references permissao (id)
);