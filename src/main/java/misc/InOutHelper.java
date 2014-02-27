package misc;

import java.util.Scanner;

public class InOutHelper {

	private static boolean DEBUG = false;

	public static void info(final String infoString){
		if(InOutHelper.DEBUG){
			System.out.println(infoString);
		}
	}

	public static void askForDebugMode(){
		System.out.println("Bitte geben Sie 'd' oder 'debug' zum Einschalten des Debug-Modus ein, ansonsten irgendetwas anderes:");
		final Scanner sc = new Scanner(System.in);
		final String input = sc.next();
		if(input.compareToIgnoreCase("d")==0 || input.compareToIgnoreCase("debug")==0){
			InOutHelper.DEBUG = true;
		} else {
			InOutHelper.DEBUG = false;
		}
	}

	public static int readInt(){
		final Scanner sc = new Scanner(System.in);
		while(true){
			System.out.println("Bitte geben Sie eine Zahl ein:");
			if (sc.hasNextInt()){
				return sc.nextInt();
			}
			sc.next();
			System.out.println("Das eingegebene Token war keine Zahl!");
		}
	}
}
