import javax.swing.*;
import java.awt.*;

/**
 * TestProcessorJFrame class creates the main frame for the text reader application.
 * It sets up the window size, position, and layout, and adds the menu bar, north panel, and center panel.
 */
public class TestProcessorJFrame extends JFrame {

    private NorthPanel northPanel;
    private CentrePanel centrePanel;

    public TestProcessorJFrame() {
        // Set the title of the window
        setTitle("Text reader");

        // Initialize the window's size and location
        initializeWindow();

        // Set the window to be resizable
        setResizable(true);


        // Initialize panels only once
        this.northPanel = new NorthPanel();
        this.centrePanel = new CentrePanel();

        // Add components to the content pane
        addComponentsToPane();

        // Set up the menu bar
        setJMenuBar(new MyMenuBar(this, northPanel, centrePanel));
    }

    private void initializeWindow() {
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension dim = tk.getScreenSize();
        // Set the size to 3/4 of the screen size
        setSize(dim.width * 3 / 4, dim.height * 3 / 4);
        // Position the window in the center of the screen
        setLocation(new Point((dim.width - (dim.width * 3 / 4)) / 2, (dim.height - dim.height * 3 / 4) / 2));
    }

    /**
     * Adds the north and center panels to the content pane.
     */
    private void addComponentsToPane() {
        Container contentPane = getContentPane();

        // Set up the North panel
        northPanel.setPreferredSize(new Dimension(northPanel.getPreferredSize().width, 55));

        // Use BorderLayout and add panels to the respective regions
        contentPane.setLayout(new BorderLayout());
        contentPane.add(northPanel, BorderLayout.NORTH);
        contentPane.add(centrePanel, BorderLayout.CENTER);
    }
}
