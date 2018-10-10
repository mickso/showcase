package breadthfirstsearch.rendering.cli;

public class CliRenderer 
{
	public static void printMap(Character[][] map)
	{
		for(Character[] mapLine : map)
		{
			for(Character resultItem : mapLine)
			{
				System.out.format(" %c ", resultItem);
				System.out.print(" ");			
			}
			System.out.println();
		}
	}
	
	public static void printFirstBreadthSearchResult(Integer[][] breadthFirstSearchMap)
	{
		for(Integer[] mapLine : breadthFirstSearchMap)
		{
			for(Integer resultItem : mapLine)
			{
				if(resultItem != null)
				{
					System.out.format("%03d", resultItem);
					System.out.print(" ");
				}
				else
				{
					System.out.print(" x ");
					System.out.print(" ");
				}
				
			}
			System.out.println();
		}
	}
}
