package kata;

import static kata.Fizzer.fizzer;

public class FizzBuzz {

    public static void main(String[] args) {

        System.out.println("Hello world");

        for (int i = 1; i < 101; i++) {
            String output = fizzer(i);
            System.out.println(i + ": " + output);
        }
    }
}
