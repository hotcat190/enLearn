package controller.test.view.clock;

import graphics.style.Decorator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.text.Text;
import javafx.util.Duration;
import user.UserTest;

import java.sql.SQLException;

public class CountDownClock extends Text implements Decorator {
//    private static final CountDownClock INSTANCE = new CountDownClock();
    /**
     * Components.
     */
    private int initMinutes;
    private int minutes;
    private int seconds;
    private boolean isTimeUp = false;

    public CountDownClock() {
        setId();
        setCSS();
        set();
    }

    /**
     * Set id.
     */
    @Override
    public void setId() {
        this.setId("count-down-clock__text");
    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {

    }

    /**
     * Set layout.
     */
    @Override
    public void set() {

    }

    /**
     * Update layout.
     */
    @Override
    public void update() throws SQLException {

    }

    @Override
    public String getLink() {
        return null;
    }

    public void playFrom(int m, int s) {
        this.minutes = m;
        this.seconds = s;
        this.initMinutes = m;
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.seconds(1),
                        event -> {
                            updateCountdown();
                            this.setText(formatTime(minutes, seconds));
                        }
                )
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public boolean isTimeUp() {
        return isTimeUp;
    }

    private void updateCountdown() {
        if (minutes > 0 || seconds > 0) {
            if (seconds == 0) {
                minutes--;
                seconds = 59;
            } else {
                seconds--;
            }
        }
        if (seconds == 0 && minutes == 0) {
            isTimeUp = true;
            UserTest.getInstance().setHasTime(false);
        }
    }

    private String formatTime(int minutes, int seconds) {
        if (isTimeUp) {
            return "Time up!";
        }
        return String.format("%02d : %02d", minutes, seconds);
    }

    public String getTimePassed() {
        int passedMinutes = initMinutes - minutes-1;
        int passedSeconds = 60 - seconds;
        return String.format("%dm %ds", passedMinutes, passedSeconds);
    }

    public int getTotalTimePassed() {
        int passedMinutes = initMinutes - minutes-1;
        int passedSeconds = 60 - seconds;
        return passedMinutes * 60 + passedSeconds;
    }

}
