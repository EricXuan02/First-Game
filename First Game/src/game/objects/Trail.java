package game.objects;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Trail extends GameObject
{
	private float alpha = 1;
	private Handler handler;
	private Color color;
	private int width,height;
	private float life;//0.001 < life < 1
	
	public Trail(float x, float y, int width, int height, float life, ID id, Handler handler, Color color) {
		super(x, y, id);
		this.handler = handler;
		this.width = width;
		this.height = height;	
		
		this.color = color;//new Color((int) (color.getRed()*0.5),(int) (color.getGreen()*0.5),(int) (color.getBlue()*0.5));
		this.life = life;
	}

	public void tick() {
		if (alpha>life)	//decreases the brightness of the trail particle every tick
		{
			alpha-=life-0.005f;
		}
		else 
		{
			handler.removeObject(this); //removes this object from the handler's LL when it is no longer visible
		}
	}
	public void render(Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setComposite(makeTransparent(alpha));
		
		g.setColor(color);
		g.fillRect((int)x+width/8, (int)y+height/8, width/4*3, height/4*3);
		
		g2d.setComposite(makeTransparent(1));
	}
	
	private AlphaComposite makeTransparent(float alpha)
	{
		int type = AlphaComposite.SRC_OVER;
		return (AlphaComposite.getInstance(type, alpha));
	}

	public Rectangle getBounds() {
		return null;
	}

}
