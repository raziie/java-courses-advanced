package ir.ac.kntu;

import java.util.Scanner;

public class AdminMenu {

    public enum AdminOption {
        QUESTION_BANK, DELETE_BANK_QUESTIONS, SEE_ALL_USERS, PROMOTE_TO_ADMIN, COMPETITION, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) {
        if(Main.currentUser.getType() == User.UserType.ADMIN) {
            printMenu();
            AdminOption option = scanOption(scanner);
            while (option != AdminOption.BACK) {
                handleOption(option, scanner);
                printMenu();
                option = scanOption(scanner);
            }
        }else {
            System.out.println("you are not an admin");
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Question bank");
        System.out.println("2.Delete a question from question bank");
        System.out.println("3.See all users");
        System.out.println("4.Promote to admin");
        System.out.println("5.Competition");
        System.out.println("6.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static AdminOption scanOption(Scanner scanner) {
        AdminOption[] options = AdminOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return AdminOption.UNDEFINED;
    }

    public static void handleOption(AdminOption option, Scanner scanner) {
        switch (option) {
            case QUESTION_BANK -> Main.bank.seeQuestions(scanner);
            case DELETE_BANK_QUESTIONS -> Main.bank.deleteQuestion(scanner);
            case SEE_ALL_USERS -> Main.displayUsers();
            case PROMOTE_TO_ADMIN -> Admin.promoteToAdmin(scanner);
            case COMPETITION -> AdminCompetitionMenu.handle(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
