import javax.print.DocFlavor;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Files;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class DownloadManager {

    Map<Integer, String> files = new TreeMap<>();
    int currentFile = 1, countFiles;

    public DownloadManager() {
        try {
            FileInputStream inputStream = new FileInputStream("src/files_urls.txt");
            Scanner sc = new Scanner(inputStream);
            String line;
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                files.put(Integer.parseInt(line.substring(0, line.indexOf(' '))), line.substring(line.indexOf(' ') + 1, line.length()));
            }
            countFiles = files.size();
        } catch (IOException e) {
            System.out.println("Can't open the files_urls.txt");
            System.exit(1);
        }
    }

    public void increaseСounterOfTheCurrentFile() {
        ++currentFile;
    }

    public int getCurrentFile() {
        return currentFile;
    }

    public void setCurrentFile(int currentFile) {
        this.currentFile = currentFile;
    }

    public String getCurrentUrl() {
        return files.get(currentFile);
    }

    public void startThreads(int countThread) {
        for (int i = 1; i <= countThread; ++i) {
            Thr thr = new Thr(i);
            thr.start();
        }
    }

    public int getCountFiles() {
        return countFiles;
    }

    public class Thr extends Thread {
        int numberFile, numberThread;
        URL urlFile;
        URLConnection connection;
        FileOutputStream fileOutputStream;
        FileChannel fileChannel;
        String url;
        ReadableByteChannel readableByteChannel;

        public Thr(int numberThread) {
            this.numberThread = numberThread;
        }

        private URL setUrl(String url) throws MalformedURLException {
            return new URL(url);
        }

        @Override
        public void run() {
            synchronized (this) {
                while ((numberFile = getCurrentFile()) <= getCountFiles()) {
                    try {
                        increaseСounterOfTheCurrentFile();
                        url = getCurrentUrl();
                        urlFile = setUrl(url);
                        System.out.println("Thread-" + numberThread + " start download file number " + numberFile);
                        readableByteChannel = Channels.newChannel(urlFile.openStream());
                        fileOutputStream = new FileOutputStream("files/" + url.substring(url.lastIndexOf('/') + 1, url.length()));
                        fileChannel = fileOutputStream.getChannel();
                        fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
                        System.out.println("Thread-" + numberThread + " finish download file number " + numberFile);
                    } catch (IOException e) {
                        System.out.println("Thread-" + numberThread + " don't start download file number " + numberFile + " connection failed");
                    }
                }
            }
        }
}


public static void printError(String error) {
    System.err.println(error);
    System.exit(1);
}


    public static void main(String args[]) {

        if (args[0] == null || (args[0].startsWith("--threadsCount=") == false)) {
            printError("args must be --threadsCount=");
        }
        int countThreads = 0;
        try {
            countThreads = Integer.parseInt(args[0].substring(15, args[0].length()));
        } catch (NumberFormatException e) {
            printError("threadsCount must be number");
        }
        if (countThreads == 0 || countThreads < 0) {
            printError("threadsCount must be > 0");
        }

        DownloadManager downloadManager = new DownloadManager();
        downloadManager.startThreads(countThreads >= downloadManager.getCountFiles() ? downloadManager.getCountFiles() : countThreads);

    }
}
