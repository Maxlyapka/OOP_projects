package Lab1;

public class Component {
    private String name;
    private String category;
    private double price;
    private int power;
    private String socket;

    public Component(String name, String category, double price, int power, String socket) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.power = power;
        this.socket = socket;
    }

    public String getName() { return name; }
    public String getCategory() { return category; }
    public double getPrice() { return price; }
    public int getPower() { return power; }
    public String getSocket() { return socket; }
}