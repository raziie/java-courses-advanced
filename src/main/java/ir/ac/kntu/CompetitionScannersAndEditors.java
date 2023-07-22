package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.Scanner;

public class CompetitionScannersAndEditors {

    public static Competition.CompetitionType scanType(Scanner scanner) {
        System.out.println("which type? (enter SPECIAL, PRIVATE, or ORDINARY)");
        switch (scanner.nextLine()) {
            case "SPECIAL" -> {
                return Competition.CompetitionType.SPECIAL;
            }
            case "PRIVATE" -> {
                return Competition.CompetitionType.PRIVATE;
            }
            case "ORDINARY" -> {
                return Competition.CompetitionType.ORDINARY;
            }
            default -> {
                break;
            }
        }
        return Competition.CompetitionType.NONE;
    }

    public static String scanName(Scanner scanner) {
        System.out.println("please enter your practice's name:");
        return scanner.nextLine();
    }

    public static LocalDateTime scanStartTime(Scanner scanner) {
        System.out.println("please enter start time:");
        System.out.println("YYYY:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MM:");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("DD:");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.println("HH:");
        int hour = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MM:");
        int minute = scanner.nextInt();
        scanner.nextLine();
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public static LocalDateTime scanEndTime(Scanner scanner) {
        System.out.println("please enter end time:");
        System.out.println("YYYY:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MM:");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("DD:");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.println("HH:");
        int hour = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MM:");
        int minute = scanner.nextInt();
        scanner.nextLine();
        return LocalDateTime.of(year, month, day, hour, minute);
    }

    public static int scanGroupSize(Scanner scanner) {
        System.out.println("please enter size of each group:");
        int input = scanner.nextInt();
        scanner.nextLine();
        return input;
    }

    public static void editName(Scanner scanner) {
        System.out.println("Enter the new name:");
        Main.currentCompetition.setName(scanner.nextLine());
    }

    public static void editStartTime(Scanner scanner) {
        System.out.println("please enter the new start time:");
        System.out.println("YYYY:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MM:");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("DD:");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.println("HH:");
        int hour = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MM:");
        int minute = scanner.nextInt();
        scanner.nextLine();
        Main.currentCompetition.setStartTime(LocalDateTime.of(year, month, day, hour, minute));
    }

    public static void editEndTime(Scanner scanner) {
        System.out.println("please enter the new start time:");
        System.out.println("YYYY:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MM:");
        int month = scanner.nextInt();
        scanner.nextLine();
        System.out.println("DD:");
        int day = scanner.nextInt();
        scanner.nextLine();
        System.out.println("HH:");
        int hour = scanner.nextInt();
        scanner.nextLine();
        System.out.println("MM:");
        int minute = scanner.nextInt();
        scanner.nextLine();
        Main.currentCompetition.setEndTime(LocalDateTime.of(year, month, day, hour, minute));
    }
}
