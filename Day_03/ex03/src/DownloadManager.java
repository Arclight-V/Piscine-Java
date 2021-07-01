import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DownloadManager {

    Map<Integer, String> files = new TreeMap<>();

    public DownloadManager() {
        try {
            FileInputStream inputStream = new FileInputStream("src/files_urls.txt");
            Scanner sc = new Scanner(inputStream);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                files.put(Integer.parseInt(line.substring(0, line.indexOf(' '))), line.substring(line.indexOf(' ') + 1, line.length()));
            }

        } catch (IOException e) {

        }
    }

    public static void main(String args[]) {
        DownloadManager downloadManager = new DownloadManager();
    }
}
