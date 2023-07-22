package ir.ac.kntu;

import java.util.Scanner;

public class CompetitionEditMenu {

    public enum CompetitionEditOption {
        EDIT_NAME, EDIT_START_TIME, EDIT_END_TIME, BACK, UNDEFINED
    }

    public static void edit(Scanner scanner) {
        if (Main.currentCompetition == null) {
            System.out.println("please open a competition first");
        } else {
            printMenu();
            CompetitionEditOption option = scanOption(scanner);
            while (option != CompetitionEditOption.BACK) {
                handleOption(option, scanner);
                printMenu();
                option = scanOption(scanner);
            }

        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Edit name");
        System.out.println("2.Edit start time");
        System.out.println("3.Edit end time");
        System.out.println("4.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");
    }

    public static CompetitionEditOption scanOption(Scanner scanner) {
        CompetitionEditOption[] options = CompetitionEditOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return CompetitionEditOption.UNDEFINED;
    }

    public static void handleOption(CompetitionEditOption option, Scanner scanner) {
        switch (option) {
            case EDIT_NAME -> CompetitionScannersAndEditors.editName(scanner);
            case EDIT_START_TIME -> CompetitionScannersAndEditors.editStartTime(scanner);
            case EDIT_END_TIME -> CompetitionScannersAndEditors.editEndTime(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
