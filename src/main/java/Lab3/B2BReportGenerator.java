package Lab3;

import java.util.List;

public class B2BReportGenerator implements ReportGenerator {
    @Override
    public String generate(Customer customer, List<Component> components, String techReport) {
        StringBuilder sb = new StringBuilder("\n=== КОМЕРЦІЙНА ПРОПОЗИЦІЯ B2B ===\n");
        sb.append("Клієнт (ТОВ/ФОП): ").append(customer.getFullName()).append("\n");

        double total = 0;
        for (Component c : components) {
            sb.append("- ").append(c.getName()).append(" (").append(c.getPrice()).append(" грн)\n");
            total += c.getPrice();
        }

        double pdv = total * 0.20;
        sb.append("Сума без ПДВ: ").append(total - pdv).append(" грн | ПДВ (20%): ").append(pdv).append(" грн\n");
        sb.append("УСЬОГО ДО ОПЛАТИ: ").append(total).append(" грн\n");
        sb.append("Гарантія 36 місяців.\n\n").append(techReport);
        return sb.toString();
    }
}