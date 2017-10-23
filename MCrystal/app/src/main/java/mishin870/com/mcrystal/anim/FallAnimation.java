package mishin870.com.mcrystal.anim;

import mishin870.com.mcrystal.GameView;
import mishin870.com.mcrystal.field.Cell;
import mishin870.com.mcrystal.field.MCResources;

/**
 * Created by Mishin870 on 24.10.2017.
 */

public class FallAnimation extends SingleAnimation {
    private static final int ANIM_SPEED = 5;
    private int sourceX, sourceY, len;

    /**
     * анимация падения ячейки на 1 вниз
     * @param cell
     * @param sourceX
     * @param sourceY
     */
    public FallAnimation(Cell cell, int sourceX, int sourceY) {
        super(cell);
        this.sourceX = sourceX;
        this.sourceY = sourceY;
    }

    @Override
    public boolean play(GameView gameView) {
        this.cell.offY += ANIM_SPEED;
        if (this.cell.offY >= MCResources.TILE_HEIGHT) {
            gameView.getGameField().setCell(this.sourceX, this.sourceY + 1, this.cell.getType());
            return false;
        } else {
            return true;
        }
    }

}
