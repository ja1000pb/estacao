create table pluviometro (
    id  bigserial not null,
    ativo boolean not null,
    dthinicio timestamp,
    dthfim timestamp,
    duracaochuva numeric(15,4),
    quantidadecuva numeric(15,4),
    estacao_id int8,
    primary key (id),
    CONSTRAINT fk_estacao  foreign key (estacao_id) 
    references estacao (id)
);

create table indicadores (
    id  bigserial not null,
    ativo boolean not null,
    dtindicador timestamp,
    tempmaxdia numeric(15,4),   
    tempminnoite numeric(15,4),
    tempamplitude numeric(15,4),
    horasluz numeric(15,4), 
    luzmax numeric(15,4),
    luzmedia numeric(15,4),
    estacao_id int8,
    primary key (id),
    CONSTRAINT fk_estacao  foreign key (estacao_id) 
    references estacao (id)
);

create table cliente (
    id  bigserial not null,
    ativo boolean,
    bairro varchar(80),
    cep varchar(20),
    cidade varchar(80),
    cnpjcpf varchar(18),
    complemento varchar(80),
    estado varchar(80),
    ierg varchar(20),
    logo bytea,
    logradouro varchar(80),
    nome varchar(100),
    numero varchar(255),
    uf varchar(2),
    telefone1 varchar(15),
    telefone2 varchar(15),
	primary key (id)
);
  
create table cultura (
    id  bigserial not null,
    ativo boolean,  
    nome varchar(100),
    primary key (id)
);

create table cultura_cliente (
    id  bigserial not null,
    ativo boolean,
    dtplantio timestamp,
    adubacao varchar(255),
    espacentrelinhas varchar(255),
    espacplantas varchar(255),
    arcodecultivo varchar(255),
    profundeoperacao varchar(255),
    cultura_id int8,
    cliente_id int8,
    primary key (id),
    CONSTRAINT fk_cultura foreign key (cultura_id) 
    references cultura (id),
    CONSTRAINT fk_cliente foreign key (cliente_id) 
    references cliente (id)
);

  
    alter table leitura  
       add column dia varchar(100),
       add column possibilidadechuva numeric(15,4) 
    ;

    alter table estacao 
       add column empresa_id int8,
       add CONSTRAINT fk_Empresa  foreign key (empresa_id) 
       references empresa (id)
    ;