package breadthfirstsearch.logic;

public class Coordinates 
{
	private int x, y;
	
	public int getX() 
	{
		return x;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public int getY() 
	{
		return y;
	}

	public void setY(int y) 
	{
		this.y = y;
	}
	
	public Coordinates(int y, int x)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return "x:"+ this.x + " y:" + this.y;
	}
	

	
	
}
