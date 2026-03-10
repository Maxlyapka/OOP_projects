package Lab1;

import java.util.ArrayList;
import java.util.List;
//Done  розділити логіки виводу від логіку самої аплікації
//Done перейменувати на build , відділити створення компонентів
//Done написати конструктор щоб зміг додавати компоненти
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
    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("\n--- Збірка клієнта: ").append(customer.getFullName()).append(" ---\n");

        String cpuSocket = "";
        String mbSocket = "";
        int totalConsumption = 0;
        int psuPower = 0;
        boolean hasPSU = false;
        double totalPrice = 0;

        for (Component c : components) {
            totalPrice += c.getPrice();
            String socketInfo = " | Немає сокету";

            if (c instanceof Processor) {
            Processor p = (Processor) c;
            cpuSocket = p.getSocket();
            socketInfo = " | Сокет: " + p.getSocket();
            } else if (c instanceof Motherboard){
                Motherboard m = (Motherboard) c;
                mbSocket = m.getSocket();
                socketInfo = " | Сокет: " + m.getSocket();
            }
            report.append("_ ").append(c.getCategory()).append(": ").append(c.getName())
                    .append(" | Ціна: ").append(c.getPrice()).append(" грн")
                    .append(" | Потужність: ").append(c.getPower()).append("W")
                    .append(socketInfo).append("\n");
            if (c.getCategory() == Category.PSU){
                hasPSU = true;
                psuPower += c.getPower();
            } else {
                totalConsumption += c.getPower();
            }
        }

        report.append("------------------------------------------------\n");
        report.append("Загальна вартість збірки: ").append(totalPrice).append(" грн\n");
        report.append("------------------------------------------------\n");
        report.append("Технічний висновок:\n");

        if (!cpuSocket.isEmpty() && !mbSocket.isEmpty()) {
            if (cpuSocket.equalsIgnoreCase(mbSocket)) {
                report.append(" Сокети сумісні: ").append(cpuSocket).append("\n");
            }else {
                report.append(" Помилка: Процесор: (").append(cpuSocket).append(") не підходить до плати(")
                        .append(mbSocket).append(")\n");
            }
        }
        if (!hasPSU) {
            report.append("Блок живлення не знайдено в збірці\n");
        } else if (psuPower >= totalConsumption) {
            report.append("Блоку живлення вистачає (").append(psuPower).append("W >").append(totalConsumption)
                    .append("W)\n") ;
        } else {
            report.append("Блоку живлення замало (Треба мінімум ").append(totalConsumption).append("W)\n");
        }
        return report.toString();
    }
}