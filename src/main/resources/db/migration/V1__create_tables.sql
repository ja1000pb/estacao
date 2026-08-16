
    create table empresa (
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
       primary key (id)
    )
; 
    
    create table estacao (
       id  bigserial not null,
       ativo boolean not null,
       nome varchar(100),
       dtinstalacao date,
       obs varchar(255),
       localgps varchar(100),
       primary key (id)
    )
; 
    
    create table leitura (
       id  bigserial not null,
       ativo boolean not null,
       dthleitura timestamp,
       umidadesolo30 numeric(15,4), 
       umidadesolo60 numeric(15,4),
       umidadesolo90 numeric(15,4),
       temperatura numeric(15,4),
       umidaddear numeric(15,4),
       pressao numeric(15,4),
       pressaorelativa numeric(15,4),
       luminosidade numeric(15,4),  
       estacao_id int8,
       primary key (id),
       CONSTRAINT fk_estacao  foreign key (estacao_id) 
       references estacao (id)
    )
; 
    
    