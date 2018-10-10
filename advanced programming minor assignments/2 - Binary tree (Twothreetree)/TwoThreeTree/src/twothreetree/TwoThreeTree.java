package twothreetree;

import java.util.ArrayList;

public class TwoThreeTree
{
	private int count;
	private int depth;
	
	private TreeNode rootNode;
	
	public TwoThreeTree(int firstValue) {		
		count = 0;
		depth = 0;
		this.rootNode = new TreeNode(firstValue, this);
	}

	public void addNumber(int number)
	{		
		System.out.println("adding "+number);
		TreeNode target = rootNode;
		while (target.hasChildren())
		{
			target = target.findAddPathChild(number);
		}				
		target.delegateNumber(number);
		this.determineDepth();
		
	}
	
	public int count()
	{
		return this.count;
	}
	
	public int depth()
	{
		return this.depth;
	}
	
	public void setRoot(TreeNode newRoot)
	{
		this.rootNode = newRoot;
	}
	
	private void determineDepth()
	{
		this.determineDepth(this.rootNode, 0);
	}
	
	private void determineDepth(TreeNode target, int depth)
	{
		if (target.hasChildren())
		{
			TreeNode[] children = target.getChildren();
			depth ++;
			for(TreeNode child : children)
			{
				if(child != null)
				{
					this.determineDepth(child, depth);					
				}
				
			}
		}				
		else
		{
			if(depth > this.depth)
			{
				this.depth = depth;
			}
		}		
	}
	
	public void print()
	{
		int maxNumberOfNodes = (int) Math.pow(3, (double)this.depth);
		int maxLines = maxNumberOfNodes+(this.depth+2);
		
		ArrayList<String> renderedLinesCollection = new ArrayList<String>();
		
		for(int i = 0; i < maxLines; i ++)
		{
			renderedLinesCollection.add("");
		}		
		int middleLine = maxLines / 2 + 1;						
		this.rootNode.render(this.depth, renderedLinesCollection, middleLine);
		
		for(int i = 0; i < renderedLinesCollection.size(); i++)
		{
			System.out.println(renderedLinesCollection.get(i));
		}
	}	
}
