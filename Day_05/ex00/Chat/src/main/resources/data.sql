INSERT INTO chat.user VALUES
(default,'User1', 'password'),
(default, 'User2', 'password'),
(default, 'User3', 'password'),
(default, 'User4', 'password'),
(default, 'User5', 'password');

INSERT INTO chat.chatroom VALUES
(default, 'Chat1', (SELECT userID FROM chat.user WHERE login = 'User1')),
(default, 'Chat2', (SELECT userID FROM chat.user WHERE login = 'User2')),
(default, 'Chat3', (SELECT userID FROM chat.user WHERE login = 'User3')),
(default, 'Chat4', (SELECT userID FROM chat.user WHERE login = 'User4')),
(default, 'Chat5', (SELECT userID FROM chat.user WHERE login = 'User5'));

INSERT INTO chat.message VALUES
(default, (SELECT userID FROM chat.user WHERE login = 'User1'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat1'), 'Message1'),
(default, (SELECT userID FROM chat.user WHERE login = 'User2'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat2'), 'Message2'),
(default, (SELECT userID FROM chat.user WHERE login = 'User3'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat3'), 'Message3'),
(default, (SELECT userID FROM chat.user WHERE login = 'User4'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat4'), 'Message4'),
(default, (SELECT userID FROM chat.user WHERE login = 'User5'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat5'), 'Message5');

INSERT INTO chat.chats VALUES
(default, (SELECT userID FROM chat.user WHERE login = 'User1'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat1')),
(default, (SELECT userID FROM chat.user WHERE login = 'User2'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat2')),
(default, (SELECT userID FROM chat.user WHERE login = 'User3'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat3')),
(default, (SELECT userID FROM chat.user WHERE login = 'User4'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat4')),
(default, (SELECT userID FROM chat.user WHERE login = 'User5'), (SELECT chatID FROM chat.chatroom WHERE chatName = 'Chat5'));


