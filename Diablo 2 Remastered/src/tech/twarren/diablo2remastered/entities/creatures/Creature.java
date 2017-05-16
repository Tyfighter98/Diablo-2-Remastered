package tech.twarren.diablo2remastered.entities.creatures;

import tech.twarren.diablo2remastered.entities.Entity;

public abstract class Creature extends Entity{

	public static final int DEFAULT_HEALTH = 10;
	public static final float DEFAULT_SPEED = 3.0f;
	public static final int DEFAULT_CREATURE_WIDTH = 64;
	public static final int DEFAULT_CREATURE_HEIGHT = 64;
	
	protected int health;
	protected float speed;
	protected int width;
	protected int height;
	
	public Creature(float x, float y, int width, int height) {
		super(x, y, width, height);
		health = DEFAULT_HEALTH;
		speed = DEFAULT_SPEED;
		width = DEFAULT_CREATURE_WIDTH;
		height = DEFAULT_CREATURE_HEIGHT;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

}
