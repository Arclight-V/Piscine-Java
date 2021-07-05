INSERT INTO chat.user VALUES
(default,'User1', 'password');
INSERT INTO chat.user VALUES
(default, 'User2', 'password');
INSERT INTO chat.user VALUES
(default, 'User3', 'password');
INSERT INTO chat.user VALUES
(default, 'User4', 'password');
INSERT INTO chat.user VALUES
(default, 'User5', 'password');

INSERT INTO chat.chatroom VALUES
(default, 'Chat1', (SELECT userID FROM chat.user WHERE login = 'User1'));
INSERT INTO chat.chatroom VALUES
(default, 'Chat2', (SELECT userID FROM chat.user WHERE login = 'User2'));
INSERT INTO chat.chatroom VALUES
(default, 'Chat3', (SELECT userID FROM chat.user WHERE login = 'User3'));
INSERT INTO chat.chatroom VALUES
(default, 'Chat4', (SELECT userID FROM chat.user WHERE login = 'User4'));
INSERT INTO chat.chatroom VALUES
(default, 'Chat5', (SELECT userID FROM chat.user WHERE login = 'User5'));
