set foreign_key_checks  = 0;

delete from state;
delete from city;
delete from kitchen;
delete from payment;
delete from payment_methods;
delete from permission;
delete from product;
delete from profile;
delete from profile_permissions;
delete from restaurant;
delete from restaurant_payment_methods;
delete from user;
delete from user_profile;

set foreign_key_checks  = 1;

alter table state auto_increment = 1;
alter table city auto_increment = 1;
alter table kitchen auto_increment = 1;
alter table payment auto_increment = 1;
alter table payment_methods auto_increment = 1;
alter table permission auto_increment = 1;
alter table product auto_increment = 1;
alter table profile auto_increment = 1;
alter table profile_permissions auto_increment = 1;
alter table restaurant auto_increment = 1;
alter table restaurant_payment_methods auto_increment = 1;
alter table user auto_increment = 1;
alter table user_profile auto_increment = 1;

insert into kitchen (id, name) values (1, 'Tailandesa');
insert into kitchen (id, name) values (2, 'Arabe');
insert into kitchen (id, name) values (3, 'Indiana');

insert into state (id, name) values (1, 'São Paulo');
insert into state (id, name) values (2, 'Pernambuco');
insert into state (id, name) values (3, 'Minas Gerais');
insert into state (id, name) values (4, 'Rio de Janeiro');
insert into state (id, name) values (5, 'Ceará');

insert into city (id, name, state_id) values (1, 'São Bernardo do Campo', 1);
insert into city (id, name, state_id) values (2, 'Santo André', 1);
insert into city (id, name, state_id) values (3, 'São Caetano', 1);
insert into city (id, name, state_id) values (4, 'Uberlândia', 1);
insert into city (id, name, state_id) values (5, 'Belo Horizonte', 3);
insert into city (id, name, state_id) values (6, 'São Paulo', 1);
insert into city (id, name, state_id) values (7, 'Campinas', 1);
insert into city (id, name, state_id) values (8, 'Fortaleza', 5);
insert into city (id, name, state_id) values (9, 'Recife', 2);
insert into city (id, name, state_id) values (10, 'Olinda', 2);
insert into city (id, name, state_id) values (11, 'Paraty', 4);
insert into city (id, name, state_id) values (12, 'Cabo Frio', 4);
insert into city (id, name, state_id) values (13, 'Arraial do Cabo', 4);

insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (1, 'Frutos Olinda', 10, 1, utc_timestamp, utc_timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (2, 'Syrian Food', 8, 2, utc_timestamp, utc_timestamp, 2, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (3, 'Gigo', 5, 2, utc_timestamp, utc_timestamp, 3, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (4, 'Indian Food', 15, 3, utc_timestamp, utc_timestamp, 4, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (5, 'Tuk Tuk', 0, 3, utc_timestamp, utc_timestamp, 5, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (6, 'Alibaba', 0, 3, utc_timestamp, utc_timestamp, 6, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (7, 'Ali Food', 0, 3, utc_timestamp, utc_timestamp, 7, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (8, 'Thai Gourmet', 10, 1, utc_timestamp, utc_timestamp, 8, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (9, 'Thai Delivery', 9.50, 1, utc_timestamp, utc_timestamp, 9, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, name, shipping_fee, kitchen_id, create_date, update_date, address_city_id, address_zipcode, address_street, address_number, address_district) values (10, 'China ib', 15, 2, utc_timestamp, utc_timestamp, 10, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');

insert into payment_methods (id, description) values (1, 'Cartão de crédito');
insert into payment_methods (id, description) values (2, 'Cartão de débito');
insert into payment_methods (id, description) values (3, 'Dinheiro');

insert into permission (id, name, description) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
insert into permission (id, name, description) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

insert into restaurant_payment_methods (restaurant_id, payment_methods_id) values (2, 1), (4, 2), (7, 3), (2, 3), (3, 2), (3, 3);

insert into product (name, description, price, active, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);
insert into product (name, description, price, active, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);
insert into product (name, description, price, active, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);
insert into product (name, description, price, active, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);
insert into product (name, description, price, active, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);
insert into product (name, description, price, active, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);