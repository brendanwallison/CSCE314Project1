package Primes;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.Math; 
import java.math.BigInteger;

/*
 *  Desc: This class generates primes, twin primes, and hexagon crosses using BigInteger data types.
 */
public class Primes {
	
	private ArrayList<BigInteger> fullPrimeList = new ArrayList<BigInteger>();
	private ArrayList<Integer> mapTwins = new ArrayList<Integer>();
	private ArrayList< Pair<Integer> > mapHexPairs = new ArrayList< Pair<Integer> >();
	// Pair class implementation.
	private class Pair<T> {
		private ArrayList<T> primePair = new ArrayList<T>(2);

		private ArrayList<T> getPrimePair()
		{
			return primePair;
		}

		private void setPrimePair(ArrayList<T> primePair)
		{
			this.primePair = primePair;
		}
	}
	
	// Member variables for containing out lists of integers, twin primes, hexagon crosses, and the pairs of twin primes that make up the hex crosses.
	
	// Add a prime to the prime list if and only iff it is not already in the list. (ignore duplicates)
	public void addPrime(BigInteger x)
	{
		if(!isPrime(x)) {
			System.out.println("Error: given value is not a prime");
			System.out.println("Please input another");
		}
		//else insert prime
	}
	
	public boolean isPrime(BigInteger x)
	{
		return true;
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		//for(int)
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
	}
		
	// Generate and store a list of primes.
	public void generatePrimes(int count)
	{
		int limit = primeLimit(count);
		int sundaramLimit = limit/2;
		
		//this method creates an array of every positive integers up to the sundaramLimit
		//every item is assumed to be a prime, and then the algorithm iterates through marking them as false
		//to get the primes, map every remaining true item from its index i to the odd prime number (2*i + 1)
		//the downside of the current implementation is it makes big integer only modestly useful:
		//since this process relies on the creation of an array, we can only generate primes up to
		//the maximum value of integer mapped by the formula (2*(maxInt) + 1)
		ArrayList<Boolean> primeTruth = new ArrayList<Boolean>(Collections.nCopies(sundaramLimit, true));
		
		//set index 0 to false; this will correspond to the prime number 1
		primeTruth.set(0, false);	
		
		//also manually count two as a prime; this is the only even prime
		//algorithm (sundaram sieve) will pick up all odd primes
		if (limit>1) {
			fullPrimeList.add(BigInteger.valueOf(2));
		}
		
		for (int i = 1; i<sundaramLimit; i++) {
			//int innerLim = (sundaramLimit - i)/(1+2*i);
			for (int j=i; i + j + 2*i*j < sundaramLimit; j++) {
				primeTruth.set(i + j + 2*i*j, false);
			}
			
		}
		
		
		for (int i = 1; i < sundaramLimit; i++ )
			if(primeTruth.get(i)) {
				fullPrimeList.add(BigInteger.valueOf(i*2+1));
			}
	}
	
	//Generate an upper bound of a range guaranteed to contain at least n primes
	//See https://en.wikipedia.org/wiki/Prime_number_theorem#Approximations_for_the_nth_prime_number
	private int primeLimit(int n) {
		if (n<6) {
			return 13;
		}
		double x = (double) n;
		double limit = x*Math.log(x)+x*Math.log(Math.log(x));
		return (int) Math.ceil(limit);
	}
	
	// Generate and store a list of twin primes.
	public void generateTwinPrimes()
	{
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
	}
}