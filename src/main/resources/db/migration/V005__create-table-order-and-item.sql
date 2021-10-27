CREATE TABLE `order`
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    subtotal           DECIMAL               NOT NULL,
    shipping_fee       DECIMAL               NOT NULL,
    total              DECIMAL               NOT NULL,

    restaurant_id      BIGINT                NOT NULL,
    user_client_id     BIGINT                NOT NULL,
    payment_methods_id BIGINT                NOT NULL,

    status             VARCHAR(10)           NOT NULL,
    creation_date      datetime              NOT NULL,
    confirmation_date  datetime              NULL,
    cancellation_date  datetime              NULL,
    delivery_date      datetime              NULL,
    address_zipcode    VARCHAR(9)            NOT NULL,
    address_street     VARCHAR(100)          NOT NULL,
    address_number     VARCHAR(20)           NOT NULL,
    address_complement VARCHAR(60)           NULL,
    address_district   VARCHAR(60)           NOT NULL,
    address_city_id    BIGINT                NOT NULL,
    address_country    VARCHAR(60)           NULL,

    CONSTRAINT pk_order PRIMARY KEY (id)
);


ALTER TABLE `order`
    ADD CONSTRAINT fk_order_on_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant (id);

ALTER TABLE `order`
    ADD CONSTRAINT fk_order_on_payment_methods FOREIGN KEY (payment_methods_id) REFERENCES payment_methods (id);

ALTER TABLE `order`
    ADD CONSTRAINT fk_order_on_user_client FOREIGN KEY (user_client_id) REFERENCES user (id);


CREATE TABLE order_item
(
    id          BIGINT AUTO_INCREMENT NOT NULL,
    quantity    smallint(6)           NOT NULL,
    unit_price  decimal(10,2)         NOT NULL,
    total_price decimal(10,2)         NOT NULL,
    observation VARCHAR(255)          NULL,
    order_id    BIGINT                NOT NULL,
    product_id  BIGINT                NOT NULL,

    CONSTRAINT pk_orderitem PRIMARY KEY (id)
);

ALTER TABLE order_item
    ADD CONSTRAINT fk_order_item_on_order FOREIGN KEY (order_id) REFERENCES `order` (id);

ALTER TABLE order_item
    ADD CONSTRAINT fk_order_item_on_product FOREIGN KEY (product_id) REFERENCES product (id);