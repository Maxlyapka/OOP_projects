package Lab1;

import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть ПІБ замовника: ");
        String name = scanner.nextLine();
        Configuration myPC = new Configuration(new Customer(name));

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

                        System.out.print("Категорія (CPU, Motherboard, PSU, RAM, Other): ");
                        String cat = scanner.nextLine();

                        System.out.print("Ціна: ");
                        double pr = scanner.nextDouble();

                        System.out.print("Потужність (W): ");
                        int pwr = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Сокет: ");
                        String sock = scanner.nextLine();

                        myPC.addComponent(n, cat, pr, pwr, sock);
                        System.out.println("Деталь додано");
                    }
                    case 2 -> {
                        myPC.validateBuild();
                    }
                    case 0 -> {
                        running = false;
                    }
                    default -> System.out.println("Невірний варіант");
                }
            } catch (InputMismatchException e) {
                System.out.println("Помилка: введіть числове значення");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}