package game.playerInput;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.objects.*;

public class MouseInput extends MouseAdapter
{	
	private Handler handler;
	int mx, my;
	
	public MouseInput(Handler handler)
	{
		this.handler = handler;
	}
	
	public void mouseClicked(MouseEvent e)
	{
		mx = e.getX();
		my = e.getY();
		
		System.out.println("This happened");
		//handler.addObject(new BasicEnemy(r.nextInt(640-34),r.nextInt(480-55),ID.BasicEnemy, handler));
		
		handler.addObject(new PlayerBullet(0, 0, 0, 0, null, handler));
	}
}
