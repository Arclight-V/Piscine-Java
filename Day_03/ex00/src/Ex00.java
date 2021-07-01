
public class Ex00 {

    public static void main(String[] args) {
        String argv0 = args[0];
        int count = 0;

        if (argv0.startsWith("--count=")) {
            try {
                count = Integer.parseInt(argv0.substring(8, argv0.length()));
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

        Egg eggThread = new Egg(count);
        Hen henThread = new Hen(count);

        eggThread.start();
        henThread.start();

        for (int i = 0; i < count; ++i) {
            System.out.println("Human");
        }
    }
}
