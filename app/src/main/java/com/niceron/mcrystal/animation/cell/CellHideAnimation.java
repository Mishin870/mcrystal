package com.niceron.mcrystal.animation.cell;

import com.niceron.mcrystal.animation.Animation;
import com.niceron.mcrystal.core.Game;
import com.niceron.mcrystal.core.GameController;
import com.niceron.mcrystal.visual.Cell;

import java.util.List;
import java.util.Random;

public class CellHideAnimation extends Animation {
	private static final Random RANDOM = new Random();
	private static final int ANIM_SPEED = 10;
	
	private final List<Cell> cells;
	private int currentAlpha;
	private GameController gameController;
	
	private CellHideAnimation(List<Cell> cells, GameController gameController) {
		this.gameController = gameController;
		this.currentAlpha = 255;
		
		this.cells = cells;
		for (Cell cell : this.cells) {
			cell.setAlpha(this.currentAlpha);
		}
	}
	
	public static CellHideAnimation create(List<Cell> cells, GameController gameController) {
		return new CellHideAnimation(cells, gameController);
	}
	
	@Override
	public boolean play(Game game) {
		currentAlpha -= ANIM_SPEED;
		
		if (currentAlpha > 0) {
			for (Cell cell : cells) {
				cell.setAlpha(this.currentAlpha);
			}
			
			return true;
		} else {
			gameController.destroy(cells);
			return false;
		}
	}
}
