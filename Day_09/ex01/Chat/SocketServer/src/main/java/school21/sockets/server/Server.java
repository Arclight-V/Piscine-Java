package school21.sockets.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import school21.sockets.services.UsersService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

@Component
public class Server {
    private ServerSocket serverSocket;
    protected static List<EchoClientHandler> echoClientHandlers = new LinkedList<>();
    public Server() {
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                echoClientHandlers.add(new EchoClientHandler(serverSocket.accept()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class EchoClientHandler extends Thread {
        @Autowired
        private UsersService usersService;
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private String login;

        public EchoClientHandler(Socket socket) {
            this.clientSocket = socket;
            start();
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                out.println("Hello from Server!");
                String messageFromClient;
                while ((messageFromClient = in.readLine()) != null) {
                    if (messageFromClient.equals("exit")) {
                        out.println("You have left the chat.");
                        break;
                    } else if (messageFromClient.equals("signUp")) {
                        signUp();
                        messaging();
                    } else if (messageFromClient.equals("signIn")) {
                        signIn();
                        messaging();
                    } else {
                        out.println("unrecognised command");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void signIn() {
            String password;

            try {
                out.println("Enter username:");
                login = in.readLine();
                out.println("Enter password:");
                password = in.readLine();
                if (login.isEmpty() || password.isEmpty()) {
                    out.println("Login and password not be empty:");
                } else {
                    usersService.signIn(login, password);
                }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }

        private void messaging() {
            out.println("Start messaging");
            String massageClient;

            try {
                while(true) {

                    if (in.ready()) {
                        massageClient = in.readLine();
                        if (massageClient.equals("exit")) {
                            break;
                        }
                        for (EchoClientHandler echoClientHandler : echoClientHandlers) {
                            echoClientHandler.out.println(login + " :" + massageClient);
                            Thread.sleep(1000);
                        }
                    }
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void signUp() {
            String password;

            try {
                    out.println("Enter username:");
                    login = in.readLine();
                    out.println("Enter password:");
                    password = in.readLine();
                    if (login.isEmpty() || password.isEmpty()) {
                        out.println("Login and password not be empty:");
                    } else {
                        usersService.signUp(login, password);
                    }
            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }


    }



}
