package ir.ac.kntu;

import java.time.LocalDateTime;

public class Test {

    public static void set() {
        User user = new Admin("negin", "nm1", "negin@yahoo.com","346", "3048679912", "09124563321");
        User user1 = new Customer("ali", "ali123", "ali81@yahoo.com","109", "3051246374", "09134567856");
        User user2 = new Customer("fatemeh", "fatemeh1", "fatemeHosseini@yahoo.com","567", "3042342233", "09134562219");
        User user3 = new Customer("razie", "razi", "rzyh@gmail.com","3467", "3046577890", "09134548863");
        User user4 = new Customer("mohammad", "m.s", "mohammad@gmail.com","1236", "3056754433", "09120982245");
        User user5 = new Customer("narges","nargess","nargesMohammadi@yahoo.com","56879","3049876657","09395462298");
        Main.users.add(user);
        Main.users.add(user1);
        Main.users.add(user2);
        Main.users.add(user3);
        Main.users.add(user4);
        Main.users.add(user5);
        Classroom class1 = new Classroom("math", "khaje nasir", user1,1399, true, "study hard!", false);
        Classroom class2 = new Classroom("physics", "stanford", user2,1400, false, "we will do our best", true);
        class2.setPassword("101010");
        class1.addStudent(user3);
        class1.addStudent(user4);
        class2.addStudent(user5);
        class2.addStudent(user1);
        class2.addStudent(user4);
        Practice practice1 = new Practice("number1", "it has one question",
                LocalDateTime.of(2022,5,9,22,5,0),
                LocalDateTime.of(2022,6,9,23,30,0),
                LocalDateTime.of(2022,6,10,23,45,0),
                false, 0.8);
        practice1.getScoreboard().setVisible(true);
        Question question1 = new Question("toy factory", 100,"calculate cost of toys",
                Question.Level.EASY, Question.QuestionType.TEST);
        practice1.addQuestion(question1);
        class1.addPractice(practice1);
        Main.classes.add(class1);
        Main.classes.add(class2);
        Question question2 = new Question("taxi", 150,"you should mange an online taxi company",
                Question.Level.MEDIUM, Question.QuestionType.BLANK_SPACE);
        Question question3 = new Question("taxi2", 150,"you should mange an online taxi company",
                Question.Level.EASY, Question.QuestionType.BLANK_SPACE);
        Main.bank.addQuestion(question2);
        Main.bank.addQuestion(question3);
        createSomeCompetition(user);
    }

    public static void createSomeCompetition(User admin) {
        Admin admin1 = new Admin("melika", "meli", "melika132@gmail.com", "39845", "305674819", "09130463456");
        User user1 = new Customer("maryam", "mari", "maryam@gmail.com","543", "3046546833", "09124563387");
        User user2 = new Customer("negar", "n009", "negar@gmail.com","465", "3468909221", "09216780094");
        User user3 = new Customer("tarannom","tata","tarannom13@yahoo.com","6557","304675881","09145672209");
        Main.users.add(admin1);
        Main.users.add(user1);
        Main.users.add(user2);
        Main.users.add(user3);
        SpecialCompetition competition1 = new SpecialCompetition("fariborz",
                LocalDateTime.of(2022,5,9,22,5,0),
                LocalDateTime.of(2022,5,9,22,5,0),
                10, (Admin) admin);
        OrdinaryCompetition competition2 = new OrdinaryCompetition("ball game",
                LocalDateTime.of(2022,5,9,22,5,0),
                LocalDateTime.of(2022,7,9,22,5,0), admin1);
        PrivateCompetition competition3 = new PrivateCompetition("BSTree",
                LocalDateTime.of(2022,5,9,22,5,0),
                LocalDateTime.of(2022,7,9,22,5,0), admin1);
        Question question = new Question("recursive shapes", 100,"you have to create a recursive shape",
                Question.Level.MEDIUM, Question.QuestionType.LONG_ANSWER);
        Answer answer = new Answer("mari", question, LocalDateTime.now(), "answer");
        competition2.addMember((Customer) user1);
        competition2.addMember((Customer) user2);
        competition2.addMember((Customer) user3);
        competition2.addQuestion(question);
        competition2.addAnswer(answer);
        Main.competitions.add(competition1);
        Main.competitions.add(competition2);
        Main.competitions.add(competition3);
    }
}
