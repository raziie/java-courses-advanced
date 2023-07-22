package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.*;

public abstract class Competition {

    public enum CompetitionType {SPECIAL, PRIVATE, ORDINARY, NONE}

    private String name;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private ArrayList<Question> questions;

    private ArrayList<Answer> answers;

    private CompetitionType type;

    private final Admin admin;

    private boolean isFull = false;

    private final int capacity;

    private ArrayList<Customer> participants;

    private HashMap<Customer, Integer> scores;

    public Competition(String name, LocalDateTime startTime, LocalDateTime endTime, Admin admin, int capacity) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        type = CompetitionType.NONE;
        this.admin = admin;
        this.capacity = capacity;
        participants = new ArrayList<>();
        scores = new HashMap<>();
        for(Customer participant : participants) {
            scores.put(participant, 0);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public ArrayList<Question> getQuestions() {
        return new ArrayList<>(questions);
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public ArrayList<Answer> getAnswers() {
        return new ArrayList<>(answers);
    }

    public CompetitionType getType() {
        return type;
    }

    public void setType(CompetitionType type) {
        this.type = type;
    }

    public Admin getAdmin() {
        return admin;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isFull() {
        return isFull;
    }

    public void setFull(boolean full) {
        isFull = full;
    }

    public void addMember(Customer member) {
        participants.add(member);
    }

    public int getNumberOfParticipants() {
        return participants.size();
    }

    public void addParticipant(Customer member, Scanner scanner) {
        if(isFull) {
            System.out.println("this competition's capacity is full");
        }else {
            participants.add(member);
            if(participants.size() == capacity) {
                isFull = true;
            }
        }
    }

    public void addScore(Customer member, int point) {
        scores.put(member, scores.get(member) + point);
    }

    public static void print() {
        if(Main.currentCompetition == null) {
            System.out.println("please open a competition first");
        }else {
            Main.currentCompetition.printBoard();
        }
    }

    public void printBoard() {
        List<Customer> sorted = sortParticipants();
        for (Customer customer : sorted) {
            System.out.println(customer.getName() + "     " + scores.get(customer));
        }
    }

    public List<Customer> sortParticipants() {
        return participants.stream().sorted(Comparator.comparing(o -> scores.get(o)).reversed()).toList();
    }

    public static void open(Scanner scanner) {
        if(Main.currentUser.getType() == User.UserType.ADMIN){
            Main.currentCompetition = getAdminCompetition(scanner);
            System.out.println("opened successfully");
        }else if(Main.currentUser.getType() == User.UserType.CUSTOMER){
            Main.currentCompetition = getCustomerCompetition(scanner);
            System.out.println("opened successfully");
        }else if(Main.currentUser.getType() == User.UserType.GUEST){
            Main.currentCompetition = getGuestCompetition(scanner);
            System.out.println("opened successfully");
        }
    }

    public static Competition getAdminCompetition(Scanner scanner) {
        for(int i=0; i< Main.competitions.size(); i++) {
            System.out.println((i + 1) + "_" + Main.competitions.get(i).getName());
        }
        int index = scanner.nextInt();
        scanner.nextLine();
        return Main.competitions.get(index - 1);
    }

    public static Competition getCustomerCompetition(Scanner scanner) {
        ArrayList<Competition> allowed = new ArrayList<>();
        for(Competition competition : Main.competitions) {
            if(competition.contains((Customer) Main.currentUser)) {
                allowed.add(competition);
            }else if(competition.getType() != CompetitionType.PRIVATE) {
                allowed.add(competition);
            }
        }
        for(int i=0; i< allowed.size(); i++) {
            System.out.println((i + 1) + "_" + allowed.get(i).getName());
        }
        int index = scanner.nextInt();
        scanner.nextLine();
        return allowed.get(index - 1);
    }

    public static Competition getGuestCompetition(Scanner scanner) {
        ArrayList<Competition> allowed = new ArrayList<>();
        for(Competition competition : Main.competitions) {
            if(competition.getEndTime().compareTo(LocalDateTime.now()) <= 0) {
                allowed.add(competition);
            }
        }
        for(int i=0; i< allowed.size(); i++) {
            System.out.println((i + 1) + "_" + allowed.get(i).getName());
        }
        int index = scanner.nextInt();
        scanner.nextLine();
        return allowed.get(index - 1);
    }

    public static void close(Scanner scanner) {
        Main.currentCompetition = null;
        System.out.println("closed successfully");
    }

    public boolean contains(Customer customer) {
        for (Customer participant : participants) {
            if (customer.equals(participant)) {
                return true;
            }
        }
        return false;
    }

    public static void participate(Scanner scanner) {
        if(Main.currentCompetition == null) {
            System.out.println("please open a competition first");
        }else {
            Competition competition = getCompetition(scanner);
            if(competition == null) {
                System.out.println("there is no competition for you");
            }else {
                competition.addParticipant((Customer) Main.currentUser, scanner);
                System.out.println("added successfully");
            }
        }
    }

    public static Competition getCompetition(Scanner scanner) {
        boolean isEmpty = true;
        for(int i=0; i<Main.competitions.size(); i++) {
            if(isOrdinaryOrSpecial(i) && !Main.competitions.get(i).isFull()) {
                System.out.println( (i + 1)+ "_" + Main.competitions.get(i));
                isEmpty = false;
            }
        }
        if(isEmpty) {
            return null;
        }else {
            int index = scanner.nextInt();
            scanner.nextLine();
            return Main.competitions.get(index - 1);
        }
    }

    public static boolean isOrdinaryOrSpecial(int index) {
        if(Main.competitions.get(index).getType() == CompetitionType.ORDINARY) {
            return true;
        }else {
            return (Main.competitions.get(index).getType() == CompetitionType.SPECIAL);
        }
    }

    public static void delete(Scanner scanner) {
        Main.competitions.remove(getAdminCompetition(scanner));
        System.out.println("deleted successfully");
    }

    public void sendAnswer(Scanner scanner) {
        if(Main.currentCompetition.contains((Customer) Main.currentUser)) {
            if(Main.currentCompetition.getEndTime().compareTo(LocalDateTime.now()) > 0) {
                Answer answer = Answer.createAnswerForCompetition(scanner);
                Main.currentCompetition.addAnswer(answer);
                System.out.println("answer added successfully");
            }else {
                System.out.println("deadline has expired");
            }
        }else {
            System.out.println("you are not allowed to do this!");
        }
    }

    public static void create(Scanner scanner) {
        CompetitionType type = CompetitionScannersAndEditors.scanType(scanner);
        switch (type) {
            case PRIVATE -> PrivateCompetition.create(scanner);
            case SPECIAL -> SpecialCompetition.create(scanner);
            case ORDINARY -> OrdinaryCompetition.create(scanner);
            default -> {
                break;
            }
        }
    }

    public static void invite(Scanner scanner) {
        if(Main.currentCompetition == null) {
            System.out.println("please open a competition first");
        }else if(Main.currentCompetition.getType() == CompetitionType.PRIVATE) {
            if(Main.currentUser.equals(Main.currentCompetition.getAdmin())) {
                ((PrivateCompetition)Main.currentCompetition).inviteMember(scanner);
            }else {
                System.out.println("you are not the admin");
            }
        }else {
            System.out.println("this competition is not private");
        }
    }

    public static void search(Scanner scanner) {
        System.out.println("search:");
        String input = scanner.nextLine();
        ArrayList<String> names = new ArrayList<>();
        for (Competition competition : Main.competitions) {
            if (competition.getName().contains(input)) {
                names.add(competition.getName());
            }
        }
        if (names.isEmpty()) {
            System.out.println("there is no competition with this name!");
        } else {
            for (int i = 0; i < names.size(); i++) {
                System.out.println((i + 1) + "_" + names.get(i));
            }
            String name = names.get(scanner.nextInt() - 1);
            scanner.nextLine();
            Main.currentCompetition = findCompetition(name);
            System.out.println("entered the competition successfully");
            System.out.println(Main.currentCompetition);
        }
    }

    public static Competition findCompetition(String name) {
        for (Competition competition : Main.competitions) {
            if (competition.getName().equals(name)) {
                return competition;
            }
        }
        return null;
    }

    public void correct(Scanner scanner) {
        if(Main.currentUser.equals(Main.currentCompetition.getAdmin())) {
            System.out.print("which participant?(enter the email)");
            Customer participant = (Customer) Main.currentCompetition.findParticipant(scanner.nextLine());
            System.out.println("which question?");
            Question question = findQuestion(scanner);
            ArrayList<Answer> theAnswers = Main.currentCompetition.findAnswers(participant.getUserName(), question);
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
            adjustPoint(inputPoints, participant);
            setFinals(theAnswers, inputPoints.get(0));
            System.out.println("done");
        }else {
            System.out.println("you are not the professor");
        }
    }

    public User findParticipant(String email) {
        for(User participant : participants) {
            if(participant.getEmail().equals(email)) {
                return participant;
            }
        }
        return null;
    }

    public static Question findQuestion(Scanner scanner) {
        Main.currentCompetition.printQuestions();
        Question question = Main.currentCompetition.getQuestion(scanner.nextInt() - 1);
        scanner.nextLine();
        return question;
    }

    public void printQuestions() {
        for(int i=0; i<questions.size(); i++) {
            System.out.println((i + 1) + "_" + questions.get(i).getName());
        }
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public ArrayList<Answer> findAnswers(String userName, Question question) {
        ArrayList<Answer> theAnswers = new ArrayList<>();
        for(Answer answer : answers) {
            if(answer.getSenderUserName().equals(userName) && answer.getQuestion().equals(question)) {
                theAnswers.add(answer);
            }
        }
        return theAnswers;
    }

    public void adjustPoint(ArrayList<Integer> points, User participant) {
        Main.currentCompetition.addScore((Customer) participant, points.get(0));
    }

    public static void setFinals(ArrayList<Answer> answers, int maxPoint) {
        for(Answer answer : answers) {
            answer.setFinal(answer.getPointWithDelay() == maxPoint);
        }
    }

    public abstract void givePoints();

    public void searchAnswer(Scanner scanner) {
        if(Main.currentUser.equals(Main.currentCompetition.getAdmin())) {
            for (int i = 0; i < questions.size(); i++) {
                System.out.println((i + 1) + "_" + questions.get(i));
            }
            int index = scanner.nextInt() - 1;
            scanner.nextLine();
            System.out.println("please enter participant's email:");
            String email = scanner.nextLine();
            User participant = findParticipant(email);
            if(participant == null) {
                System.out.println("there is no participant with this email");
            }else {
                printAnswers(participant, questions.get(index));
            }
        }else {
            System.out.println("you are not allowed to do this");
        }
    }

    public void printAnswers(User participant, Question question) {
        for(Answer answer : answers) {
            if(isRelated(answer, question, participant)) {
                System.out.println(answer);
            }
        }
    }

    public void printFinalAnswers(User participant, Question question) {
        for(Answer answer : answers) {
            if(isRelated(answer, question, participant) && answer.isFinal()) {
                System.out.println(answer);
            }
        }
    }

    public boolean isRelated(Answer answer, Question question, User student) {
        if(answer.getSenderUserName().equals(student.getUserName())) {
            return answer.getQuestion().equals(question);
        }
        return false;
    }

    public void seeAnswers(Scanner scanner) {
        if(Main.currentCompetition.contains((Customer) Main.currentUser)) {
            printAnswers(Main.currentUser, findQuestion(scanner));
        }else {
            System.out.println("you are not a participant");
        }
    }

    public void seeFinalAnswers(Scanner scanner) {
        if(Main.currentCompetition.contains((Customer) Main.currentUser)) {
            printFinalAnswers(Main.currentUser, findQuestion(scanner));
        }else {
            System.out.println("you are not a participant");
        }
    }

    @Override
    public String toString() {
        return "name:" + name + "\n"
                + "start time:" + startTime + "\n"
                + "end time:" + endTime + "\n"
                + "type:" + type + "\n"
                + "admin:" + admin.getName() + "\n"
                + "is it full?" + isFull + "\n"
                + "capacity:" + capacity + "\n"
                + "participants:" + participants;
    }
}