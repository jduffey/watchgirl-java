package kata;

public class Checkout {

    private int total;

    public void scan(Item item) {
        total += item.getPrice();
    }

    public int getTotal() {

        return total;
    }
}
