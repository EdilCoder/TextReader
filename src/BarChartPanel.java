import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class BarChartPanel extends JPanel {

    private Map<Integer, Integer> lengthDistribution;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (lengthDistribution == null || lengthDistribution.isEmpty()) {
            g.drawString("No data available to display.", getWidth() / 2 - 50, getHeight() / 2);
            return;
        }

        int width = getWidth();
        int height = getHeight();
        int padding = 50;
        int chartWidth = width - 2 * padding;
        int chartHeight = height - 2 * padding;

        // Draw axes
        g.drawLine(padding, padding, padding, height - padding); // Y axis
        g.drawLine(padding, height - padding, width - padding, height - padding); // X axis

        int maxBarHeight = chartHeight;
        int maxCount = lengthDistribution.values().stream().max(Integer::compare).orElse(1);
        int barWidth = chartWidth / lengthDistribution.size();

        int x = padding + 10;

        g.setFont(new Font("Arial", Font.BOLD, 12));
        g.drawString("Linked List Length Distribution", width / 2 - 50, padding - 20);

        double sum = 0;
        int countSum = 0;

        // Draw bars and calculate the sum for standard deviation
        for (Map.Entry<Integer, Integer> entry : lengthDistribution.entrySet()) {
            int length = entry.getKey();
            int count = entry.getValue();
            int barHeight = (int) (((double) count / maxCount) * maxBarHeight);

            g.setColor(new Color(100, 149, 237)); // Cornflower Blue for bars
            g.fillRect(x, height - padding - barHeight, barWidth - 10, barHeight);

            g.setColor(Color.BLACK);
            g.drawString(String.valueOf(length), x + (barWidth - 10) / 2 - 5, height - padding + 20);
            g.drawString(String.valueOf(count), x + (barWidth - 10) / 2 - 5, height - padding - barHeight - 10);

            x += barWidth;

            // Sum for standard deviation calculation
            sum += length * count;
            countSum += count;
        }

        // Calculate standard deviation
        double mean = sum / countSum;
        double sumOfSquare = 0;
        for (Map.Entry<Integer, Integer> entry : lengthDistribution.entrySet()) {
            int length = entry.getKey();
            int count = entry.getValue();
            sumOfSquare += Math.pow(length - mean, 2) * count;
        }
        double standardDeviation = Math.sqrt(sumOfSquare / countSum);
        String standardDeviationStr = String.format("Standard Deviation: %.2f", standardDeviation);

        // Display standard deviation
        g.setColor(Color.BLUE);
        g.drawString(standardDeviationStr, padding, padding - 5);

        // Label axes
        g.drawString("Linked List Length", width / 2 - 50, height - 10);
        g.drawString("Count", 10, height / 2);
    }

    public void setLengthDistribution(Map<Integer, Integer> distribution) {
        this.lengthDistribution = distribution;
        repaint();
    }
}
