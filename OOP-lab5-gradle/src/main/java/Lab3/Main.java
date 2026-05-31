package Lab3;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ПІБ замовника: ");
        String name = scanner.nextLine();

        BuildController controller;
        try {
            controller = new BuildController(name);
        } catch (IllegalArgumentException e) {
            System.out.println(" Помилка: " + e.getMessage());
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n1. Додати деталь");
            System.out.println("2. Перевірити збірку");
            System.out.println("0. Вихід");
            System.out.print("Виберіть дію: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1 -> {
                        System.out.print("Назва: ");
                        String n = scanner.nextLine();

                        System.out.print("Категорія (CPU, MOTHERBOARD, PSU, RAM, OTHER): ");
                        String cat = scanner.nextLine();

                        System.out.print("Ціна: ");
                        double pr = scanner.nextDouble();

                        System.out.print("Потужність (W): ");
                        int pwr = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Сокет (натисніть Enter, якщо немає): ");
                        String sock = scanner.nextLine();

                        try {
                            controller.addComponent(n, cat, pr, pwr, sock);
                            System.out.println(" Деталь додано");
                        } catch (IllegalArgumentException e) {
                            System.out.println(" Помилка валідації: " + e.getMessage());
                        }
                    }
                    case 2 -> {
                        System.out.print("Оберіть тип перевірки (1 - Строга, 2 - М'яка): ");
                        int compChoice = scanner.nextInt();
                        CompatibilityChecker compChecker = (compChoice == 1) ? new StrictChecker() : new SoftChecker();

                        System.out.print("Оберіть тип клієнта (1 - B2B Бізнес, 2 - B2C Звичайний): ");
                        int reportChoice = scanner.nextInt();
                        ReportGenerator reportGen = (reportChoice == 1) ? new B2BReportGenerator() : new B2CReportGenerator();

                        String report = controller.generateFinalDocument(compChecker, reportGen);
                        System.out.println(report);
                    }
                    case 0 -> running = false;
                    default -> System.out.println(" Невірний варіант");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Помилка: введіть числове значення");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}