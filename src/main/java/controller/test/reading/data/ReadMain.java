package controller.test.reading.data;

import java.io.FileNotFoundException;

public class ReadMain {
    public static void main(String[] args) throws FileNotFoundException {
        ReadingQuestion readingQuestion = new ReadingQuestion();
        System.out.println(readingQuestion.getParagraph());
        System.out.println(readingQuestion.getQuestion());
        System.out.println(readingQuestion.getAnswers());
        System.out.println(readingQuestion.getCorrectAnswer());
        System.out.println(readingQuestion.getExplanation());
    }
}
