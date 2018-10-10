package breadthfirstsearch;

import breadthfirstsearch.logic.Coordinates;
import breadthfirstsearch.logic.Map;
import breadthfirstsearch.rendering.cli.*;

public class MazeGame 
{	
	private Map gameMap;
	
	public MazeGame()
	{
		setGameMap(new Map());		
	}

	public Map getGameMap() 
	{
		return gameMap;
	}
	
	public void BruteForceGame()
	{
		Coordinates startingPos = gameMap.find('s');
		Coordinates destinationPos = gameMap.find('x');
		Integer[][] breadthFirstSearchMap = gameMap.breadthFirstSearch(startingPos, destinationPos);
		CliRenderer.printMap(gameMap.getMap());
		System.out.println();
		System.out.println();
		CliRenderer.printFirstBreadthSearchResult(breadthFirstSearchMap);		
		System.out.println();
		System.out.println();
		Character[][] fastestPath = gameMap.getBestPathFromResult(destinationPos, breadthFirstSearchMap);
		CliRenderer.printMap(fastestPath);		
	}
	


	public void setGameMap(Map gameMap) 
	{
		this.gameMap = gameMap;
	}

}
