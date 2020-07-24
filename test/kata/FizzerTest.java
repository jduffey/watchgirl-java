package kata;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;

import static kata.Fizzer.fizzer;
import static org.junit.Assert.assertEquals;

public class FizzerTest {

    @Mock
    private String name;

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 4})
    public void givenInputNotDivisibleBy3or5_return1(int i) {
        String actual = fizzer(i);

        String expected = String.valueOf(i);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {3, 6, 9})
    public void givenInputDivisibleBy3AndNot5_returnFizz(int i) {
        String actual = fizzer(i);

        String expected = "Fizz";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {5, 10, 20})
    public void givenInputDivisibleBy5AndNot3_returnBuzz(int i) {
        String actual = fizzer(i);

        String expected = "Buzz";
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @ValueSource(ints = {15, 30, 45})
    public void givenInputDivisibleBy3And5_returnFizzBuzz(int i) {
        String actual = fizzer(i);

        String expected = "FizzBuzz";
        assertEquals(expected, actual);
    }
}
