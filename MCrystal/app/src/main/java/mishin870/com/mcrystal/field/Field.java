package mishin870.com.mcrystal.field;
import java.util.Random;

/**
 * Created by Mishin870 on 23.10.2017.
 * само игровое поле
 */
public class Field {
    private Cell[][] field;
    private int width, height;

    public Field(int width, int height) {
        this.width = width;
        this.height = height;
        field = new Cell[width][height];
    }

    private void initField() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y] = new Cell(Cell.RED);
            }
        }
    }

    public void randomize() {
        Random r = new Random();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                field[x][y].setType(r.nextInt(Cell.NUM_OF_COLORS));
            }
        }
    }

}