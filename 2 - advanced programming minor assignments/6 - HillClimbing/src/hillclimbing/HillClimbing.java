package hillclimbing;


public class HillClimbing {
	private int[][] map;
	private static final int DIMENSION = 10, MAX = 99, CLIMBERS = 8;
	public static void main(String arg[]) {
		HillClimbing hc = new HillClimbing();
		Climber c = new Climber();
		int[][] temp = hc.generateMap();
		for(int i = 0; i < CLIMBERS;i++) {
			c.climb(temp, hc.getRandom(DIMENSION), hc.getRandom(DIMENSION));
		}
	}
	public int[][] generateMap() {
		map = new int[DIMENSION][DIMENSION];
		String result = "";
		int highest = 0;
		for(int y = 0; y < DIMENSION; y++) {
			result += "\n";
			for(int x = 0; x < DIMENSION; x++) {
				map[x][y] = getRandom(MAX);
				if(map[x][y] < 10){
					result += " 0" + map[x][y];
				}else {
					result += " " + map[x][y];
				}
				if(highest < map[x][y]) {
					highest = map[x][y];
				}
			}
		}
		System.out.println(result + "\nHighest: " + highest);
		//System.out.println(map[1][5]);
		return map;
	}
	private int getRandom(int max) {
		return (int)(Math.random() * max);
	}
}
