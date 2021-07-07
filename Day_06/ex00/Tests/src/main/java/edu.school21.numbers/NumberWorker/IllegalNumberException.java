public class IllegalNumberException extends RuntimeException {
    public IllegalNumberException() {
        super("ERROR! Num must be > 1");
    }
}