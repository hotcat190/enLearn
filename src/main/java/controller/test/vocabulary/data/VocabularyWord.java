package controller.test.vocabulary.data;

import java.util.*;

public class VocabularyWord {
    private static final int NUM_OF_ANS = 4;

    private final String displayWord;
    private final String correctWord;
    private final List<String> choices; // answers will be in the format "[a-z],[a-z]"
    private final String correctAnswer;

    public VocabularyWord(String word) {
        word = word.toLowerCase().trim();
        correctWord = word;
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
        correctAnswer = c1 + "," + c2;
        // Set displayWord.
        StringBuffer sb = new StringBuffer(word); // Using StringBuffer for thread-synchronization, change if needed.
        sb.setCharAt(i1, '_');
        sb.setCharAt(i2, '_');
        displayWord = sb.toString().replace("_"," _ ");
        // Generate answers list.
        choices = new ArrayList<>(NUM_OF_ANS);
        choices.add(correctAnswer);
        for (int i = 0; i < NUM_OF_ANS - 1; i++) {
            char a1 = (char) (rand.nextInt(26) + 'a');
            char a2 = (char) (rand.nextInt(26) + 'a');
            String answer = a1 + "," + a2;
            if (choices.contains(answer)) {
                // Ensure that newly generated answers will not be the same as added answers.
                i--;
                continue;
            }
            choices.add(answer);
        }
        Collections.shuffle(choices);
    }

    public String getDisplayWord() {
        return displayWord;
    }

    public List<String> getChoices() {
        return choices;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getCorrectWord() {
        return correctWord;
    }
}
