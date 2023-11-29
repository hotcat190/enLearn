package app.dashboard.test;

import controller.test.controller.TestController;
import app.dashboard.model.Dashboard;
import graphics.style.StyleHelper;

import java.sql.SQLException;

public class TestDashboard extends Dashboard {
    private final static TestDashboard TEST_DASHBOARD = new TestDashboard();
    private final TestController testController = TestController.getInstance();
    public TestDashboard() {
    }

    /**
     * Set listener with other object.
     */
    @Override
    public void setListener(Object object) {

    }

    /**
     * Set listener all.
     */
    @Override
    public void setListener() {

    }

    @Override
    public void setTitle() {

    }

    /**
     * Set id.
     */
    @Override
    public void setId() {

    }

    /**
     * Set CSS if this class is parent. Else CSS root is its parent.
     */
    @Override
    public void setCSS() {
        this.getStylesheets().add(StyleHelper.getStyleSheet(this, "/css/style_for_test_dashboard.css"));
    }

    /**
     * Set layout.
     */
    @Override
    public void set() {
        this.getChildren().add(testController);

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

    public static TestDashboard getInstance() {
        return TEST_DASHBOARD;
    }

    @Override
    public void load() {
        setId();
        setCSS();
        set();
    }
}
