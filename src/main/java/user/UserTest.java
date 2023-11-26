package user;

import controller.progress.view.ProgressView;
import sql.user.SQLUser;

import java.util.HashMap;

public class UserTest {
    public static final UserTest INSTANCE = new UserTest();
    private final HashMap<Integer, String> userAnswerFIBTest = new HashMap<>();
    private final HashMap<Integer, String> userAnswerRTest = new HashMap<>();
    private boolean hasTime = true;
    private int fibPoint = 0;
    private int rPoint = 0;
    public int passedFIBTime = 0;
    public int passedRTime = 0;
    private UserTest() {
    }

    public void userAnswerFIBOf(Integer idx, String answer) {
        userAnswerFIBTest.put(idx, answer);
    }

    public void userAnswerROf(Integer idx, String answer) {
        userAnswerRTest.put(idx, answer);
    }

    public HashMap<Integer, String> getUserAnswerFIB() {
        return userAnswerFIBTest;
    }

    public void markFIBTest(HashMap<Integer, String> correctAnswerHashMap) {
        correctAnswerHashMap.forEach((idx, correctAnswer) -> {
            if (userAnswerFIBTest.get(idx) != null && userAnswerFIBTest.get(idx).equals(correctAnswer)) {
                fibPoint += 2;
            }
        });
        SQLUser.setBand(rPoint + fibPoint);
        SQLUser.update();
        ProgressView.getInstance().update();
    }

    public void markRTest(HashMap<Integer, String> correctAnswerHashMap) {
        correctAnswerHashMap.forEach((idx, correctAnswer) -> {
            if (userAnswerRTest.get(idx) != null && userAnswerRTest.get(idx).equals(correctAnswer)) {
                rPoint += 20;
            }
        });
        SQLUser.setBand(rPoint + fibPoint);
        SQLUser.update();
        ProgressView.getInstance().update();
    }

    public int getFIBPoint() {
        return fibPoint;
    }

    public int getRPoint() {
        return rPoint;
    }

    public int getPassedFIBTime() {
        return passedFIBTime;
    }

    public void setPassedFIBTime(int passedFIBTime) {
        this.passedFIBTime = passedFIBTime;
    }

    public int getPassedRTime() {
        return passedRTime;
    }

    public void setPassedRTime(int passedRTime) {
        this.passedRTime = passedRTime;
    }

    public boolean hasTime() {
        return hasTime;
    }

    public void setHasTime(boolean hasTime) {
        this.hasTime = hasTime;
    }

    public static UserTest getInstance() {
        return INSTANCE;
    }
}
