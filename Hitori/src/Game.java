import javax.swing.*;
import java.io.IOException;

public class Game {
    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GUI frameHitori = null;
                try {
                    frameHitori = new GUI();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                frameHitori.setVisible(true);
            }
        });
    }
}