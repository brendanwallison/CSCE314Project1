import java.util.ArrayList; 
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
	}
	
	public boolean isPrime(BigInteger x)
	{
		if()
	}
	
	// Output the prime list. Each prime should be on a separate line and the total number of primes should be on the following line.
	public void printPrimes()
	{
		for(int)
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
	}
	
	//Generate an upper bound of a range guaranteed to contain at least n primes
	//Uses Dusart 2018 algorithm, valid for x>1
	private int primeLimit(int n) {
		if (n==1) {
			return 2;
		}
		double x = (double) n;
		double limit = (x/Math.log(x)) * (1 + 1/Math.log(x) + 2/Math.pow(Math.log(x), 2) + 7.59/Math.pow(Math.log(x), 3));
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