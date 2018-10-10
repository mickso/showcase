import java.util.ArrayList;


public class AnagramFinder {
	
	public static String text = "Ik ben een kat die houdt van een tak. Die ene tak die naast de boom ligt vindt ik erg spannend.";
	public static char[] characters = "abcdefghijklmnopqrstuvwxyz".toCharArray();
	public static int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101};
	
	public static void main(String args[]){
		
		AnagramFinder anagramFinder = new AnagramFinder();
		anagramFinder.findAnagrams(AnagramFinder.text);
	}
	
	public void findAnagrams(String text){	
		String[] words = setWordsFromText(text);
		ArrayList<String> alreadyFoundAnagrams = new ArrayList<String>();
		
		System.out.println("Scanning text for words.... ");
		System.out.println("Found words:");
		
		this.printWords(words);
						
		for(int i = 0; i < words.length; i++){
			String currentWord = words[i];
			if(!alreadyFoundAnagrams.contains(currentWord)){
				System.out.println("Finding anagrams for word:"+ currentWord);
				alreadyFoundAnagrams.add(currentWord);
				int currentWordSum = this.getWordSum(words[i]);						
				for(int j = i; j < words.length; j++){
					String nextWord = words[j];					
					if(!alreadyFoundAnagrams.contains(nextWord) && currentWord.length() == nextWord.length()){
						int nextWordSum = this.getWordSum(nextWord); 
						
						if(currentWordSum == nextWordSum){
							alreadyFoundAnagrams.add(nextWord);
							System.out.println("--------- found anagram :" + nextWord);
						}
					}
					
				}												
			}					
		}
		
	}
	
	
	public boolean alreadyFound(String anagram, ArrayList<String> foundAnagrams){
		return foundAnagrams.contains(anagram);
	}
	
	private void printWords(String[] words){
		for(int i = 0; i < words.length; i++){
			System.out.println(words[i]);
		}
		
	}
	
	private String[] setWordsFromText(String s){
		String[] words = s.split("\\s+");
		for (int i = 0; i < words.length; i++) {
			
			words[i] = words[i].replaceAll("[^a-zA-Z]", "").toLowerCase();
		}
		
		return words;
	}	
	
	private int getWordSum(String word){
		char[] charactersOfWord = word.toCharArray();
		int wordSum = 0;
		for(int i = 0; i < charactersOfWord.length; i++){				
			wordSum += getAssociatedPrimeNumber(charactersOfWord[i]);
		}
		
		return wordSum;
	}
	
	private int getAssociatedPrimeNumber(char c){
		for(int i = 0; i < AnagramFinder.characters.length; i++){
			if(AnagramFinder.characters[i] == c){
				return AnagramFinder.primeNumbers[i];
			}
		}
		
		return -1;
	}

}
