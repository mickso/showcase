package queens;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JFrame;

import queens.tiles.Tile;


public class Game {
	//private final int DIMENSION = 100, TILEDIMENSION = 10, AMOUNTOFQUEENS = 100, INTERVAL = 0;
	private final int DIMENSION = 8, TILEDIMENSION = 100, AMOUNTOFQUEENS = 8, INTERVAL = 100;
	private Board board;
	private Tile[][] tiles;
	private Tile potentialTile;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.createBoard();
		game.start();
	}
	private void createBoard() {
		JFrame frame = new JFrame("Queens");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		int boardDimension = DIMENSION * TILEDIMENSION;
		frame.setPreferredSize(new Dimension(boardDimension + 5, boardDimension + 35));
		board = new Board(DIMENSION, TILEDIMENSION);
		frame.add(board);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	private void start() {
		try {
			//placeQueens(getRandom(DIMENSION), getRandom(DIMENSION));
			placeQueens(0, 0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void placeQueens(int x, int y) throws InterruptedException{
		board.initialize(DIMENSION, TILEDIMENSION);
		tiles = board.getTiles();
		placeQueen(x, y);
		queens = new ArrayList<>();
		int amountOfQueens = 1;
		while(amountOfQueens < AMOUNTOFQUEENS) {
			int[] next = getNextEmptyTile();
			if(next == null) {
				int newX = x + 1;
				if(newX < DIMENSION) {
					placeQueens(newX, y);
				}else if(y + 1 < DIMENSION) {
					placeQueens(0, y + 1);
				}else {
					placeQueens(0, 0);
				}
				return;
			}else {
				placeQueen(next[0], next[1]);
				queens.add(tiles[y][x]);
				potentialTile.setIsLowest(false);
				amountOfQueens += 1;
				board.repaint();
			}
			
		}
	}
	private void placeQueen(int x, int y) {
		Tile target = tiles[y][x];
		if(!target.hasQueen() && target.isAccessible()) {
			target.setHasQueen(true);
			manipulateTiles(x, y);
		}
	}
	private int getRandom(int max) {
		return (int) (Math.random() * max);
	}
	ArrayList<Tile> queens = new ArrayList<>();
	
	private void manipulateTiles(int xQueen, int yQueen) {
		for(int x = 0;x < DIMENSION;x++) {
			for(int y = 0;y < DIMENSION;y++) {
				if(x == xQueen || y == yQueen || (xQueen - x == yQueen - y) || (xQueen - x == -(yQueen - y))) {
					tiles[y][x].setIsAccessible(false);
				}
			}
		}
	}
	private int[] getNextEmptyTile() {
		ArrayList<int[]> emptyTiles = new ArrayList<int[]>();
		int nextTileIndex = 0, lowestAmountOfPotentialTiles = -1;
		for(int x = 0;x < DIMENSION;x++) {
			for(int y = 0;y < DIMENSION;y++) {
				if(!tiles[y][x].hasQueen() && tiles[y][x].isAccessible()) {
					int[] emptyTile = new int[]{x,y};
					emptyTiles.add(emptyTile);
					int amountOfPotentialTiles;
					try {
						amountOfPotentialTiles = getAmountOfPotentialTiles(x, y);
						if(lowestAmountOfPotentialTiles == -1 || amountOfPotentialTiles < lowestAmountOfPotentialTiles) {
							if(potentialTile != null) {
								potentialTile.setIsLowest(false);
								potentialTile.amount = 1000;
							}
							nextTileIndex = emptyTiles.size() - 1;
							lowestAmountOfPotentialTiles = amountOfPotentialTiles;
							potentialTile = tiles[y][x];
							potentialTile.setIsLowest(true);
							tiles[y][x].amount = amountOfPotentialTiles;
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		if(emptyTiles.size() > 0) {
			
			return emptyTiles.get(nextTileIndex);
		}
		return null;
	}
	private int getAmountOfPotentialTiles(int xQueen, int yQueen) throws InterruptedException {
		int amountOfEmptyTiles = 0;
		tiles[yQueen][xQueen].setHasQueen(true);
		for(int x = 0;x < DIMENSION;x++) {
			for(int y = 0;y < DIMENSION;y++) {
				if(x == xQueen || y == yQueen || (xQueen - x == yQueen - y) || (xQueen - x == -(yQueen - y))) {
					if(!tiles[y][x].hasQueen() && tiles[y][x].isAccessible()) {
						amountOfEmptyTiles += 1;
					}
				}
			}
		}
		System.out.println(amountOfEmptyTiles);
		Thread.sleep(INTERVAL);
		tiles[yQueen][xQueen].setHasQueen(false);
		board.repaint();
		return amountOfEmptyTiles;
	}
}
