package ir.ac.kntu;

import java.util.Scanner;

public class AdminCompetitionMenu {

    public enum AdminCompetitionOption {
        CREATE, OPEN, CLOSE, EDIT, DELETE, INVITE_USER, PRINT_BOARD, CORRECT_ANSWERS, GIVE_POINTS, SEARCH_ANSWER, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) {
        printMenu();
        AdminCompetitionOption option = scanOption(scanner);
        while (option != AdminCompetitionOption.BACK) {
            handleOption(option, scanner);
            printMenu();
            option = scanOption(scanner);
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Create");
        System.out.println("2.Open");
        System.out.println("3.Close");
        System.out.println("4.Edit");
        System.out.println("5.Delete");
        System.out.println("6.Invite users");
        System.out.println("7.Print board");
        System.out.println("8.Correct answers");
        System.out.println("9.Give points to superiors");
        System.out.println("10.Search answer by email");
        System.out.println("11.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static AdminCompetitionOption scanOption(Scanner scanner) {
        AdminCompetitionOption[] options = AdminCompetitionOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return AdminCompetitionOption.UNDEFINED;
    }

    public static void handleOption(AdminCompetitionOption option, Scanner scanner) {
        switch (option) {
            case CREATE -> Competition.create(scanner);
            case OPEN -> Competition.open(scanner);
            case CLOSE -> Competition.close(scanner);
            case EDIT -> CompetitionEditMenu.edit(scanner);
            case DELETE -> Competition.delete(scanner);
            case INVITE_USER -> Competition.invite(scanner);
            case PRINT_BOARD -> Competition.print();
            case CORRECT_ANSWERS -> Main.currentCompetition.correct(scanner);
            case GIVE_POINTS -> Main.currentCompetition.givePoints();
            case SEARCH_ANSWER -> Main.currentCompetition.searchAnswer(scanner);
            default -> System.out.println("invalid choice!");
        }
    }

}
