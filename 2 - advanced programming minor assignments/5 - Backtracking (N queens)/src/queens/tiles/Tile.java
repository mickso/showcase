package queens.tiles;

import java.awt.Color;
import java.awt.Graphics;


public abstract class Tile {
	
	private Color color;
	private boolean isAccessible, hasQueen, isLowest;
	public static int tileDimension;
	private int x, y, queenPadding, accessiblePadding, potentialPadding;
	public int amount;
	public Tile(Color color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
		isAccessible = true;
		hasQueen = false;
		queenPadding = tileDimension / 8;
		accessiblePadding = tileDimension / 4;
		potentialPadding = tileDimension / 4;
	}
	public Color getColor() {
		return color;
	}
	public void setHasQueen(boolean hasQueen) {
		this.hasQueen = hasQueen;
	}
	public void setIsAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}
	public boolean isAccessible() {
		return isAccessible;
	}
	public boolean hasQueen() {
		return hasQueen;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
		
	}
	public void setIsLowest(boolean isLowest) {
		this.isLowest = isLowest;
	}
	public static void setTimeDimension(int tileDimension) {
		Tile.tileDimension = tileDimension;
	}
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, tileDimension, tileDimension);
		if(hasQueen) {
			g.setColor(Color.blue);
			g.fillRect(x + queenPadding, y + queenPadding, tileDimension - (2 * queenPadding), tileDimension - (2 * queenPadding));
		}else
		if(!isAccessible) {
			g.setColor(Color.red);
			g.fillRect(x + accessiblePadding, y + accessiblePadding, tileDimension - (2 * accessiblePadding), tileDimension - (2 * accessiblePadding));
		}else {
			//g.setColor(Color.green);
			//g.fillRect(x + accessiblePadding, y + accessiblePadding, tileDimension - (2 * accessiblePadding), tileDimension - (2 * accessiblePadding));
		}
		if(isLowest) {
			g.setColor(Color.yellow);
			g.fillRect(x + potentialPadding, y + potentialPadding, tileDimension - (2 * potentialPadding), tileDimension - (2 * potentialPadding));
		}
	}
}
