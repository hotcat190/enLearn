package data.test.reading;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ReadingQuestion {
    private static final int NUM_OF_TEST_DATA = 19;

    private final String paragraph;
    private final String question;
    private final List<String> answers = new ArrayList<>();
    private final String correctAnswer;
    private final String explanation;

    /**
     * Generate a test by reading from a file, of which name ends with the random number.
     */
    public ReadingQuestion() throws FileNotFoundException {
        Random rand = new Random();
        String testFilePath = "src\\main\\resources\\data\\reading\\test" + rand.nextInt(1, NUM_OF_TEST_DATA + 1) + ".xml";
        Scanner s = new Scanner(new File(testFilePath));
        String line = "";
        while (!line.equals("<paragraph>")) {
            line = s.nextLine().trim();
        }
        paragraph = s.nextLine().trim();
        while (!line.equals("<question>")) {
            line = s.nextLine().trim();
        }
        question = s.nextLine().trim();
        while (!line.equals("<answers>")) {
            line = s.nextLine().trim();
        }
        line = s.nextLine().trim();
        while (!line.equals("</answers>")) {
            answers.add(line);
            line = s.nextLine().trim();
        }
        while (!line.equals("<correctAnswer>")) {
            line = s.nextLine().trim();
        }
        correctAnswer = s.nextLine().trim();
        while (!line.equals("<explanation>")) {
            line = s.nextLine().trim();
        }
        explanation = s.nextLine().trim();
        s.close();
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getExplanation() {
        return explanation;
    }
}
