package controller.test.vocabulary.data;

import sql.dictionary.SQLDictionary;
import sql.dictionary.SQLMyDictionary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class VocabularyTestData {
    private final int AMOUNT;
    private final List<VocabularyWord> vocabularyWords = new ArrayList<>();
    private final List<String> dictionary = SQLDictionary.getInstance().getDictionary();
    private final List<String> myDictionary = SQLMyDictionary.getInstance().getMyWords();

    public VocabularyTestData(int amount) {
        AMOUNT = amount;
        Random random = new Random();
        Collections.shuffle(myDictionary);
        for (String myWord : myDictionary) {
            if (myWord.length()>8) continue;
            VocabularyWord vocabularyWord = new VocabularyWord(myWord);
            vocabularyWords.add(vocabularyWord);
        }
        while(vocabularyWords.size()<AMOUNT) {
            String word = dictionary.get(random.nextInt(dictionary.size()));
            while (word.length()>=8) {
                word = dictionary.get(random.nextInt(dictionary.size()));
            }
            VocabularyWord vocabularyWord = new VocabularyWord(word);
            vocabularyWords.add(vocabularyWord);
        }
    }

    public List<VocabularyWord> getTest() {
        return vocabularyWords;
    }

    public int getAmount() {
        return AMOUNT;
    }
}
