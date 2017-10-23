package mishin870.com.mcrystal.anim;

import java.util.ArrayList;
import java.util.ListIterator;

import mishin870.com.mcrystal.GameView;

/**
 * Created by Mishin870 on 23.10.2017.
 */

public class AnimationManager implements Runnable {
    private ArrayList<IAnimation> toAdd;
    private boolean addFlag = false;

    private ArrayList<IAnimation> animations;
    private GameView gameView;

    /**
     * поток, управляющий всеми анимациями
     */
    public AnimationManager(GameView gameView) {
        this.gameView = gameView;
        animations = new ArrayList<IAnimation>();
        toAdd = new ArrayList<IAnimation>();
    }

    /**
     * получить исходный список анимаций
     * @return
     */
    public ArrayList<IAnimation> getAnimations() {
        return this.animations;
    }

    /**
     * получить список анимаций на внесение
     * @return
     */
    public ArrayList<IAnimation> getToAdds() {
        return this.toAdd;
    }

    /**
     * проверка на пустоту всех анимаций
     * @return
     */
    public boolean isEmpty() {
        return toAdd.isEmpty() && animations.isEmpty();
    }

    /**
     * добавить анимацию в список на выполнение
     * @param animation
     */
    public void addAnimation(IAnimation animation) {
        toAdd.add(animation);
        addFlag = true;
    }

    @Override
    public void run() {
        boolean needRepaint = false;
        while (true) {
            needRepaint = false;
            ListIterator<IAnimation> iter = animations.listIterator();
            while (iter.hasNext()) {
                needRepaint = true;
                if (!iter.next().play(gameView)) {
                    iter.remove();
                }
            }
            if (addFlag) {
                animations.addAll(toAdd);
                toAdd.clear();
                addFlag = false;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                System.err.println("InterruptedException in AnimationManager::run()");
                return;
            }
            if (needRepaint) gameView.postInvalidate();
        }
    }

}
