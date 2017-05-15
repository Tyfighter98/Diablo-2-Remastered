package tech.twarren.diablo2remastered.gfx;

import java.awt.image.BufferedImage;

public class Assets {

	private static final int tWidth = 45, tHeight = 45;
	private static final int zWidth = 72, zHeight = 72;
	
	public static BufferedImage player, dirt, grass, flower;
	
	// Loads in assets to the game
	public static void init() {
		SpriteSheet terrain = new SpriteSheet(ImageLoader.loadImage("/textures/terrain_sprite_sheet.png"));
		SpriteSheet enitites = new SpriteSheet(ImageLoader.loadImage("/textures/zelda_sprite_sheet.png"));
		
		
		player = enitites.crop(0, 0, zWidth, zHeight);
		dirt = terrain.crop(4 * tWidth, tHeight, tWidth, tHeight);
		grass = terrain.crop(tWidth, 0, tWidth, tHeight);
		flower = terrain.crop(2 * tWidth, 0, tWidth, tHeight);
	}

}
