CREATE TABLE product (
                         product_id bigint primary key auto_increment,
                         name varchar(20) not null,
                         price int not null,
                         quantity int not null,
                         created_at datetime not null
);

INSERT INTO product (name, price, quantity, created_at) VALUES ('상품1', 10000, 100);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품2', 20000, 60);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품3', 5500, 200);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품4', 8000, 140);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품5', 15000, 70);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품6', 110000, 37);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품7', 1000, 91);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품8', 6800, 5);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품9', 5790, 1);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품10', 4340, 84);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품11', 129890, 13);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품12', 10400, 73);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품13', 10000, 149);
INSERT INTO product (name, price, quantity, created_at) VALUES ('상품14', 45400, 298);
