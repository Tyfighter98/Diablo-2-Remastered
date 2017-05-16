package tech.twarren.diablo2remastered.states;

import java.awt.Graphics;

import tech.twarren.diablo2remastered.Game;

public abstract class State {
	
	private static State currentState = null;
	
	public abstract void tick();
	public abstract void render(Graphics g);
	
	protected Game game;
	
	public State(Game game) {
		this.game = game;
	}
	
	public static void setState(State state) {
		currentState = state;
	}
	
	public static State getState() {
		return currentState;
	}
	
}
