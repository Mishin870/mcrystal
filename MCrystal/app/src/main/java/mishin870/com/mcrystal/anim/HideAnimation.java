package mishin870.com.mcrystal.anim;

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
    public boolean play() {
        this.currentAlpha--;
        this.cell.paint.setAlpha(this.currentAlpha);
        return this.currentAlpha > 0;
    }

}
