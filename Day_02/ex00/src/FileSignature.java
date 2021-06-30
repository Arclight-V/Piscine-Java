import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Base64;

public class FileSignature {

    HashMap<String, String> token   = new HashMap<>();
    String buf                      = new String();
    boolean isSignature             = false;
    OutputStream output;

    FileSignature() {
        try {
            FileInputStream input = new FileInputStream("signatures.txt");
            Scanner sc = new Scanner(input);
            String line = new String(), value = new String();
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                value = line.substring(line.indexOf(',') + 1);
                token.put(line.substring(0, line.indexOf(',')), value.replaceAll("\\s", ""));
            }
            output = new FileOutputStream("result.txt");
        }
        catch (IOException e) {
            System.out.println("Exception");
        }
    }

    void checkFormat() {
        System.out.println("PROCESSED");
        for (Map.Entry<String, String> pair : token.entrySet()) {
            if (buf.lastIndexOf(pair.getValue()) == -1) {
                isSignature = false;
            }
            else {
                isSignature = true;
                buf = pair.getKey();
                return;
            }
        }
    }

    void writeToFile() {
        if (isSignature == true) {

            try {
                output.write(buf.getBytes());
                output.write('\n');
            }
            catch (IOException e) {
                System.out.println("Exception");
            }
        }

    }

    boolean readFromFile() {

        Scanner in = new Scanner(System.in);

        String nextLine = in.nextLine();
        try {
            FileInputStream sigfis = new FileInputStream(nextLine);
            byte[] sigToVerify = new byte[8];
            sigfis.read(sigToVerify);
            sigfis.close();
            buf = bytesToHex(sigToVerify);
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private static final char[] HEX_ARRAY = "0123456789ABCDEFabcdef".toCharArray();
    public static String bytesToHex(byte[] bytes) {
        char[] hexChars = new char[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars);
    }

    public static void main(String args[]) {
        FileSignature file =  new FileSignature();

        boolean loop = true;
        while  (loop == true) {
            loop = file.readFromFile();
            file.checkFormat();
            file.writeToFile();
        }

    }
}

