import java.io.*;
import java.util.Scanner;

public class MicroShell {
    public static void main(String args[])throws InterruptedException, IOException {
        if (args[0].lastIndexOf("--current-folder=") == -1) {
            System.out.println("argv[0] must be start the --current-folder=");
            System.exit(1);
        }

        ProcessBuilder processBuilder = new ProcessBuilder("/bin/sh", "-c", "ls");
        processBuilder.directory(new File(args[0].substring(17, args[0].length())));
        Process process = processBuilder.start();

        BufferedReader inn = new BufferedReader(new InputStreamReader(process.getInputStream()));

        String results;
        while((results = inn.readLine()) != null) {
            System.out.println(results);
        }

        Scanner in = new Scanner(System.in);
        String command, arg1, arg2;

        boolean loop = true;

        while (loop) {
            command = in.next();
            if (command.equals("ls")) {
                processBuilder.command("bash", "-c", "ls");
                process = processBuilder.start();
                inn = new BufferedReader(new InputStreamReader(process.getInputStream()));
                while((results = inn.readLine()) != null) {
                    System.out.println(results);
                }
            }
            else if (command.equals("mv")) {
                arg1 = in.next();
                arg2 = in.next();
                processBuilder.command("bash", "-c", "mv " + arg1 + " " + arg2);
                process = processBuilder.start();
            }
            else if (command.equals("cd")) {
                arg1 = in.next();
                processBuilder.command("bash", "-c", "cd " + arg1 + " ; pwd" );
                process = processBuilder.start();
                inn = new BufferedReader(new InputStreamReader(process.getInputStream()));
                results = inn.readLine();
                processBuilder.directory(new File(results));
            }
            else if (command.equals("exit")) {
                loop = false;
            } else {
                System.out.println("the command is not supported");
            }
        }
        System.out.println("Program terminated!");
    }
}
