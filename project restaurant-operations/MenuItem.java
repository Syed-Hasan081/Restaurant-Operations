public class MenuItem {
    private String code;
    private String name;
    private double price;

    // Constructor with parameters
    public MenuItem(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    // Getter for code
    public String getCode() {
        return code;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Getter for price
    public double getPrice() {
        return price;
    }
}