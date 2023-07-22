package ir.ac.kntu;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class User implements Comparable<User> {

    public enum UserType {ADMIN, GUEST, CUSTOMER, NONE}

    public UserType type = UserType.NONE;

    private String name;

    private String userName;

    private String email;

    private String password;

    private String nationalId;

    private String phoneNumber;

    public User() {

    }

    public User(String name, String userName, String email, String password, String nationalId, String phoneNumber) {
        this.name = name;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.nationalId = nationalId;
        this.phoneNumber = phoneNumber;
        PasswordMap.passwords.put(userName, password);
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNationalId() {
        return nationalId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public UserType getType() {
        return type;
    }

    public static boolean userNameIsValid(String userName) {
        for(User user : Main.users) {
            if(user.getUserName().equals(userName)) {
                return false;
            }
        }
        return true;
    }

    public static boolean emailIsValid(String email) {
        return email.matches("\\S+@\\w+\\.\\S+");
    }

    public static boolean nationalIdIsValid(String nationalId) {
        return nationalId.matches("\\d{10}");
    }

    public static boolean phoneNumberIsValid(String phoneNumber) {
        return phoneNumber.matches("09\\d{9}");
    }

    public static void addUser(Scanner scanner) {
        String inputName = scanName(scanner);
        String inputUserName = scanUserName(scanner);
        String inputEmail = scanEmail(scanner);
        String inputPassword = scanPassword(scanner);
        String inputNationalId = scanId(scanner);
        String inputPhone = scanPhoneNumber(scanner);
        User user = new Customer(inputName, inputUserName, inputEmail, inputPassword, inputNationalId, inputPhone);
        Main.users.add(user);
        System.out.println("successfully added");
    }

    public static String scanName(Scanner scanner) {
        System.out.println("please enter your name:");
        return scanner.nextLine();
    }

    public static String scanUserName(Scanner scanner) {
        System.out.println("please enter a userName:");
        String inputUserName = scanner.nextLine();
        while(!userNameIsValid(inputUserName)) {
            System.out.println("user name is already token!");
            System.out.println("please enter a userName:");
            inputUserName = scanner.nextLine();
        }
        return inputUserName;
    }

    public static String scanEmail(Scanner scanner) {
        System.out.println("please enter your email:");
        String inputEmail = scanner.nextLine();
        while(!emailIsValid(inputEmail)) {
            System.out.println("email is invalid!");
            System.out.println("please enter your email:");
            inputEmail = scanner.nextLine();
        }
        return inputEmail;
    }

    public static String scanPassword(Scanner scanner) {
        System.out.println("please enter your password");
        return scanner.nextLine();
    }

    public static String scanId(Scanner scanner) {
        System.out.println("please enter your national ID:");
        String inputNationalId = scanner.nextLine();
        while(!nationalIdIsValid(inputNationalId)) {
            System.out.println("national ID is invalid!");
            System.out.println("please enter your national ID:");
            inputNationalId = scanner.nextLine();
        }
        return inputNationalId;
    }

    public static String scanPhoneNumber(Scanner scanner) {
        System.out.println("please enter your phone number:");
        String inputPhone = scanner.nextLine();
        while(!phoneNumberIsValid(inputPhone)) {
            System.out.println("phone number is invalid!");
            System.out.println("please enter your phone number:");
            inputPhone = scanner.nextLine();
        }
        return inputPhone;
    }

    public static void logIn(Scanner scanner) throws IOException {
        if(Main.currentUser != null) {
            System.out.println("please log out of previous account first!");
        }else {
            System.out.print("username: ");
            String username = scanner.nextLine();
            if (PasswordMap.passwords.containsKey(username)) {
                requestUserPassword(scanner, username);
                Main.currentUser = findUser(username);
                System.out.println("logged in successfully");
                UserMenu.handle(scanner);
            } else {
                System.out.println("there is no user with this username!");
            }
        }
    }

    public void logOut() {
        if(Main.currentUser == null) {
            System.out.println("you haven't logged in yet!");
        }else {
            Main.currentUser = null;
            System.out.println("logged out successfully!");
        }
    }

    public static User findUser(String userName) {
        for(User user : Main.users) {
            if(user.getUserName().equals(userName)) {
                return user;
            }
        }
        return null;
    }

    public static void enterClass(Scanner scanner) {
        if(Main.currentUser == null) {
            System.out.println("please log in first!");
        }else {
            for (int i = 0; i < Main.classes.size(); i++) {
                System.out.println((i + 1) + "_" + Main.classes.get(i).getName());
            }
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            if(isAllowedToEnter(index)) {
                if(Main.classes.get(index).isPrivate()) {
                    requestClassPassword(scanner, index);
                }
                Main.currentClass = Main.classes.get(index);
                System.out.println("entered successfully!");
            }else {
                System.out.println("you can't enter this class");
            }
        }
    }

    public static boolean isAllowedToEnter(int index) {
        if(Main.currentUser.equals( Main.classes.get(index).getProfessor())) {
            return true;
        }else if(Main.currentUser.equals(Main.classes.get(index).getTA())) {
            return true;
        }else if(Main.classes.get(index).containsStudent(Main.currentUser)) {
            return true;
        }else {
            return (Main.currentUser.getType() == UserType.ADMIN);
        }
    }

    public static void requestClassPassword(Scanner scanner, int index) {
        System.out.print("password:");
        String password = scanner.nextLine();
        while(!(password.equals(Main.classes.get(index).getPassword()))) {
            System.out.println("incorrect!");
            System.out.print("password:");
            password = scanner.nextLine();
        }
    }

    public static void requestUserPassword(Scanner scanner, String username) {
        System.out.print("password: ");
        String password = scanner.nextLine();
        while (!(PasswordMap.passwords.get(username).equals(password))) {
            System.out.println("password is incorrect!");
            System.out.print("password: ");
            password = scanner.nextLine();
        }
    }

    @Override
    public int compareTo(User other) {
        double points =  Main.currentPractice.calculatePoints(getUserName());
        double otherPoints = Main.currentPractice.calculatePoints(other.getUserName());
        if(points != otherPoints) {
            return (int)(points - otherPoints);
        }
        int time = Main.currentPractice.calculateTime(getUserName());
        int otherTime = Main.currentPractice.calculateTime(other.getUserName());
        return otherTime - time;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
        if(obj == null || getClass() != obj.getClass()) {
            return false;
        }
        User user = (User) obj;
        return (this.userName.equals(user.getUserName()));
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }

    @Override
    public String toString() {
        return "[name: " + name
                + "|username: " + userName
                + "|email: " + email
                + "|national ID: " + nationalId
                + "|phone number: " + phoneNumber + "]" + "\n";
    }
}
