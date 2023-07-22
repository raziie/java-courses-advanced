package ir.ac.kntu;

import java.util.ArrayList;
import java.util.Scanner;

public class SearchClass {
    public static void searchAndEnterByName(Scanner scanner) {
        if(Main.currentClass != null) {
            System.out.println("please get out of previous class first");
        }else if(Main.currentUser == null) {
            System.out.println("please log in first");
        }else {
            searchByName(scanner);
        }
    }

    public static void searchByName(Scanner scanner) {
        System.out.println("search:");
        String input = scanner.nextLine();
        ArrayList<String> names = new ArrayList<>();
        for (Classroom aClass : Main.classes) {
            if (aClass.getName().contains(input)) {
                names.add(aClass.getName());
            }
        }
        if (names.isEmpty()) {
            System.out.println("there is no class with this name!");
        } else {
            for (int i = 0; i < names.size(); i++) {
                System.out.println((i + 1) + "_" + names.get(i));
            }
            String name = names.get(scanner.nextInt() - 1);
            scanner.nextLine();
            Classroom theClass = findClassByName(name);
            requestPasswordIfNeeded(scanner, theClass);
            Main.currentClass = theClass;
            System.out.println("entered the class successfully");
            System.out.println(Main.currentClass);
            Classroom.register();
        }
    }

    public static Classroom findClassByName(String name) {
        for (Classroom aClass : Main.classes) {
            if (aClass.getName().equals(name)) {
                return aClass;
            }
        }
        return null;
    }

    public static void requestPasswordIfNeeded(Scanner scanner, Classroom theClass) {
        if(theClass.isPrivate()) {
            Classroom.requestPassword(scanner,theClass);
        }
    }

    public static void searchAndEnterByInstitution(Scanner scanner) {
        if(Main.currentClass != null) {
            System.out.println("please get out of previous class first");
        }else if(Main.currentUser == null) {
            System.out.println("please log in first");
        }else {
            searchByInstitution(scanner);
        }
    }

    public static void searchByInstitution(Scanner scanner) {
        System.out.println("search:");
        String input = scanner.nextLine();
        ArrayList<String> institutions = new ArrayList<>();
        for (Classroom aClass : Main.classes) {
            if (aClass.getEducationalInstitution().contains(input)) {
                institutions.add(aClass.getEducationalInstitution());
            }
        }
        if (institutions.isEmpty()) {
            System.out.println("there is no class with this educational institution!");
        } else {
            for (int i = 0; i < institutions.size(); i++) {
                System.out.println((i + 1) + "_" + institutions.get(i));
            }
            int theClass = scanner.nextInt();
            scanner.nextLine();
            Classroom aClass = findClassByInstitution(institutions.get(theClass - 1));
            requestPasswordIfNeeded(scanner, aClass);
            Main.currentClass = aClass;
            System.out.println("entered the class successfully");
            System.out.println(Main.currentClass);
            Classroom.register();
        }
    }

    public static Classroom findClassByInstitution(String institution) {
        for (Classroom theClass : Main.classes) {
            if (theClass.getEducationalInstitution().equals(institution)) {
                return theClass;
            }
        }
        return null;
    }

    public static void searchAndEnterByOwner(Scanner scanner) {
        if(Main.currentClass != null) {
            System.out.println("please get out of previous class first");
        }else if(Main.currentUser == null) {
            System.out.println("please log in first");
        }else {
            searchByOwner(scanner);
        }
    }

    public static void searchByOwner(Scanner scanner) {
        System.out.println("search:");
        String input = scanner.nextLine();
        ArrayList<String> owners = new ArrayList<>();
        for (Classroom aClass : Main.classes) {
            if (aClass.getProfessor().getName().contains(input)) {
                owners.add(aClass.getProfessor().getName());
            }
        }
        if (owners.isEmpty()) {
            System.out.println("there is no class with this owner!");
        } else {
            for (int i = 0; i < owners.size(); i++) {
                System.out.println((i + 1) + "_" + owners.get(i));
            }
            int theClass = scanner.nextInt();
            scanner.nextLine();
            Classroom aClass = findClassByOwner(owners.get(theClass - 1));
            requestPasswordIfNeeded(scanner, aClass);
            Main.currentClass = aClass;
            System.out.println("entered the class successfully");
            System.out.println(Main.currentClass);
            Classroom.register();
        }
    }

    public static Classroom findClassByOwner(String owner) {
        for (Classroom aClass : Main.classes) {
            if (aClass.getProfessor().getName().equals(owner)) {
                return aClass;
            }
        }
        return null;
    }
}
