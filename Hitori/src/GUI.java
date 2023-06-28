import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class GUI extends JFrame {

    PanelHitoriHome panelHome = new PanelHitoriHome(this);
    Board panelMiddle;
    ImageIcon icon;
    PanelHitoriTop panelTop;
    PanelHitoriBottom panelBottom;
    CardLayout cardLayout;
    JPanel cardPanel;
    JPanel panelWith3;


    public GUI() throws IOException {
        icon = new ImageIcon("./Hitori/resources/five.png");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Hitori");
        this.setSize(500,808);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        cardLayout = new CardLayout();
        cardPanel = new JPanel();

        cardPanel.setLayout(cardLayout);
        cardPanel.add(panelHome, "panelHome");
        this.add(cardPanel);


        cardLayout.show(cardPanel,"panelHome");
    }

    void switchHome() {
        cardLayout.show(cardPanel,"panelHome");
    }

    Board switchBoard(int size) throws IOException {

        panelTop = new PanelHitoriTop(null, null, this);
        panelMiddle = new Board(size);
        panelBottom = new PanelHitoriBottom(panelMiddle, panelTop);

        panelWith3 = new JPanel();
        panelWith3.setLayout(new BorderLayout());
        panelWith3.add(panelTop, BorderLayout.NORTH);
        panelWith3.add(panelMiddle, BorderLayout.CENTER);
        panelWith3.add(panelBottom, BorderLayout.SOUTH);

        cardPanel.add(panelWith3, "panelWith3");
        cardPanel.add(panelHome, "panelHome");

        this.add(cardPanel);
        cardLayout.show(cardPanel,"panelWith3");

        return panelMiddle;
    }
    @Override
    public void paintComponents(Graphics g){
        super.paintComponents(g);
        ImageIcon imageIcon = new ImageIcon(("./Hitori/resources/tłodobre.png"));
        g.drawImage(imageIcon.getImage(),0,0,null);
    }
}
