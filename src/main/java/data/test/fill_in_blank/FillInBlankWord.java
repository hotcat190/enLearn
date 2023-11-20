package data.test.fill_in_blank;

import java.util.*;

public class FillInBlankWord {
    private static final int NUM_OF_ANS = 4;

    private final StringBuffer displayWord;
    private final StringBuffer correctWord;
    private final List<StringBuffer> choices; // answers will be in the format "[a-z],[a-z]"
    private final StringBuffer correctAnswer;

    public FillInBlankWord(String word) {
        word = word.toLowerCase();
        correctWord=new StringBuffer(word);
        // Perform random generation.
        Random rand = new Random();
        int r1 = rand.nextInt(word.length());
        int r2 = rand.nextInt(word.length());
        while (r2 == r1) r2 = rand.nextInt(word.length()); // Ensure that r2 != r1.
        // Ensure that i1 < i2.
        int i1 = Math.min(r1, r2);
        int i2 = Math.max(r1, r2);
        char c1 = word.charAt(i1);
        char c2 = word.charAt(i2);
        // Set correct answer.
        correctAnswer = new StringBuffer().append(c1).append(",").append(c2);
        // Set displayWord.
        StringBuffer sb = new StringBuffer(word); // Using StringBuffer for thread-synchronization, change if needed.
        sb.setCharAt(i1, '_');
        sb.setCharAt(i2, '_');
        displayWord = sb;
        // Generate answers list.
        choices = new ArrayList<>(NUM_OF_ANS);
        choices.add(correctAnswer);
        for (int i = 0; i < NUM_OF_ANS - 1; i++) {
            char a1 = (char) (rand.nextInt(26) + 'a');
            char a2 = (char) (rand.nextInt(26) + 'a');
            StringBuffer answer = new StringBuffer().append(a1).append(",").append(a2);
            if (choices.contains(answer)) {
                // Ensure that newly generated answers will not be the same as added answers.
                i--;
                continue;
            }
            choices.add(answer);
        }
        Collections.shuffle(choices);
    }

    public StringBuffer getDisplayWord() {
        return displayWord;
    }

    public List<StringBuffer> getChoices() {
        return choices;
    }

    public StringBuffer getCorrectAnswer() {
        return correctAnswer;
    }

    public StringBuffer getCorrectWord() {
        return correctWord;
    }
}
