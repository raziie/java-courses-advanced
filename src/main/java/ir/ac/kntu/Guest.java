package ir.ac.kntu;

public class Guest extends User{

    public Guest() {
        setType(UserType.GUEST);
    }

    @Override
    public void logOut() {
        Main.currentUser = null;
    }
}
