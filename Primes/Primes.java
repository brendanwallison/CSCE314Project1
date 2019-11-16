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
	private ArrayList<Pair<Integer>> mapTwins = new ArrayList<Pair<Integer>>();
	private ArrayList< Pair<Pair<Integer>> > mapHexPairs = new ArrayList< Pair<Pair<Integer>> >();
	// Pair class implementation.
	private class Pair<T> {
		private ArrayList<T> primePair = new ArrayList<T>(2);

		private ArrayList<T> getPrimePair()
		{
			return primePair;
		}
		
		
		private void setPrimePair(T a, T b)
		{
			this.primePair.clear();
			this.primePair.add(a);
			this.primePair.add(b);
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
		//efficient prime test from existing list
		if(fullPrimeList.contains(x)) {
			return true;
		}
		//ultra-inefficient not-a-prime test, checking every factorization
		else {
			for (int i=2; i < x.intValue(); i++) {
				if (x.intValue() % i == 0) {
					return false;
				}
			}
		}
		return true;
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		for(int i = 0; i < fullPrimeList.size(); i++) {
			System.out.println(fullPrimeList.get(i).toString());
		}
		System.out.println("Total Primes: " + fullPrimeList.size());
	}
		
	// Output the twin prime list. Each twin prime should be on a separate line with a comma separating them, and the total number of twin primes should be on the following line.
	public void printTwins()
	{
		for (int i = 0; i < mapTwins.size(); i++) {
			int a = mapTwins.get(i).getPrimePair().get(0);
			int b = mapTwins.get(i).getPrimePair().get(1);
			BigInteger A = fullPrimeList.get(a);
			BigInteger B = fullPrimeList.get(b);
			System.out.println(A.toString() + ", " + B.toString());
		}
		System.out.println("Total Twins: " + mapTwins.size());
	}
		
	// Output the hexagon cross list. Each should be on a separate line listing the two twin primes and the corresponding hexagon cross, with the total number on the following line.
	public void printHexes()
	{
		for (int i = 0; i < mapHexPairs.size(); i++) {
			int a = mapHexPairs.get(i).getPrimePair().get(0).getPrimePair().get(0);
			int b = mapHexPairs.get(i).getPrimePair().get(0).getPrimePair().get(1);
			int c = mapHexPairs.get(i).getPrimePair().get(1).getPrimePair().get(0);
			int d = mapHexPairs.get(i).getPrimePair().get(1).getPrimePair().get(1);
			BigInteger A = fullPrimeList.get(a);
			BigInteger A2 = A.add(BigInteger.ONE);
			BigInteger B = fullPrimeList.get(b);
			BigInteger C = fullPrimeList.get(c);
			BigInteger C2 = C.add(BigInteger.ONE);
			BigInteger D = fullPrimeList.get(d);
			System.out.println("Prime Pairs: " + A.toString() + ", " + B.toString() + " and " + C.toString() + ", " + D.toString() + " separated by " + A2.toString() + ", " + C2.toString());
		}
		System.out.println("Total Hexes: " + mapHexPairs.size());
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
		
		//set index 0 to false; this will correspond to the non-prime number 1 (thus false)
		primeTruth.set(0, false);	
		
		//manually count two as a prime; this is the only even prime
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
		
		//fullPrimeList is always larger than count; but we only need count primes
		//check against primeTruth.size() should be unnecessary, but 
		//starts at one because the first prime--2--has already been manually added
		int i = 1;
		int primes=1;
		while(primes < count && i < primeTruth.size()) {
			if(primeTruth.get(i)) {
				fullPrimeList.add(BigInteger.valueOf(i*2+1));
				primes = primes+1;
			}
			i=i+1;
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
	// Specifically, the list is a mapping of index pairs in fullPrimeList
	public void generateTwinPrimes()
	{
		int difference;
		for(int i = 0; i < fullPrimeList.size()-1; i++) {
			difference = (fullPrimeList.get(i+1).subtract(fullPrimeList.get(i))).intValue();
			if(difference == 2) {
				Pair<Integer> newPair = new Pair<Integer>();
				newPair.setPrimePair(i,i+1);
				mapTwins.add(newPair);
			}
		}
		
	}
	
	// Generate and store the hexagon crosses, along with the two twin primes that generate the hexagon cross.
	public void generateHexPrimes()
	{
		for (int i = 0; i < mapTwins.size(); i++) {
			int a = mapTwins.get(i).getPrimePair().get(0);
			//int b = mapTwins.get(i).getPrimePair().get(1);
			BigInteger A = fullPrimeList.get(a);
			
			//calculate ostensible starting value of next twin prime
			//only need to know first value in any given prime pair to fully characterize the pair
			A=A.add(BigInteger.ONE);
			A=A.multiply(BigInteger.valueOf(2));
			A=A.subtract(BigInteger.ONE);
			
			for(int j = i+1; j < mapTwins.size(); j++) {
				int s = mapTwins.get(j).getPrimePair().get(0);	//index of first item in prime pair
				BigInteger S = fullPrimeList.get(s);	//value of first item
				if (S.equals(A)) {
					Pair<Integer> upperPair = new Pair<Integer>();
					int t = mapTwins.get(j).getPrimePair().get(1);
					upperPair.setPrimePair(s,t);
					Pair<Pair<Integer>> HexCross = new Pair<Pair<Integer>>();
					HexCross.setPrimePair(mapTwins.get(i), upperPair);
					mapHexPairs.add(HexCross);
					break;
				}
			}
			
		
		}
		
	}
}