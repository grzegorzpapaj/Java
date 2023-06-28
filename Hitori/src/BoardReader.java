import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
public class BoardReader {

    int size;
    final int DLUGOSC_PLIKU = 5;
    BoardReader(int size){
        this.size = size;
    }

    int[][] czytaj5x5(int difficulty) throws IOException {

        Random random = new Random();
        String filename = "Hitori/resources/" + difficulty + "x" +difficulty + ".txt";
        int randomIndex = random.nextInt((int) DLUGOSC_PLIKU);
        String randomLine = Files.readAllLines(Paths.get(filename)).get(randomIndex);
        String[] splitLine = randomLine.split("");
        int[] intLine = new int[size*size];
        for(int i=0;i<splitLine.length;i++){
            intLine[i] = Integer.parseInt(splitLine[i]);
        }

        int[][] intLineArray = new int[size][size];
        for(int i=0;i<size;i++){
            for(int j=0;j<size;j++){
                intLineArray[i][j] = intLine[j*size+i];
            }
        }

       return intLineArray;
    }

}

