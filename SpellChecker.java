
public class SpellChecker {


	public static void main(String[] args) {
		String word = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word, threshold, dictionary);
		System.out.println(correction);
	}

	public static String tail(String str) {
		String tail = str.substring(1); 

		return tail ;
	}

	public static int levenshtein(String word1, String word2) {
		word1 = word1.toLowerCase();
		word2 = word2.toLowerCase() ;

		int counter = 0 ; 
		int a = word1.length() ;
		int b = word2.length() ;

		if (a == 0 ){
			return b ; 
		}

		else if (b == 0) {
			return a ;
		}
		
		else if(word1.charAt(0)== word2.charAt(0)) {
			return levenshtein(tail(word1), tail(word2));
		}

		else {
			int x = levenshtein(tail(word1), word2);
			int y = levenshtein(word1, tail(word2));
			int z = levenshtein(tail(word1), tail(word2));

			
		counter = 1 + Math.min(x , Math.min(y, z));	
		}

	return counter;	
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];

		In in = new In(fileName);
		for (int i = 0 ; i < 3000 ; i ++){
			dictionary[i] = in.readLine();
		}

		return dictionary;
	}

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		String save = "";
			for (int i = 0 ; i < 3000 ; i ++){
				int lev1 = levenshtein(word, dictionary[i]);
				int lev2 = levenshtein(word, save);
				if (lev1 < lev2 && lev1 <= threshold) { 
					save = dictionary [i];
				}
				}
			if (save.isEmpty()){
				save = word ;
			}

			return save;
	}

}
