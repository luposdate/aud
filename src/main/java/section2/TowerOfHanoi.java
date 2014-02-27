package section2;

import misc.InOutHelper;

public class TowerOfHanoi {

	private static void rec_hanoi(final int start, final int ziel, final int hilf, final int hoehe) {
		InOutHelper.info("rec_hanoi("+start+", "+ziel+", "+hilf+", "+hoehe+")");
		if (hoehe > 0) {
			rec_hanoi(start,hilf, ziel,hoehe-1);
			move(start,ziel);
			rec_hanoi(hilf,ziel, start,hoehe-1);
		}
		InOutHelper.info("return from rec_hanoi("+start+", "+ziel+", "+hilf+", "+hoehe+")");
	}

	private static void move(final int start, final int ziel) {
		System.out.println ("Bewege Scheibe von "+ start + " nach "+ ziel);
	}

	public static void rec_hanoi(final int hoehe){
		InOutHelper.info("rec_hanoi("+hoehe+")");
		rec_hanoi(1,2,3,hoehe);
		InOutHelper.info("return from rec_hanoi("+hoehe+")");
	}

	static void it_hanoi(final int hoehe) {
		InOutHelper.info("it_hanoi("+hoehe+")");
		final int [][] staebe = new int[3][hoehe+1];	// die drei Staebe als zweidimensionales Feld
		final int [] last = {hoehe, 0, 0}; 				// momentane Hoehe der Staebe
		int pKS=0,										// Position der kleinsten Scheibe
				pos1, 									// Position der zu versetzenden Scheibe
				pos2; 									// Zielposition der zu versetzenden Scheibe
		final int step = (hoehe %2 == 0)? 2: 1;			// Falls die Anzahl Scheiben geradzahlig ist, so bewege die Scheiben nach rechts, sonst links
		// initialisiere Staebe
		for (int i=0; i<hoehe+1; ++i) {
			staebe[0][i] = hoehe+1-i;
		}
		staebe[1][0] = hoehe+1;
		staebe[2][0] = hoehe+1;
		printOutBars(staebe, last);
		while(last[1]<hoehe) {							// schon fertig?
			move(pKS+1, (pKS+step)%3+1);				// Bewege kleinste Scheibe je nach Position nach links oder rechts
			staebe[pKS][last[pKS]--] = 0;
			pKS = (pKS+step)%3;
			staebe[pKS][++last[pKS]] = 1;
			printOutBars(staebe, last);
			// Mache den einzig moeglichen Zug
			pos2 = (staebe[(pKS+1)%3][last[(pKS+1)%3]]>staebe[(pKS+2)%3][last[(pKS+2)%3]]) ? (pKS+2)%3 : (pKS+1)%3;
			pos1 = 2*(pKS+pos2) % 3;
			if (staebe[pos1][last[pos1]] != staebe[pos2][last[pos2]]) {
				move(pos2+1, pos1+1);
				staebe[pos1][++last[pos1]] = staebe[pos2][last[pos2]];
				staebe[pos2][last[pos2]--] = 0;
				printOutBars(staebe, last);
			}
		}
		InOutHelper.info("return from it_hanoi("+hoehe+")");
	}

	public static void printOutBars(final int [][] staebe, final int [] last){
		if(InOutHelper.inDebugMode()){
			for(int i=0; i<3; i++){
				InOutHelper.info("Stab "+(i+1));
				if(last[i]>0){
					for(int j=last[i]; j>0; j--){
						InOutHelper.info(staebe[i][j]+"");
					}
				}
			}
		}
	}


	public static void main(final String[] args) {
		System.out.println("Program to compute the factorial of a given number...");
		InOutHelper.askForDebugMode();
		final int hoehe = InOutHelper.readInt();
		System.out.println("Rekursiv:");
		rec_hanoi(hoehe);
		System.out.println("Iterativ:");
		it_hanoi(hoehe);
	}
}
