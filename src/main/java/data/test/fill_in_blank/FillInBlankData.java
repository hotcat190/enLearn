package data.test.fill_in_blank;

import dictionary.Dictionary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FillInBlankData {
    private final int AMOUNT;
    private final List<FillInBlankWord> fillInBlankWords = new ArrayList<>();
    private final List<String> dictionary = Dictionary.getDictionary();

    public FillInBlankData(int amount) {
        AMOUNT = amount;
        Random random = new Random();
        for (int i = 0; i < AMOUNT; i++) {
            String s = dictionary.get(random.nextInt(dictionary.size()));
            System.out.println(s);
            FillInBlankWord fillInBlankWord = new FillInBlankWord(s);
            fillInBlankWords.add(fillInBlankWord);
        }
    }

    public List<FillInBlankWord> getTest() {
        return fillInBlankWords;
    }
}
