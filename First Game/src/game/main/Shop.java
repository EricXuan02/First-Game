package game.main;

import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import game.objects.Handler;

public class Shop extends MouseAdapter
{
	Handler handler;
	
	public Shop(Handler handler)
	{
		this.handler = handler;
	}
	
	public void render(Graphics g)
	{
		g.drawString(Game.FPS + " FPS", 550, 50);
	}
	
	public void mousePressed(MouseEvent e)
	{
		
	}
}
