package section11;

import misc.InOutHelper;

public class StringPatternMatching {

	// -------------------------------------- brute force approach ------------------------------------------------------------

	/**
	 * Textsuche nach einem Muster
	 * @param text Der Text, in dem gesucht wird
	 * @param pattern Das Muster, nach dem gesucht wird
	 * @return Position des Musters im Text, oder -1 bei erfolgloser Suche
	 */
	public static int bruteforce(final String text, final String pattern) {
		final int n = text.length();
		final int m = pattern.length();
		int j;
		for(int i=0; i <= (n-m); i++) {
			j = 0;
			while ((j < m) && (text.charAt(i+j) == pattern.charAt(j))){
				j++;
			}
			if (j == m) {
				return i;  // erfolgreiche Suche! Gebe Index des Matches zurück!
			}
		}
		return -1; // erfolglose Suche
	}

	// -------------------------------------- Knuth-Morris-Pratt-approach ------------------------------------------------------------

	private static int[] computeF(final String pattern){
		final int fail[] = new int[pattern.length()-1];
		fail[0] = 0;
		final int m = fail.length;
		int j = 0;
		int i = 1;
		while (i < m) {
			if  (pattern.charAt(j) == pattern.charAt(i)) { // j+1 Zeichen stimmen überein
				fail[i] = j + 1;
				i++;
				j++;
			} else if (j > 0){ // j folgt dem übereinstimmenden Präfix
				j = fail[j-1];
			} else {     // Kein match
				fail[i] = 0;
				i++;
			}
		}
		return fail;
	}

	public static int knuthMorrisPratt(final String text, final String pattern){
		final int n = text.length();
		final int m = pattern.length();
		final int fail[] = computeF(pattern);
		int i=0;
		int j=0;
		while (i < n) {
			if (pattern.charAt(j) == text.charAt(i)) {
				if (j == m - 1){
					return i - m + 1; // Suche erfolgreich!
				}
				i++;
				j++;
			} else if (j > 0){
				j = fail[j-1];
			} else {
				i++;
			}
		}
		return -1; // Suche erfolglos!
	}

	// -------------------------------------- Boyer-Moore-approach ------------------------------------------------------------

	private static final int[] buildL(final String pattern) {
		final int l[] = new int[128]; // Unterstütze nur ASCII
		for(int i=0; i < 128; i++){
			l[i] = -1;
		}
		for (int i = 0; i < pattern.length(); i++){
			l[pattern.charAt(i)] = i;
		}
		return l;
	}

	public static int boyerMoore(final String text, final String pattern) {
		final int l[] = buildL(pattern);
		final int n = text.length();
		final int m = pattern.length();
		int i = m-1;
		if (i > n-1){
			return -1; // Muster ist länger als Text => erfolglose Suche!
		}
		int j = m-1;
		do {
			if (pattern.charAt(j) == text.charAt(i)){
				if (j == 0){
					return i; // Suche erfolgreich!
				} else { // Spiegeltechnik
					i--;
					j--;
				}
			} else { // Zeichensprungtechnik
				final int lo = l[text.charAt(i)];
				i = i + m - Math.min(j, 1+lo);
				j = m - 1;
			}
		} while (i <= n-1);
		return -1; // Suche erfolglos!
	}

	// -------------------------------------- User Interface ------------------------------------------------------------

	public static void main(final String[] args) {
		System.out.println("Programm zur Textsuche nach einem Muster");
		System.out.println("B für Boyer-Moore, K für Knuth-Morris-Pratt, jede andere Eingabe für Brute-Force-Ansatz");
		final String command = InOutHelper.readLine();
		System.out.println("Bitte geben Sie den Text ein:");
		final String text = InOutHelper.readLine();
		System.out.println("Bitte geben Sie das Muster ein:");
		final String pattern = InOutHelper.readLine();
		final int result;
		if(command.compareToIgnoreCase("b")==0){
			System.out.println("Suchen nach Boyer-Moore");
			result = boyerMoore(text, pattern);
		} else if(command.compareToIgnoreCase("k")==0){
			System.out.println("Suchen nach Knuth-Morris-Pratt");
			result = knuthMorrisPratt(text, pattern);
		} else {
			System.out.println("Suchen mit Brute-Force-Ansatz");
			result = bruteforce(text, pattern);
		}
		System.out.println("Indexposition des Musters im Text (-1 bei erfolgloser Suche): " + result);
	}
}