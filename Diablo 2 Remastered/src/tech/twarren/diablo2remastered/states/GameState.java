package tech.twarren.diablo2remastered.states;

import java.awt.Graphics;

import tech.twarren.diablo2remastered.Game;
import tech.twarren.diablo2remastered.entities.creatures.Player;

public class GameState extends State{

	private Player player;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
	}
	
	public void tick() {
		player.tick();
	}

	public void render(Graphics g) {
		player.render(g);
	}

	
	
}
