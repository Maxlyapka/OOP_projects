package Lab1;
//Done написати ще один клас component generator ,убрати сокет та додати тут тип(категорію)
public class Component {
    private String name;
    private Category category;
    private double price;
    private int power;

    public Component(String name, Category category, double price, int power) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.power = power;
    }

    public String getName() { return name; }
    public Category getCategory() { return category; }
    public double getPrice() { return price; }
    public int getPower() { return power; }
}