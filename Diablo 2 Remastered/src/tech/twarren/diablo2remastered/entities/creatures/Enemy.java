package tech.twarren.diablo2remastered.entities.creatures;

import java.awt.Graphics;

import tech.twarren.diablo2remastered.gfx.Assets;

public class Enemy extends Creature {

	public Enemy(float x, float y) {
		super(x, y);
	}

	public void tick() {
		
	}

	public void render(Graphics g) {
		g.drawImage(Assets.flower, (int) x, (int) y, null);
	}

	
	
}
