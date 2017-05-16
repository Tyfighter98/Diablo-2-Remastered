package tech.twarren.diablo2remastered.entities.creatures;

import java.awt.Graphics;

import tech.twarren.diablo2remastered.Game;
import tech.twarren.diablo2remastered.gfx.Assets;

public class Player extends Creature {

	private Game game;
	
	public Player(Game game, float x, float y) {
		super(x, y, Creature.DEFAULT_CREATURE_WIDTH, Creature.DEFAULT_CREATURE_HEIGHT);
		this.game = game;
	}

	public void tick() {
		if (game.getKeyManager().up) {
			y -= 3;
		}
		else if (game.getKeyManager().down) {
			y += 3;
		}
		else if (game.getKeyManager().left) {
			x -= 3;
		}
		else if (game.getKeyManager().right) {
			x += 3;
		}
	}

	public void render(Graphics g) {
		g.drawImage(Assets.player, (int) x, (int) y, null);
	}

	
	
}
