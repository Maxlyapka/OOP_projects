package Lab3;

import java.util.List;

public interface ReportGenerator {
    String generate(Customer customer, List<Component> components, String techReport);
}