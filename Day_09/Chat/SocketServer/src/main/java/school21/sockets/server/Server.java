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

@Component
public class Server {
    @Autowired
    private UsersService usersService;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public Server() {
    }

    public void start(int port) {
        try {
            serverSocket = new ServerSocket(port);
            clientSocket = serverSocket.accept();
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            out.println("Hello from Server!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void stop() {
        try {
            in.close();
            out.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void interactionСlient() {
        String messageFromClient = null;
        try {
            messageFromClient = in.readLine();
            while (true) {
                if (messageFromClient.equals("Sign up")) {
                    out.println("Enter username:");
                    messageFromClient = in.readLine();
                    out.println(messageFromClient);

                } else if (messageFromClient.equals("exit")) {
                    out.println("Successful!");
                    stop();
                }
                else {
                    out.println("unrecognised greeting");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
