package ir.ac.kntu;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Answer {

    private String senderUserName;

    private Question question;

    private LocalDateTime sendingTime;

    private double delayFactor;

    private double point = 0;

    private double pointWithDelay = 0;

    private boolean isFinal;

    private String answer;

    public Answer(String senderUserName, Question question, LocalDateTime sendingTime, double delayFactor, String answer) {
        this.senderUserName = senderUserName;
        this.question = question;
        this.sendingTime = sendingTime;
        this.delayFactor = delayFactor;
        this.answer = answer;
        isFinal = true;
    }

    public Answer(String senderUserName, Question question, LocalDateTime sendingTime, String answer) {
        this.senderUserName = senderUserName;
        this.question = question;
        this.sendingTime = sendingTime;
        this.answer = answer;
    }

    public String getSenderUserName() {
        return senderUserName;
    }

    public Question getQuestion() {
        return question;
    }

    public LocalDateTime getSendingTime() {
        return sendingTime;
    }

    public double getDelayFactor() {
        return delayFactor;
    }

    public double getPoint() {
        return point;
    }

    public double getPointWithDelay() {
        return pointWithDelay;
    }

    public boolean isFinal() {
        return isFinal;
    }

    public String getAnswer() {
        return answer;
    }

    public void setSenderUserName(String senderUserName) {
        this.senderUserName = senderUserName;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public void setSendingTime(LocalDateTime sendingTime) {
        this.sendingTime = sendingTime;
    }

    public void setDelayFactor(double delayFactor) {
        this.delayFactor = delayFactor;
    }

    public void setPoint(double point) {
        this.point = point;
        setPointWithDelay(calculatePointWithDelay(LocalDateTime.now(), point));
    }

    public void setPointWithDelay(double pointWithDelay) {
        this.pointWithDelay = pointWithDelay;
    }

    public void setFinal(boolean aFinal) {
        isFinal = aFinal;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static Answer createAnswerForPractice(Scanner scanner) {
        String senderUserName = Main.currentUser.getUserName();
        Question chosenQuestion = scanPracticeQuestion(scanner);
        LocalDateTime sendingTime = LocalDateTime.now();
        double delayFactor = Main.currentPractice.getDelayFactor();
        String inputAnswer = scanAnswer(scanner);
        return new Answer(senderUserName, chosenQuestion, sendingTime, delayFactor, inputAnswer);
    }

    public static Question scanPracticeQuestion(Scanner scanner) {
        Main.currentPractice.printQuestions();
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println("question: " + Main.currentPractice.getQuestion(index));
        return Main.currentPractice.getQuestion(index);
    }

    public static double calculatePointWithDelay(LocalDateTime sendingTime, double point) {
        double delayFactor = Main.currentPractice.getDelayFactor();
        LocalDateTime endTime = Main.currentPractice.getEndTime();
        if(sendingTime.compareTo(endTime) >= 0) {
            return (point * delayFactor);
        }
        return point;
    }

    public static String scanAnswer(Scanner scanner) {
        System.out.println("please enter your answer:");
        return scanner.nextLine();
    }

    public static void seeAnswers() {
        if(Main.currentClass == null) {
            System.out.println("please enter a class first");
        }else if(Main.currentPractice == null) {
            System.out.println("please open a practice first");
        }else if(Main.currentUser == null){
            System.out.println("please log in first");
        }else if(Main.currentClass.containsStudent(Main.currentUser)) {
            Main.currentPractice.printAnswers(Main.currentUser.getUserName(), false);
        }else {
            System.out.println("you are not a student of this class");
        }
    }

    public static void seeFinalAnswers() {
        if(Main.currentClass == null) {
            System.out.println("please enter a class first");
        }else if(Main.currentPractice == null) {
            System.out.println("please open a practice first");
        }else if(Main.currentUser == null){
            System.out.println("please log in first");
        }else if(Main.currentClass.containsStudent(Main.currentUser)) {
            Main.currentPractice.printAnswers(Main.currentUser.getUserName(), true);
        }else {
            System.out.println("you are not a student of this class");
        }
    }

    public static void seeAndMarkAnswers(Scanner scanner) {
        if(Main.currentClass == null) {
            System.out.println("please enter a class first");
        }else if(Main.currentPractice == null) {
            System.out.println("please open a practice first");
        }else if(Main.currentUser == null){
            System.out.println("please log in first");
        }else if(Main.currentClass.getProfessor().equals(Main.currentUser)) {
            Main.currentPractice.markAnswers(scanner);
        }else {
            System.out.println("you are not a student of this class");
        }
    }

    public static Answer createAnswerForCompetition(Scanner scanner) {
        String senderUserName = Main.currentUser.getUserName();
        Question chosenQuestion = scanCompetitionQuestion(scanner);
        LocalDateTime sendingTime = LocalDateTime.now();
        String inputAnswer = scanAnswer(scanner);
        return new Answer(senderUserName, chosenQuestion, sendingTime, 1, inputAnswer);
    }

    public static Question scanCompetitionQuestion(Scanner scanner) {
        Main.currentCompetition.printQuestions();
        int index = scanner.nextInt() - 1;
        scanner.nextLine();
        System.out.println("question: " + Main.currentCompetition.getQuestion(index));
        return Main.currentCompetition.getQuestion(index);
    }

    @Override
    public String toString() {
        return "user name: " + senderUserName + "\n"
                + "question: " + question.getName() + "\n"
                + "sending time: " + sendingTime + "\n"
                + "delay factor: " + delayFactor + "\n"
                + "point: " + point + "\n"
                + "point with delay: " + pointWithDelay + "\n"
                + "is final: " + isFinal + "\n"
                + "answer: " + answer;
    }
}
