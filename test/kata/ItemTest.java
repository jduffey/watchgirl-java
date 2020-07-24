package kata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {

    @Test
    public void getName_returnsName() {
        Item underTest = new Item("Cheese", 3);

        String actual = underTest.getName();

        String expected = "Cheese";
        assertEquals(expected, actual);
    }

    @Test
    public void getPrice_returnsPrice() {
        Item underTest = new Item("Cheese", 3);

        int actual = underTest.getPrice();

        int expected = 3;
        assertEquals(expected, actual);
    }
}
