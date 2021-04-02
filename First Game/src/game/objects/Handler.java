package game.objects;
import java.awt.Graphics;
import java.util.LinkedList;

//This class handles objects in the game.
/*Loops through every object in the game
and individually handles and renders them.*/
public class Handler
{
	public LinkedList<GameObject> object = new LinkedList<GameObject>();
	public void tick()
	{
		for (int i = 0; i < object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.tick();
		}
	}
	
	public void render(Graphics g)
	{
		for (int i = 0; i<object.size(); i++)
		{
			GameObject tempObject = object.get(i);
			
			tempObject.render(g);
		}
	}
	
	public void clearAllEnemies()
	{
		for (int i = 0; i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			if(tempObject.getId()!=ID.Player)
			{
				removeObject(tempObject);
				i--;
			}
		}
	}
	
	public void clearAll()
	{
		for (int i = 0; i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			removeObject(tempObject);
			i--;
		}
	}
	
	public void clearBoss()
	{
		for (int i = 0; i<object.size();i++)
		{
			GameObject tempObject = object.get(i);
			if(tempObject.getId() == ID.EnemyBoss)
			{
				removeObject(tempObject);
				i--;
			}
		}
	}
	
	public void addObject(GameObject object)
	{
		this.object.add(object);
	}
	
	public void removeObject(GameObject object)
	{
		this.object.remove(object);
	}
	
}
