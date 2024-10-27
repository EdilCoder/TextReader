import javax.swing.*;

/**
 * This is the main entry point for the MyLanguageModel application.
 * It creates a JFrame for the TestProcessor GUI.
 */
public class MyLanguageModel {
    public static void main(String[] args) {
        initUI();
    }

    /**
     * Initializes and displays the main JFrame for the application.
     */
    private static void initUI() {
        JFrame jfrm = new TestProcessorJFrame();
        jfrm.setVisible(true);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
