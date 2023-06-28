import java.io.IOException;
import javax.swing.*;
import java.awt.*;
public class Board extends JPanel {

    int size;
    Cell[][] cells;
    int[][] values;
    BoardReader boardReader;
    public Board(int size) throws IOException {
        this.size = size;
        this.setLayout(new GridLayout(size, size));
        boardReader = new BoardReader(size);
        this.values = boardReader.czytaj5x5(size);
        this.cells = new Cell[size][size];

        for(int i = 0; i<size; i++) {
            for(int j = 0; j<size; j++){
                Cell cell = new Cell(i, j, values[j][i]);
                this.cells[i][j] = cell;
                this.add(cell);
            }
        }

        this.setPreferredSize(new Dimension(500,500));
        this.setBackground(Color.LIGHT_GRAY);


        int marginSize = 10;
        this.setBorder(BorderFactory.createEmptyBorder(marginSize, marginSize, marginSize, marginSize));
    }

    public Board getBoard(){
        return this;
    }

    public void populateAnotherBoard() throws IOException {
        this.values = boardReader.czytaj5x5(size);

        for(int i = 0; i<size; i++) {
            for(int j = 0; j<size; j++){
                cells[i][j].changeValue(values[j][i]);
            }
        }
    }

    public int getSizee(){
        return size;
    }


    public Cell[][] getCells() {
        return cells;
    }
}