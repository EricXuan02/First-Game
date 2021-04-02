package game.objects;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.main.Game;

public class SmartEnemy extends GameObject
{
	private Handler handler;
	private GameObject player;
	
	public SmartEnemy(int x, int y, ID id, Handler handler)
	{
		super(x, y, id);
		this.handler = handler;
		
		for (int i = 0; i<handler.object.size();i++)
		{
			if (handler.object.get(i).getId()==ID.Player)
			{
				player = handler.object.get(i);
			}
		}			
	}
	
	public void tick()
	{
		float diffX = x-player.getX()-8;
		float diffY = y-player.getY()-8;
		
		velX = (-diffX/50);
		velY = (-diffY/50);
		
		x+=velX;
		y+=velY;
		
		if (y<=0||y>=Game.HEIGHT - 60)
			velY*=-1;
		if (x<=0||x>=Game.WIDTH - 33)
			velX*=-1;
		
		if (Math.abs(velX)>0||Math.abs(velY)>0)
			handler.addObject(new Trail(x,y,16,16, 0.05f, ID.Trail, this.handler, Color.MAGENTA));
	}
	
	public void render(Graphics g)
	{
//		Graphics2D g2d = (Graphics2D) g; //This has the draw() method
		
		//g.setColor(Color.GREEN);
		//g2d.draw(getBounds());   <--- Draws the bounds of the enemy
		
		g.setColor(Color.MAGENTA);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}

}
