package com.niceron.mcrystal.animation;

import com.niceron.mcrystal.core.Game;

public abstract class Animation {
	private Animation next;
	
	public final void then(Animation next) {
		this.next = next;
	}
	
	public final Animation getNext() {
		return next;
	}
	
	public abstract boolean play(Game game);
}
