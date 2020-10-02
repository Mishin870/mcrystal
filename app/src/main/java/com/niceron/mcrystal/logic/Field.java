package com.niceron.mcrystal.logic;

import android.graphics.Canvas;

import com.niceron.mcrystal.core.GameController;
import com.niceron.mcrystal.math.Position;
import com.niceron.mcrystal.math.PositionInt;
import com.niceron.mcrystal.visual.Cell;
import com.niceron.mcrystal.visual.Drawable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Field implements Drawable {
	private static final Random RANDOM = new Random();
	
	private final GameController gameController;
	private final List<Cell> cells;
	private final Cell[][] field;
	private final int width;
	private final int height;
	
	public Field(int width, int height, GameController gameController) {
		this.width = width;
		this.height = height;
		this.gameController = gameController;
		
		this.field = new Cell[height][width];
		this.cells = new ArrayList<>(height * width);
		init();
	}
	
	private void init() {
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < width; x++) {
				Position visualPosition = FieldUtils.fieldToReal(PositionInt.of(x, y - height - 1));
				PositionInt fieldPosition = PositionInt.of(x, y);
				spawnCell(visualPosition, fieldPosition);
			}
		}
	}
	
	private void pullFromUp(int x, int y) {
		final PositionInt myPosition = PositionInt.of(x, y);
		
		if (y > 0) {
			for (int cy = y - 1; cy >= 0; cy--) {
				Cell upCell = field[cy][x];
				
				if (upCell != null) {
					field[cy][x] = null;
					moveTo(upCell, myPosition);
					return;
				}
			}
		}
		
		Position visualPosition = FieldUtils.fieldToReal(PositionInt.of(x, -2));
		spawnCell(visualPosition, myPosition);
	}
	
	private void moveTo(Cell cell, PositionInt fieldPosition) {
		field[fieldPosition.y][fieldPosition.x] = cell;
		cell.getFieldPosition().setFrom(fieldPosition);
		gameController.move(cell, FieldUtils.fieldToReal(fieldPosition));
	}
	
	private void spawnCell(Position visualPosition, PositionInt fieldPosition) {
		Cell cell = new Cell(CellType.random(RANDOM), visualPosition, fieldPosition);
		cells.add(cell);
		moveTo(cell, fieldPosition);
	}
	
	/**
	 * "Заполняет" игровое поле снизу-вверх слева-направо<br>
	 * Для заполнения использует анимацию падения
	 */
	public void fill() {
		for (int y = height - 1; y >= 0; y--) {
			for (int x = 0; x < width; x++) {
				if (field[y][x] == null) {
					pullFromUp(x, y);
				}
			}
		}
	}
	
	@Override
	public void draw(Canvas canvas) {
		for (Cell cell : cells) {
			cell.draw(canvas);
		}
	}
	
	public void destroyCells(List<Cell> cells) {
		for (Cell cell : cells) {
			PositionInt position = cell.getFieldPosition();
			field[position.y][position.x] = null;
		}
		this.cells.removeAll(cells);
		fill();
	}
	
	public void explode() {
		boolean[][] collected = new boolean[height][width];
		List<Cell> toExplode = new ArrayList<>(width * height);
		
		for (Cell cell : cells) {
			List<Cell> possible = collect(cell, collected, false);
			
			if (possible.size() >= 3) {
				toExplode.addAll(possible);
			}
		}
		
		gameController.explode(toExplode);
	}
	
	private Cell getCell(int x, int y) {
		if (x >= 0 && x < width && y >= 0 && y < height) {
			return field[y][x];
		} else {
			return null;
		}
	}
	
	private List<Cell> collect(Cell cell, boolean[][] collected, boolean collectSelfIfEmpty) {
		PositionInt position = cell.getFieldPosition();
		int x = position.x;
		int y = position.y;
		
		if (!collected[y][x]) {
			CellType type = cell.getType();
			List<Cell> result = new ArrayList<>(5);
			collected[y][x] = true;
			
			Cell up = getCell(x, y - 1);
			Cell down = getCell(x, y + 1);
			Cell left = getCell(x - 1, y);
			Cell right = getCell(x + 1, y);
			if (up != null && up.getType() == type) result.addAll(collect(up, collected, true));
			if (down != null && down.getType() == type) result.addAll(collect(down, collected, true));
			if (left != null && left.getType() == type) result.addAll(collect(left, collected, true));
			if (right != null && right.getType() == type) result.addAll(collect(right, collected, true));
			
			if (collectSelfIfEmpty || !result.isEmpty()) {
				result.add(cell);
			}
			
			return result;
		} else {
			return Collections.emptyList();
		}
	}
}