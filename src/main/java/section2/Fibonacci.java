package section2;

import misc.InOutHelper;

public class Fibonacci {

	public static long rec_fib(final int n) {
		InOutHelper.info("rec_fib("+n+")");
		if (n <= 0) {
			InOutHelper.info("return 0");
			 return 0;
		} else if (n == 1) {
			InOutHelper.info("return 1");
			return 1;
		} else {
			final long result = rec_fib(n-2) + rec_fib(n-1);
			InOutHelper.info("return " + result);
			return result;
		}
	}


	public static long it_fib(final int n) {
		InOutHelper.info("it_fib("+n+")");
		if (n <= 0) {
			InOutHelper.info("return 0");
			return 0;
		}
		if (n == 1) {
			InOutHelper.info("return 1");
			return 1;
		}

		long fibiminus2;
		int i = 1;

		long fibi=1;
		long fibiminus1 = 0;

		while (i < n) {
			InOutHelper.info("i:"+i+" fibi:"+fibi+" fibiminus1:"+fibiminus1);
			i++;
			fibiminus2 = fibiminus1;
			fibiminus1 = fibi;
			fibi = fibiminus2 + fibiminus1;
		}
		InOutHelper.info("i:"+i+" fibi:"+fibi+" fibiminus1:"+fibiminus1);
		InOutHelper.info("return "+fibi);
		return fibi;
	}


	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		System.out.println("Program to compute the fibonacci number of a given number...");
		InOutHelper.askForDebugMode();
		final int n = InOutHelper.readInt();
		System.out.println("Resultat rekursiv:"+rec_fib(n));
		System.out.println("Resultat iterativ:"+it_fib(n));
	}

}
