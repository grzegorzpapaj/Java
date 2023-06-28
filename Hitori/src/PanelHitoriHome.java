import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelHitoriHome extends JPanel implements ActionListener {

    ImageIcon background;
    JLabel backgroundLabel;
    JButton easyButton;
    JButton mediumButton;
    JButton hardButton;
    GUI gui;
    PanelHitoriHome(GUI gui){

        this.gui = gui;
        background = new ImageIcon("./Hitori/resources/tłodobre.png");
        backgroundLabel = new JLabel(background);
        backgroundLabel.setSize(500,808);

        easyButton = new JButton("Łatwy");
        mediumButton = new JButton("Średni");
        hardButton = new JButton("Trudny");


        this.setVisible(false);
        this.setPreferredSize(new Dimension(500,808));
        this.setLayout(new BorderLayout());
        this.add(backgroundLabel);

        Font buttonFont = new Font("Corbel", Font.BOLD, 20);

        JLabel title = new JLabel("HITORI");
        title.setBounds(80,65,500,250);
        backgroundLabel.add(title);

        easyButton.setBounds(120,270,250,80);
        easyButton.setFocusable(false);
        easyButton.setFont(buttonFont);
        easyButton.addActionListener(this);
        backgroundLabel.add(easyButton);

        mediumButton.setBounds(120,390,250,80);
        mediumButton.setFocusable(false);
        mediumButton.setFont(buttonFont);
        mediumButton.addActionListener(this);
        backgroundLabel.add(mediumButton);

        hardButton.setBounds(120,510,250,80);
        hardButton.setFocusable(false);
        hardButton.setFont(buttonFont);
        hardButton.addActionListener(this);
        backgroundLabel.add(hardButton);

        title.setHorizontalTextPosition(JLabel.CENTER);
        title.setVerticalTextPosition(JLabel.CENTER);
        title.setFont(new Font("Consolas", Font.BOLD, 100));

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Cell.score = 0;

        if(e.getSource()==easyButton){
            try {
                gui.switchBoard(5);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==mediumButton){
            try {
                gui.switchBoard(7);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==hardButton){
            try {
                gui.switchBoard(9);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

    }
}
