package edu.school21.numbers.NumberWorker;

public class IllegalNumberException extends RuntimeException {
    public IllegalNumberException() {
        super("ERROR! Num must be > 1");
    }
}