package com.nusransaleem.api;

import java.math.BigInteger;

interface PerformOperation {
	boolean check(int a);
}
	
@SuppressWarnings("hiding")
public final class NInteger<Integer> {
   
		static NInteger integer;
		static PerformOperation operation;
		
		private boolean isOdd;
		private boolean isPrime;
		private boolean isPalindrom;
		private boolean isTriangle; 
		
		public boolean isOdd() {
			return isOdd;
		}

		public void setOdd(boolean isOdd) {
			this.isOdd = isOdd;
		}

		public boolean isPrime() {
			return isPrime;
		}

		public void setPrime(boolean isPrime) {
			this.isPrime = isPrime;
		}

		public boolean isPalindrom() {
			return isPalindrom;
		}

		public void setPalindrom(boolean isPalindrom) {
			this.isPalindrom = isPalindrom;
		}

		public boolean isTriangle() {
			return isTriangle;
		}

		public void setTriangle(boolean isTriangle) {
			this.isTriangle = isTriangle;
		}

		public PerformOperation odd() {
			return (int oddNum) -> oddNum % 2 != 0;
		}

		public PerformOperation prime() {
		return (int primeNum) -> BigInteger.valueOf(primeNum).isProbablePrime(1);
		}

//		public PerformOperation palindrome() {
//			return (int palindrome) -> Integer.toString(palindrome).equals(new StringBuilder(Integer.toString(palindrome)).reverse().toString());
//		}
	    
		public PerformOperation triangle() {
			return (int triangle) -> (long) (Math.sqrt(8*triangle+1)*Math.sqrt(8*triangle+1)) == 8*triangle+1;
		}
		
		public boolean isOdd(int num) {
			operation = integer.odd();
			return operation.check(num);
		}
		
		public boolean checker(PerformOperation p, int num) {
			return p.check(num);
		}
}

