package travelingsalesman;

public class City {
	private String name;
	private int x,y;
	public City(int x, int y, String name) {
		this.setName(name);
		this.setX(x);
		this.setY(y);
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
}
