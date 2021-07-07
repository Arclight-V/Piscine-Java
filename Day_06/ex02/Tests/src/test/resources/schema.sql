DROP SCHEMA IF EXISTS product CASCADE;
CREATE SCHEMA IF NOT EXISTS product;

CREATE TABLE IF NOT EXISTS product.productTable (
    userID int auto_increment,
    name varchar(30) NOT NULL UNIQUE,
    price int NOT NULL
);