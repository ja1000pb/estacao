    alter table usuario
    add column cliente_id int8,
    add CONSTRAINT fk_cliente foreign key (cliente_id) 
    references cliente (id)
    ;