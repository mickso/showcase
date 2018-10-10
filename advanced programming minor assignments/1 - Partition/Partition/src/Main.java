import java.util.ArrayList;

public class Main {	
    
    private static void partition(int n)
    {
    	ArrayList<Integer> currentPartitionArray = new ArrayList<Integer>();    	
    	currentPartitionArray.add(n);    	    	    	
    	    	
    	while(doPartition(currentPartitionArray))
    	{        			
    		System.out.println(currentPartitionArray.toString());       	       
    	}    
    	
    }        
    
    private static boolean doPartition(ArrayList<Integer> currentPartitionArray)
    {
    	//loop through current array from right to left
       	for(int i = (currentPartitionArray.size()-1); i >= 0; i--)
    	{
    		int base = currentPartitionArray.get(i);
    		//if the current number found is bigger than 1(partitionable);
    		if(base > 1)
    		{
    			base--;
    			currentPartitionArray.set(i, base);            		          			
        		int remainder = 0;
        		for(int j = currentPartitionArray.size()-1; j > i; j --)
        		{
        			remainder += currentPartitionArray.get(j);
        			currentPartitionArray.remove(j);            				
        		}   
        		remainder++;
        			            		    			    			            	
        		while(remainder > base)
        		{
        			remainder -= base;
        			currentPartitionArray.add(base);
        		}
        		currentPartitionArray.add(remainder);        
        		
        		return true;
    		}        		    		
    	}  
       	return false;
    }        

	public static void main(String[] args) 
	{
		partition(12);	
	}

}
