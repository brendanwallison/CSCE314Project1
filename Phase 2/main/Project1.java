package main;

import java.math.BigInteger;
import java.util.Iterator;


public class Project1
{
	public static void main(String[] args)
	{
		// Instantiate Primes Class
		Primes p = new Primes();
		p.generatePrimes(BigInteger.TWO, 49);
		p.generateTwinPrimes();
//		p.printTwins();
		p.generateHexPrimes();
//		p.printHexes();
		p.addPrime(BigInteger.valueOf(123456789));
//		System.out.println(p.sizeofLastPrime());
		p.addCross(new Pair<BigInteger>(BigInteger.valueOf(12345),BigInteger.valueOf(123456)));
//		System.out.println(p.sizeofLastCross().left());
//		System.out.println(p.sizeofLastCross().right());
		
//		for (BigInteger x : p.iteratePrimes())
//		{
//			System.out.println("Potential Prime " + x + " Primality: " + ThoughtfulTest.isPrime(x));
//		}
		
//		BigInteger big = new BigInteger("15485863");
//		BigInteger big2 = new BigInteger("982451653");
//		BigInteger reallyBig = new BigInteger("67280421310721");
		
//		System.out.println("Potential Prime " + big + " Primality: " + ThoughtfulTest.isPrime(big));
//		System.out.println("Potential Prime " + big2 + " Primality: " + ThoughtfulTest.isPrime(big2));
//		System.out.println("Potential Prime " + reallyBig + " Primality: " + ThoughtfulTest.isPrime(reallyBig));
//		
//		Iterator<BigInteger> iter = p.iteratePrimes().iterator();
//		while(iter.hasNext()) {
//			System.out.println(iter.next());
//		}
//		
//		for (Pair<BigInteger> x : p.iterateCrosses()) {
//			System.out.println(x.left());
//		}
		
		
		//System.out.println(NaiveTest.isPrime(BigInteger.TEN));
		
		MainWindow mw = new MainWindow(Config.APPLICATIONNAME, p);
	}
}
