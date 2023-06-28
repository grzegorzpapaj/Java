import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class PanelHitoriTop extends JPanel implements ActionListener{

    JButton wczytajButton;
    JButton saveButton;
    JButton homeButton;
    JLabel labelStatus;
    JPanel panelSaveHome;
    JPanel panelWczytaj;
    PanelHitoriHome panelHome;
    Board panelMiddle;
    GUI gui;
    PanelHitoriBottom panelBottom;
    JPanel statusPanel;
    public PanelHitoriTop(Board panelMiddle, PanelHitoriBottom panelBottom, GUI gui){
        this.gui = gui;
        this.setPreferredSize(new Dimension(500, 150));
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout());

        this.panelMiddle = panelMiddle;

        ImageIcon homeIcon = new ImageIcon("./Hitori/resources/domek.png");
        ImageIcon saveIcon = new ImageIcon("./Hitori/resources/dyskietka.png");

        wczytajButton = new JButton("Wczytaj ostatnie");
        saveButton = new JButton();
        saveButton.setIcon(saveIcon);
        homeButton = new JButton();
        homeButton.setIcon(homeIcon);

        labelStatus = new JLabel("");
        labelStatus.setForeground(new Color(125,60,16));
        labelStatus.setFont(new Font(labelStatus.getFont().getFontName(),Font.BOLD,20));

        panelSaveHome = new JPanel();
        panelSaveHome.setPreferredSize(new Dimension(220,50));
        panelSaveHome.setLayout(new GridLayout(1,2, 20, 0));
        panelSaveHome.setBackground(Color.LIGHT_GRAY);
        panelSaveHome.add(saveButton);
        panelSaveHome.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
        saveButton.setFocusable(false);
        panelSaveHome.add(homeButton);
        homeButton.setFocusable(false);
        this.setVisible(true);


        panelWczytaj = new JPanel(new BorderLayout());
        panelWczytaj.setBackground(Color.LIGHT_GRAY);
        panelWczytaj.add(wczytajButton);
        wczytajButton.setFocusable(false);
        panelWczytaj.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        statusPanel = new JPanel();
        statusPanel.setBackground(Color.LIGHT_GRAY);
        statusPanel.add(labelStatus);


        this.add(statusPanel, BorderLayout.SOUTH);
        this.add(panelSaveHome,BorderLayout.EAST);
        this.add(panelWczytaj, BorderLayout.WEST);

        homeButton.addActionListener(this);
        saveButton.addActionListener(this);
        wczytajButton.addActionListener(this);
    }

    public void setBoard(Board board){
        this.panelMiddle = board;
    }
    public void setPanelBottom(PanelHitoriBottom panelBottom){
        this.panelBottom = panelBottom;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==homeButton){
            gui.switchHome();
        } else if (e.getSource()==saveButton){
            try {
                saveBoard(gui.panelMiddle);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getSource()==wczytajButton){
            try {
                loadBoard();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void saveBoard(Board board) throws IOException {
        String filePath = "./Hitori/resources/save.txt";
        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));

        for(int i = 0; i<board.getSizee(); i++) {
            for (int j = 0; j < board.getSizee(); j++) {
                if(board.getCells()[i][j].isMarked()){
                    writer.write(String.valueOf(board.getCells()[i][j].getValue()));
                    writer.write("X");
                    writer.write(System.lineSeparator());
                } else {
                    writer.write(String.valueOf(board.getCells()[i][j].getValue()));
                    writer.write(System.lineSeparator());
                }
            }
        }
        writer.write(String.valueOf(Cell.score));
        labelStatus.setForeground(Color.BLACK);
        labelStatus.setText("Zapisano");
        writer.close();
    }

    public void loadBoard() throws IOException {

        String filePath = "./Hitori/resources/save.txt";
        int valueCount = ((int) Files.lines(Path.of(filePath)).count()) - 1;
        int size = (int)Math.sqrt(valueCount);

        File file = new File(filePath);
        Scanner scanner = new Scanner(file);

        int[][] values = new int[size][size];

        Board board = gui.switchBoard(size);
        Cell[][] cells = board.getCells();

        for(int i=0;i<size;i++){
            for(int j=0; j<size; j++){

                String cellValue = scanner.nextLine();
                if(cellValue.contains("X")){
                    String[] splitCellValue = cellValue.split("");
                    cells[i][j].changeValue(Integer.parseInt(splitCellValue[0]));
                    cells[i][j].mark();

                } else {
                    cells[i][j].changeValue((Integer.parseInt(cellValue)));
                }
            }
        }
        Cell.score = scanner.nextInt();

    }



}