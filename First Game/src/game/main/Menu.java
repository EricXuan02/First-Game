package game.main;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import game.main.Game.STATE;
import game.objects.BasicEnemy;
import game.objects.GameObject;
import game.objects.Handler;
import game.objects.ID;
import game.objects.Player;
import game.objects.Spawn;

public class Menu extends MouseAdapter
{
//	private Game game;
	private Handler handler;
	private HUD hud;
	
	private Random r = new Random();
	
	public Menu (Game game, Handler handler, HUD hud)
	{
//		this.game = game;
		this.handler = handler;
		this.hud = hud;
	}
	
	public void render(Graphics g)
	{
		if (Game.gameState == STATE.Menu)
		{
			Font f1 = new Font("arial", 1, 50);
			Font f2 = new Font("arial", 1, 30);
		
			g.setColor(Color.WHITE);
			g.drawString(Game.FPS + " FPS", 550, 50);
			
			g.setFont(f1);
			g.setColor(Color.WHITE);
			g.drawString("Menu", 255, 70);
		
			g.setFont(f2);
			g.drawRect(220, 105, 200, 64);
			g.drawString("Play", 287, 148);
		
			g.drawRect(220, 205, 200, 64);
			g.drawString("Help", 287, 248);
		
			g.drawRect(220, 305, 200, 64);
			g.drawString("Quit", 287, 348);
			
		}
		
		if (Game.gameState == STATE.Help)
		{
			Font f1 = new Font("arial", 1, 50);
			Font f2 = new Font("arial", 1, 30);
			Font f3 = new Font("arial", 1, 12);
			
			g.setColor(Color.WHITE);
			g.drawString(Game.FPS + " FPS", 550, 50);
			
			g.setFont(f1);
			g.setColor(Color.WHITE);
			g.drawString("Help", 255, 70);
			
			g.setFont(f3);
			g.drawString("You are the white square. Move around using W,A,S,D to avoid the enemies.", 15, 110);
			g.drawString("At every 100 points, you go up a level. After a certain amount of levels, the enemy boss will spawn.", 15, 130);
			
			g.setFont(f2);
			g.drawRect(220, 305, 200, 64);
			g.drawString("Back", 287, 348);
		}
		
		if (Game.gameState == STATE.GameOver)
		{
			Font f1 = new Font("arial", 1, 50);
			Font f2 = new Font("arial", 1, 30);
			Font f3 = new Font("arial", 1, 15);
			
			g.setColor(Color.WHITE);
			
			g.drawString(Game.FPS + " FPS", 550, 50);
			
			g.setFont(f1);
			g.drawString("Game Over!", 185, 120);
			
			g.setFont(f3);
			g.drawString("Score: " + hud.getScore(), 290,190);
			g.drawString("Level: " + hud.getLevel(), 290,210);
			
			g.setFont(f2);
			g.drawRect(220, 305, 200, 64);
			g.drawString("Retry", 280, 348);
			
			
		}
	}
	
	public void tick()
	{
		
	}
	
	public void mousePressed(MouseEvent e)
	{
		int mx = e.getX();
		int my = e.getY();
		
		//Play Button
		if (mouseOver(mx,my,220,105,200,64) && Game.gameState == STATE.Menu)
		{
//			System.out.println("Clicking Play");
			Game.gameState = STATE.Game;
			handler.addObject(new Player((640-32)/2,(480-32)/2, 32, 32,ID.Player, handler));
			handler.addObject(new BasicEnemy(r.nextInt(640-34),r.nextInt(480-60),ID.BasicEnemy, handler));
			
			for (int i = 0; i<handler.object.size();i++)
			{
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId()==ID.MenuParticle)
				{
					handler.removeObject(tempObject);
					i--;
				}
			}
			
			
		}
		
		//Help Button
		if (mouseOver(mx,my,220, 205, 200, 64) && Game.gameState == STATE.Menu)
		{
//			System.out.println("Clicking Help");
			Game.gameState = STATE.Help;
		}
		
		//Quit Button
		if (mouseOver(mx,my,220, 305, 200, 64) && Game.gameState == STATE.Menu)
		{
//			System.out.println("Clicking Quit");
			System.exit(0);
		}
		
		//Back Button (Help Screen)
		if (mouseOver(mx,my,220, 305, 200, 64) && Game.gameState == STATE.Help)
		{
//			System.out.println("Clicking Back");
			Game.gameState = STATE.Menu;
		}
		
		//Retry Button (Game Over Screen)
		if (mouseOver(mx,my,220, 305, 200, 64) && Game.gameState == STATE.GameOver)
		{
//			System.out.println("Clicking Retry");
			HUD.reset();
			Spawn.scoreKeep = 0;
			Game.gameState = STATE.Menu;
			
//			handler.addObject(new Player((640-32)/2,(480-32)/2, 32, 32,ID.Player, handler));
//			handler.addObject(new BasicEnemy(r.nextInt(640-34),r.nextInt(480-60),ID.BasicEnemy, handler));
			
			for (int i = 0; i<handler.object.size();i++)
			{
				GameObject tempObject = handler.object.get(i);
				if(tempObject.getId()==ID.MenuParticle)
				{
					handler.removeObject(tempObject);
					i--;
				}
			}
		}
		
	}
	
	public void mouseReleased(MouseEvent e)
	{
		
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height)
	{
		if (mx>x && mx<x+width && my>y && my<y+height)
			return true;
		return false;
	}
}
