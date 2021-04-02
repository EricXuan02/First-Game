package game.main;
import java.awt.Color;
import java.awt.Graphics;

public class HUD
{
	public static int HEALTH = 100;
	
	private int greenValue = 255;
	private int redValue;
	
	private static int score = 0;
	private static int level = 1;
	
	public void tick()
	{
		HEALTH = Game.clamp(HEALTH, 0, 100);
		greenValue = Game.clamp(greenValue, 0, 255);
		greenValue = HEALTH*2;
		redValue = 255-greenValue;
		
		if (HEALTH>0)
			score++;
			
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.GRAY);
		g.fillRect(12, 12, 200, 20);
		g.setColor(new Color (redValue, greenValue, 0));
		g.fillRect(12, 12, HEALTH*2, 20);
		g.setColor(Color.WHITE);
		g.drawRect(12, 12, 200, 20);
		
		g.drawString("Score: " + score, 10,50);
		g.drawString("Level: " + level, 10,70);
		if (HEALTH==0)
			g.drawString("You Lose!", 550, 420);
		
		g.drawString(Game.FPS + " FPS", 550, 50);
	}
	
	public static void reset()
	{
		HEALTH = 100;
		score = 0;
		level = 1;
	}
	
	public void setScore (int score)
	{
		HUD.score = score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public int getLevel()
	{
		return level;
	}
	
	public void setLevel(int level)
	{
		HUD.level = level;
	}
	
	public int getHealth()
	{
		return HEALTH;
	}
	
}
