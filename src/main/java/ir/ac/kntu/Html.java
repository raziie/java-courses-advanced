package ir.ac.kntu;

import java.io.*;

public class Html {

    public static void write(Scoreboard scoreBoard) throws FileNotFoundException, IOException{
        File file = new File("scoreBoard.html");
        try (BufferedWriter bufferedReader = new BufferedWriter(new FileWriter(file))) {
            bufferedReader.write(getTable(scoreBoard));
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static String getTable(Scoreboard scoreBoard) {
        String result = "<html>" +
                "<head>" +
                "<title>ScoreBoard</title>" +
                "</head>" +
                "<body>" +
                "<h1>Board</h1>" +
                "<tr>" +
                "User        |" +
                Main.currentPractice.getQuestionsWithoutEnter() +
                "total" +
                "<br>" +
                scoreBoard.getUsers().replace("\n", "<br>") +
                "</tr></table></body></html>";
        return result;
    }
}
