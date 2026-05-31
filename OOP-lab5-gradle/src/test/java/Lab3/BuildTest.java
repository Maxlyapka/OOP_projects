package Lab3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BuildTest {

    @Test
    void testPriceCalculationWithVAT() {
        Build build = new Build(new Customer("Corp"));
        build.addComponent("SSD 1TB", "OTHER", 3000, 5, "");

        B2BReportGenerator generator = new B2BReportGenerator();
        String report = generator.generate(build.getCustomer(), build.getComponents(), "OK");

        assertTrue(report.contains("ПДВ (20%): 600.0"));
    }

    @Test
    void testValidationNegativePrice() {
        BuildController controller = new BuildController("Max");
        assertThrows(IllegalArgumentException.class, () -> {
            controller.addComponent("GPU", "OTHER", -1000, 200, "");
        });
    }

    @Test
    void testStrictCompatibilityFail() {
        Build build = new Build(new Customer("Test User"));
        build.addComponent("Intel i6", "CPU", 15000, 125, "LGA1700");
        build.addComponent("AMD 550", "MOTHERBOARD", 4000, 50, "AM4");

        StrictChecker checker = new StrictChecker();
        String result = checker.check(build.getComponents());

        assertTrue(result.contains("ПОМИЛКА"));
    }

    @Test
    void testSoftCheckerPowerWarning() {
        Build build = new Build(new Customer("Test User"));
        build.addComponent("RTX 4090", "OTHER", 70000, 450, "");
        build.addComponent("БЖ 400W", "PSU", 1500, 400, "");

        SoftChecker checker = new SoftChecker();
        String result = checker.check(build.getComponents());

        System.out.println("Текст від софт чекера: " + result);

        assertTrue(result.contains("ПОПЕРЕДЖЕННЯ"));
    }
}