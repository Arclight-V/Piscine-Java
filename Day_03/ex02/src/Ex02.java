import java.util.Random;

class RealMultithreading {

    String[] args;
    int arraySize = 0, threadsCount = 0, minNumInArray = 0, maxNumInArray = 1000, sumArrayBase = 0, sumThreads = 0;
    int[] arrayInt;
    NumForThread[] NFT;

    class NumForThread {
        int first = 0, last = 0, sum = 0;


        NumForThread() {
        }

        NumForThread(int first, int last) {
            this.first = first;
            this.last = last;
        }

        public void setFirstLast(int first, int last) {
            this.first = first;
            this.last = last;
        }

        public int getFrist() {
            return first;
        }

        public int getLast() {
            return last;
        }

    }

    public class countSumInArray extends Thread {
        NumForThread NT;
        int sum = 0, numThread = 0;


        countSumInArray(NumForThread NT, int num) {
            this.NT = NT;
            this.numThread = num;

            for (int first = NT.getFrist(), last = NT.getLast(); first < last; ++first) {
                if (first >= arraySize)
                    break;
                sum = sum + arrayInt[first];
            }
            sumThreads += sum;
        }

        @Override
        public void run() {
            synchronized (this) {

                System.out.println("Thread " + numThread + ": from " + (NT.getFrist() == 0 ? NT.getFrist() : NT.getFrist()) + " to " + (NT.getLast() - 1) + " sum is " + sum);
            }
        }

    }

    RealMultithreading(String[] args) {
        this.args = args;
    }

    public void parseArgs() {

        String arraySizeStr = args[0], threadsCountStr = args[1];

        if ((arraySizeStr.startsWith("--arraySize=")) && (threadsCountStr.startsWith("--threadsCount="))) {
            try {
                arraySize = Integer.parseInt(arraySizeStr.substring(12, arraySizeStr.length()));
                threadsCount = Integer.parseInt(threadsCountStr.substring(15, threadsCountStr.length()));
                if ((arraySize < 1) || (threadsCount < 1) || (arraySize < threadsCount)) {
                    System.out.println("ERROR the number is negative or arraySize < threadsCount");
                    System.exit(-1);
                }
            } catch (NumberFormatException e) {
                System.out.println("ERROR no number");
                System.exit(-1);
            }
        } else {
            System.out.println("ARGV 0 must by --count='integer > 0'");
            System.exit(-1);
        }
        NFT = new NumForThread[threadsCount];
    }

    public void createArray() {
        arrayInt = new int[arraySize];
        Random random = new Random();

        for (int i = 0; i < arraySize; ++i) {
            arrayInt[i] = random.nextInt(maxNumInArray - minNumInArray);
            sumArrayBase += arrayInt[i];
        }
        defineBlocksInArray();
    }

    private void defineBlocksInArray() {
        int sum = arraySize % threadsCount;
        int sum1 = arraySize / threadsCount;

        if (sum != 0) {
            int i = 0, k = 0;
            for (; k < threadsCount - 1; i += sum1, ++k) {
                NFT[k] = new NumForThread(i, i + sum1);
            }
            NFT[threadsCount - 1] = new NumForThread(i, arraySize);
        } else {
            for (int i = 0, k = 0; k < threadsCount; i += sum1, ++k) {
                NFT[k] = new NumForThread(i, i + sum1);
            }

        }

    }

    public void printBaseSum() {
        System.out.println("Sum: " + sumArrayBase);
    }

    public void printThreadSum() throws InterruptedException {
        synchronized (this) {
            wait(1);
            System.out.println("Sum: " + sumThreads);
        }
    }

    public void startThreads() {
        for (int i = 0; i < threadsCount; ++i) {
            countSumInArray thr = new countSumInArray(NFT[i], i);
            thr.start();
        }
    }

}

public class Ex02 {

    public static void main(String[] args) throws InterruptedException {
        RealMultithreading RMT = new RealMultithreading(args);

        RMT.parseArgs();
        RMT.createArray();
        RMT.printBaseSum();
        RMT.startThreads();
        RMT.printThreadSum();
    }
}
