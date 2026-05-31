package Lab3;

import java.util.List;

public class StrictChecker implements CompatibilityChecker {
    @Override
    public String check(List<Component> components) {
        String cpuSocket = "";
        String mbSocket = "";
        int totalConsumption = 0;
        int psuPower = 0;
        boolean hasPSU = false;

        for (Component c : components) {
            if (c instanceof Processor) cpuSocket = ((Processor) c).getSocket();
            if (c instanceof Motherboard) mbSocket = ((Motherboard) c).getSocket();

            if (c.getCategory() == Category.PSU) {
                hasPSU = true;
                psuPower += c.getPower();
            } else {
                totalConsumption += c.getPower();
            }
        }

        StringBuilder result = new StringBuilder("--- Технічний висновок (СТРОГА ПЕРЕВІРКА) ---\n");
        if (!cpuSocket.isEmpty() && !mbSocket.isEmpty()) {
            if (cpuSocket.equalsIgnoreCase(mbSocket)) {
                result.append(" Сокети сумісні (").append(cpuSocket).append(")\n");
            } else {
                result.append(" ПОМИЛКА: Процесор (").append(cpuSocket).append(") не підходить до плати (").append(mbSocket).append(")\n");
            }
        }

        if (!hasPSU) {
            result.append(" ПОМИЛКА: Відсутній блок живлення.\n");
        } else if (psuPower >= totalConsumption) {
            result.append(" БЖ вистачає (").append(psuPower).append("W > ").append(totalConsumption).append("W)\n");
        } else {
            result.append(" ПОМИЛКА: Нестача живлення. (Потрібно: ").append(totalConsumption).append("W)\n");
        }

        return result.toString();
    }
}