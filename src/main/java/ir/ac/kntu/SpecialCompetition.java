package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SpecialCompetition extends Competition{

    private ArrayList<Group> groups;

    private int groupSize;

    public SpecialCompetition(String name, LocalDateTime startTime, LocalDateTime endTime, int groupSize, Admin admin) {
        super(name, startTime, endTime, admin, 100);
        setType(CompetitionType.SPECIAL);
        groups = new ArrayList<>();
        this.groupSize = groupSize;
    }

    @Override
    public void addParticipant(Customer member, Scanner scanner) {
        if(isFull()) {
            System.out.println("this competition's capacity is full");
        }else {
            addMember(member);
            chooseOrCreateGroup(scanner);
            if(getNumberOfParticipants() == getCapacity()) {
                setFull(true);
            }
        }
    }

    public void chooseOrCreateGroup(Scanner scanner) {
        System.out.println("1.Choose a group");
        System.out.println("2.Create a group");
        int index = scanner.nextInt();
        scanner.nextLine();
        if(index == 1) {
            chooseGroup(scanner);
        }else {
            createGroup(scanner);
        }
    }

    public void chooseGroup(Scanner scanner) {
        boolean isEmpty = true;
        for (int i = 0; i < groups.size(); i++) {
            if (groups.get(i).getMembers().size() < groupSize) {
                System.out.println((i + 1) + "_" + groups.get(i));
                isEmpty = false;
            }
        }
        if(isEmpty) {
            System.out.println("there is no group that you can join");
        }else {
            groups.get(scanner.nextInt() - 1).addMember((Customer) Main.currentUser);
            scanner.nextLine();
            System.out.println("added successfully");
        }
    }

    public void createGroup(Scanner scanner) {
        System.out.println("please enter your group's name:");
        String inputName = scanner.nextLine();
        Group group = new Group(inputName);
        group.addMember((Customer) Main.currentUser);
        groups.add(group);
        System.out.println("created and added successfully");
    }

    @Override
    public void printBoard() {
        Collections.sort(groups);
        Collections.reverse(groups);
        for(Group group : groups) {
            System.out.println(group.getName() + "     " + group.getScore());
        }
    }

    public static void create(Scanner scanner) {
        String inputName = CompetitionScannersAndEditors.scanName(scanner);
        LocalDateTime inputStart = CompetitionScannersAndEditors.scanStartTime(scanner);
        LocalDateTime inputEnd = CompetitionScannersAndEditors.scanEndTime(scanner);
        int inputSize = CompetitionScannersAndEditors.scanGroupSize(scanner);
        SpecialCompetition competition = new SpecialCompetition(inputName, inputStart, inputEnd, inputSize, (Admin) Main.currentUser);
        Main.competitions.add(competition);
        System.out.println("successfully added");
    }

    @Override
    public void givePoints() {
        Collections.sort(groups);
        Collections.reverse(groups);
        for(int i=0; i<10; i++) {
            addPoint(groups.get(i).getMembers());
        }
        System.out.println("added");
    }

    @Override
    public void correct(Scanner scanner) {
        if(Main.currentUser.equals(Main.currentCompetition.getAdmin())) {
            System.out.print("which group?(enter name)");
            Group group = findGroup(scanner.nextLine());
            System.out.println("which question?");
            Question question = findQuestion(scanner);
            ArrayList<Answer> theAnswers = findGroupAnswers(group, question);
            ArrayList<Integer> inputPoints = new ArrayList<>();
            for(Answer answer : theAnswers) {
                System.out.println("answer:" + answer);
                System.out.print("point:");
                int inputPoint = scanner.nextInt();
                scanner.nextLine();
                answer.setPoint(inputPoint);
                inputPoints.add(inputPoint);
            }
            Collections.sort(inputPoints);
            adjustGroupPoint(inputPoints, group);
            setFinals(theAnswers, inputPoints.get(0));
            System.out.println("done");
        }else {
            System.out.println("you are not the professor");
        }
    }

    public void adjustGroupPoint(ArrayList<Integer> points, Group group) {
        for(Customer member : group.getMembers()) {
            Main.currentCompetition.addScore(member, points.get(0));
        }
        group.addScore(points.get(0));
    }

    public Group findGroup(String name) {
        for(Group group : groups) {
            if(group.getName().equals(name)) {
                return group;
            }
        }
        return null;
    }

    public ArrayList<Answer> findGroupAnswers(Group group, Question question) {
        ArrayList<Answer> answers = new ArrayList<>();
        for(Answer answer : getAnswers()) {
            if(answer.getQuestion().equals(question)) {
                if(group.containsMember(answer.getSenderUserName())) {
                    answers.add(answer);
                }
            }
        }
        return answers;
    }

    public void addPoint(ArrayList<Customer> members) {
        for(Customer member : members) {
            member.setPoint(member.getPoint() + 25);
        }
    }
}
