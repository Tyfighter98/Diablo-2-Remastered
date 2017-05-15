package tech.twarren.diablo2remastered;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import tech.twarren.diablo2remastered.display.Display;
import tech.twarren.diablo2remastered.gfx.Assets;
import tech.twarren.diablo2remastered.input.KeyManager;
import tech.twarren.diablo2remastered.states.State;
import tech.twarren.diablo2remastered.states.GameState;

public class Game implements Runnable {
	
	private Display display;
	private Thread thread;
	private Boolean isRunning = false;
	
	private BufferStrategy bs;
	private Graphics g;
	
	public int width, height;
	public String title;
	
	// States
	private State gameState;
	private State menuState;
	
	private KeyManager keyManager;
	
	
	public Game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keyManager = new KeyManager();
	}

	private void init() {
		display = new Display(title, width, height);
		display.getFrame().addKeyListener(keyManager);
		Assets.init();
		
		gameState = new GameState(this);
		menuState = new GameState(this);
		State.setState(gameState);
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
		
		while (isRunning) {
			now = System.nanoTime();
			delta += (now- lastTime) / timePerTick;
			lastTime = now;
			
			if (delta >= 1) {
				tick();
				render();
				delta--;
			}	
		}
		
		stop();
	}
	
	private void tick() {
		keyManager.tick();
		
		if (State.getState() != null) {
			State.getState().tick();
		}
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
		
		if (State.getState() != null) {
			State.getState().render(g);
		}
		
		// Draw here!
		
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
	
	public KeyManager getKeyManager() {
		return keyManager;
	}
	
}
