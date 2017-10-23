package mishin870.com.mcrystal.anim;

import java.util.ArrayList;
import java.util.ListIterator;

import mishin870.com.mcrystal.GameView;
import mishin870.com.mcrystal.anim.IAnimation;

/**
 * Created by Mishin870 on 23.10.2017.
 */

public class AnimationManager implements Runnable {
    private ArrayList<IAnimation> animations;
    private GameView gameView;

    /**
     * поток, управляющий всеми анимациями
     */
    public AnimationManager(GameView gameView) {
        this.gameView = gameView;
        animations = new ArrayList<IAnimation>();
    }

    public void addAnimation(IAnimation animation) {
        animations.add(animation);
    }

    @Override
    public void run() {
        boolean needRepaint = false;
        while (true) {
            ListIterator<IAnimation> iter = animations.listIterator();
            needRepaint = false;
            while (iter.hasNext()) {
                needRepaint = true;
                if (!iter.next().play(gameView)) {
                    iter.remove();
                }
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                System.err.println("InterruptedException in AnimationManager::run()");
                return;
            }
            if (needRepaint) gameView.invalidate();
        }
    }

}
