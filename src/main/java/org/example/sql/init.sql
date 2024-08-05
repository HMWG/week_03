CREATE TABLE `products` (
                            `product_id` BIGINT NOT NULL AUTO_INCREMENT,
                            `name` varchar(100) NOT NULL,
                            `price` INT NOT NULL,
                            `quantity` INT NOT NULL,
                            `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
                            PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

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
