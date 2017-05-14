package tech.twarren.diablo2remastered.gfx;

import java.awt.image.BufferedImage;

public class SpriteSheet {
	
	private BufferedImage sheet;
	
	public SpriteSheet(BufferedImage sheet) {
		this.sheet = sheet;
	}

	// (0,0) is the first tile
	// Each tile is a unit of 1
	public BufferedImage crop(int x, int y, int width, int height) {
		x = (x*45)+(1*x);
		y = (y*45)+(1*y);
		
		if (x < 0 || y < 0) {
			System.out.println("Sprite Location does not exists at" + " ("+ (x/45) + "," + (y/45) + ")");
			System.exit(1);
			return null;
		}
		else {
			return sheet.getSubimage(x, y, width, height);
		}
		
	}
}
