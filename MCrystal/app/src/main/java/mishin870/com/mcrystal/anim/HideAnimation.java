package mishin870.com.mcrystal.anim;

import java.util.Random;

import mishin870.com.mcrystal.GameView;
import mishin870.com.mcrystal.field.Cell;

/**
 * Created by Mishin870 on 23.10.2017.
 */
public class HideAnimation extends SingleAnimation {
    private static final Random rnd = new Random();
    private static final int ANIM_SPEED = 20;
    private int currentAlpha;
    private int x, y;

    /**
     * анимация затухания ячейки в небытье
     * @param cell
     */
    public HideAnimation(Cell cell) {
        super(cell);
        this.currentAlpha = 255;
        this.cell.paint.setAlpha(this.currentAlpha);
    }

    @Override
    public boolean play(GameView gameView) {
        this.currentAlpha -= ANIM_SPEED;
        this.cell.paint.setAlpha(this.currentAlpha);
        if (this.currentAlpha > 0) {
            return true;
        } else {
            this.cell.setType(Cell.NONE);
            this.cell.paint.setAlpha(255);
            int x = this.cell.x;
            int y = this.cell.y;
            Cell tmp = gameView.getGameField().getCell(x, y - 1);
            if (tmp != null) {
                if (tmp.getType() != Cell.NONE) {
                    gameView.getAnimationManager().addAnimation(new FallAnimation(tmp, gameView.getGameField(), x, y - 1));
                }
            } else {
                this.cell.setType(rnd.nextInt(Cell.NUM_OF_COLORS));
            }
            return false;
        }
    }

}
