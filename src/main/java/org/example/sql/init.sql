CREATE TABLE product (
                         product_id bigint primary key auto_increment,
                         name varchar(20) not null,
                         price int not null,
                         quantity int not null,
                         created_at datetime not null
);

INSERT INTO product (name, price, quantity, created_at) VALUES ('상품1', 10000, 100, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품2', 20000, 60, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품3', 5500, 200, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품4', 8000, 140, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품5', 15000, 70, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품6', 110000, 37, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품7', 1000, 91, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품8', 6800, 5, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품9', 5790, 1, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품10', 4340, 84, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품11', 129890, 13, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품12', 10400, 73, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품13', 10000, 149, now());
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품14', 45400, 298, now());
