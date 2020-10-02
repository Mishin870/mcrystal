package com.niceron.mcrystal.animation;

import com.niceron.mcrystal.core.Game;

import java.util.ArrayList;
import java.util.ListIterator;

public class AnimationManager implements Runnable {
	private static final int FRAME_TIME = 16;
	
	private ArrayList<Animation> toAdd;
	private boolean addFlag = false;
	
	private ArrayList<Animation> animations;
	private Game game;
	
	public AnimationManager(Game game) {
		this.game = game;
		animations = new ArrayList<>();
		toAdd = new ArrayList<>();
	}
	
	public ArrayList<Animation> getAnimations() {
		return this.animations;
	}
	
	public ArrayList<Animation> getToAdds() {
		return this.toAdd;
	}
	
	public boolean isEmpty() {
		return toAdd.isEmpty() && animations.isEmpty();
	}
	
	public Animation addAnimation(Animation animation) {
		toAdd.add(animation);
		addFlag = true;
		return animation;
	}
	
	@Override
	public void run() {
		while (true) {
			boolean needRepaint = false;
			
			ListIterator<Animation> iterator = animations.listIterator();
			while (iterator.hasNext()) {
				needRepaint = true;
				
				Animation animation = iterator.next();
				if (!animation.play(game)) {
					Animation next = animation.getNext();
					
					if (next == null) {
						iterator.remove();
						
						if (isEmpty()) {
							game.onUnlock();
						}
					} else {
						iterator.set(next);
					}
				}
			}
			
			if (addFlag) {
				animations.addAll(toAdd);
				toAdd.clear();
				addFlag = false;
			}
			
			try {
				Thread.sleep(FRAME_TIME);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			
			if (needRepaint) {
				game.postInvalidate();
			}
		}
	}
	
}
