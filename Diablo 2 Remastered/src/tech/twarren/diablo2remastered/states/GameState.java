package tech.twarren.diablo2remastered.states;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import tech.twarren.diablo2remastered.Game;
import tech.twarren.diablo2remastered.entities.creatures.Player;
import tech.twarren.diablo2remastered.gfx.Assets;
import tech.twarren.diablo2remastered.tiles.Tile;
import tech.twarren.diablo2remastered.worlds.World;

public class GameState extends State{

	private World world;
	private Player player;
	int flower = 1;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
		world = new World("res/worlds/world1.txt");
	}
	
	public void tick() {
		world.tick();
		player.tick();
	}

	int x = 0;
	int y = 0;
	BufferedImage terrain = Assets.grass;
	
	public void render(Graphics g) {
		Tile.tiles[2].render(g, 0, 0);
		world.render(g);
		player.render(g);
	}
}
