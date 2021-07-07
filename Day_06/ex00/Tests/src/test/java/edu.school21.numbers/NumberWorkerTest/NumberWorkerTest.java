import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 11, 5, 7, 53, 61, 199, 151})
    public void isPrimeForPrimes(int candidate) {
        assertTrue(NumberWorker.isPrime(candidate));
    }

    @ParameterizedTest
    @ValueSource(ints = {4, 8, 12, 169, 39, 81, 100, 102})
    void isPrimeForNotPrimes(int candidate) {
        assertFalse(NumberWorker.isPrime(candidate));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, -0, -11})
    void isPrimeForIncorrectNumbers(int candidate) {
        assertThrows(IllegalNumberException.class,()->{NumberWorker.isPrime(candidate);});
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    void checkDigitsSum(int num, int sum) {
            assertEquals(NumberWorker.digitsSum(num), sum);
    }


}