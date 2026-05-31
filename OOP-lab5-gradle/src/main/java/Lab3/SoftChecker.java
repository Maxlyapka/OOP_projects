package Lab3;

import java.util.List;

public class SoftChecker implements CompatibilityChecker {
    @Override
    public String check(List<Component> components) {
        int totalConsumption = 0;
        int psuPower = 0;

        for (Component c : components) {
            if (c.getCategory() == Category.PSU) psuPower += c.getPower();
            else totalConsumption += c.getPower();
        }

        StringBuilder result = new StringBuilder("--- Технічний висновок (М'яка перевірка) ---\n");
        result.append(" Сумісність процесора та материнської плати (сокети) не перевірялась.\n");
        if (psuPower >= totalConsumption) result.append(" Потужності блоку живлення достатньо для цієї конфігурації.\n");
        else result.append(" ПОПЕРЕДЖЕННЯ: Можливі просадки напруги, бажано замінити БЖ.\n");

        return result.toString();
    }
}