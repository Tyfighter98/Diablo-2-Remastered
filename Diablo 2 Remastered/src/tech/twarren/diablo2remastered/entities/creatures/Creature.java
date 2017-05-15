package tech.twarren.diablo2remastered.entities.creatures;

import tech.twarren.diablo2remastered.entities.Entity;

public abstract class Creature extends Entity{

	protected int health;
	
	public Creature(float x, float y) {
		super(x, y);
		health = 10;
	}

}
