package Lab1;

public class ComponentGenerator {
    public static Component create(String name, String categoryStr, double price, int power, String socket) {
        Category cat = Category.valueOf(categoryStr.toUpperCase());

        if (cat == Category.CPU) {
            return new Processor(name, price, power, socket);
        } else if (cat == Category.MOTHERBOARD) {
            return new Motherboard(name, price, power, socket);
        } else {
            return new Component(name, cat, price, power);
        }
    }
}