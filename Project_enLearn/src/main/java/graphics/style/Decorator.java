package graphics.style;

import java.sql.SQLException;

public interface Decorator {
    /**
     * Set id.
     */
    void setId();

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    void setCSS();

    /**
     * Set layout.
     */
    void set();

    /**
     * Update layout.
     */
    void update() throws SQLException;

    String getLink();

}
