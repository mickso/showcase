package queens;
import java.awt.Graphics;

import javax.swing.JPanel;

import queens.tiles.BlackTile;
import queens.tiles.Tile;
import queens.tiles.WhiteTile;


public class Board extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6248457438917110249L;
	
	private Tile[][] tiles;
	
	public Board(int dimension, int tileDimension) {
		initialize(dimension, tileDimension);
	}
	public void initialize(int dimension, int tileDimension) {
		tiles = new Tile[dimension][dimension];
		Tile.setTimeDimension(tileDimension);
		for(int x = 0;x < dimension;x++) {
			for(int y = 0;y < dimension;y++) {
				if(x % 2 == 0 && y % 2 == 0 || x % 2 != 0 && y % 2 != 0) {
					tiles[x][y] = new WhiteTile(x * tileDimension, y * tileDimension);
				}else {
					tiles[x][y] = new BlackTile(x * tileDimension, y * tileDimension);
				}
			}
		}
	}
	public Tile[][] getTiles() {
		return tiles;
	}
	public void setTiles(Tile[][] tiles) {
		this.tiles = tiles;
	}
	@Override
	public void paint(Graphics g) {
		for(int x = 0;x < tiles.length;x++) {
			for(int y = 0;y < tiles.length;y++) {
				tiles[x][y].draw(g);
			}
		}
	}
}
