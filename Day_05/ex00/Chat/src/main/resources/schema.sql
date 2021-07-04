CREATE SCHEMA IF NOT EXISTS chat;

CREATE TABLE IF NOT EXISTS chat.user (
    userID SERIAL PRIMARY KEY,
    login varchar(30) NOT NULL UNIQUE,
    password varchar(30) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.chatroom (
    chatID SERIAL PRIMARY KEY,
    chatName varchar(30) NOT NULL UNIQUE,
    chatOwner int REFERENCES chat.user(userID) NOT NULL
);

CREATE TABLE IF NOT EXISTS chat.message (
    messageID SERIAL PRIMARY KEY,
    author int REFERENCES chat.user(userID) NOT NULL,
    chatRoom int REFERENCES chat.chatroom(chatID) NOT NULL,
    text varchar(max) NOT NULL,
    localDataTime TIMESTAMP NOT NULL
)