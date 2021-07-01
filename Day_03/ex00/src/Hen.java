public class Hen extends Thread {
    int countIteration;

    Hen(int i) {
        countIteration = i;
    }

    @Override
    public void run() {
        for (int i = 0; i < countIteration; ++i) {
            System.out.println("Hen");
        }
    }
}
