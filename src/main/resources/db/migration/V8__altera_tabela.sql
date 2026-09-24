    alter table pluviometro
    add column leitura_id int8,
    add CONSTRAINT fk_leitura foreign key (leitura_id) 
    references leitura (id)
    ;