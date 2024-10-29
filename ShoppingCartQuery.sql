CREATE DATABASE shoppingcart;

USE shoppingcart;

CREATE TABLE user(
	id INT AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE shoppingCart(
	id INT AUTO_INCREMENT,
    name VARCHAR(100),
    category VARCHAR(100),
    price FLOAT(10),
    quantity INT,
    totalValue FLOAT(10),
    item_id INT,
    PRIMARY KEY(id),
    FOREIGN KEY(id) REFERENCES stock(id)
);

CREATE TABLE stock(
	id INT AUTO_INCREMENT,
	name VARCHAR(100),
    category VARCHAR(100),
    price FLOAT(10),
    quantity INT,
    PRIMARY KEY(id)
);

SELECT * FROM shoppingcart;