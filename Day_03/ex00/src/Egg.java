public class Egg extends Thread {
    int countIteration;

    Egg(int i) {
        countIteration = i;
    }

    @Override
    public void run() {
        for (int i = 0; i < countIteration; ++i) {
            System.out.println("Egg");
        }
    }
}
