package kata;

public class Fizzer {

    public static String fizzer(int input) {
        if (isDivBy(input, 15)) {
            return "FizzBuzz";
        } else if (isDivBy(input, 3)) {
            return "Fizz";
        } else if (isDivBy(input, 5)) {
            return "Buzz";
        } else {
            return String.valueOf(input);
        }
    }

    private static boolean isDivBy(int input, int divisor) {
        return input % divisor == 0;
    }
}
