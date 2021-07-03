package edu.school21.printer.app;
import com.beust.jcommander.ParameterException;
import edu.school21.printer.logic.Args;

import java.io.IOException;
import com.beust.jcommander.JCommander;

public class Main {


    public static void main(String[] args) throws IOException {

        if (args.length != 2) {
            System.err.println("invalid arguments");
            System.exit(1);
        }


        Args arguments = new Args();

        try {
             JCommander.newBuilder()
                        .addObject(arguments)
                        .build()
                        .parse(args);
            arguments.run();
        } catch (IOException e) {
            System.err.println("Exception");
        } catch (IllegalArgumentException e) {
            System.err.println("invalid color");
        } catch (ParameterException e) {
            System.err.println("Parametrs must be --white=COLOUR --black=COLOUR");
        }
    }
}