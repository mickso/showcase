package twothreetree;

import java.util.ArrayList;
import java.util.Arrays;

public class TreeNode {
	public static final int PRINT_NODE_CHARSIZE = 6;
	public static final String[] BRANCH_SEPERATORS = { " / ", " - ", " \\ " };

	protected Integer[] values;
	protected TreeNode[] children;
	protected TwoThreeTree wrapper;

	private TreeNode parent;

	public TreeNode(int firstNumber, TwoThreeTree wrapper) {
		this.initialize(firstNumber, null);
		this.wrapper = wrapper;
	}

	public TreeNode(int firstNumber, TwoThreeTree wrapper, TreeNode parent) {
		this.initialize(firstNumber, parent);
		this.wrapper = wrapper;
	}

	public TreeNode(int firstNumber, TwoThreeTree wrapper, TreeNode leftChild,
			TreeNode rightChild) {
		this.initialize(firstNumber, null);
		this.children[0] = leftChild;
		this.children[2] = rightChild;
		leftChild.setParent(this);
		rightChild.setParent(this);
		this.wrapper = wrapper;
	}

	public Integer getLowestValue() {
		return this.values[0];
	}

	public Integer getHighestValue() {
		if (this.values[1] != null) {
			return this.values[1];
		}
		return this.values[0];
	}

	/**
	 * This function decided what to do with a given number.
	 * 
	 * @param number
	 */
	public void delegateNumber(int number) {
		if(!numberAlreadyExists(number))
		{
			// if second value is not null
			if (values[1] == null) {
				this.addValueToThis(number);
			} else {
				this.split(number);
			}			
		}
		else
		{
			System.out.println("Number already exists in tree");
		}
	}
	
	private boolean numberAlreadyExists(int number)
	{
		if(this.values[0] == number)
		{			
			return true;
		}
		if(this.values[1] != null && this.values[1] == number)
		{
			
		}
		return false;
	}

	public void delegateNumberFromChild(TreeNode lowest, int middle,
			TreeNode highest) {
		// if second value is not null
		if (values[1] == null) {
			this.addValueToThis(middle);
			if (middle == this.values[0]) {
				this.children[0] = lowest;
				this.children[1] = highest;
			} else {
				this.children[1] = lowest;
				this.children[2] = highest;
			}
			lowest.setParent(this);
			highest.setParent(this);
		} else {
			this.splitWithChildren(lowest, middle, highest);
		}
	}
	
	public void render(int depth,
			ArrayList<String> renderedLinesCollection, int currentLinePosition) {						

		int currentLineLength = addToPrintLine(renderedLinesCollection, currentLinePosition, this.renderNode());
		
		int currentLineSpacing = depth * 2 - 1;
		
		if(this.hasChildren())
		{
			for (int i = 0; i < 3; i ++)
			{
				int offset = currentLineLength;
				int linePosition = currentLinePosition;
				switch(i)
				{			
				case 0:				
					linePosition -= currentLineSpacing;
					break;
				case 1: 
					offset = 0;
					break;				
				case 2:
					linePosition += currentLineSpacing;
					break;			
				}			
				this.renderChild(i, depth, renderedLinesCollection, linePosition, offset);
			}	
		}
				
	}
	
	/**
	 * 
	 * @param renderedLinesCollection
	 * @param position
	 * @param addition
	 * @return length of the new line
	 */
	private int addToPrintLine(ArrayList<String> renderedLinesCollection, int position, String addition)
	{
		String currentLine = renderedLinesCollection.get(position);
		currentLine+= addition;	
		renderedLinesCollection.set(position, currentLine);		
		
		return currentLine.length();
	}
	
	private void renderChild(int i, int depth, ArrayList<String> renderedLinesCollection, int linePosition, int offset)
	{		
		//add offset to Child position
		String pre = TreeNode.getStringWithLengthAndFilledWithCharacter(offset, ' ') + TreeNode.BRANCH_SEPERATORS[i];
		this.addToPrintLine(renderedLinesCollection, linePosition, pre);
		
		
		//add Seperator character 		
		if(this.children[i] != null)
		{
			this.children[i].render(depth-1, renderedLinesCollection, linePosition);
		}
		else
		{
			this.addToPrintLine(renderedLinesCollection, linePosition, TreeNode.getStringWithLengthAndFilledWithCharacter(TreeNode.PRINT_NODE_CHARSIZE, ' ')) ;
		}
	}


private String renderNode() {
	String nodeLine = "[" + this.values[0] + "<>";
	
	
	if (this.values[1] != null) {
		nodeLine += this.values[1];
	} else {
		nodeLine += "-";
	}
	nodeLine += "]";
	return nodeLine;
}

	public TreeNode findAddPathChild(int number) {
		if (number > values[0] && this.children[2] != null) {
			if (this.children[1] != null && this.values[1] != null
					&& this.values[1] > number) {
				return this.children[1];
			}
			return this.children[2];
		}
		return this.children[0];
	}
	
	public TreeNode[] getChildren()
	{
		return this.children;
	}

	public Boolean hasChildren() {
		for (TreeNode child : this.children) {
			if (child != null) {
				return true;
			}
		}

		return false;
	}

	public void removeChild(TreeNode child) {
		for (int i = 0; i < this.children.length; i++) {
			if (this.children[i] != null && this.children[i].equals(child)) {
				this.children[i] = null;
			}
		}
	}

	public void setParent(TreeNode parent) {
		this.parent = parent;
	}

	private int[] sortThreeNumbers(int thirdNumber) {
		int[] numbers = new int[3];

		numbers[0] = this.values[0];
		numbers[1] = thirdNumber;
		numbers[2] = this.values[1];

		// loop through numbers array to sort the list. Only need to go through
		// once with three numbers.
		for (int i = 0; i < numbers.length; i++) {
			if ((i + 1) != numbers.length && numbers[i] > numbers[(i + 1)]) {
				Integer temp = numbers[(i + 1)];
				numbers[(i + 1)] = numbers[i];
				numbers[i] = temp;
			}
		}

		return numbers;
	}

	private void addValueToThis(int number) {
		// if new value is smaller than the current.
		if (values[0] > number) {
			values[1] = values[0];
			values[0] = number;
		} else {
			values[1] = number;
		}
	}

	private void split(int thirdNumber) {
		// get middle number.
		int[] numbers = this.sortThreeNumbers(thirdNumber);
		// set self to lowest number.

		TreeNode leftSibling = new TreeNode(numbers[0], this.wrapper);
		TreeNode rightSibling = new TreeNode(numbers[2], this.wrapper);

		if (this.parent != null) {
			this.parent.removeChild(this);
		}

		for (int i = 0; i < this.children.length; i++) {
			if (children[i] != null) {
				if (children[i].getLowestValue() > numbers[2]) {
					rightSibling.addChild(children[i]);
					children[i] = null;
				}
				if (children[i].getLowestValue() < numbers[0]) {
					leftSibling.addChild(children[i]);
					children[i] = null;
				}
			}
		}
		// if parent exists
		if (this.parent != null) {
			// send middle number to parent with two new nodes, one of them
			// being the original child.
			parent.delegateNumberFromChild(leftSibling, numbers[1],
					rightSibling);
		}
		// if no parent create parent and set wrapper root to new parent;
		else {
			this.parent = new TreeNode(numbers[1], this.wrapper, leftSibling,
					rightSibling);
			wrapper.setRoot(this.parent);
		}
	}

	private void splitWithChildren(TreeNode lowest, int middle, TreeNode highest) {
		// get middle number.
		int[] numbers = this.sortThreeNumbers(middle);

		TreeNode leftSibling = new TreeNode(numbers[0], this.wrapper);
		TreeNode rightSibling = new TreeNode(numbers[2], this.wrapper);

		ArrayList<TreeNode> tempChildren = new ArrayList<TreeNode>();
		tempChildren.add(lowest);
		tempChildren.add(highest);

		for (int i = 0; i < this.children.length; i++) {
			if (this.children[i] != null) {
				tempChildren.add(this.children[i]);
				this.children[i] = null;
			}
		}

		for (TreeNode node : tempChildren) {
			if (node.getHighestValue() < numbers[1]) {
				leftSibling.addChild(node);
			}
			if (node.getLowestValue() > numbers[1]) {
				rightSibling.addChild(node);
			}
		}

		// if parent exists
		if (this.parent != null) {
			// send middle number to parent with two new nodes, one of them
			// being the original child.
			parent.removeChild(this);
			parent.delegateNumberFromChild(leftSibling, numbers[1],
					rightSibling);

		}
		// if no parent create parent and set wrapper root to new parent;
		else {
			this.parent = new TreeNode(numbers[1], this.wrapper, leftSibling,
					rightSibling);
			wrapper.setRoot(this.parent);
		}
	}

	public void addChild(TreeNode newChild) {

		if (this.children[0] == null) {
			this.children[0] = newChild;
			newChild.setParent(this);
			return;
		}

		if (this.children[2] == null) {
			this.children[2] = newChild;
			newChild.setParent(this);

			if (this.children[2].getHighestValue() < this.children[0]
					.getLowestValue()) {
				TreeNode temp = this.children[2];
				this.children[2] = this.children[0];
				this.children[0] = temp;
			}
			return;
		}

		if (this.children[1] == null) {
			this.children[1] = newChild;
			newChild.setParent(this);
			return;
		}

	}

	public static String getStringWithLengthAndFilledWithCharacter(int length,
			char charToFill) {
		if (length > 0) {
			char[] array = new char[length];
			Arrays.fill(array, charToFill);
			return new String(array);
		}
		return "";
	}

	private void initialize(int firstNumber, TreeNode parent) {
		this.parent = parent;
		this.values = new Integer[2];
		this.children = new TreeNode[3];
		this.values[0] = firstNumber;
	}

}
