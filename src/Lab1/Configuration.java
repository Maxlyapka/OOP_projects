package Lab1;

import java.util.ArrayList;
import java.util.List;

public class Configuration {
    private Customer customer;
    private List<Component> components = new ArrayList<>();

    public Configuration(Customer customer) {
        this.customer = customer;
    }

    public void addComponent(String name, String category, double price, int power, String socket) {
        Component newComponent = new Component(name, category, price, power, socket);
        components.add(newComponent);
    }

    public void validateBuild() {
        String cpuSocket = "";
        String mbSocket = "";
        int totalConsumption = 0;
        int psuPower = 0;
        boolean hasPSU = false;
        double totalPrice = 0;

        System.out.println("\n--- Збірка клієнта: " + customer.getFullName() + " ---");

        for (Component c : components) {
            String socketInfo = c.getSocket().isEmpty() ? "Немає сокету" : "Сокет: " + c.getSocket();

            System.out.println("- " + c.getCategory() + ": " + c.getName() +
                    " | Ціна: " + c.getPrice() + " грн" +
                    " | Потужність: " + c.getPower() + "W" +
                    " | " + socketInfo);

            totalPrice += c.getPrice();

            if (c.getCategory().equalsIgnoreCase("CPU")) cpuSocket = c.getSocket();
            if (c.getCategory().equalsIgnoreCase("Motherboard")) mbSocket = c.getSocket();

            if (c.getCategory().equalsIgnoreCase("PSU")) {
                psuPower = c.getPower();
                hasPSU = true;
            } else {
                totalConsumption += c.getPower();
            }
        }

        System.out.println("------------------------------------------------");
        System.out.println("Загальна вартість збірки: " + totalPrice + " грн");
        System.out.println("------------------------------------------------");

        System.out.println("Технічний висновок:");

        if (!cpuSocket.isEmpty() && !mbSocket.isEmpty()) {
            if (cpuSocket.equalsIgnoreCase(mbSocket)) {
                System.out.println("Сокети сумісні (" + cpuSocket + ")");
            } else {
                System.out.println("Помилка сумісності: Процесор (" + cpuSocket + ") не підходить до плати (" + mbSocket + ")");
            }
        }

        if (!hasPSU) {
            System.out.println("Блок живлення не знайдено в збірці");
        } else if (psuPower >= totalConsumption) {
            System.out.println("Блоку живлення вистачає (" + psuPower + "W > " + totalConsumption + "W)");
        } else {
            System.out.println("Блоку живлення замало (Треба мінімум " + totalConsumption + "W, а є " + psuPower + "W)");
        }
    }
}