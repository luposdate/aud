package section20;

import misc.InOutHelper;
import section2.Fibonacci;

public class MemoizedFibonacci {

	public static long mem_fib(final int n) {
		final long[] table = new long[n+1];
		table[0]=0;
		table[1]=1;
		for(int i=2; i<=n; i++){
			table[i]=-1; // dummy value for not yet calculated
		}
		return mem_fib(table, n);
	}

	private static long mem_fib(final long[] table, final int n) {
		InOutHelper.info("mem_fib("+n+")");
		if(table[n]!=-1){
			InOutHelper.info("Value for mem_fib("+n+") = " + table[n] + " has already been computed before!");
			return table[n];
		}
		InOutHelper.info("Calculate mem_fib("+n+") = mem_fib("+(n-1)+") + mem_fib("+(n-2)+")");
		table[n] = mem_fib(table, n-1) + mem_fib(table, n-2);
		InOutHelper.info("return mem_fib("+n+") = " + table[n]);
		return table[n];
	}


	/**
	 * @param args
	 */
	public static void main(final String[] args) {
		System.out.println("Program to compute the fibonacci number of a given number...");
		InOutHelper.askForDebugMode();
		final int n = InOutHelper.readInt();
		System.out.println("Resultat rekursiv:"+Fibonacci.rec_fib(n));
		System.out.println("Resultat durch Memoisation:"+mem_fib(n));
		System.out.println("Resultat iterativ:"+Fibonacci.it_fib(n));
	}

}
