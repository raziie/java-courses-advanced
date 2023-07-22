package ir.ac.kntu;

public class Customer extends User{

    private int point = 0;

    private int degree = 0;

    public Customer(String name, String userName, String email, String password, String nationalId, String phoneNumber) {
        super(name, userName, email, password, nationalId, phoneNumber);
        setType(UserType.CUSTOMER);
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
        setDegree(point / 50);
    }

    public int getDegree() {
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
