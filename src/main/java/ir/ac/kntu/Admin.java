package ir.ac.kntu;

import java.util.Scanner;

public class Admin extends User{

    public Admin(String name, String userName, String email, String password, String nationalId, String phoneNumber) {
        super(name, userName, email, password, nationalId, phoneNumber);
        setType(UserType.ADMIN);
    }

    public static void promoteToAdmin(Scanner scanner) {
        Main.displayUsers();
        System.out.print("Which? ");
        User user = Main.users.get(scanner.nextInt() - 1);
        scanner.nextLine();
        if(user.getType() == UserType.CUSTOMER) {
            Main.users.remove(user);
            User newAdmin = new Admin(user.getName(), user.getUserName(), user.getEmail(),
                    user.getPassword(), user.getNationalId(), user.getNationalId());
            Main.users.add(newAdmin);
            System.out.println("promoted successfully");
        }else {
            System.out.println("you can't promote this user!");
        }
    }
}
