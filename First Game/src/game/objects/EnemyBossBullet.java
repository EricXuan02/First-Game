package game.objects;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import game.main.*;

public class EnemyBossBullet extends GameObject
{
	private Handler handler;
	Random r = new Random();
	
	public EnemyBossBullet(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = (r.nextInt(10)-5);
		velY = (r.nextInt(10)-5);
		
	}
	
	public void tick()
	{
		x+=velX;
		y+=velY;
		
		//if (y<=0||y>=Game.HEIGHT - 60)
			//velY*=-1;
		//if (x<=0||x>=Game.WIDTH - 33)
			//velX*=-1;
		
		if (y>=Game.HEIGHT||y<=-16)handler.removeObject(this); 
		if (x>=Game.WIDTH||x<=-16)handler.removeObject(this); 
		if(velX==0&&velY==0)
			handler.removeObject(this);
		
		
		//if (Math.abs(velX)>0||Math.abs(velY)>0)
			//handler.addObject(new Trail(x,y,16,16, 0.02f, ID.Trail, this.handler, Color.red));
	}
	
	public void render(Graphics g)
	{
//		Graphics2D g2d = (Graphics2D) g; //This has the draw() method
		
		//g.setColor(Color.GREEN);
		//g2d.draw(getBounds());   <--- Draws the bounds of the enemy
		
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 16, 16);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,16,16);
	}

}
