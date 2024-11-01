CREATE DATABASE shoppingcart;

USE shoppingcart;

CREATE TABLE stock(
    id INT AUTO_INCREMENT,
    name VARCHAR(100),
    category VARCHAR(100),
    price FLOAT(10),
    quantity INT,
    PRIMARY KEY(id)
);

CREATE TABLE user(
    id INT AUTO_INCREMENT,
    name VARCHAR(100),
    PRIMARY KEY(id)
);

CREATE TABLE shoppingCart (
    id INT AUTO_INCREMENT PRIMARY KEY,
    stock_id INT,
    name VARCHAR(100),
    category VARCHAR(100),
    price FLOAT(10),
    quantity INT,
    totalValue FLOAT(10),
    FOREIGN KEY (stock_id) REFERENCES stock(id) ON DELETE CASCADE
);

SELECT * FROM user;
SELECT * FROM shoppingcart;
SELECT * FROM stock;