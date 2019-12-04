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
		
		for (BigInteger x : p.iteratePrimes())
		{
			System.out.println(x);
		}
		
		Iterator<BigInteger> iter = p.iteratePrimes().iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
		
		for (Pair<BigInteger> x : p.iterateCrosses()) {
			System.out.println(x.left());
		}
		
		//MainWindow mw = new MainWindow(Config.APPLICATIONNAME, p);
	}
}
