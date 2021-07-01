import sun.tools.jstat.Scale;

import java.io.*;
import java.util.*;
import java.lang.String;

import static java.lang.Double.NaN;
import static java.lang.Math.sqrt;

public class Words {

    private Map<String, Integer> dictonary = new TreeMap<>();
    private Map<String, Integer> dictonaryFirstFile = new TreeMap<>();
    private Map<String, Integer> dictonarySecondFile = new TreeMap<>();

    private int[] vectorFirstFile;
    private int[] vectorSecondFile;

    private double similairy;

    public Words(String firstFile, String secondFile) {
        readFromFile(firstFile, dictonaryFirstFile);
        readFromFile(secondFile, dictonarySecondFile);
        try {
            writeToFile();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void readFromFile(String fileToRead, Map<String, Integer> map) {
        String line = new String(), value = new String();

        try {
            FileInputStream inputStream = new FileInputStream(fileToRead);
            Scanner scanner = new Scanner(inputStream);
            String[] split;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                split = line.split("\\s+");
                for (int i = 0; i < split.length; ++i) {
                    if (dictonary.get(split[i]) != null) {
                        int find = dictonary.get(split[i]);
                        dictonary.put(split[i], ++find);
                    } else {
                        dictonary.put(split[i], 1);
                    }
                    if (map.get(split[i]) != null) {
                        int find = map.get(split[i]);
                        map.put(split[i], ++find);
                    } else {
                       map.put(split[i], 1);
                    }
                }
            }
        }
        catch (IOException e) {
            System.out.println("Exception");
        }
    }

    private void fillVector(int[] vectorFile, Map<String, Integer> map) {
        Iterator it = dictonary.entrySet().iterator();

        for (int i = 0, find = 0; i < vectorFile.length; ++i) {
            Map.Entry entry = (Map.Entry) it.next();
            if (map.get(entry.getKey()) != null) {
                find = map.get(entry.getKey());
                vectorFile[i] = find;
            } else {
                vectorFile[i] = 0;
            }
        }
    }


    public void createVector() {
        vectorFirstFile = new int[dictonary.size()];
        vectorSecondFile = new int[dictonary.size()];

        fillVector(vectorFirstFile, dictonaryFirstFile);
        fillVector(vectorSecondFile, dictonarySecondFile);

    }

    public void determinetes() {
        int numerator = 0, denominatorFirst = 0, denominatorSecond = 0;

        for (int i = 0; i < vectorFirstFile.length; ++i) {
            numerator += vectorFirstFile[i] * vectorSecondFile[i];
            denominatorFirst += vectorFirstFile[i] * vectorFirstFile[i];
            denominatorSecond += vectorSecondFile[i] * vectorSecondFile[i];
        }
        similairy = numerator / (sqrt(denominatorFirst) * sqrt(denominatorSecond));
        if (Double.isNaN(similairy)) {
            similairy = 0;
        }
    }

    private void writeToFile() throws FileNotFoundException {
        OutputStream output = new FileOutputStream("dictionary.txt");
        String tmp;
        for(Map.Entry<String, Integer> pair: dictonary.entrySet()) {
            try {
                output.write(pair.getKey().getBytes());
                output.write('\n');
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public double getSimilairy() {
        return similairy;
    }

    public static void main(String args[]) {
        Words words = new Words(args[0], args[1]);
        words.createVector();
        words.determinetes();
        System.out.println("Similarity = " + String.format("%.5f", words.getSimilairy()));
    }
}
