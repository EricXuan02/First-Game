//FIRST GAME\\

package game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;

import game.objects.*;
import game.playerInput.*;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = -1442798787354930462L;

	public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;

	private Thread thread;
	private boolean running = false;

	private Random r = new Random();
	private Handler handler;
	private HUD hud;
	private Spawn spawner;
	private Menu menu;
	private Shop shop;

	public static int FPS;

	public enum STATE {
		Menu, Help, Game, Shop, GameOver
	};

	public static STATE gameState = STATE.Menu;

	public Game() {
		handler = new Handler();
		menu = new Menu(this, handler, hud);

		shop = new Shop(handler);

		this.addKeyListener(new KeyInput(handler, this));
		this.addMouseListener(menu);

		new Window(WIDTH, HEIGHT, "Game", this);

		hud = new HUD(); // Making a new HUD object
		spawner = new Spawn(handler, hud); // Making a new Spawn object
		menu = new Menu(this, handler, hud);

		for (int i = 0; i < 20; i++) {
			handler.addObject(
					new MenuParticle(r.nextInt(WIDTH - 32), r.nextInt(HEIGHT - 32), ID.MenuParticle, handler));
		}
	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}

	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				// System.out.println("FPS: " + frames);
				FPS = frames;
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		handler.tick();

		if (gameState == STATE.Game) {
			hud.tick();
			spawner.tick();
			if (HUD.HEALTH == 0) {
//				System.out.println("This happened");
				handler.clearAll();
				for (int i = 0; i < 20; i++) {
//					System.out.println("This also happened");
					handler.addObject(
							new MenuParticle(r.nextInt(WIDTH - 32), r.nextInt(HEIGHT - 32), ID.MenuParticle, handler));
				}
				gameState = STATE.GameOver;
			}
		} else if (gameState == STATE.Menu || gameState == STATE.GameOver) {
			menu.tick();
		}
	}

	private void render() {
//		System.out.println("this happened");
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}

		Graphics g = bs.getDrawGraphics();

		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WIDTH, HEIGHT);

		handler.render(g);

		if (gameState == STATE.Game) {
			hud.render(g);
		} else if (gameState == STATE.Menu || gameState == STATE.Help || gameState == STATE.GameOver) {
			menu.render(g);
		} else if (gameState == STATE.Shop) {
			shop.render(g);
		}

		g.dispose();
		bs.show();
	}

	public static int clamp(float var, int min, int max) {
		int v = (int) var;

		if (v >= max)
			return v = max;
		if (var <= min)
			return v = min;
		return v;
	}

	public static void main(String[] args) {
		new Game();
	}

}
