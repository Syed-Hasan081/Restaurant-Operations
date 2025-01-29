import java.util.Arrays;

public class Order {
    private MenuItem[] items;
    private int itemCount;
    private static final int MAX_ITEMS = 50;

    public Order() {
        items = new MenuItem[MAX_ITEMS];
        itemCount = 0;
    }

    public void addItem(MenuItem item) {
        if (itemCount < MAX_ITEMS) {
            items[itemCount++] = item;
        } else {
            System.out.println("Cannot add more items to the order.");
        }
    }

    public MenuItem[] getItems() {
        return Arrays.copyOf(items, itemCount);
    }

    public double getTotal() {
        double total = 0.0;
        for (int i = 0; i < itemCount; i++) {
            total += items[i].getPrice();
        }
        return total;
    }
}
