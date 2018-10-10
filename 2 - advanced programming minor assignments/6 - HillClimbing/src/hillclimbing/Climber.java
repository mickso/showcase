package hillclimbing;

public class Climber {
	private final int[] UP = { 0, 1 }, DOWN = { 0, -1 }, LEFT = { -1, 0 }, RIGHT = { 1, 0 };
	private final int[][] searchDirections = { UP, DOWN, LEFT, RIGHT };
	public Climber() {
		
	}
	public void climb(int[][] map, int x, int y) {
		int highestValue = map[x][y];
		System.out.println("Start op " + x + "," + y + " = " + highestValue);
		climbNext(map, x, y, highestValue);
	}
	private void climbNext(int[][] map, int x, int y, int highestValue) {
		int[] next = null;
		for(int i = 0; i < searchDirections.length; i++) {
			int newX = x + searchDirections[i][0],
				newY = y + searchDirections[i][1];
			if(canReach(map, newX, newY)) {
				int newValue = map[newX][newY];
				if(newValue > highestValue) {
					highestValue = newValue;
					next = new int[]{newX, newY, highestValue};
				}
			}
		}
		if(next != null) {
			climbNext(map, next[0], next[1], next[2]);
		}else {
			System.out.println("Local max found: " + x + "," + y + " = " + highestValue);
		}
	}
	private boolean canReach(int[][] map, int x, int y) {
		return x < map.length && x >= 0 && y >=0 && y < map[0].length;
	}
}
