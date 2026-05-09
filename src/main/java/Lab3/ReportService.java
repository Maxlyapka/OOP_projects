package Lab3;

public class ReportService {
    public String createDocument(Build build, CompatibilityChecker compChecker, ReportGenerator reportGen) {
        String techReport = compChecker.check(build.getComponents());
        return reportGen.generate(build.getCustomer(), build.getComponents(), techReport);
    }
}