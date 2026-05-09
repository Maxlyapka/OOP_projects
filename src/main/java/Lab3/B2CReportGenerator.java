package Lab3;

import java.util.List;

public class B2CReportGenerator implements ReportGenerator {
    @Override
    public String generate(Customer customer, List<Component> components, String techReport) {
        StringBuilder sb = new StringBuilder("\n=== ФІСКАЛЬНИЙ ЧЕК (B2C) ===\n");
        sb.append("Покупець: ").append(customer.getFullName()).append("\n");

        double total = 0;
        for (Component c : components) {
            sb.append(c.getCategory()).append(": ").append(c.getName()).append(" -> ").append(c.getPrice()).append(" грн\n");
            total += c.getPrice();
        }

        sb.append("Разом до сплати: ").append(total).append(" грн\n");
        sb.append("Дякуємо за покупку. Гарантія 12 місяців.\n\n").append(techReport);
        return sb.toString();
    }
}