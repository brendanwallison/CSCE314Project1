package main;

import java.math.BigInteger;

//This class employees deterministic variants of the Miller Rabin test as its primary strategy
//Performance is great for n < 3,317,044,064,679,887,385,961,981 
//After that, performance will fall off a cliff
//Further deterministic variants, if they exist, might expand this

public class ThoughtfulTest
{
	public static boolean isPrime(BigInteger candidate)
	{
		
		Pair<Boolean>millerRabinResult = millerRabinDriver(candidate);
		//deterministic true
		if(millerRabinResult.left()){
			//deterministic and prime true
			if(millerRabinResult.right()) {
				return true;
			}
			//deterministic true and prime false
			else return false;
		}
		//deterministic false, prime true
		else if (!millerRabinResult.right()) {
			return false;
		}
		
		//everything that follows is a probable prime case

		BigInteger loopStop = candidate.sqrt().add(BigInteger.ONE);
		for (BigInteger outerIndex = BigInteger.TWO; !outerIndex.equals(loopStop); outerIndex = outerIndex
				.add(BigInteger.ONE))
		{
			for (BigInteger innerIndex = outerIndex; !innerIndex.equals(loopStop); innerIndex = innerIndex
					.add(BigInteger.ONE))
			{
				if (candidate.equals(innerIndex.multiply(outerIndex)))
					return false;
			}
		}
		return true;
	}
	
	//left value says whether result is deterministic
	//right value is the result
	public static Pair<Boolean> millerRabinDriver(BigInteger candidate) {
		Boolean isDeterministic = true;
		Boolean isPrime = false;
		BigInteger comparison = BigInteger.TWO;		
		//basic prime tests
		if (candidate.compareTo(comparison) == -1)  
			return new Pair<Boolean>(isDeterministic, isPrime);
		//equals two?
		if (candidate.compareTo(comparison) == 0) {
			isPrime = true;
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		//even number?
		if (candidate.mod(comparison).compareTo(BigInteger.ZERO) == 0) {
			return new Pair<Boolean>(isDeterministic, isPrime);
		}	
		//equals three?
		comparison = comparison.add(BigInteger.ONE);
		if (candidate.compareTo(comparison) == 0) {
			isPrime = true;
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		//divisible by three?
		if (candidate.mod(comparison).compareTo(BigInteger.ZERO) == 0) {
			return new Pair<Boolean>(isDeterministic, isPrime);
		}	
		

		BigInteger d = candidate.subtract(BigInteger.ONE);
		//BigInteger s = BigInteger.ZERO;
		long s = 0;
		//rewrite n-1 as 2^s*d
		//the below function finds the largest odd composite of n-1
		while (d.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
			d = d.divide(BigInteger.TWO);
			s++;
			//s.add(BigInteger.ONE);
		}

		if(candidate.compareTo(BigInteger.valueOf(2047)) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		
		if(candidate.compareTo(BigInteger.valueOf(1373653)) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		
		if(candidate.compareTo(BigInteger.valueOf(9080191)) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(31)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(73));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		
		if(candidate.compareTo(BigInteger.valueOf(25326001)) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}		
		if(candidate.compareTo(new BigInteger("3215031751")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(7));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}		
		if(candidate.compareTo(new BigInteger("4759123141")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(7))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(61));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		if(candidate.compareTo(new BigInteger("1122004669633")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(13))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(23))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(1662803));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		if(candidate.compareTo(new BigInteger("2152302898747")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(7))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(11));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		if(candidate.compareTo(new BigInteger("3474749660383")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(7))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(11))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(13));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		if(candidate.compareTo(new BigInteger("341550071728321")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(7))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(11))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(17));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		if(candidate.compareTo(new BigInteger("3825123056546413051")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(7))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(11))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(13))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(17))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(19))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(23));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		if(candidate.compareTo(new BigInteger("318665857834031151167461")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(7))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(11))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(13))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(17))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(19))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(23))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(29))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(31))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(37));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		if(candidate.compareTo(new BigInteger("3317044064679887385961981")) == -1) {
			isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(7))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(11))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(13))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(17))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(19))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(23))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(29))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(31))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(37))
					&& millerRabinTest(candidate, s, d, BigInteger.valueOf(41));
			return new Pair<Boolean>(isDeterministic, isPrime);
		}
		
		isDeterministic = false;
		isPrime = millerRabinTest(candidate, s, d, BigInteger.valueOf(2)) 
				&& millerRabinTest(candidate, s, d, BigInteger.valueOf(3))
				&& millerRabinTest(candidate, s, d, BigInteger.valueOf(5));
		return new Pair<Boolean>(isDeterministic, isPrime);
		
		
	}
	
	public static boolean millerRabinTest(BigInteger candidate, long s, BigInteger d, BigInteger a) {
		BigInteger x = a.modPow(d, candidate);

		if (x.compareTo(BigInteger.ONE) == 0 || x.compareTo(candidate.subtract(BigInteger.ONE)) == 0) {
			return true;
		}

		long i;
		for (i = 1; i < s; i++) {
			x = x.modPow(BigInteger.TWO, candidate);
			if (x.compareTo(BigInteger.ONE) == 0) {
				return false;
			}
			if (x.compareTo(candidate.subtract(BigInteger.ONE)) == 0) {
				return true;
			}
		}
		return false;
		
	}
	
	
	
}
