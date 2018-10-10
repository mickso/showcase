import java.text.NumberFormat;


public class VendingMachine {
	
	public enum Valuta{
		EUR, GLD
	}
	
	
	public static int eurUnitValues[] = {200, 100, 50, 20, 10, 5, 2, 1};			
	public static int gldUnitValues[] = {250, 100, 25, 10, 5};
	
	public static void main(String[] args){
		VendingMachine machine = new VendingMachine();
		machine.getChange(23.33, Valuta.GLD);
	}
	
	public void getChange(double ammount, Valuta valuta){
		
		int tempAmmount =  (int)Math.round(ammount*100);		
		int unitValues[] = null;
		
		switch (valuta){
			case GLD:
				unitValues = VendingMachine.gldUnitValues;
				break;
			default:
				unitValues = VendingMachine.eurUnitValues;
				break;		
		}
		
		String startMessage = "Calculating change of " + ammount + " in " + valuta;
		System.out.println(startMessage);	
		
		int currentUnitValueIndex = 0;
		int currentUnitValueCount = 0;
		
		while (currentUnitValueIndex < unitValues.length){
			int currentUnitValue = unitValues[currentUnitValueIndex];
			
			if(currentUnitValue <= tempAmmount){				
				tempAmmount -= currentUnitValue;			
				currentUnitValueCount++;
				
			}else{				
				currentUnitValueIndex++;						
				if(currentUnitValueIndex == unitValues.length && tempAmmount > (currentUnitValue / 2)){
					currentUnitValueCount ++;
				}
				
				printAmmountOfUnitValue(currentUnitValueCount, currentUnitValue, valuta);
				currentUnitValueCount = 0;
			}
			
		}
		
	}
	
	private void printAmmountOfUnitValue(int ammountFound, int unitValue, Valuta valuta){
		NumberFormat nf = NumberFormat.getInstance();
		String line = ammountFound + " of " + nf.format(unitValue/100.0) + " " + valuta.toString();
		System.out.println(line);
	}

}
