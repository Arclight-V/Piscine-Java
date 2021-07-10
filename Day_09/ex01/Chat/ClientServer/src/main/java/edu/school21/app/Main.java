package edu.school21.app;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    public static class ClientServer {
        private Socket clientSocket;
        private PrintWriter out;
        private BufferedReader in;
        private BufferedReader inputClient;

        public void startConnection(String ip, int port) {
            try {
                clientSocket = new Socket(ip, port);
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                inputClient = new BufferedReader(new InputStreamReader(System.in));
                new ReadFromSocket().start();
                new WriteInSocket().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        private class ReadFromSocket extends Thread {
            @Override
            public void run() {
                String str;
                try {
                    while ((str = in.readLine()) != null ){
                        System.out.println(str);
                    }
                } catch (IOException e) {
                    stopConnection();
                }
            }
        }

        private class WriteInSocket extends Thread {
            @Override
            public void run() {
                String str;
                try {
                    while ((str = inputClient.readLine()) != null ){
                        out.println(str);
                    }
                } catch (IOException e) {
                    stopConnection();
                }
            }
        }

        public void work() {
            String messageFromServer, msgClient;
            while (true) {
                try {
                    messageFromServer = in.readLine();
                    System.out.println(messageFromServer);
                    if (messageFromServer.equals("Successful!")) {
                        break;
                    }
                    msgClient = inputClient.readLine();
                    if (msgClient.equals("Exit")) {
                        out.println(msgClient);
                        break;
                    }
                    out.println(msgClient);

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        }

        public void stopConnection() {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }



    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("ERROR! Expected --server-port='port'");
            System.exit(1);
        }
        if (args[0].startsWith("--server-port=") == false) {
            System.err.println("ERROR! Expected --server-port='port'");
            System.exit(1);
        }
        int port = 0;
        try {
            port = Integer.parseInt(args[0].substring(14, args[0].length()));
        } catch (NumberFormatException e) {
            System.err.println("port must be number");
            System.exit(1);
        }

        ClientServer clientServer = new ClientServer();
        clientServer.startConnection("localhost", port);


    }
}
