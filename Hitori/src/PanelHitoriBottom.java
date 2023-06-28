import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.HashSet;

public class PanelHitoriBottom extends JPanel {

    JButton gotoweButton = new JButton("Gotowe");
    JButton noweZadanieButton = new JButton("Nowe Zadanie");

    Board board;
    Cell[][] cells;
    PanelHitoriTop panelTop;
    public PanelHitoriBottom(Board board, PanelHitoriTop panelTop){
        this.setPreferredSize(new Dimension(500,100));
        this.setBackground(Color.LIGHT_GRAY);

        this.setLayout(new BorderLayout());
        cells = board.getCells();

        gotoweButton.setFocusable(false);
        noweZadanieButton.setFocusable(false);

        this.add(gotoweButton, BorderLayout.WEST);
        this.add(noweZadanieButton, BorderLayout.EAST);

        this.board = board;
        this.panelTop = panelTop;

        gotoweButton.setPreferredSize(new Dimension(140, 100));
        noweZadanieButton.setPreferredSize(new Dimension(140, 100));

        int marginSize = 10;

        this.setBorder(BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize));

        gotoweButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!checkWin()){
                    panelTop.labelStatus.setForeground(new Color(125,60,16));
                    panelTop.labelStatus.setText("Twoje rozwiązanie zawiera błędy");
                } else {
                    panelTop.labelStatus.setForeground(new Color(16,125,42));
                    panelTop.labelStatus.setFont(new Font(panelTop.labelStatus.getFont().getFontName(),Font.BOLD,20));
                    panelTop.labelStatus.setText("Poprawne rozwiązanie! Rozwiązano w "+Cell.score+" ruchach");

                    for (int i = 0; i < board.getSizee(); i++) {
                        for (int j = 0; j < board.getSizee(); j++) {
                            cells[i][j].setEnabled(false);
                        }
                    }
                    gotoweButton.setEnabled(false);

                }
            }
        });

        noweZadanieButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    board.populateAnotherBoard();
                    for (int i = 0; i < board.getSizee(); i++) {
                        for (int j = 0; j < board.getSizee(); j++) {
                            cells[i][j].setEnabled(true);
                            cells[i][j].unmark();
                        }
                    }
                    Cell.score = 0;
                    gotoweButton.setEnabled(true);
                    panelTop.labelStatus.setText("");
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    boolean checkWin() {
        Cell[][] cells = board.getCells();

        for (int i = 0; i < board.getSizee(); i++) {
            HashSet<Integer> set = new HashSet<Integer>();
            for (int j = 0; j < board.getSizee(); j++) {

                if(cells[i][j].isMarked()) {
                    if (!(i - 1 < 0 || j - 1 < 0 || i + 1 > board.getSizee() - 1 || j + 1 > board.getSizee() - 1)) {
                        if (cells[i - 1][j].isMarked() || cells[i][j - 1].isMarked() || cells[i + 1][j].isMarked() || cells[i][j + 1].isMarked()) {
                            return false;
                        }
                    } else if ((i == 0) && j == 0 ){
                        if(cells[i+1][j].isMarked() || cells[i][j+1].isMarked()){
                            return false;
                        }
                    } else if ((i == 0) && (j == board.getSizee()-1)){
                        if(cells[i+1][j].isMarked() || cells[i][j-1].isMarked()){
                            return false;
                        }
                    } else if ((i == board.getSizee()-1) && (j==0)) {
                        if(cells[i-1][j].isMarked() || cells[i][j+1].isMarked()){
                            return false;
                        }
                    } else if ((i==board.getSizee()-1) && (j==board.getSizee()-1)){
                        if(cells[i-1][j].isMarked() || cells[i][j-1].isMarked()){
                            return false;
                        }
                    } else if ((i==0) && (j != 0) && (j != board.getSizee()-1)){
                        if(cells[i+1][j].isMarked() || cells[i][j-1].isMarked() || cells[i][j+1].isMarked()){
                            return false;
                        }
                    } else if ((i==(board.getSizee()-1) && (j != 0) && (j != board.getSizee()-1))){
                        if(cells[i-1][j].isMarked() || cells[i][j-1].isMarked() || cells[i][j+1].isMarked()){
                            return false;
                        }
                    } else if ((i != 0) && (i != (board.getSizee()-1)) && j == 0){
                        if(cells[i][j+1].isMarked() || cells[i-1][j].isMarked() || cells[i+1][j].isMarked()){
                            return false;
                        }
                    } else if ((i != 0) && (i != (board.getSizee()-1)) && j == board.getSizee()-1){
                        if(cells[i][j-1].isMarked() || cells[i-1][j].isMarked() || cells[i+1][j].isMarked()){
                            return false;
                        }
                    }
                }

                if(!(cells[i][j].isMarked())){
                    if(!(i - 1 < 0 || j - 1 < 0 || i + 1 > board.getSizee() - 1 || j + 1 > board.getSizee() - 1)){
                        if (cells[i - 1][j].isMarked() && cells[i][j - 1].isMarked() && cells[i + 1][j].isMarked() && cells[i][j + 1].isMarked()) {
                            return false;
                        }
                    } else if ((i == 0) && j == 0 ){
                        if(cells[i+1][j].isMarked() && cells[i][j+1].isMarked()){
                            return false;
                        }
                    } else if ((i == 0) && (j == board.getSizee()-1)){
                        if(cells[i+1][j].isMarked() && cells[i][j-1].isMarked()){
                            return false;
                        }
                    } else if ((i == board.getSizee()-1) && (j==0)) {
                        if(cells[i-1][j].isMarked() && cells[i][j+1].isMarked()){
                            return false;
                        }
                    } else if ((i==board.getSizee()-1) && (j==board.getSizee()-1)){
                        if(cells[i-1][j].isMarked() && cells[i][j-1].isMarked()){
                            return false;
                        }
                    } else if ((i==0) && (j != 0) && (j != board.getSizee()-1)){
                        if(cells[i+1][j].isMarked() && cells[i][j-1].isMarked() && cells[i][j+1].isMarked()){
                            return false;
                        }
                    } else if ((i==(board.getSizee()-1) && (j != 0) && (j != board.getSizee()-1))){
                        if(cells[i-1][j].isMarked() && cells[i][j-1].isMarked() && cells[i][j+1].isMarked()){
                            return false;
                        }
                    } else if ((i != 0) && (i != (board.getSizee()-1)) && j == 0){
                        if(cells[i][j+1].isMarked() && cells[i-1][j].isMarked() && cells[i+1][j].isMarked()){
                            return false;
                        }
                    } else if ((i != 0) && (i != (board.getSizee()-1)) && j == board.getSizee()-1){
                        if(cells[i][j-1].isMarked() && cells[i-1][j].isMarked() && cells[i+1][j].isMarked()){
                            return false;
                        }
                    }
                    if (set.contains(cells[i][j].getValue())){
                        return false;
                    }
                    set.add(cells[i][j].getValue());
                }
            }
        }

        return true;
    }
}