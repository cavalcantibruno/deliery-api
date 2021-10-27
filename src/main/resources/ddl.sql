create table city (id bigint not null auto_increment, name varchar(255), state_id bigint, primary key (id)) engine=InnoDB
create table kitchen (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table order (id bigint not null auto_increment, address_complement varchar(255), address_country varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), address_zipcode varchar(255), cancellation_date datetime(6), confirmation_date datetime(6), creation_date datetime(6), delivery_date datetime(6), shipping_fee decimal(19,2), status integer, subtotal decimal(19,2), total decimal(19,2), address_city_id bigint, user_client_id bigint not null, payment_methods_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table order_item (id bigint not null auto_increment, observation varchar(255), quantity integer, total_price decimal(19,2), unit_price decimal(19,2), order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table payment (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table payment_methods (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table permission (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table product (id bigint not null auto_increment, active bit, description varchar(255), name varchar(255), price decimal(19,2), restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table profile (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table profile_permissions (profile_id bigint not null, permission_id bigint not null) engine=InnoDB
create table restaurant (id bigint not null auto_increment, address_complement varchar(255), address_country varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), address_zipcode varchar(255), create_date datetime(6) not null, name varchar(255), shipping_fee decimal(19,2), update_date datetime(6) not null, address_city_id bigint, kitchen_id bigint not null, primary key (id)) engine=InnoDB
create table restaurant_payment_methods (restaurant_id bigint not null, payment_methods_id bigint not null) engine=InnoDB
create table state (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table user (id bigint not null auto_increment, create_date datetime(6) not null, email varchar(255), name varchar(255), password varchar(255), primary key (id)) engine=InnoDB
create table user_profile (user_id bigint not null, profile_id bigint not null) engine=InnoDB
alter table city add constraint FK6p2u50v8fg2y0js6djc6xanit foreign key (state_id) references state (id)
alter table order add constraint FKcf8v4u565syfcb0ybjrkawsdf foreign key (address_city_id) references city (id)
alter table order add constraint FKbxpmw9isre0rl3cwimb98x6ue foreign key (user_client_id) references user (id)
alter table order add constraint FK7bckohj544j2fweuld0ksqtwk foreign key (payment_methods_id) references payment_methods (id)
alter table order add constraint FK64rch5g46ue4a83ww6cq7r92w foreign key (restaurant_id) references restaurant (id)
alter table order_item add constraint FKt6wv8m7eshksp5kp8w4b2d1dm foreign key (order_id) references order (id)
alter table order_item add constraint FK551losx9j75ss5d6bfsqvijna foreign key (product_id) references product (id)
alter table product add constraint FKp4b7e36gck7975p51raud8juk foreign key (restaurant_id) references restaurant (id)
alter table profile_permissions add constraint FK2av7ndo3xvosgoku6l9ml6xdo foreign key (permission_id) references permission (id)
alter table profile_permissions add constraint FK58sdxd4c83xuvusbujkp83xdm foreign key (profile_id) references profile (id)
alter table restaurant add constraint FK8pcwgn41lfg43d8u82ewn3cn foreign key (address_city_id) references city (id)
alter table restaurant add constraint FKrur1dqx79jim8s8dvdp16qc3d foreign key (kitchen_id) references kitchen (id)
alter table restaurant_payment_methods add constraint FKkqiblg41jgkvfpmpdoxrp8t0u foreign key (payment_methods_id) references payment_methods (id)
alter table restaurant_payment_methods add constraint FKy9pnuae6v8kwh57mdyum00o5 foreign key (restaurant_id) references restaurant (id)
alter table user_profile add constraint FKqfbftbxicceqbmvj87g9be2qn foreign key (profile_id) references profile (id)
alter table user_profile add constraint FK6kwj5lk78pnhwor4pgosvb51r foreign key (user_id) references user (id)
create table city (id bigint not null auto_increment, name varchar(255), state_id bigint, primary key (id)) engine=InnoDB
create table kitchen (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table order (id bigint not null auto_increment, address_complement varchar(255), address_country varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), address_zipcode varchar(255), cancellation_date datetime(6), confirmation_date datetime(6), creation_date datetime(6), delivery_date datetime(6), shipping_fee decimal(19,2), status integer, subtotal decimal(19,2), total decimal(19,2), address_city_id bigint, user_client_id bigint not null, payment_methods_id bigint not null, restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table order_item (id bigint not null auto_increment, observation varchar(255), quantity integer, total_price decimal(19,2), unit_price decimal(19,2), order_id bigint not null, product_id bigint not null, primary key (id)) engine=InnoDB
create table payment (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table payment_methods (id bigint not null auto_increment, description varchar(255), primary key (id)) engine=InnoDB
create table permission (id bigint not null auto_increment, description varchar(255), name varchar(255), primary key (id)) engine=InnoDB
create table product (id bigint not null auto_increment, active bit, description varchar(255), name varchar(255), price decimal(19,2), restaurant_id bigint not null, primary key (id)) engine=InnoDB
create table profile (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table profile_permissions (profile_id bigint not null, permission_id bigint not null) engine=InnoDB
create table restaurant (id bigint not null auto_increment, address_complement varchar(255), address_country varchar(255), address_district varchar(255), address_number varchar(255), address_street varchar(255), address_zipcode varchar(255), create_date datetime(6) not null, name varchar(255), shipping_fee decimal(19,2), update_date datetime(6) not null, address_city_id bigint, kitchen_id bigint not null, primary key (id)) engine=InnoDB
create table restaurant_payment_methods (restaurant_id bigint not null, payment_methods_id bigint not null) engine=InnoDB
create table state (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table user (id bigint not null auto_increment, create_date datetime(6) not null, email varchar(255), name varchar(255), password varchar(255), primary key (id)) engine=InnoDB
create table user_profile (user_id bigint not null, profile_id bigint not null) engine=InnoDB
alter table city add constraint FK6p2u50v8fg2y0js6djc6xanit foreign key (state_id) references state (id)
alter table order add constraint FKcf8v4u565syfcb0ybjrkawsdf foreign key (address_city_id) references city (id)
alter table order add constraint FKbxpmw9isre0rl3cwimb98x6ue foreign key (user_client_id) references user (id)
alter table order add constraint FK7bckohj544j2fweuld0ksqtwk foreign key (payment_methods_id) references payment_methods (id)
alter table order add constraint FK64rch5g46ue4a83ww6cq7r92w foreign key (restaurant_id) references restaurant (id)
alter table order_item add constraint FKt6wv8m7eshksp5kp8w4b2d1dm foreign key (order_id) references order (id)
alter table order_item add constraint FK551losx9j75ss5d6bfsqvijna foreign key (product_id) references product (id)
alter table product add constraint FKp4b7e36gck7975p51raud8juk foreign key (restaurant_id) references restaurant (id)
alter table profile_permissions add constraint FK2av7ndo3xvosgoku6l9ml6xdo foreign key (permission_id) references permission (id)
alter table profile_permissions add constraint FK58sdxd4c83xuvusbujkp83xdm foreign key (profile_id) references profile (id)
alter table restaurant add constraint FK8pcwgn41lfg43d8u82ewn3cn foreign key (address_city_id) references city (id)
alter table restaurant add constraint FKrur1dqx79jim8s8dvdp16qc3d foreign key (kitchen_id) references kitchen (id)
alter table restaurant_payment_methods add constraint FKkqiblg41jgkvfpmpdoxrp8t0u foreign key (payment_methods_id) references payment_methods (id)
alter table restaurant_payment_methods add constraint FKy9pnuae6v8kwh57mdyum00o5 foreign key (restaurant_id) references restaurant (id)
alter table user_profile add constraint FKqfbftbxicceqbmvj87g9be2qn foreign key (profile_id) references profile (id)
alter table user_profile add constraint FK6kwj5lk78pnhwor4pgosvb51r foreign key (user_id) references user (id)
