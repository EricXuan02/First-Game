package game.objects;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import game.main.*;

public class MenuParticle extends GameObject
{
	private Handler handler;
	
	Random r = new Random();
	private int red = r.nextInt(255);
	private int green = r.nextInt(255);
	private int blue = r.nextInt(255);
	//GIVES RANDOM COLOR VALUES
	
	private Color col;
	
	private int dir = 0;
	private int fastSpeed = 5, slowSpeed = 3;
	
	public MenuParticle(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		col = new Color(red,green,blue);
		col = col.darker().darker();
		
		dir = r.nextInt(2);
		if (dir==0)
		{
			velX = slowSpeed;
			velY = fastSpeed;
		}
		else if(dir==1)
		{
			velX=fastSpeed;
			velY=slowSpeed;
		}
	}
	
	public float getVelX()
	{
		return velX;
	}
	
	public float getVelY()
	{
		return velY;
	}
	
	public void tick()
	{
		x+=velX;
		y+=velY;
		
		if (y<=0||y>=Game.HEIGHT - 55)
			velY*=-1;
		if (x<=0||x>=Game.WIDTH - 33)
			velX*=-1;
		
		if (Math.abs(velX)>0||Math.abs(velY)>0)
			handler.addObject(new Trail(x,y,8,8, 0.05f, ID.Trail, this.handler, col));
	}
	
	public void render(Graphics g)
	{
//		Graphics2D g2d = (Graphics2D) g; //This has the draw() method
		
		//g.setColor(Color.GREEN);
		//g2d.draw(getBounds());   <--- Draws the bounds of the enemy
		
		g.setColor(col);
		g.fillRect((int)x, (int)y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}

}
