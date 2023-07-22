package ir.ac.kntu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public enum Option {
        ADD_A_USER, LOG_IN, LOG_OUT, GUEST_OPTIONS, EXIT, UNDEFINED
    }

    public static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<Classroom> classes = new ArrayList<>();

    public static ArrayList<Competition> competitions = new ArrayList<>();

    public static Classroom currentClass;

    public static User currentUser;

    public static Practice currentPractice;

    public static Competition currentCompetition;

    public static QuestionBank bank = new QuestionBank();

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Add a user");
        System.out.println("2.Log in");
        System.out.println("3.Log out");
        System.out.println("4.Continue as a guest");
        System.out.println("5.Exit");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static Option scanOption(Scanner scanner) {
        Option[] options = Option.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return Option.UNDEFINED;
    }

    public static void handleOption(Option option, Scanner scanner) throws IOException {
        switch (option) {
            case ADD_A_USER -> User.addUser(scanner);
            case LOG_IN -> User.logIn(scanner);
            case LOG_OUT -> currentUser.logOut();
            case GUEST_OPTIONS -> GuestMenu.handle(scanner);
            default -> System.out.println("invalid choice!");
        }
    }

    public static void displayUsers() {
        for(int i=0; i< users.size(); i++) {
            System.out.println((i+1) + "_" + users.get(i));
        }
    }

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Test.set();
        printMenu();
        Option option = scanOption(scanner);
        while(option != Option.EXIT) {
            handleOption(option, scanner);
            printMenu();
            option = scanOption(scanner);
        }
        scanner.close();
    }
}