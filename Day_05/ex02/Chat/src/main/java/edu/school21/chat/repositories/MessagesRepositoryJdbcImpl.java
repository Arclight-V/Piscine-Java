package edu.school21.chat.repositories;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import java.sql.*;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {

    private DataSource dataSource;
    private Message    message;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;
    String selectMessageId = "SELECT * FROM chat.message WHERE messageID = ";
    String selectUserId =  "SELECT * FROM chat.user WHERE userID = ";
    String selectChatRoomId =  "SELECT * FROM chat.chatroom WHERE chatID = ";
    Long userId;
    Long chatId;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        try {
            preparedStatement = dataSource.getConnection().prepareStatement(selectMessageId + id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            message = new Message(resultSet.getLong(1),
                                            resultSet.getString(4),
                                            resultSet.getTimestamp(5).toLocalDateTime());

            userId =  resultSet.getLong(2);
            chatId = resultSet.getLong(3);

            preparedStatement = dataSource.getConnection().prepareStatement(selectUserId + userId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            message.setAuthor(new User(userId, resultSet.getString(2), resultSet.getString(3)));

            preparedStatement = dataSource.getConnection().prepareStatement(selectChatRoomId + chatId);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }
            message.setChatroom(new Chatroom(chatId, resultSet.getString(2)));
            resultSet.close();
            preparedStatement.close();
            return Optional.of(message);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Optional.empty();
    }


    @Override
    public void save(Message message) {
        try {

            if (message.getAuthor().getUserId() == 0 || message.getChatroom().getChatId() == 0) {
                throw  new NotSavedSubEntityException();
            }
            preparedStatement = dataSource.getConnection().prepareStatement(selectUserId + message.getAuthor().getUserId());
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next()) {
                throw new NotSavedSubEntityException();
            }
            String text = message.getText();
            if (text != null) {
                text = "\'" + text + "\'";
            }
            String localDateTime;
            if (message.getLocalDateTime() != null) {
                localDateTime = Timestamp.valueOf(message.getLocalDateTime()).toString();
                localDateTime = "\'" + localDateTime + "\'";
            }
            else {
                localDateTime = "null";
            }
            userId = resultSet.getLong(1);
            preparedStatement = dataSource.getConnection().prepareStatement(selectChatRoomId + message.getChatroom().getChatId());
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException();
            }

            chatId = resultSet.getLong(1);
            preparedStatement = dataSource.getConnection().prepareStatement("INSERT INTO chat.message VALUES (" + "default" + ", " + userId +
                    ", " + chatId + ", " +  text + ", " + localDateTime + ") RETURNING messageID");
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new NotSavedSubEntityException();
            }
            message.setMessageId(resultSet.getLong(1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
