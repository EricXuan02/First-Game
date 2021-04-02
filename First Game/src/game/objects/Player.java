package game.objects;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.main.*;

public class Player extends GameObject
{
	Handler handler;
	private int WIDTH, HEIGHT;
	
	public Player(int x, int y, int width, int height, ID id, Handler handler) {
		super(x, y, id);
		//this.velX = r.nextInt(5);
		//this.velY = r.nextInt(5);
		this.handler = handler;
		this.WIDTH = width;
		this.HEIGHT = height;
	}
	
	public void tick()
	{
		x+=velX;
		y+=velY;
		
		x = Game.clamp(x, 0, Game.WIDTH-48);
		y = Game.clamp(y, 0, Game.HEIGHT - 70);
		
		collision();
		if (Math.abs(velX)>0||Math.abs(velY)>0)
			handler.addObject(new Trail(x,y, WIDTH, HEIGHT, 0.05f, ID.Trail, handler, Color.WHITE));
	}
	
	private void collision()
	{
		for (int i = 0; i<handler.object.size();i++)
		{
			GameObject tempObject = handler.object.get(i); //tempObject is now BasicEnemy
			
			if(tempObject.getId()==ID.BasicEnemy||tempObject.getId()==ID.FastEnemy||tempObject.getId()==ID.SmartEnemy)
			{
				if (getBounds().intersects(tempObject.getBounds()))
					HUD.HEALTH-=2;
			}
			if(tempObject.getId()==ID.EnemyBoss)
			{
				if (getBounds().intersects(tempObject.getBounds()))
					HUD.HEALTH-=999;
			}
			
		}
	}
	
	public void render(Graphics g) {
		
//		Graphics2D g2d = (Graphics2D) g; //This has the draw() method
		
		//g.setColor(Color.GREEN);
		//g2d.draw(getBounds()); <--- Draws the bounds of the player
		
		g.setColor(Color.WHITE);
		g.fillRect((int)x, (int)y, WIDTH, HEIGHT);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,32,32);
	}
	
}
