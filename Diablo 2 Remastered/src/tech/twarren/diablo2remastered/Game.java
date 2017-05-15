package tech.twarren.diablo2remastered;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import tech.twarren.diablo2remastered.display.Display;
import tech.twarren.diablo2remastered.gfx.Assets;

public class Game implements Runnable {
	
	private Display display;
	private Thread thread;
	private Boolean isRunning = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public int width, height;
	public String title;
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
	}

	private void init() {
		display = new Display(title, width, height);
		Assets.init();
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
		g.drawImage(Assets.grass, coord(0), coord(0), null);
		g.drawImage(Assets.dirt, coord(1), coord(0), null);
		g.drawImage(Assets.player, (width/2)-36, (height/2)-36, null);
		
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
	
	public int coord(int coordinate) {
		int location = coordinate * 45;
		return location;
	}
}
