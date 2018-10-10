import java.util.Scanner;

import twothreetree.*;

public class Main {	
       
	public static TwoThreeTree twothreeTree;
	
	public static void main(String[] args) 
	{		

		System.out.println("Options:");
		System.out.println("----------------------------------");
		System.out.println("----------------------------------");
		System.out.println("R || r : reset tree");
		System.out.println("Enter any number to insert in tree");
		
		String input = "";
		Scanner s = new Scanner(System.in);
		while(true)
		{
			input = s.next();
			System.out.println(input);
			handleInput(input);
			System.out.println("Enter next number or reset tree");
			
		}
		//s.close();	
	}
	
	public static void handleInput(String input)
	{
		switch(input)
		{
		case "r":
			twothreeTree = null;
			System.out.println("Tree has been reset");
			break;
		case "R":
			twothreeTree = null;
			System.out.println("Tree has been reset");
			break;
		default:
			addNumberToThree(input);
			break;		
		}
	}
	
	public static void addNumberToThree(String numberString)
	{
		if(isInteger(numberString))
		{			
			int number = Integer.parseInt(numberString);
			System.out.println("Adding the number "+ number + " to the tree");
			if(twothreeTree != null)
			{
				twothreeTree.addNumber(number);				
			}
			else
			{
				twothreeTree = new TwoThreeTree(number);
			}
			twothreeTree.print();
		}
		else
		{
			System.out.println("Invalid input, try again");
		}
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}

}
