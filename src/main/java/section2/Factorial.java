package section2;

import misc.InOutHelper;

public class Factorial {

	public static int rec_fakul(final int n){
		InOutHelper.info("rec_fakul("+n+")");
		if (n <= 0) {
			InOutHelper.info("return 1");
			return 1;
		} else {
			final int result = n*rec_fakul(n-1);
			InOutHelper.info("return "+result);
			return result;
		}
	}

	public static int it_fakul(final int n){
		InOutHelper.info("it_fakul("+n+")");
		int f=1;
		int i=2;
		while(i<=n){
			f*=i;
			InOutHelper.info("i:"+i+" f:"+f);
			i++;
		}
		InOutHelper.info("return "+f);
		return f;
	}



	public static void main(final String[] args) {
		System.out.println("Program to compute the factorial of a given number...");
		InOutHelper.askForDebugMode();
		final int n = InOutHelper.readInt();
		System.out.println("Resultat rekursiv:"+rec_fakul(n));
		System.out.println("Resultat iterativ:"+it_fakul(n));
	}

}
