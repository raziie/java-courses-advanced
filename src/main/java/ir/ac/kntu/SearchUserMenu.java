package ir.ac.kntu;

import java.util.Scanner;

public class SearchUserMenu {

    public enum SearchUserOption {
        BY_NATIONAL_ID, BY_EMAIL, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) {
        printMenu();
        SearchUserOption option = scanOption(scanner);
        while (option != SearchUserOption.BACK) {
            handleOption(option, scanner);
            printMenu();
            option = scanOption(scanner);
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.by national ID");
        System.out.println("2.by email");
        System.out.println("3.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static SearchUserOption scanOption(Scanner scanner) {
        SearchUserOption[] options = SearchUserOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return SearchUserOption.UNDEFINED;
    }

    public static void handleOption(SearchUserOption option, Scanner scanner) {
        switch (option) {
            case BY_NATIONAL_ID -> SearchUser.searchById(scanner);
            case BY_EMAIL -> SearchUser.searchByEmail(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
