package ir.ac.kntu;

import java.util.Scanner;

public class CustomerCompetitionMenu {

    public enum CustomerCompetitionOption {
        SEND_ANSWER, PARTICIPATE, OPEN, CLOSE, PRINT_BOARD, SEE_ANSWERS, SEE_FINAL_ANSWERS, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) {
        printMenu();
        CustomerCompetitionOption option = scanOption(scanner);
        while (option != CustomerCompetitionOption.BACK) {
            handleOption(option, scanner);
            printMenu();
            option = scanOption(scanner);
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Send answers");
        System.out.println("2.Participate");
        System.out.println("3.Open competition");
        System.out.println("4.Close competition");
        System.out.println("5.Print board");
        System.out.println("6.See my answers");
        System.out.println("7.See my final answers");
        System.out.println("8.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static CustomerCompetitionOption scanOption(Scanner scanner) {
        CustomerCompetitionOption[] options = CustomerCompetitionOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return CustomerCompetitionOption.UNDEFINED;
    }

    public static void handleOption(CustomerCompetitionOption option, Scanner scanner) {
        switch (option) {
            case SEND_ANSWER -> Main.currentCompetition.sendAnswer(scanner);
            case PARTICIPATE -> Competition.participate(scanner);
            case OPEN -> Competition.open(scanner);
            case CLOSE -> Competition.close(scanner);
            case PRINT_BOARD -> Competition.print();
            case SEE_ANSWERS -> Main.currentCompetition.seeAnswers(scanner);
            case SEE_FINAL_ANSWERS -> Main.currentCompetition.seeFinalAnswers(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
