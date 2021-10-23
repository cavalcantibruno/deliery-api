insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Arabe');
insert into kitchen (id, name) values (3, 'Indiana');


insert into restaurants (name, shipping_fee, kitchen_id) values ('Thai Gourmet', 10, 1);
insert into restaurants (name, shipping_fee, kitchen_id) values ('Syrian Food', 8, 2);
insert into restaurants (name, shipping_fee, kitchen_id) values ('Gigo Sfiha', 5, 2);
insert into restaurants (name, shipping_fee, kitchen_id) values ('Indian Food', 15, 3);

insert into state (id, name) values (1, 'São Paulo');
insert into state (id, name) values (2, 'Pernambuco');
insert into state (id, name) values (3, 'Minas Gerais');
insert into state (id, name) values (4, 'Rio de Janeiro');

insert into city (id, name, state_id) values (1, 'São Bernardo do Campo', 1);
insert into city (id, name, state_id) values (2, 'Santo André', 1);
insert into city (id, name, state_id) values (3, 'São Caetano', 1);

insert into city (id, name, state_id) values (4, 'Recife', 2);
insert into city (id, name, state_id) values (5, 'Olinda', 2);

insert into city (id, name, state_id) values (6, 'Belo Horizonte', 3);

insert into city (id, name, state_id) values (7, 'Paraty', 4);
insert into city (id, name, state_id) values (8, 'Cabo Frio', 4);
insert into city (id, name, state_id) values (9, 'Arraial do Cabo', 4);


