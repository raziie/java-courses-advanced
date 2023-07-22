package ir.ac.kntu;

import java.io.IOException;
import java.time.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Practice {

    private String name;

    private String explanation;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private LocalDateTime delayTime;

    private boolean isBeingTested;

    private double delayFactor;

    private ArrayList<Question> questions;

    private ArrayList<Answer> answers;

    private Scoreboard scoreboard;

    public Practice(String name, String explanation, LocalDateTime startTime, LocalDateTime endTime,
                    LocalDateTime delayTime, boolean isBeingTested, double delayFactor) {
        this.name = name;
        this.explanation = explanation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.delayTime = delayTime;
        this.isBeingTested = isBeingTested;
        this.delayFactor = delayFactor;
        questions = new ArrayList<>();
        answers = new ArrayList<>();
        scoreboard = new Scoreboard(false);
    }

    public String getName() {
        return name;
    }

    public String getExplanation() {
        return explanation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public LocalDateTime getDelayTime() {
        return delayTime;
    }

    public double getDelayFactor() {
        return delayFactor;
    }

    public boolean isBeingTested() {
        return isBeingTested;
    }

    public Scoreboard getScoreboard() {
        return scoreboard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setDelayTime(LocalDateTime delayTime) {
        this.delayTime = delayTime;
    }

    public void setBeingTested(boolean beingTested) {
        isBeingTested = beingTested;
    }

    public void setDelayFactor(double delayFactor) {
        this.delayFactor = delayFactor;
    }

    public void setScoreboard(Scoreboard scoreboard) {
        this.scoreboard = scoreboard;
    }

    public void addAnswer(Answer answer) {
        if(Main.currentClass.containsStudent(Main.currentUser)) {
            Answer previousAnswer = findAnswer(answer.getSenderUserName(), answer.getQuestion());
            if (previousAnswer != null) {
                previousAnswer.setFinal(false);
            }
            answers.add(answer);
        }
    }

    public Answer findAnswer(String userName, Question question) {
        for(Answer answer : answers) {
            if(answer.getSenderUserName().equals(userName)) {
                if(answer.getQuestion().equals(question) && answer.isFinal()) {
                    return answer;
                }
            }
        }
        return null;
    }

    public void removeAnswer(Answer answer) {
        answers.remove(answer);
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void removeQuestion(Scanner scanner) {
        System.out.println("which question?");
        for(int i=0; i<questions.size(); i++) {
            System.out.println((i+1) + "_" + questions.get(i));
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        questions.remove(index);
        System.out.println("removed successfully");
    }

    public void editQuestion(Scanner scanner) {
        System.out.println("which question?");
        for(int i=0; i<questions.size(); i++) {
            System.out.println((i+1) + "_" + questions.get(i));
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        QuestionEditMenu.edit(scanner, questions.get(index));
    }

    public static void activateTest() {
        Main.currentPractice.setBeingTested(true);
    }

    public static void deactivateTest() {
        Main.currentPractice.setBeingTested(false);
    }

    public static void handleExceptions() {
        if(Main.currentClass == null) {
            System.out.println("please enter a class first");
        }else if(Main.currentPractice == null) {
            System.out.println("please open a practice first");
        }else if(Main.currentUser == null){
            System.out.println("please log in first");
        }
    }

    public static boolean hasException() {
        return (Main.currentClass == null || Main.currentUser == null || Main.currentPractice == null);
    }

    public static void addPractice(Scanner scanner) {
        String inputName = PracticeScannersAndEditors.scanName(scanner);
        String inputExplanation = PracticeScannersAndEditors.scanExplanation(scanner);
        LocalDateTime inputStart = PracticeScannersAndEditors.scanStartTime(scanner);
        LocalDateTime inputEnd = PracticeScannersAndEditors.scanEndTime(scanner);
        LocalDateTime inputDelay = PracticeScannersAndEditors.scanDelayTime(scanner);
        boolean inputTest = PracticeScannersAndEditors.scanIsBeingTested(scanner);
        double inputFactor = PracticeScannersAndEditors.scanDelayFactor(scanner);
        Practice practice = new Practice(inputName, inputExplanation, inputStart,
                inputEnd, inputDelay, inputTest, inputFactor);
        if(Main.currentClass.getPractices().contains(practice)) {
            System.out.println("this practice is already added");
        }else {
            Main.currentClass.addPractice(practice);
            System.out.println("successfully added");
        }
    }

    public static void openPractice(Scanner scanner){
        if(hasOpeningException()) {
            handleOpeningExceptions();
        }else if(Main.currentUser.equals(Main.currentClass.getProfessor())){
            openPracticeForProfessor(scanner);
        }else if(Main.currentClass.containsStudent(Main.currentUser)) {
            openPracticeForStudent(scanner);
        }else {
            System.out.println("you are not allowed to access this practice");
        }
    }

    public static void handleOpeningExceptions() {
        if(Main.currentClass == null) {
            System.out.println("please enter a class first");
        }else if(Main.currentUser == null) {
            System.out.println("please log in first");
        }else if(Main.currentClass.getPractices().isEmpty()) {
            System.out.println("this class doesn't have any practice");
        }
    }

    public static boolean hasOpeningException() {
        return (Main.currentClass == null || Main.currentUser == null || Main.currentClass.getPractices().isEmpty());
    }

    public static void openPracticeForStudent(Scanner scanner) {
        Main.currentClass.printPractices();
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        if(Main.currentClass.getPractices().get(index).isBeingTested()) {
            System.out.println("practice is being tested now");
        }else if(Main.currentClass.getPractices().get(index).getStartTime().compareTo(LocalDateTime.now()) > 0) {
            System.out.println("deadline hasn't started yet");
        }else {
            Main.currentPractice = Main.currentClass.getPractices().get(index);
            System.out.println("opened successfully");
            System.out.println(Main.currentPractice);
        }
    }

    public static void openPracticeForProfessor(Scanner scanner) {
        Main.currentClass.printPractices();
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        Main.currentPractice = Main.currentClass.getPractices().get(index);
        System.out.println("opened successfully");
    }

    public static void delete() {
        if(Main.currentPractice == null) {
            System.out.println("please open a practice first!");
        }else {
            Main.currentClass.removePractice(Main.currentPractice);
            Main.currentPractice = null;
        }
    }

    public static void addQuestion(Scanner scanner) {
        if(hasException()) {
            handleExceptions();
        }else if(Main.currentUser.equals(Main.currentClass.getProfessor())) {
            Question question = Question.createQuestion(scanner);
            Main.currentPractice.addQuestion(question);
            System.out.println("question added successfully");
        }else {
            System.out.println("you are not allowed to do this!");
        }
    }

    public boolean nameIsRepeated(String name) {
        for(Question question : questions) {
            if(question.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void addAnswer(Scanner scanner) {
        if(hasException()) {
            handleExceptions();
        }else if(Main.currentClass.containsStudent(Main.currentUser)) {
            if(Main.currentPractice.getDelayTime().compareTo(LocalDateTime.now()) > 0) {
                Answer answer = Answer.createAnswerForPractice(scanner);
                Main.currentPractice.addAnswer(answer);
                System.out.println("answer added successfully");
            }else {
                System.out.println("deadline has expired");
            }
        }else {
            System.out.println("you are not allowed to do this!");
        }
    }

    public void printQuestions() {
        for(int i=0; i<questions.size(); i++) {
            System.out.println((i+1) + "_" + questions.get(i).getName());
        }
    }

    public String getQuestionsWithoutEnter() {
        String result = "";
        for(Question question : questions) {
            int size = question.getName().length();
            String spaces = "";
            for(int i=0; i<10 - size; i++) {
                spaces += " ";
            }
            result += question.getName() + spaces + "|";
        }
        return result.trim();
    }

    public Question getQuestion(int index) {
        return questions.get(index);
    }

    public double calculatePoints(String userName) {
        double points = 0;
        for(Answer answer : answers) {
            if(answer.getSenderUserName().equals(userName) && answer.isFinal()) {
                points += answer.getPointWithDelay();
            }
        }
        return points;
    }

    public int calculateTime(String userName) {
        long time = 0;
        for(Answer answer : answers) {
            if(answer.getSenderUserName().equals(userName) && answer.isFinal()) {
                time += answer.getSendingTime().toEpochSecond(ZoneOffset.UTC);
            }
        }
        return (int)time;
    }

    public String getPoints(String userName) {
        String points = "";
        for(Question question : questions) {
            if(findAnswer(userName, question) == null) {
                points += "0         |";
            }else {
                String spaces = createSpace(findAnswer(userName, question).getPointWithDelay());
                points += findAnswer(userName, question).getPointWithDelay() + spaces + "|";
            }
        }
        return points.trim();
    }


    public String createSpace(double point) {
        int size = String.valueOf(point).length();
        String spaces = "";
        for(int i=0; i<10 - size; i++) {
            spaces += " ";
        }
        return spaces;
    }

    public static void printScoreboard() throws IOException {
        if(hasException()) {
            handleExceptions();
        }else if(Main.currentUser.equals(Main.currentClass.getProfessor())) {
            Main.currentPractice.printBoard();
        }else if(Main.currentClass.containsStudent(Main.currentUser)) {
            if(Main.currentPractice.getScoreboard().isVisible()) {
                Main.currentPractice.printBoard();
            }else {
                System.out.println("board is not visible now");
            }
        }else {
            System.out.println("you are not allowed to do this!");
        }
    }

    public void printBoard() throws IOException {
        scoreboard = new Scoreboard(true);
        System.out.println(scoreboard);
        Html.write(scoreboard);
    }

    public void printAnswers(String userName, boolean justFinals) {
        for(Answer answer : answers) {
            if(answer.getSenderUserName().equals(userName)) {
                if(!justFinals || answer.isFinal()) {
                    System.out.println(answer);
                }
            }
        }
    }

    public void markAnswers(Scanner scanner) {
        for(Answer answer : answers) {
            System.out.println(answer);
            System.out.print("point:");
            answer.setPoint(scanner.nextDouble());
            answer.setPointWithDelay(Answer.calculatePointWithDelay(answer.getSendingTime(),answer.getPoint()));
            scanner.nextLine();
            System.out.println("point is set");
        }
    }

    public static void remove(Scanner scanner) {
        if(hasException()) {
            handleExceptions();
        }else if(Main.currentUser.equals(Main.currentClass.getProfessor())) {
            Main.currentPractice.removeQuestion(scanner);
        }else {
            System.out.println("you are not allowed to do this!");
        }
    }

    public static void edit(Scanner scanner) {
        if(hasException()) {
            handleExceptions();
        }else if(Main.currentUser.equals(Main.currentClass.getProfessor())) {
            Main.currentPractice.editQuestion(scanner);
        }else {
            System.out.println("you are not allowed to do this!");
        }
    }

    public static void searchAnswers(Scanner scanner) {
        if(hasException()) {
            handleExceptions();
        }else if(Main.currentUser.equals(Main.currentClass.getProfessor())) {
            Main.currentPractice.search(scanner);
        }else {
            System.out.println("you are not allowed to do this!");
        }
    }

    public void search(Scanner scanner) {
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + "_" + questions.get(i));
        }
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println("please enter student's email:");
        String email = scanner.nextLine();
        User student = Main.currentClass.findStudent(email);
        if(student == null) {
            System.out.println("there is no student with this email");
        }else {
            printAnswers(student, questions.get(index));
        }
    }

    public void printAnswers(User student, Question question) {
        for(Answer answer : answers) {
            if(isRelated(answer, question, student)) {
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

    public static void correct(Scanner scanner) {
        if(Main.currentUser.equals(Main.currentClass.getProfessor())) {
            System.out.print("which student?(enter the email)");
            Customer student = (Customer) Main.currentClass.findStudent(scanner.nextLine());
            System.out.println("which question?");
            Question question = findQuestion(scanner);
            Answer answer = Main.currentPractice.findAnswer(student.getUserName(), question);
            System.out.println("answer:" + answer);
            System.out.print("point:");
            int inputPoint = scanner.nextInt();
            scanner.nextLine();
            student.setPoint(student.getPoint() + inputPoint);
            answer.setPoint(inputPoint);
            System.out.println("done");
        }else {
            System.out.println("you are not the professor");
        }
    }

    public static Question findQuestion(Scanner scanner) {
        Main.currentPractice.printQuestions();
        Question question = Main.currentPractice.getQuestion(scanner.nextInt() - 1);
        scanner.nextLine();
        return question;
    }

    @Override
    public String toString() {
        return "[name: " + name
                + " |explanation: " + explanation
                + " |Start time: " + startTime
                + " |end time: " + endTime + "]";
    }
}
