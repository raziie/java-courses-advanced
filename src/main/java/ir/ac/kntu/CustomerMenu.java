package ir.ac.kntu;

import java.util.Scanner;

public class CustomerMenu {

    public enum CustomerOption {
        GO_TO_QUESTION_BANK, SEE_MY_ANSWERS, SEE_MY_FINAL_ANSWERS, ADD_ANSWER, COMPETITIONS, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) {
        if(Main.currentUser.getType() == User.UserType.CUSTOMER) {
            printMenu();
            CustomerOption option = scanOption(scanner);
            while (option != CustomerOption.BACK) {
                handleOption(option, scanner);
                printMenu();
                option = scanOption(scanner);
            }
        }else {
            System.out.println("you are not a costumer");
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Question bank");
        System.out.println("2.See my answers");
        System.out.println("3.See my final answers");
        System.out.println("4.send answer");
        System.out.println("5.Competitions");
        System.out.println("6.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static CustomerOption scanOption(Scanner scanner) {
        CustomerOption[] options = CustomerOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return CustomerOption.UNDEFINED;
    }

    public static void handleOption(CustomerOption option, Scanner scanner) {
        switch (option) {
            case GO_TO_QUESTION_BANK -> Main.bank.answerQuestion(scanner);
            case SEE_MY_ANSWERS -> Answer.seeAnswers();
            case SEE_MY_FINAL_ANSWERS -> Answer.seeFinalAnswers();
            case ADD_ANSWER -> Practice.addAnswer(scanner);
            case COMPETITIONS -> CustomerCompetitionMenu.handle(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
