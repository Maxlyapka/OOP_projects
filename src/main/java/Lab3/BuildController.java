package Lab3;

public class BuildController {
    private Build currentBuild;
    private ReportService reportService;

    public BuildController(String customerName) {
        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException("Ім'я замовника не може бути порожнім");
        }

        Customer customer = new Customer(customerName);
        this.currentBuild = new Build(customer);
        this.reportService = new ReportService();
    }

    public void addComponent(String name, String categoryStr, double price, int power, String socket) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва деталі не може бути порожньою.");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Ціна не може бути від'ємною.");
        }
        if (power < 0) {
            throw new IllegalArgumentException("Потужність не може бути меншою за 0.");
        }

        try {
            Category.valueOf(categoryStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Невідома категорія. Використовуйте: CPU, MOTHERBOARD, PSU, RAM, OTHER.");
        }

        currentBuild.addComponent(name, categoryStr, price, power, socket);
    }

    public String generateFinalDocument(CompatibilityChecker compChecker, ReportGenerator reportGen) {
        return reportService.createDocument(currentBuild, compChecker, reportGen);
    }
}