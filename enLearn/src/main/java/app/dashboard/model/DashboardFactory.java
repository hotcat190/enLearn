package app.dashboard.model;

import app.dashboard.game.GameDashboard;
import app.dashboard.home.HomeDashboard;
import app.dashboard.my_dictionary.MyDictionaryDashboard;
import app.dashboard.test.TestDashboard;
import app.dashboard.translator.TranslatorDashboard;

public class DashboardFactory {
    private DashboardFactory() {
    }

    public static Dashboard createDashboard(DashboardType type) {
        switch (type) {
            case HOME_DASHBOARD -> {
                return new HomeDashboard();
            }
            case TRANSLATE_DASHBOARD -> {
                return new TranslatorDashboard();
            }
            case MY_DICTIONARY_DASHBOARD -> {
                return new MyDictionaryDashboard();
            }
            case TEST_DASHBOARD -> {
                return new TestDashboard();
            }
            case GAME_DASHBOARD -> {
                return new GameDashboard();
            }
            default -> throw new IllegalStateException("Unexpected value: " + type);
        }
    }
}
