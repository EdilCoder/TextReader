import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

public class CentrePanel extends JPanel implements ActionListener {

    private static final Insets insets = new Insets(10,10,10,10);

    private JTextArea filePathText;
    private JButton importButton;
    private JButton extractButton;
    private JTextArea textArea;
    private JComboBox comboBox;

    DefaultTableModel model = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;  // Set all cells to be non-editable
        }
    };

    private JTable wordTable;
    private JButton barChartButton;
    private JButton exportButton;
    private BarChartPanel barChartPanel;
    private JTextField wordCountText;
    private JTextField wordWithoutSameT;

    private Box boxH;
    private Box boxVOne, boxVTwo, boxVThree, boxVFour;

    private JTextArea checkWord;
    private JTextArea CheckedtextArea;
    private Box boxV;
    private Box boxHOne, boxHTwo;

    private JButton checkedButton;

    private MyHashTable myHashTable;  // Global variable
    private int selectedNgramOption;

    private Map<Integer, Integer> distribution; // Global variable to store the linked list length distribution

    public CentrePanel(){

        setLayout(new GridBagLayout());

        // Setting the page layout
        filePathText = new JTextArea(1,20);
        filePathText.setBackground(Color.WHITE);
        filePathText.setEditable(false);
        addComponent(filePathText, 0, 0, 1, 1, 0,0,GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        importButton = new JButton("Import");
        addComponent(importButton, 1, 0, 1, 1, 0,0,GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE);
        importButton.addActionListener(this);

        textArea = new JTextArea(25,30);
        textArea.setBackground(Color.WHITE);
        textArea.setEditable(false);
        JScrollPane scrollPane1 = new JScrollPane(textArea);
        scrollPane1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addComponent(scrollPane1, 0, 1, 2, 2, 0.5,0.5,GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // ================================================================

        boxH = Box.createHorizontalBox();
        boxVOne = Box.createVerticalBox();
        boxVTwo = Box.createVerticalBox();
        boxVOne.add(new JLabel("Unique num of words: "));
        boxVOne.add(new JLabel("Total num of words: "));
        wordCountText = new JTextField(5);
        wordWithoutSameT = new JTextField(5);
        wordCountText.setEditable(false);
        wordCountText.setPreferredSize(new Dimension(50, 20));
        wordCountText.setMinimumSize(new Dimension(50, 20));
        wordWithoutSameT.setEditable(false);
        wordWithoutSameT.setPreferredSize(new Dimension(50, 20));
        wordWithoutSameT.setMinimumSize(new Dimension(50, 20));
        boxVTwo.add(wordWithoutSameT);
        boxVTwo.add(wordCountText);
        boxH.add(boxVOne);
        boxH.add(Box.createHorizontalStrut(10));
        boxH.add(boxVTwo);
        addComponent(boxH, 0, 3, 1, 1, 0,0,GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE);

        // ===================================================================
        extractButton = new JButton("Extract");
        addComponent(extractButton, 1, 3, 1, 1, 0,0,GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE);
        extractButton.addActionListener(this);

        JLabel filterLabel = new JLabel("Sorted: ");
        comboBox = new JComboBox();
        Dimension preferredSize = new Dimension(240, comboBox.getPreferredSize().height);
        comboBox.setMaximumSize(preferredSize);
        comboBox.setPreferredSize(preferredSize);

        comboBox.addItem("By word Frequency");
        comboBox.addItem("Alphabetical order (Predictive)");
        comboBox.addItem("Descending order of occurrence (Predictive)");
        boxH = Box.createHorizontalBox();
        boxVOne = Box.createVerticalBox();
        boxVTwo = Box.createVerticalBox();
        boxVOne.add(filterLabel);
        boxVTwo.add(comboBox);
        boxH.add(boxVOne);
        boxH.add(boxVTwo);
        boxH.add(Box.createHorizontalStrut(5));
        addComponent(boxH, 2, 0, 1, 1, 0,0,GridBagConstraints.SOUTH, GridBagConstraints.BOTH);

        model.addColumn("No.");
        model.addColumn("Word");
        model.addColumn("Frequency");
        wordTable = new JTable(model);
        wordTable.setPreferredScrollableViewportSize(new Dimension(50,200));
        JScrollPane scrollPane2 = new JScrollPane(wordTable);
        scrollPane2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addComponent(scrollPane2, 2, 1, 1, 2, 0.5,0.5,GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        exportButton = new JButton("Export");
        addComponent(exportButton, 2, 3, 1, 1, 1,0,GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE);
        exportButton.addActionListener(this);

        barChartButton = new JButton("Bar chart");
        addComponent(barChartButton, 2, 3, 1, 1, 1,0,GridBagConstraints.SOUTHWEST, GridBagConstraints.NONE);
        barChartButton.addActionListener(this);

        // ================================================================
        // N-gram Language Prediction Title
        JLabel nGramTitle = new JLabel("N-gram Language Prediction", SwingConstants.CENTER);
        nGramTitle.setFont(new Font("Arial", Font.BOLD, 16));

        // Create a new box to contain the title
        boxV = Box.createVerticalBox();
        boxV.add(nGramTitle);
        boxV.add(Box.createVerticalStrut(10));

        // Add nGramTitle to the layout at (4, 0)
        addComponent(boxV, 4, 0, 1, 1, 0, 0, GridBagConstraints.CENTER, GridBagConstraints.BOTH);

        // ==========================================================
        JLabel CheckLabel = new JLabel("Checked: ");
        checkWord = new JTextArea(1,1);
        boxH = Box.createHorizontalBox();

        boxVThree = Box.createVerticalBox();
        boxVFour = Box.createVerticalBox();

        boxVThree.add(CheckLabel);
        boxVFour.add(checkWord);
        boxH.add(boxVThree);
        boxH.add(boxVFour);
        boxH.add(Box.createHorizontalStrut(5));
        addComponent(boxH, 4, 1, 1, 1, 0,0,GridBagConstraints.SOUTH, GridBagConstraints.BOTH);

        JLabel TitleLabel = new JLabel("Most Useful word: ");
        CheckedtextArea = new JTextArea(10,30);
        JScrollPane scrollPane3 = new JScrollPane(CheckedtextArea);
        scrollPane3.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        boxV = Box.createVerticalBox();
        boxHOne = Box.createHorizontalBox();
        boxHTwo = Box.createHorizontalBox();
        boxHOne.add(TitleLabel);
        boxHTwo.add(scrollPane3);
        boxV.add(boxHOne);
        boxV.add(boxHTwo);
        boxV.add(Box.createVerticalStrut(5));
        addComponent(boxV, 4, 2, 1, 1, 0,0,GridBagConstraints.SOUTH, GridBagConstraints.BOTH);

        checkedButton = new JButton("Checked");
        addComponent(checkedButton, 4, 3, 1, 1, 1,0,GridBagConstraints.SOUTHEAST, GridBagConstraints.NONE);
        checkedButton.addActionListener(this);
    }

    // Setting Component Properties
    private void addComponent(Component component, int gridx, int gridy, int gridwidth, int gridheight, double weightx, double weighty, int anchor, int fill) {
        GridBagConstraints gbc = new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, 0, 0);
        add(component, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == importButton) {

            JFileChooser fileChooser = new JFileChooser();
            int returnValue = fileChooser.showOpenDialog(null);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                // Clear previous content
                textArea.setText(""); // Clear the text area

                // Use HashSet to store unique words
                HashSet<String> uniqueWords = new HashSet<>();
                ArrayList<String> arrayList = new ArrayList<>();

                try (BufferedReader inputFile = new BufferedReader(new FileReader(fileChooser.getSelectedFile().getPath()))) {
                    String line;

                    while ((line = inputFile.readLine()) != null) {
                        textArea.append(line + "\n");

                        line = line.replaceAll("[^a-zA-Z0-9'\\s]", "");
                        String[] str = line.split("\\s+");

                        for (String s : str) {
                            s = s.toLowerCase(); // Convert to lowercase to normalize
                            if (!s.trim().isEmpty()) {  // Ignore empty strings
                                arrayList.add(s);
                                uniqueWords.add(s);
                            }
                        }
                    }
                } catch (IOException exo) {
                    exo.printStackTrace();
                }

                // Set the read folder path to filePath
                String filePath = fileChooser.getSelectedFile().getPath();
                filePathText.setText(filePath);

                // Update the totalWordField with the count of unique words
                wordWithoutSameT.setText(String.valueOf(uniqueWords.size()));
                System.out.println("Unique words: " + wordWithoutSameT.getText());

                // Update the totalWordCountField with the count of all words
                int totalWordCount = arrayList.size();
                wordCountText.setText(String.valueOf(totalWordCount));
                System.out.println("Total words: " + wordCountText.getText());
            }
        }

        if (e.getSource() == extractButton) {

            // Check if the text box is empty
            if (textArea.getText().trim().isEmpty()) {
                // Pop-up warning dialogues
                JOptionPane.showMessageDialog(null, "The text box cannot be empty!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Clear the previous hash table object to prevent reference cycles or other issues
            myHashTable = null;  // Clear the previous object to ensure no residual reference of old data

            // The list of hash functions to test
            String[] hashFunctions = {"DivisionMethodHash", "DivisionMethodHash2", "MultiplicationMethodHash", "UniversalHash"};
            String[] nGramOptions = {"Bigram (2-gram)", "Trigram (3-gram)", "Four-gram (4-gram)", "Five-gram (5-gram)"};

            int totalWords = 0;

            // Prompt selection dialog, first select the hash function
            int selectedHashOption = JOptionPane.showOptionDialog(null, "Please select your hash function:", "Hash Function Options",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, hashFunctions, hashFunctions[0]);

            // Prompt selection dialog, select the n-gram model
            selectedNgramOption = JOptionPane.showOptionDialog(null, "Please select your n-gram model:", "N-gram Options",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, nGramOptions, nGramOptions[0]);

            // Size of the array
            int size = 40;

            switch (selectedHashOption) {
                case 0:
                    myHashTable = new MyHashTable(size, new DivisionMethodHash(size));
                    break;
                case 1:
                    myHashTable = new MyHashTable(size, new DivisionMethodHash2(size));
                    break;
                case 2:
                    myHashTable = new MyHashTable(size, new MultiplicationMethodHash(size));
                    break;
                case 3:
                    myHashTable = new MyHashTable(size, new UniversalHash(size));
                    break;
                default:
                    throw new RuntimeException("Unknown hash function option");
            }

            // Determine the n-gram quantity to add
            int nGram = selectedNgramOption + 2; // Bigram corresponds to n=2, Trigram to n=3, etc.

            // Using a HashSet to store non-repeating words
            HashSet<String> uniqueWords = new HashSet<>();
            Map<String, Integer> wordCountMap = new HashMap<>(); // Used to count occurrences of unique words

            // Storing text in a HashTable
            String[] txt = textArea.getText().split("\\s+");
            List<String> filteredWords = new ArrayList<>();
            for (String word : txt) {
                word = word.replaceAll("[^a-zA-Z0-9'\\s]", "").toLowerCase(); // Remove punctuation and convert to lowercase
                if (!word.trim().isEmpty()) {
                    filteredWords.add(word);
                    totalWords++;
                    uniqueWords.add(word);
                    wordCountMap.put(word, wordCountMap.getOrDefault(word, 0) + 1); // Count word occurrences
                }
            }
            myHashTable.addWords(filteredWords.toArray(new String[0]), nGram);

            // Pass the total number of words into the wordCountText
            wordCountText.setText(String.valueOf(totalWords));

            // Get the total number of non-repeating words
            int uniqueWordCount = uniqueWords.size();
            wordWithoutSameT.setText(String.valueOf(uniqueWordCount));

            // Clear the table model before adding new data
            model.setRowCount(0);

            // Adding unique words and their counts to the table model
            int[] index = {1};  // Use array to store serial numbers so they can be modified within lambda expressions
            wordCountMap.entrySet().stream()
                    .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by value in descending order
                    .forEach(entry -> model.addRow(new Object[]{index[0]++, entry.getKey(), entry.getValue()})); // Add serial numbers

            // Setting a Table into a Table
            wordTable.setModel(model);

            // Getting the distribution of the length of a linked table
            this.distribution = myHashTable.getLinkedListLengthDistribution();
            // Plotting histograms
            // barChartPanel.setLengthDistribution(distribution);

            // Sorting
            if (comboBox.getActionListeners().length > 0) {
                comboBox.removeActionListener(comboBox.getActionListeners()[0]); // Remove previous listener
            }
            comboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedSort = (String) comboBox.getSelectedItem();
                    model.setRowCount(0); // Clear table model
                    // Get data from the hash table and add it to the Table
                    MyLinkedObject[] table = myHashTable.getTable();

                    if("By word Frequency".equals(selectedSort)){

                        int[] index = {1};  // Use array to store serial numbers so they can be modified within lambda expressions
                        wordCountMap.entrySet().stream()
                                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue())) // Sort by value in descending order
                                .forEach(entry -> model.addRow(new Object[]{index[0]++, entry.getKey(), entry.getValue()})); // Add serial numbers
                    }

                    if ("Alphabetical order (Predictive)".equals(selectedSort)){
                        // Empty the form
                        model.setRowCount(0);

                        Map<String[], Integer> map = new HashMap<>();
                        for (MyLinkedObject myLinkedObject : table) {
                            while (myLinkedObject != null) {
                                String[] words = myLinkedObject.getWords();
                                int count = myLinkedObject.getCount();
                                if (map.get(words) != null) {
                                    map.put(words, map.get(words) + count);
                                } else {
                                    map.put(words, count);
                                }
                                if (myLinkedObject.getNext() == null) {
                                    break;
                                }
                                myLinkedObject = myLinkedObject.getNext();
                            }
                        }

                        // Serial numbers start from 1
                        int[] index = {1};
                        Stream<Map.Entry<String[], Integer>> sorted = map.entrySet().stream().sorted((w1, w2) -> Arrays.toString(w1.getKey()).compareTo(Arrays.toString(w2.getKey())));

                        // Add sorted data to the model and increase serial number
                        sorted.forEach(entry -> {
                            model.addRow(new Object[]{index[0]++, Arrays.toString(entry.getKey()), entry.getValue()});
                        });

                        wordTable.setModel(model);
                    }

                    if ("Descending order of occurrence (Predictive)".equals(selectedSort)){
                        // Empty the form
                        model.setRowCount(0);

                        Map<String[], Integer> map = new HashMap<>();
                        for (MyLinkedObject myLinkedObject : table) {
                            while (myLinkedObject != null) {
                                String[] words = myLinkedObject.getWords();
                                int count = myLinkedObject.getCount();
                                if (map.get(words) != null) {
                                    map.put(words, map.get(words) + count);
                                } else {
                                    map.put(words, count);
                                }
                                if (myLinkedObject.getNext() == null) {
                                    break;
                                }
                                myLinkedObject = myLinkedObject.getNext();
                            }
                        }

                        // Serial numbers start from 1
                        int[] index = {1};
                        Stream<Map.Entry<String[], Integer>> sorted = map.entrySet().stream().sorted((w1, w2) -> {
                            int frequencyComparison = w2.getValue().compareTo(w1.getValue());
                            return frequencyComparison != 0 ? frequencyComparison : Arrays.toString(w1.getKey()).compareTo(Arrays.toString(w2.getKey()));
                        });

                        // Add sorted data to the model and increase serial number
                        sorted.forEach(entry -> {
                            model.addRow(new Object[]{index[0]++, Arrays.toString(entry.getKey()), entry.getValue()});
                        });

                        wordTable.setModel(model);
                    }
                }
            });
        }

        if (e.getSource() == barChartButton) {
            if (distribution == null) {
                JOptionPane.showMessageDialog(null, "Please extract data first to view the distribution!", "Warning", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Create a new JFrame to display the histogram
            JFrame barChartFrame = new JFrame("Linked List Length Distribution");
            barChartFrame.setSize(800, 600);
            barChartFrame.setLocationRelativeTo(null); // Center the window on the screen
            barChartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

            // Create and set BarChartPanel
            BarChartPanel barChartPanel = new BarChartPanel();
            barChartPanel.setLengthDistribution(distribution);

            barChartFrame.add(barChartPanel);
            barChartFrame.setVisible(true);
        }

        if (e.getSource() == exportButton) {
            // Check if the table is empty
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "The table is empty!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            int option = fileChooser.showSaveDialog(null);
            if (option == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String filePath = file.getAbsolutePath();

                // Ensure the file extension is .txt
                if (!filePath.toLowerCase().endsWith(".txt")) {
                    filePath += ".txt";
                }

                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(filePath));

                    // Write header
                    writer.write("No.    Word          Frequency");
                    writer.newLine();
                    writer.write("-------------------------------");
                    writer.newLine();

                    // Iterate through the table model to write each row to the file
                    for (int i = 0; i < model.getRowCount(); i++) {
                        int serialNumber = i + 1; // Serial number starts from 1
                        String word = model.getValueAt(i, 1).toString(); // Get word
                        String count = model.getValueAt(i, 2).toString(); // Get frequency

                        writer.write(String.format("%-5d %-20s %s", serialNumber, word, count));
                        writer.newLine();
                    }

                    JOptionPane.showMessageDialog(null, "File exported successfully!", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error occurred while saving the file!", "ERROR", JOptionPane.ERROR_MESSAGE);
                } finally {
                    if (writer != null) {
                        try {
                            writer.close();
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        }

        if (e.getSource() == checkedButton) {
            // Get the word entered by the user
            String userInput = checkWord.getText().toLowerCase().trim();

            if (userInput.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter a word!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Tokenize the input content
            String[] tokens = userInput.split("\\s+");

            // Use the n-gram count and the current hash table from extraction
            int nGram = selectedNgramOption + 2; // selectedNgramOption was chosen with the Extract button for n-gram type

            // Check if the number of entered words meets the n-gram requirement
            if (tokens.length != nGram - 1) {
                JOptionPane.showMessageDialog(null, "Please enter exactly " + (nGram - 1) + " words for prediction!", "WARNING!", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Get the prefix (last (n-1) words entered)
            String[] nGramPrefix = Arrays.copyOfRange(tokens, tokens.length - (nGram - 1), tokens.length);

            // Use hash table to get all possible following words and their frequencies
            Map<String, Integer> nextWordsWithFrequency = myHashTable.getAllNextWordsWithFrequency(nGramPrefix);

            // Display prediction results
            if (nextWordsWithFrequency.isEmpty()) {
                CheckedtextArea.setText("No prediction available");
            } else {
                StringBuilder resultBuilder = new StringBuilder();
                nextWordsWithFrequency.entrySet().stream()
                        .sorted(Map.Entry.<String, Integer>comparingByValue().reversed()) // Sort by frequency in descending order
                        .forEach(entry -> resultBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n"));

                CheckedtextArea.setText(resultBuilder.toString());
            }
        }
    }
}
