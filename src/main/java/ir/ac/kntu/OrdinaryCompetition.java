package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class OrdinaryCompetition extends Competition{

    public OrdinaryCompetition(String name, LocalDateTime startTime, LocalDateTime endTime, Admin admin) {
        super(name, startTime, endTime, admin, 50);
        setType(CompetitionType.ORDINARY);
    }

    public static void create(Scanner scanner) {
        String inputName = CompetitionScannersAndEditors.scanName(scanner);
        LocalDateTime inputStart = CompetitionScannersAndEditors.scanStartTime(scanner);
        LocalDateTime inputEnd = CompetitionScannersAndEditors.scanEndTime(scanner);
        OrdinaryCompetition competition = new OrdinaryCompetition(inputName, inputStart, inputEnd, (Admin) Main.currentUser);
        Main.competitions.add(competition);
        System.out.println("successfully added");
    }

    @Override
    public void givePoints() {
        List<Customer> participants = sortParticipants();
        for(int i=0; i<5; i++) {
            participants.get(i).setPoint(participants.get(i).getPoint() + 20);
        }
    }
}
