package ir.ac.kntu;

import java.io.IOException;
import java.util.Scanner;

public class UserMenu {

    public enum UserOption {
        ADMIN_OPTIONS, CUSTOMER_OPTIONS, SEARCH_USER, SEARCH_CLASSROOM, SEARCH_COMPETITION, CLASSROOM_OPTIONS, PRACTICE_OPTIONS, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) throws IOException {
        printMenu();
        UserOption option = scanOption(scanner);
        while(option != UserOption.BACK) {
            handleOption(option, scanner);
            printMenu();
            option = scanOption(scanner);
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Admin options");
        System.out.println("2.Customer options");
        System.out.println("3.Search user");
        System.out.println("4.Search class");
        System.out.println("5.Search competition");
        System.out.println("6.Classroom options");
        System.out.println("7.Practice options");
        System.out.println("8.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static UserOption scanOption(Scanner scanner) {
        UserOption[] options = UserOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return UserOption.UNDEFINED;
    }

    public static void handleOption(UserOption option, Scanner scanner) throws IOException {
        switch (option) {
            case ADMIN_OPTIONS -> AdminMenu.handle(scanner);
            case CUSTOMER_OPTIONS -> CustomerMenu.handle(scanner);
            case SEARCH_USER -> SearchUserMenu.handle(scanner);
            case SEARCH_CLASSROOM -> SearchClassroomMenu.handle(scanner);
            case SEARCH_COMPETITION -> Competition.search(scanner);
            case CLASSROOM_OPTIONS -> ClassroomMenu.handle(scanner);
            case PRACTICE_OPTIONS -> PracticeMenu.handle(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
