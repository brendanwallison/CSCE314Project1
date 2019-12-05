package main;

// This file gives access to the underlying datafile and stores the data in the Workout class.
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Iterator;
import java.util.Scanner;

public class FileAccess
{

	public static boolean loadPrimes(Primes primes, String filename)
	{
		primes.clearPrimes();
		File primeFile = new File(Config.DATAPATH + filename); 
		try{
			Scanner sc = new Scanner(primeFile); 
			while (sc.hasNext()) {
				BigInteger val = new BigInteger(sc.next());
				primes.addPrime(val);
			} 
			sc.close();
		}
		catch (IOException e){
			return false;
		}
		return true;
	}

	public static boolean loadCrosses(Primes primes, String filename)
	{
		primes.clearCrosses();
		File crossFile = new File(Config.DATAPATH + filename); 
		try{
			Scanner sc = new Scanner(crossFile); 
			while (sc.hasNextLine()) {
				//System.out.println(sc.nextLine());
				String ln = sc.nextLine();
				String crs[]= ln.split(",", 2);
				BigInteger left = new BigInteger(crs[0]);
				BigInteger right = new BigInteger(crs[1]);
				primes.addCross(new Pair<BigInteger>(left,right));
			} 
			sc.close();
		}
		catch (IOException e){
			return false;
		}
		return true;
	}

	public static boolean savePrimes(Primes primes, String filename)
	{
		try {
			FileWriter w = new FileWriter(Config.DATAPATH + filename);
			for (BigInteger x : primes.iteratePrimes())
			{
				w.write(x.toString() + "\n");			
			}
			w.close();
		}
		catch(IOException e) {
			return false;
		}
		return true;
	}

	public static boolean saveCrosses(Primes primes, String filename)
	{
		try {
			FileWriter w = new FileWriter(Config.DATAPATH + filename);
			for (Pair<BigInteger> x : primes.iterateCrosses())
			{
				w.write(x.left().toString() + "," + x.right().toString() + "\n");			
			}
			w.close();
		}
		catch(IOException e) {
			return false;
		}
		return true;
	}
}