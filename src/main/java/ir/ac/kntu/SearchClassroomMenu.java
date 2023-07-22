package ir.ac.kntu;

import java.util.Scanner;

public class SearchClassroomMenu {

    public enum SearchClassroomOption {
        BY_NAME, BY_INSTITUTION, BY_OWNER, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) {
        printMenu();
        SearchClassroomOption option = scanOption(scanner);
        while (option != SearchClassroomOption.BACK) {
            handleOption(option, scanner);
            printMenu();
            option = scanOption(scanner);
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.by name");
        System.out.println("2.by institution");
        System.out.println("3.by owner's name");
        System.out.println("4.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static SearchClassroomOption scanOption(Scanner scanner) {
        SearchClassroomOption[] options = SearchClassroomOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return SearchClassroomOption.UNDEFINED;
    }

    public static void handleOption(SearchClassroomOption option, Scanner scanner) {
        switch (option) {
            case BY_NAME -> SearchClass.searchAndEnterByName(scanner);
            case BY_INSTITUTION -> SearchClass.searchAndEnterByInstitution(scanner);
            case BY_OWNER -> SearchClass.searchAndEnterByOwner(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
