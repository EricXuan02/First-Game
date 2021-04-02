package game.objects;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.main.Game;

public class FastEnemy extends GameObject
{
	private Handler handler;
	
	public FastEnemy(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 5;
		velY = 10;
		
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
			handler.addObject(new Trail(x,y,8,8, 0.05f, ID.Trail, this.handler, Color.BLUE));
	}
	
	public void render(Graphics g)
	{
//		Graphics2D g2d = (Graphics2D) g; //This has the draw() method
		
		//g.setColor(Color.GREEN);
		//g2d.draw(getBounds());   <--- Draws the bounds of the enemy
		
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, 8, 8);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,8,8);
	}

}
