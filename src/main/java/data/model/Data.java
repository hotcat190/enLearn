package data.model;

import sql.SQLData;

public abstract class Data extends SQLData {
    /**
     * Load and update data.
     */
    public abstract void load();

    /**
     * Set data.
     */
    public abstract void set();
}
