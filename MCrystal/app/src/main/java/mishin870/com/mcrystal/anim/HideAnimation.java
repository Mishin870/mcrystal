package mishin870.com.mcrystal.anim;

import java.util.Random;

import mishin870.com.mcrystal.GameView;
import mishin870.com.mcrystal.field.Cell;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class HideAnimation extends SingleAnimation {
    private int currentAlpha;

    /**
     * анимация затухания ячейки в небытье
     * @param cell
     */
    public HideAnimation(Cell cell) {
        super(cell);
        this.currentAlpha = 255;
        cell.paint.setAlpha(this.currentAlpha);
    }

    @Override
    public boolean play(GameView gameView) {
        this.currentAlpha--;
        this.cell.paint.setAlpha(this.currentAlpha);
        if (this.currentAlpha > 0) {
            return true;
        } else {
            Random r = new Random();
            cell.setType(r.nextInt(Cell.NUM_OF_COLORS));
            return false;
        }
    }

}
