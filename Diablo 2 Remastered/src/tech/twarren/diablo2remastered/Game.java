package tech.twarren.diablo2remastered;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import tech.twarren.diablo2remastered.display.Display;
import tech.twarren.diablo2remastered.gfx.ImageLoader;
import tech.twarren.diablo2remastered.gfx.SpriteSheet;

public class Game implements Runnable {
	
	private Display display;
	private Thread thread;
	private Boolean isRunning = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	private BufferedImage link;
	private BufferedImage terrain;
	private SpriteSheet sheet;
	
	public int width, height;
	public String title;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	private void init() {
		display = new Display(title, width, height);
		link = ImageLoader.loadImage("/textures/link.png");
		terrain = ImageLoader.loadImage("/textures/terrain_sprite_sheet.png");
		sheet = new SpriteSheet(terrain);
	}
	
	private void tick() {
		
	}
	
	private void render() {
		bs = display.getCanvas().getBufferStrategy();
		
		if (bs == null) {
			display.getCanvas().createBufferStrategy(3);
			return;
		}
		
		g = bs.getDrawGraphics();
		 
		// Clear Screen
		g.clearRect(0, 0, width, height);
		
		// Draw here!
		g.drawImage(sheet.crop(0, 0, 45, 45), 0, 0, null);
		g.drawImage(link, imageCenter(66), imageCenter(84), null);
		
		// Push to screen
		bs.show();
		g.dispose();
		
	}
	
	// Called after thread is started
	// Majority of code goes here
	public void run() {
		
		init();
		
		while (isRunning) {
			tick();
			render();	
		}
		
		stop();
	}
	
	// synchronized is used with threads
	public synchronized void start() {
		if (isRunning) {
			return;
		}
		
		thread = new Thread(this);
		thread.start();
		isRunning = true;
	}
	
	public synchronized void stop() {
		if(!isRunning) {
			return;
		}
		
		isRunning = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private int imageCenter(int size) {
		int location = (width/2)-(size/2);
		return location;
	}

}
