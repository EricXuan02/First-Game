package game.playerInput;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
//import java.util.Random;

import game.main.*;
import game.objects.*;

public class KeyInput extends KeyAdapter
{
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
//	private Game game;
	
//	private Random r = new Random();
//	private HUD h = new HUD();
	
	public KeyInput (Handler handler, Game game)
	{
		this.handler = handler;
//		this.game = game;
		
		keyDown[0] = false;//W
		keyDown[1] = false;//S
		keyDown[2] = false;//D
		keyDown[3] = false;//A
	}
	
	public void keyPressed(KeyEvent e)
	{
		int key = e.getKeyCode();
		//System.out.println(key);
		
		for (int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId()==ID.Player)
			{
				//KeyEvents for Player ID
				if (key==KeyEvent.VK_W) {
					tempObject.setVelY(-5);
					keyDown[0]=true;
				}
				if (key==KeyEvent.VK_S) {
					tempObject.setVelY(5);
					keyDown[1]=true;
				}
				if (key==KeyEvent.VK_D) {
					tempObject.setVelX(5);
					keyDown[2]=true;
				}
				if (key==KeyEvent.VK_A) {
					tempObject.setVelX(-5);
					keyDown[3]=true;
				}
			}
		}
		
//		if (key==KeyEvent.VK_R)
//		{
//			h.HEALTH = 100;
//		}
		
		//To be implemented - pressing space will open up the shop menu
//		if (key==KeyEvent.VK_SPACE)
//		{
//			if (Game.gameState == STATE.Game)
//				Game.gameState = STATE.Shop;
//			else if (Game.gameState == STATE.Shop)
//				Game.gameState = STATE.Game;
//		}
		
	}
	
	public void keyReleased(KeyEvent e)
	{
		int key = e.getKeyCode();
		
		for (int i = 0; i < handler.object.size(); i++)
		{
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId()==ID.Player)
			{
				//KeyEvents for Player ID
				if (key==KeyEvent.VK_W) {
					keyDown[0]=false;
					//tempObject.setVelY(0);
				}
				if (key==KeyEvent.VK_S) {
					keyDown[1]=false;
					//tempObject.setVelY(0);
				}
				if (key==KeyEvent.VK_D) {
					keyDown[2]=false;
					//tempObject.setVelX(0);
				}
				if (key==KeyEvent.VK_A) {
					keyDown[3]=false;
					//tempObject.setVelX(0);
				}
				
				//Vertical Movement
				if(!keyDown[0]&&!keyDown[1]) tempObject.setVelY(0);
				//Horizontal Movement
				if(!keyDown[2]&&!keyDown[3]) tempObject.setVelX(0);
			}
			
		}
		
		
	}
}
