package Lab3;

import java.util.ArrayList;
import java.util.List;

public class Build {
    private Customer customer;
    private List<Component> components = new ArrayList<>();

    public Build(Customer customer) {
        this.customer = customer;
    }

    public void addComponent(String name, String categoryStr, double price, int power, String socket) {
        Component newComponent = ComponentGenerator.create(name, categoryStr, price, power, socket);
        components.add(newComponent);
    }

    public List<Component> getComponents() { return components; }
    public Customer getCustomer() { return customer; }
}