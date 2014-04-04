package section1;

import misc.InOutHelper;

public class PrimTable {

	public static void main(final String[] args) {
		System.out.println("Program to compute the prim numbers up to a given number...");
		InOutHelper.askForDebugMode();
		final int n = InOutHelper.readInt();
		final boolean[] primNumberCandidates = PrimTable.calculatePrimNumberTable(n);
		PrimTable.printPrimNumbers(primNumberCandidates);
	}

	public static void printPrimNumbers(final boolean[] primNumberCandidates){
		System.out.println("Berechnete Primzahlen:");
		for(int i=0; i<primNumberCandidates.length; i++){
			if(primNumberCandidates[i]){
				if(i!=0){
					System.out.print(", ");
				}
				System.out.print(indexToNumber(i));
			}
		}
		System.out.println();
	}

	public static boolean[] calculatePrimNumberTable(final int n){
		// first initialize the prim table (all entries are set to true)
		final boolean[] primNumberCandidates = new boolean[n-1]; // the numbers 0 and 1 are not checked to be prim, better implementations would use a bit array in order to save main memory
		for(int z=0; z<primNumberCandidates.length; z++){
			primNumberCandidates[z]=true;
		}
		// now calculate the prim number table
		for(int i=2; i<=Math.sqrt(n); i++){
			InOutHelper.info("i:"+i);
			if(primNumberCandidates[numberToIndex(i)]){
				for(int k=n/i; k>=i; k--){
					InOutHelper.info("k:"+k);
					if(primNumberCandidates[numberToIndex(k)]){ // checking this is only faster if real big numbers are multiplied...
						InOutHelper.info("remove i*k="+i*k+" from prim number candidates");
						primNumberCandidates[numberToIndex(i*k)]=false;
					}
				}
			}
		}
		return primNumberCandidates;
	}

	private final static int numberToIndex(final int number){
		return number-2;
	}

	private final static int indexToNumber(final int index){
		return index+2;
	}

}
