package game.objects;
import java.awt.Color;
import java.awt.Graphics;
//import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import game.main.*;

public class EnemyBoss extends GameObject
{
	private Handler handler;
	private Random r = new Random();
	
	private int timer1 = 80;
	private int timer2 = 50;
	private int timer3 = 50;
	
	private int cDTimer = 100;
	private int countDown = 10;
	
	public EnemyBoss(int x, int y, ID id, Handler handler) {
		super(x, y, id);
		
		this.handler = handler;
		
		velX = 0;
		velY = 1;
		
	}
	
	public void tick()
	{
		x+=velX;
		y+=velY;
		if (timer1 <= 0)
			velY=0;
		else
			timer1--;
		if (timer1 <= 0)
			timer2--;
		if (timer2 <= 0)
		{
			timer3--;
			if (velX==0)
				velX=2;
			if (timer3 <= 0 && velX<10)
				velX+=0.005f;
			
			cDTimer--;
			if (cDTimer<=0 && countDown > 1)
			{
				cDTimer = 100;
				countDown--;
			}
			int spawn = r.nextInt(countDown);
//			System.out.println(countDown);
			if (spawn == 0)
				handler.addObject(new EnemyBossBullet((int)x+25,(int)y+50,ID.BasicEnemy, handler));
		}
		//if (y<=0||y>=Game.HEIGHT - 60)
			//velY*=-1;
		if (x<=0||x>=Game.WIDTH - 64)
			velX*=-1;
		//if (Math.abs(velX)>0||Math.abs(velY)>0)
			//handler.addObject(new Trail(x,y,50,100, 0.05f, ID.Trail, this.handler, Color.RED));
	}
	
	public void render(Graphics g)
	{
//		Graphics2D g2d = (Graphics2D) g; //This has the draw() method
		
		//g.setColor(Color.GREEN);
		//g2d.draw(getBounds());   <--- Draws the bounds of the enemy
		
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, 50, 100);
	}

	public Rectangle getBounds() {
		return new Rectangle((int)x,(int)y,50,100);
	}

}
