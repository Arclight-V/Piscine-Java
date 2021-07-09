DROP SCHEMA IF EXISTS users CASCADE;
CREATE SCHEMA IF NOT EXISTS usertable;

CREATE TABLE IF NOT EXISTS users.usertable (
    userid int auto_increment,
    email varchar(30) NOT NULL UNIQUE,
    password varchar(30) NOT NULL UNIQUE,
);