package kata;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CheckoutTest {

    @InjectMocks
    Checkout underTest;
    @Mock
    private Item item;

    @BeforeEach()
    public void setup() {
        underTest = new Checkout();
    }

    @Test
    public void scan_addsItemPriceToTotal() {
        Item item= new Item("Cheese", 3);

        underTest.scan(item);

        int actual = underTest.getTotal();

        int expected = 3;
        assertEquals(expected, actual);
    }

    @Test
    public void mockFoo() {
        when(item.getPrice()).thenReturn(3);

        underTest.scan(item);

        int actual = underTest.getTotal();

        int expected = 3;
        assertEquals(expected, actual);
    }
}
