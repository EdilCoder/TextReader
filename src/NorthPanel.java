import javax.swing.*;
import java.awt.*;

public class NorthPanel extends JPanel {

    // Define constants for reusability
    private static final Color BACKGROUND_COLOR = Color.white;
    private static final Color TEXT_COLOR = Color.black;
    private static final Font TITLE_FONT = new Font("Papyrus", Font.BOLD, 22);
    private static final String TEXT = "TextReader v1.0  ";

    public NorthPanel() {
        // Set background color in the constructor
        setBackground(BACKGROUND_COLOR);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D gTitle = (Graphics2D) g;

        // Enable anti-aliasing for smoother text
        gTitle.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON
        );

        // Set text properties and color
        gTitle.setFont(TITLE_FONT);
        gTitle.setPaint(TEXT_COLOR);
        gTitle.setStroke(new BasicStroke(5));

        // Calculate the position to center the text
        float x = calculateTextXPosition(gTitle, TEXT);
        float y = calculateTextYPosition(gTitle);

        // Draw the text
        gTitle.drawString(TEXT, x, y);
    }

    /**
     * Calculates the X position to center the text horizontally.
     *
     * @param gTitle Graphics2D object for drawing
     * @param text   The text to draw
     * @return The X coordinate for centered text
     */
    private float calculateTextXPosition(Graphics2D gTitle, String text) {
        FontMetrics metrics = gTitle.getFontMetrics(TITLE_FONT);
        float width = getWidth();
        return (width - metrics.stringWidth(text));
    }

    /**
     * Calculates the Y position to center the text vertically.
     *
     * @param gTitle Graphics2D object for drawing
     * @return The Y coordinate for centered text
     */
    private float calculateTextYPosition(Graphics2D gTitle) {
        FontMetrics metrics = gTitle.getFontMetrics(TITLE_FONT);
        float height = getHeight();
        return ((height - metrics.getHeight()) / 2) + metrics.getAscent();
    }
}
