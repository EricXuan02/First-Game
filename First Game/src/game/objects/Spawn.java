package game.objects;
import java.util.Random;

import game.main.*;

public class Spawn
{
	private Handler handler;
	private HUD hud;
	
	private Random r = new Random();
	
	public static int scoreKeep = 0;
	
	public Spawn(Handler handler, HUD hud)
	{
		this.handler = handler;
		this.hud = hud;
	}
	
	public void tick()
	{
		if (HUD.HEALTH>0)
			scoreKeep++;
		
		if (scoreKeep>=200)
		{
			scoreKeep=0;
			hud.setLevel(hud.getLevel()+1);
			if (hud.getLevel()%15==1)
			{
				handler.clearAllEnemies();
				handler.addObject(new BasicEnemy(r.nextInt(640-34),r.nextInt(480-60),ID.BasicEnemy, handler));
//				System.out.println("Basic Enemy Spawned");
			}
			if (hud.getLevel()%15==2)
			{
				handler.addObject(new BasicEnemy(r.nextInt(640-34),r.nextInt(480-60),ID.BasicEnemy, handler));
//				System.out.println("Basic Enemy Spawned");
			}
			else if (hud.getLevel()%15==3||hud.getLevel()%15==4)
			{
				handler.addObject(new FastEnemy(r.nextInt(640-34),r.nextInt(480-55),ID.FastEnemy, handler));
//				System.out.println("Fast Enemy Spawned");
			}
			else if (hud.getLevel()%15==5||hud.getLevel()%15==6)
			{
				handler.addObject(new SmartEnemy(r.nextInt(640-34),r.nextInt(480-55),ID.SmartEnemy, handler));
//				System.out.println("Smart Enemy Spawned");
			}
			if (hud.getLevel()%15==10)
			{
				handler.clearAllEnemies();
				handler.addObject(new EnemyBoss(Game.WIDTH/2-40,0,ID.EnemyBoss, handler));
//				System.out.println("Enemy Boss Spawned");
			}
		}
	}
	
	
}
