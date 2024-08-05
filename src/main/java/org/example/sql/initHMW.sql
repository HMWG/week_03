use shop;

drop table if exists Refund;
drop table if exists order_items;
drop table if exists basket_items;
drop table if exists deliver;
drop table if exists delivery_address;
drop table if exists orderEntities;
drop table if exists users;
drop table if exists products;


CREATE TABLE users (
                       user_id bigint AUTO_INCREMENT PRIMARY KEY,
                       name VARCHAR(20) NOT NULL,
                       email VARCHAR(20) NOT NULL,
                       password VARCHAR(20) NOT NULL,
                       phone_num VARCHAR(20) NOT NULL,
                       is_admin boolean NOT NULL,
                       created_at DATETIME DEFAULT CURRENT_TIMESTAMP
)ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
ALTER TABLE users
ADD CONSTRAINT unique_name UNIQUE (name),
ADD CONSTRAINT unique_email UNIQUE (email);

CREATE TABLE orderEntities(
                       order_id BIGINT NOT NULL auto_increment,
                       user_id BIGINT NOT NULL,
                       order_detail VARCHAR(100) NOT NULL,
                       total_price INT NOT NULL,
                       order_status ENUM('PAY_REQUEST', 'ORDER_CANCELED', 'PAY_COMPLETE', 'SHIPPING', 'SHIPPED', 'REFUND_REQUEST', 'REFUND_COMPLETED') NOT NULL,
                       created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       PRIMARY KEY (order_id),
                       FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE products (
                          product_id BIGINT NOT NULL AUTO_INCREMENT,
                          name varchar(100) NOT NULL,
                          price INT NOT NULL,
                          quantity INT NOT NULL,
                          created_at datetime DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE order_items (
                             order_id BIGINT NOT NULL,
                             product_id BIGINT NOT NULL,
                             FOREIGN KEY (order_id) REFERENCES orderEntities(order_id) ON DELETE CASCADE,
                             FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE basket_items (
                              user_id BIGINT NOT NULL,
                              product_id BIGINT NOT NULL,
                              quantity INT NOT NULL,
                              FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
                              FOREIGN KEY (product_id) REFERENCES products(product_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE delivery_address (
                                  delivery_address_id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  user_id BIGINT NOT NULL,
                                  address VARCHAR(50) NOT NULL,
                                  is_configured boolean NOT NULL,
                                  coordinate POINT NOT NULL,
                                  FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE deliver (
                         delivery_id BIGINT NOT NULL AUTO_INCREMENT,
                         delivery_address_id BIGINT NOT NULL,
                         order_id BIGINT NOT NULL,
                         PRIMARY KEY (delivery_id),
                         FOREIGN KEY (delivery_address_id) REFERENCES Delivery_Address(delivery_address_id) ON DELETE CASCADE,
                         FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE Refund(
                       refund_id BIGINT NOT NULL AUTO_INCREMENT,
                       order_id BIGINT NOT NULL,
                       refund_reason varchar(100) NOT NULL,
                       PRIMARY KEY (refund_id),
                       FOREIGN KEY (order_id) REFERENCES Orders(order_id) ON DELETE CASCADE
);



INSERT INTO products (name, price, quantity) VALUES ('상품1', 10000, 100);
INSERT INTO products (name, price, quantity) VALUES ('상품2', 20000, 60);
INSERT INTO products (name, price, quantity) VALUES ('상품3', 5500, 200);
INSERT INTO products (name, price, quantity) VALUES ('상품4', 8000, 140);
INSERT INTO products (name, price, quantity) VALUES ('상품5', 15000, 70);
INSERT INTO products (name, price, quantity) VALUES ('상품6', 110000, 37);
INSERT INTO products (name, price, quantity) VALUES ('상품7', 1000, 91);
INSERT INTO products (name, price, quantity) VALUES ('상품8', 6800, 5);
INSERT INTO products (name, price, quantity) VALUES ('상품9', 5790, 1);
INSERT INTO products (name, price, quantity) VALUES ('상품10', 4340, 84);
INSERT INTO products (name, price, quantity) VALUES ('상품11', 129890, 13);
INSERT INTO products (name, price, quantity) VALUES ('상품12', 10400, 73);
INSERT INTO products (name, price, quantity) VALUES ('상품13', 10000, 149);
INSERT INTO products (name, price, quantity) VALUES ('상품14', 45400, 298);

insert INTO users (name, email, password, phone_num, is_admin) values ('a', 'a@a.com', '1234', '12345678', false);
insert INTO users (name, email, password, phone_num, is_admin) values ('b', 'b@a.com', '5678', '12345678', false);
insert INTO users (name, email, password, phone_num, is_admin) values ('admin', 'admin@a.com', '1231234', '12345678', true);
INSERT INTO orderEntities (user_Id, order_detail, total_price, order_status) values (2, 'qwe', 200, 'PAY_REQUEST');
INSERT INTO orderEntities (user_Id, order_detail, total_price, order_status) values (3, 'zxczxc', 0, 'SHIPPING');