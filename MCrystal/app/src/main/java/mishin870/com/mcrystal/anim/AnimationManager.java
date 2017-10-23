package mishin870.com.mcrystal.anim;

import java.util.ArrayList;
import java.util.ListIterator;

import mishin870.com.mcrystal.anim.IAnimation;

/**
 * Created by Mishin870 on 23.10.2017.
 */

public class AnimationManager implements Runnable {
    private ArrayList<IAnimation> animations;

    /**
     * поток, управляющий всеми анимациями
     */
    public AnimationManager() {
        animations = new ArrayList<IAnimation>();
    }

    public void addAnimation(IAnimation animation) {
        animations.add(animation);
    }

    @Override
    public void run() {
        while (true) {
            ListIterator<IAnimation> iter = animations.listIterator();
            while (iter.hasNext()) {
                if (!iter.next().play()) iter.remove();
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException ie) {
                System.err.println("InterruptedException in AnimationManager::run()");
                return;
            }
        }
    }

}
