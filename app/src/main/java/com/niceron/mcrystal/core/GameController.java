package com.niceron.mcrystal.core;

import com.niceron.mcrystal.animation.Animation;
import com.niceron.mcrystal.math.Position;
import com.niceron.mcrystal.visual.Cell;

import java.util.List;

public interface GameController {
	boolean isLocked();
	void onUnlock();
	
	Animation move(Cell cell, Position to);
	Animation hide(List<Cell> cell);
	
	void destroy(Cell cell);
	void destroy(List<Cell> cells);
	
	void explode(List<Cell> cells);
}
