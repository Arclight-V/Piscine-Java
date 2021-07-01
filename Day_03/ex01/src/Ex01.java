public class Ex01 {

    public static void main(String[] args) throws InterruptedException {

        int count = 0;

        if (args.length != 0 && args[0].startsWith("--count=")) {
            try {
                count = Integer.parseInt(args[0].substring(8, args[0].length()));
                if (count < 1) {
                    System.out.println("ERROR the number is negative");
                    System.exit(-1);
                }
            }
            catch (NumberFormatException e) {
                System.out.println("ERROR no number");
            }
        }
        else {
            System.out.println("ARGV 0 must by --count='integer > 0'" );
            System.exit(-1);
        }


        Producer pc = new Producer(count);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.egg();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    pc.hen();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();
    }

    public static class Producer {

        int count = 0 ,countPrintMessageEgg = 0, countPrintMessageHen;

        Producer(int countPrintMessage) {
            countPrintMessageEgg = countPrintMessage;
            countPrintMessageHen = countPrintMessage;
        }

        public void egg() throws InterruptedException {
            while (countPrintMessageEgg > 0) {
                synchronized (this) {
                    if (count == 1)  {
                        wait();
                    }
                    System.out.println("Egg");
                    count = 1;
                    notify();
                    --countPrintMessageEgg;
                }
            }
        }

        public void hen() throws InterruptedException {
            while (countPrintMessageHen > 0) {
                synchronized (this) {
                    if (count == 0) {
                        wait();
                    }
                    System.out.println("Hen");
                    count = 0;
                    notify();
                    --countPrintMessageHen;
                }
            }
        }
    }
}
