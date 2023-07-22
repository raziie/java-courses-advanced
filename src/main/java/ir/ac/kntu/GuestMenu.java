package ir.ac.kntu;

import java.util.Scanner;

public class GuestMenu {

    public enum GuestOption {
        OPEN_QUESTION_BANK, OPEN_COMPETITION, CLOSE_COMPETITION, PRINT_BOARD, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) {
        if(Main.currentUser == null) {
            Main.currentUser = new Guest();
            printMenu();
            GuestOption option = scanOption(scanner);
            while (option != GuestOption.BACK) {
                handleOption(option, scanner);
                printMenu();
                option = scanOption(scanner);
            }
            Main.currentUser.logOut();
        }else {
            System.out.println("you are not a guest");
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Open question bank");
        System.out.println("2.Open competition");
        System.out.println("3.Close competition");
        System.out.println("4.Print board");
        System.out.println("5.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static GuestOption scanOption(Scanner scanner) {
        GuestOption[] options = GuestOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return GuestOption.UNDEFINED;
    }

    public static void handleOption(GuestOption option, Scanner scanner) {
        switch (option) {
            case OPEN_QUESTION_BANK -> Main.bank.seeQuestions(scanner);
            case OPEN_COMPETITION -> Competition.open(scanner);
            case CLOSE_COMPETITION -> Competition.close(scanner);
            case PRINT_BOARD -> Competition.print();
            default -> System.out.println("invalid choice!");
        }
    }
}
