create table payment (
    id bigint not null auto_increment,
    description varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table payment_methods (
    id bigint not null auto_increment,
    description varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;


create table permission (
    id bigint not null auto_increment,
    description varchar(60) not null,
    name varchar(100) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table product (
    id bigint not null auto_increment,
    active tinyint(1) not null,
    description text not null,
    name varchar(255) not null,
    price decimal(10,2) not null,
    restaurant_id bigint not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table profile (
    id bigint not null auto_increment,
    name varchar(60) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table profile_permissions (
    profile_id bigint not null,
    permission_id bigint not null,

    primary key (profile_id, permission_id)
) engine=InnoDB default charset=utf8;

create table restaurant (
    id bigint not null auto_increment,
    kitchen_id bigint not null,
    create_date datetime not null,
    name varchar(255) not null,
    shipping_fee decimal(10,2) not null,
    update_date datetime not null,

    address_city_id bigint,
    address_complement varchar(60),
    address_country varchar(60),
    address_district varchar(60),
    address_number varchar(20),
    address_street varchar(100),
    address_zipcode varchar(9),

    primary key (id)
) engine=InnoDB default charset=utf8;

create table restaurant_payment_methods (
    restaurant_id bigint not null,
    payment_methods_id bigint not null,

    primary key (restaurant_id, payment_methods_id)

) engine=InnoDB default charset=utf8;

create table user (
    id bigint not null auto_increment,
    create_date datetime not null,
    email varchar(255) not null,
    name varchar(100) not null,
    password varchar(255) not null,

    primary key (id)
) engine=InnoDB default charset=utf8;

create table user_profile (
    user_id bigint not null,
    profile_id bigint not null,

    primary key (user_id, profile_id)

) engine=InnoDB default charset=utf8;


alter table product add constraint fk_product_restaurant
    foreign key (restaurant_id) references restaurant (id);

alter table profile_permissions add constraint fk_profile_permissions_id
    foreign key (permission_id) references permission (id);

alter table profile_permissions add constraint fk_permissions_profile_id
    foreign key (profile_id) references profile (id);

alter table restaurant add constraint fk_restaurant_city
    foreign key (address_city_id) references city (id);

alter table restaurant add constraint fk_restaurant_kitchen
    foreign key (kitchen_id) references kitchen (id);

alter table restaurant_payment_methods add constraint fk_restaurant_payment_methods_id
    foreign key (payment_methods_id) references payment_methods (id);

alter table restaurant_payment_methods add constraint fk_payment_methods_restaurant_id
    foreign key (restaurant_id) references restaurant (id);

alter table user_profile add constraint fk_user_profile_id
    foreign key (profile_id) references profile (id);

alter table user_profile add constraint fk_profile_user_id
    foreign key (user_id) references user (id);

