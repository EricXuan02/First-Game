package game.objects;
import java.awt.Graphics;
import java.awt.Rectangle;

//Every object in the game is considered a game object.
//Every object will inherit properties in this
public abstract class GameObject
{
	//Protected means it can only be affected by the object that inherits it.
	protected float x, y;
	protected ID id;
	protected float velX, velY;
	protected double direction;
	
	public GameObject(float x, float y, ID id)
	{
		this.x = x;
		this.y = y;
		this.id = id;
	}
	
	public abstract void tick();
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	
	public ID getId() {
		return id;
	}
	public void setId(ID id) {
		this.id = id;
	}

	public float getVelX() {
		return velX;
	}
	public void setVelX(float velX) {
		this.velX = velX;
	}

	public float getVelY() {
		return velY;
	}
	public void setVelY(float velY) {
		this.velY = velY;
	}
	
	
}
