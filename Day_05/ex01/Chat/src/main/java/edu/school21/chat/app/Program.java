package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.DataSource;
import edu.school21.chat.repositories.MessagesRepository;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Optional;
import java.util.Scanner;


public class Program {

    private static final String schema = "./src/main/resources/schema.sql";
    private static final String data = "./src/main/resources/data.sql";

    private MessagesRepository messagesRepository;
    private Connection dbconnection;

    public Program () {
        try {
            DataSource dataSource = new DataSource();
            messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
            dbconnection = dataSource.getConnection();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void executeUpdate(Connection connection, String file) {
        try {
            FileInputStream inputStream = new FileInputStream(file);
            Scanner sc = new Scanner(inputStream).useDelimiter(";");
            Statement statement = connection.createStatement();
            while (sc.hasNext()) {
                statement.executeUpdate(sc.next().trim());
            }
            statement.close();
        } catch (IOException | SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void runFindMessage() {
        Scanner scanner = new Scanner(System.in);
        String command = "";
        Optional<Message> message;
        while (true) {
            System.out.print("Enter a message ID\n -> ");
            if (scanner.hasNextLong()) {
                message = messagesRepository.findById(scanner.nextLong());
                try {
                    System.out.println(message.get().toString());
                } catch (Exception e) {
                    System.err.println("null optional returned");
                }
            }
            else if (scanner.next().equals("stop")) {
                break;
            } else {
                System.out.println("ID must by number");
            }
        }
    }

    public Connection getDbconnection() {
        return dbconnection;
    }

    public static void main(String args[]) {
        Program chat = new Program();
        chat.executeUpdate(chat.getDbconnection(), schema);
        chat.executeUpdate(chat.getDbconnection(), data);
        chat.runFindMessage();


    }
}
