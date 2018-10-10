package breadthfirstsearch.logic;

import java.util.ArrayList;

public class Map {
			
	private static Character[][] playingField =			
		{
			{'x', 'o', 'o', 'o', 'o', 'o', '#'},
			{'o', '#', '#', 'o', '#', 'o', '#'},
			{'o', 'o', 'o', 'o', '#', 'o', 'o'},
			{'#', '#', '#', 'o', '#', '#', 'o'},
			{'o', 'o', 'o', 'o', '#', 'o', 'o'},
			{'o', '#', '#', '#', '#', '#', 'o'},
			{'o', 'o', 'o', 'o', 'o', 'o', 's'}
		};
	
	public Character[][] getMap()
	{
		return Map.playingField;
	}

	public Coordinates find(char value)
	{	
		for(int y = 0; y < playingField.length; y++)
		{
			for(int x = 0; x < playingField.length; x++)
			{
				if(playingField[y][x] == value)
				{
					return new Coordinates(y, x); 
				}
			}			
		}		
		return null;
	}
	
	public Character[][] getBestPathFromResult(Coordinates destinationPos, Integer[][] result)
	{
		Character[][] returnMap = this.getMap().clone();		
		int currentStep = result[destinationPos.getY()][destinationPos.getX()];				
		Coordinates currentPos = destinationPos;
		
		while(currentStep != 0)
		{	
			returnMap[currentPos.getY()][currentPos.getX()] = '+';		
			Coordinates[] neighbors =  this.getTileCoordinatesArround(currentPos);
			Coordinates fastestRouteNeighbor = currentPos; 
			for(Coordinates neighbor : neighbors)
			{
				if(neighbor != null && result[neighbor.getY()][neighbor.getX()] != null)
				{
					currentStep = result[fastestRouteNeighbor.getY()][fastestRouteNeighbor.getX()];
					int neighborStep = result[neighbor.getY()][neighbor.getX()]; 
					if(neighborStep < currentStep)
					{
						fastestRouteNeighbor = neighbor;
					}					
				}				
			}
			currentPos = fastestRouteNeighbor;			
		}		
		return returnMap;				
	}
	
	private boolean walkAble(Coordinates pos)
	{
		if(Map.playingField[pos.getY()][pos.getX()] != '#')
		{
			return true;
		}
		return false;
	}
	
	private boolean outOfBounds(int y, int x)
	{
		if(y < 0 || y >= Map.playingField.length)
		{
			return true;
		}
		if(x < 0 || x >= Map.playingField.length)
		{
			return true;
		}
		return false;
	}
	
	private Coordinates[] getTileCoordinatesArround(Coordinates pos)
	{		
		int index = 0;
		Coordinates[] returnList = new Coordinates[4];		
				
		//one up
		if(!this.outOfBounds(pos.getY()+1, pos.getX()))
		{
			returnList[index] = new Coordinates(pos.getY()+1, pos.getX());
			index++;
		}
		//one down
		if(!this.outOfBounds(pos.getY()-1, pos.getX()))
		{
			returnList[index] = new Coordinates(pos.getY()-1, pos.getX());
			index++;
		}
		//one to the left
		if(!this.outOfBounds(pos.getY(), pos.getX()-1))
		{
			returnList[index] = new Coordinates(pos.getY(), pos.getX()-1);
			index++;
		}
		//one to the right
		if(!this.outOfBounds(pos.getY(), pos.getX()+1))
		{
			returnList[index] = new Coordinates(pos.getY(), pos.getX()+1);
			index++;			
		}
		return returnList;
	}
		
	public Integer[][] breadthFirstSearch(Coordinates startingPos, Coordinates destinationPos)
	{		
		Integer[][] resultMap = new Integer[Map.playingField.length][Map.playingField.length];
		resultMap[startingPos.getY()][startingPos.getX()] = 0;
		ArrayList<Coordinates> previousCoordinates = new ArrayList<Coordinates>();
		previousCoordinates.add(startingPos);
		
		
		return this.breadthFirstSearch(previousCoordinates, destinationPos, resultMap);
	}
	
	public Integer[][] breadthFirstSearch(ArrayList<Coordinates> previousCoordinates, Coordinates destinationPos, Integer[][] resultMap)
	{			
		//You only want to end this loop with a return.
		while(true)
		{			
			ArrayList<Coordinates> newPreviousCoordinates = new ArrayList<Coordinates>();
			//get neighbors
			//for each coordinates in list
			for(Coordinates previousCoordinate : previousCoordinates)
			{
				Coordinates[] neighbors = this.getTileCoordinatesArround(previousCoordinate);
				
				for(Coordinates neighbor : neighbors)
				{
					if(neighbor != null  && this.walkAble(neighbor))
					{
						if(neighbor.getY() == destinationPos.getY() && neighbor.getX() == destinationPos.getX())
						{
							resultMap[neighbor.getY()][neighbor.getX()] = (resultMap[previousCoordinate.getY()][previousCoordinate.getX()]+1);					
							return resultMap;
						}
						
						if(resultMap[neighbor.getY()][neighbor.getX()] == null)
						{
							resultMap[neighbor.getY()][neighbor.getX()] = (resultMap[previousCoordinate.getY()][previousCoordinate.getX()]+1);
							newPreviousCoordinates.add(neighbor);
						}					
					}
	
				}	
			}
			previousCoordinates = newPreviousCoordinates;
			if(newPreviousCoordinates.size() < 1)
			{
								return resultMap;
			}							
		}
				//get tiles around current. Add count, execute function again with new result map. 													
	}
	
	public void findPathToX()
	{
		
		
	}

}
