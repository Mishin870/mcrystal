package com.niceron.mcrystal.animation.cell;

import com.niceron.mcrystal.animation.Animation;
import com.niceron.mcrystal.core.Game;
import com.niceron.mcrystal.math.Position;
import com.niceron.mcrystal.visual.Cell;

public class CellMoveAnimation extends Animation {
	private static final int ANIM_SPEED = 5;
	private final Cell cell;
	private final Position to;
	
	private CellMoveAnimation(Cell cell, Position to) {
		this.cell = cell;
		this.to = to;
	}
	
	public static CellMoveAnimation create(Cell cell, Position to) {
		return new CellMoveAnimation(cell, to);
	}
	
	@Override
	public boolean play(Game game) {
	    return cell.getPosition().smoothMove(to, ANIM_SPEED);
	}
}
