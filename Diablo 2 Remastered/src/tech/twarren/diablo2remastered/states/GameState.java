package tech.twarren.diablo2remastered.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tech.twarren.diablo2remastered.Game;
import tech.twarren.diablo2remastered.entities.creatures.Player;
import tech.twarren.diablo2remastered.gfx.Assets;

public class GameState extends State{

	private Player player;
	int flower = 1;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
	}
	
	public void tick() {
		player.tick();
	}

	int x = 0;
	int y = 0;
	BufferedImage terrain = Assets.grass;
	
	public void render(Graphics g) {
		player.render(g);
	}
}
