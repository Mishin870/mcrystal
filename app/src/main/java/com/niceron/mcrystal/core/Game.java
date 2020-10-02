package com.niceron.mcrystal.core;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.View;

import com.niceron.mcrystal.animation.Animation;
import com.niceron.mcrystal.animation.AnimationManager;
import com.niceron.mcrystal.animation.cell.CellHideAnimation;
import com.niceron.mcrystal.animation.cell.CellMoveAnimation;
import com.niceron.mcrystal.logic.CellType;
import com.niceron.mcrystal.logic.Field;
import com.niceron.mcrystal.math.Position;
import com.niceron.mcrystal.visual.Cell;
import com.niceron.mcrystal.visual.Score;

import java.util.Collections;
import java.util.List;

public class Game extends View implements GameController {
	private final Field field;
	private final Score score;
	
	private AnimationManager animations;
	
	@SuppressLint("ClickableViewAccessibility")
	public Game(Context context) {
		super(context);
		
		CellType.init(this.getResources());
		
		score = new Score();
		
		animations = new AnimationManager(this);
		new Thread(animations).start();
		
		field = new Field(7, 8, this);

        /*this.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return onClick(event.getAction(), event.getX(), event.getY());
            }
        });*/
	}
    
    /*private boolean onClick(int action, float cx, float cy) {
        if (action == MotionEvent.ACTION_UP) {
            if (!animations.isEmpty()) return true;
            int x = (int) ((cx - Field.OFFSET.x) / (Config.TILE_WIDTH + Field.SPACING.x));
            int y = (int) ((cy - Field.OFFSET.y) / (Config.TILE_HEIGHT + Field.SPACING.y));

            Cell cell = field.getCell(x, y);
            if (cell != null) {
                field.clearCollected();
                ArrayList<Cell> cells = field.collect(cell, x, y);
                if (cells.size() >= 2) {
                    int scoreAdd = 0;
                    int additiveScoreAdd = 0;
                    for (Cell current : cells) {
                        animations.addAnimation(new CellHideAnimation(current));
                        scoreAdd += Config.SCORE_BY_CELL;
                        additiveScoreAdd += Config.ADDITIVE_SCORE;
                        scoreAdd += additiveScoreAdd;
                    }
                    this.score.add(scoreAdd);
                }
            }
            this.invalidate();
        }
        return true;
    }*/
	
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.WHITE);
		field.draw(canvas);
		score.draw(canvas);
	}
	
	@Override
	public Animation move(Cell cell, Position to) {
		return animations.addAnimation(CellMoveAnimation.create(cell, to));
	}
	
	@Override
	public Animation hide(List<Cell> cells) {
		return animations.addAnimation(CellHideAnimation.create(cells, this));
	}
	
	@Override
	public void destroy(Cell cell) {
		destroy(Collections.singletonList(cell));
	}
	
	@Override
	public void destroy(List<Cell> cells) {
		field.destroyCells(cells);
	}
	
	@Override
	public void explode(List<Cell> cells) {
		hide(cells);
		score.add(cells.size());
	}
	
	@Override
	public boolean isLocked() {
		return !animations.isEmpty();
	}
	
	@Override
	public void onUnlock() {
		field.explode();
	}
}