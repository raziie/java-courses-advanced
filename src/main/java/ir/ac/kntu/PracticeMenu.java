package ir.ac.kntu;

import java.io.IOException;
import java.util.Scanner;

public class PracticeMenu {

    public enum PracticeOption {
        OPEN_PRACTICE, EDIT_PRACTICE, DELETE_PRACTICE, PRINT_SCOREBOARD, SEE_ANSWERS, SEARCH_ANSWERS, CORRECT_ANSWERS,
        BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) throws IOException {
        printMenu();
        PracticeOption option = scanOption(scanner);
        while(option != PracticeOption.BACK) {
            handleOption(option, scanner);
            printMenu();
            option = scanOption(scanner);
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Open practice");
        System.out.println("2.Edit practice");
        System.out.println("3.Delete practice");
        System.out.println("4.Print scoreboard");
        System.out.println("5.See answers");
        System.out.println("6.Search answers");
        System.out.println("7.correct answers");
        System.out.println("8.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static PracticeOption scanOption(Scanner scanner) {
        PracticeOption[] options = PracticeOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return PracticeOption.UNDEFINED;
    }

    public static void handleOption(PracticeOption option, Scanner scanner) throws IOException {
        switch (option) {
            case OPEN_PRACTICE -> Practice.openPractice(scanner);
            case EDIT_PRACTICE -> PracticeEditMenu.edit(scanner);
            case DELETE_PRACTICE -> Practice.delete();
            case PRINT_SCOREBOARD -> Practice.printScoreboard();
            case SEE_ANSWERS -> Answer.seeAndMarkAnswers(scanner);
            case SEARCH_ANSWERS -> Practice.searchAnswers(scanner);
            case CORRECT_ANSWERS -> Practice.correct(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
