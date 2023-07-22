package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchUser {

    public static void searchById(Scanner scanner) {
        System.out.println("search:");
        String input = scanner.nextLine();
        ArrayList<String> nationalIds = new ArrayList<>();
        for (User user : Main.users) {
            if (user.getNationalId().contains(input)) {
                nationalIds.add(user.getNationalId());
            }
        }
        if (nationalIds.isEmpty()) {
            System.out.println("there is no user with this national id!");
        } else {
            for (int i = 0; i < nationalIds.size(); i++) {
                System.out.println((i + 1) + "_" + nationalIds.get(i));
            }
            int index = scanner.nextInt();
            scanner.nextLine();
            System.out.println(findUserById(nationalIds.get(index - 1)));
        }
    }

    public static User findUserById(String id) {
        for (User user : Main.users) {
            if (user.getNationalId().equals(id)) {
                return user;
            }
        }
        return null;
    }

    public static void searchByEmail(Scanner scanner) {
        System.out.println("search:");
        String input = scanner.nextLine();
        ArrayList<String> emails = new ArrayList<>();
        for (User user : Main.users) {
            if (user.getEmail().contains(input)) {
                emails.add(user.getEmail());
            }
        }
        if (emails.isEmpty()) {
            System.out.println("there is no user with this email!");
        } else {
            for (int i = 0; i < emails.size(); i++) {
                System.out.println((i + 1) + "_" + emails.get(i));
            }
            int index = scanner.nextInt();
            scanner.nextLine();
            System.out.println(findUserByEmail(emails.get(index - 1)));
        }
    }

    public static User findUserByEmail(String email) {
        for (User user : Main.users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }
        return null;
    }
}
