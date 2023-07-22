package ir.ac.kntu;

import java.util.Scanner;

public class ClassroomMenu {

    public enum ClassOption {
        EDIT_CLASS, DELETE_CLASS,ENTER_CLASS, GET_OUT_OF_CLASS, CREATE_CLASS, BACK, UNDEFINED
    }

    public static void handle(Scanner scanner) {
        printMenu();
        ClassOption option = scanOption(scanner);
        while(option != ClassOption.BACK) {
            handleOption(option, scanner);
            printMenu();
            option = scanOption(scanner);
        }
    }

    public static void printMenu() {
        System.out.println("──────────────────────────────────");
        System.out.println("1.Edit class");
        System.out.println("2.Delete class");
        System.out.println("3.Enter class");
        System.out.println("4.Get out of class");
        System.out.println("5.Create class");
        System.out.println("6.Back");
        System.out.println("──────────────────────────────────");
        System.out.println("Please select your choice:");

    }

    public static ClassOption scanOption(Scanner scanner) {
        ClassOption[] options = ClassOption.values();
        int userInput = scanner.nextInt();
        scanner.nextLine();
        userInput--;
        if(userInput>=0 && userInput<=options.length) {
            return options[userInput];
        }
        return ClassOption.UNDEFINED;
    }

    public static void handleOption(ClassOption option, Scanner scanner) {
        switch (option) {
            case EDIT_CLASS -> ClassEditMenu.edit(scanner);
            case DELETE_CLASS -> Classroom.delete();
            case ENTER_CLASS -> User.enterClass(scanner);
            case GET_OUT_OF_CLASS -> Classroom.getOut();
            case CREATE_CLASS -> Classroom.createClass(scanner);
            default -> System.out.println("invalid choice!");
        }
    }
}
