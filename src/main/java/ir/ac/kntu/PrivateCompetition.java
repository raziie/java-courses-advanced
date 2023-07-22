package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class PrivateCompetition extends Competition{

    public PrivateCompetition(String name, LocalDateTime startTime, LocalDateTime endTime, Admin admin) {
        super(name, startTime, endTime, admin,20);
        setType(CompetitionType.PRIVATE);
    }

    public void inviteMember(Scanner scanner) {
        System.out.println("please enter new member's userName:");
        String inputUserName = scanner.nextLine();
        Customer user  = (Customer) User.findUser(inputUserName);
        if(user == null) {
            System.out.println("there is no user with this username");
        }else {
            addParticipant(user, scanner);
            System.out.println("added successfully");
        }
    }

    public static void create(Scanner scanner) {
        String inputName = CompetitionScannersAndEditors.scanName(scanner);
        LocalDateTime inputStart = CompetitionScannersAndEditors.scanStartTime(scanner);
        LocalDateTime inputEnd = CompetitionScannersAndEditors.scanEndTime(scanner);
        PrivateCompetition competition = new PrivateCompetition(inputName, inputStart, inputEnd, (Admin) Main.currentUser);
        Main.competitions.add(competition);
        System.out.println("successfully added");
    }

    @Override
    public void givePoints() {
        List<Customer> participants = sortParticipants();
        for(int i=0; i<3; i++) {
            participants.get(i).setPoint(participants.get(i).getPoint() + 10);
        }
    }
}
