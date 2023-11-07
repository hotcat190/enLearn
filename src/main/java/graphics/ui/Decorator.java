package graphics.ui;

import java.sql.SQLException;

public interface Decorator {
    void setID();

    void setCSS();

    void setUI();

    void updateUI() throws SQLException;

    String getLink();

}
