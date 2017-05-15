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
	
	// Called after thread is started
	// Majority of code goes here
	public void run() {
		
		init();
		
		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while (isRunning) {
			now = System.nanoTime();
			delta += (now- lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}	
			
			if (timer >= 1000000000) {
				System.out.println("Ticks and Frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		
		stop();
	}
	
	int x = 0;
	
	private void tick() {
		x += 1;
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
		g.drawImage(Assets.grass, x, coord(0), null);
		g.drawImage(Assets.dirt, coord(1), coord(0), null);
		g.drawImage(Assets.player, (width/2)-36, (height/2)-36, null);
		
		// Push to screen
		bs.show();
		g.dispose();
		
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
	
	public int coord (int coordinate) {
		int location = coordinate * 45;
		return location;
	}
}
