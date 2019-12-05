package main;

import java.math.BigInteger;
import java.util.Iterator;


public class Project1
{
	//@SuppressWarnings("static-access")
	public static void main(String[] args)
	{
		// Instantiate Primes Class
		Primes p = new Primes();
		p.generateHexPrimes();

		//System.out.println(p.crossesCount());
		new MainWindow(Config.APPLICATIONNAME, p);
	}
}
