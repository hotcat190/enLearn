package user;

public class UserSkill {
    private static String skillPoint = "*";

    public static String getSkillPoint() {
        final int rPoint = UserTest.getInstance().getRPoint();
        final int fibPoint = UserTest.getInstance().getFIBPoint();

        final int timePassedR = UserTest.INSTANCE.getPassedRTime(); /* Time unit is seconds.*/
        final int timePassedFIB = UserTest.getInstance().getPassedFIBTime(); /* Time unit is seconds.*/

        int readingAverage=0;
        int vocabularyAverage=0;
        double average;

        /*
            Calculate vocabulary point - max 10.
         */
        if (fibPoint == 0) {
            vocabularyAverage = 0;
        } else if (fibPoint <= 10) {
            vocabularyAverage = 3;
        } else if (fibPoint <= 20) {
            if (timePassedFIB <= 240) {
                vocabularyAverage = 5;
            } else {
                vocabularyAverage = 4;
            }
        } else if (fibPoint <= 30) {
            if (timePassedFIB <= 240) {
                vocabularyAverage = 7;
            } else {
                vocabularyAverage = 6;
            }
        } else if (fibPoint <= 40) {
            if (timePassedFIB <= 180) {
                vocabularyAverage = 10;
            } else if (timePassedFIB <= 240) {
                vocabularyAverage = 9;
            } else {
                vocabularyAverage = 8;
            }
        }

        /*
            Calculate reading point - max 10.
         */
        if (rPoint == 0) {
            readingAverage = 0;
        } else if (rPoint == 20) {
            if (timePassedR <= 240) {
                readingAverage = 6;
            } else {
                readingAverage = 5;
            }
        } else if (rPoint == 40) {
            if (timePassedR <= 240) {
                readingAverage = 8;
            } else {
                readingAverage = 7;
            }
        } else if(rPoint==60) {
            if (timePassedFIB <= 240) {
                readingAverage = 10;
            } else {
                readingAverage = 9;
            }
        }

        average = (double) (vocabularyAverage + readingAverage * 2) / 3;
        if (average < 3) {
            return "C";
        }
        if (average < 4) {
            return "C+";
        }
        if (average < 5) {
            return "B";
        }
        if (average < 6) {
            return "B+";
        }
        if (average < 7) {
            return "A";
        }
        return "A+";
    }
}
