import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyMenuBar extends JMenuBar implements ActionListener {

    private JFrame jfrm;
    private JPanel northPanel;
    private JPanel centreP;

    public MyMenuBar(JFrame jfrm, JPanel northPanel, JPanel centreP){

        this.jfrm = jfrm;
        this.northPanel = northPanel;
        this.centreP = centreP;

        JMenu menuFile = new JMenu("File");
        JMenu menuHelp = new JMenu("Help");

        JMenuItem itemSetting = new JMenuItem("Setting");
        JMenuItem itemExit = new JMenuItem("Exit");

        JMenuItem itemContactUs = new JMenuItem("Contact Us");

        menuFile.add(itemSetting);
        menuFile.add(itemExit);

        menuHelp.add(itemContactUs);

        add(menuFile);
        add(menuHelp);

        itemExit.addActionListener(this);
        itemSetting.addActionListener(this);

        itemContactUs.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String source = e.getActionCommand();
        //Exit
        if (source.equals("Exit")){
            System.exit(0);

        }

        //Setting
        if (source.equals("Setting")){

            //Adjusting the background colour of the interface

            JDialog settingDialog = new JDialog(jfrm,"Setting");

            settingDialog.setSize(450, 100);
            settingDialog.setLocationRelativeTo(null);
            settingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JLabel SettingLabel = new JLabel("Please select background colour: ", SwingConstants.CENTER);
            settingDialog.add(SettingLabel);

            JButton redButton = new JButton("White");
            JButton greenButton = new JButton("Green");
            JButton blueButton = new JButton("Blue");

            redButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    northPanel.setBackground(Color.WHITE);
                    centreP.setBackground(Color.WHITE);
                }
            });
            greenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    northPanel.setBackground(Color.GREEN);
                    centreP.setBackground(Color.GREEN);
                }
            });
            blueButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    northPanel.setBackground(Color.BLUE);
                    centreP.setBackground(Color.BLUE);
                }
            });

            settingDialog.setLayout(new FlowLayout());
            settingDialog.add(redButton);
            settingDialog.add(greenButton);
            settingDialog.add(blueButton);

            settingDialog.setVisible(true);

        }
        //Contact Us
        if (source.equals("Contact Us")){


            JDialog helpDialog = new JDialog(jfrm,"Contact Us");

            helpDialog.setSize(300, 200);
            helpDialog.setLocationRelativeTo(null);
            helpDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

            JLabel helpLabel = new JLabel("<html>Author: Yedeli Ahati<br>Email: ahati.yedeli@gmail.com</html>", SwingConstants.CENTER);
            helpDialog.add(helpLabel);

            helpDialog.setVisible(true);
        }
    }
}
