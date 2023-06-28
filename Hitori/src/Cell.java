import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Cell extends JButton implements ActionListener {
    int row;
    int column;
    int value;
    static int score = 0;
    private boolean isMarked;
    Color defaultColor = this.getBackground();

    public Cell(int row, int column, int value){
        this.row = row;
        this.column = column;
        this.value = value;
        this.addActionListener(this);
        this.setText(Integer.toString(value));
        this.setFocusable(false);
        this.isMarked = false;
    }

    public int getValue(){
        return value;
    }

    public boolean isMarked(){
        return isMarked;
    }
    public int getRow(){
        return row;
    }

    public int getColumn(){
        return column;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==this){
            score++;
            if(!isMarked){
                isMarked = true;
                this.setBackground(Color.BLACK);
                this.setForeground(Color.WHITE);
            } else {
                isMarked = false;
                this.setBackground(defaultColor);
                this.setForeground(Color.BLACK);
            }
        }
    }

    public void changeValue(int value) {
        this.value = value;
        this.setText(Integer.toString(value));
    }

    public void unmark(){
        if(this.isMarked){
            isMarked = false;
            this.setBackground(defaultColor);
            this.setForeground(Color.BLACK);
        }

    }

    public void mark(){
        isMarked = true;
        this.setBackground(Color.BLACK);
        this.setForeground(defaultColor);
    }
}

