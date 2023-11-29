package controller.test.reading.data;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class ReadingTestData {
    private final int AMOUNT;
    private final HashMap<ReadingQuestion, Integer> questionHashMap = new HashMap<>();

    public ReadingTestData(int amount) {
        AMOUNT = amount;
        for (int i = 0; i < AMOUNT; i++) {
            try {
                questionHashMap.put(new ReadingQuestion(), i+1);
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public HashMap<ReadingQuestion, Integer> getTest() {
        return questionHashMap;
    }

    public int getAmount() {
        return AMOUNT;
    }
}
