package com.nusransaleem.api;

import java.math.BigInteger;
import java.util.StringTokenizer;

import javax.print.DocFlavor.STRING;

interface PerformOperation {
	boolean check(int a);
}

public class NInteger extends StringTokenizer {
	
	public NInteger(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}


	public boolean checker(int num) {
		PerformOperation p = new PerformOperation() {	
			@Override
			public boolean check(int a) {
				return false;
			}
		}; 
		return p.check(num);
	}

	
	public PerformOperation isOdd() {
		return (int oddNum) -> oddNum % 2 != 0;
	}

	public PerformOperation isPrime() {
		return (int primeNum) -> BigInteger.valueOf(primeNum).isProbablePrime(1);
	}

	public PerformOperation isPalindrome() {
		return (int palindrome) -> Integer.toString(palindrome)
				.equals(new StringBuilder(Integer.toString(palindrome)).reverse().toString());
	}

	public PerformOperation isTriangle() {
		return (int triangle) -> (long) (Math.sqrt(8 * triangle + 1) * Math.sqrt(8 * triangle + 1)) == 8 * triangle + 1;
	}
	
	public static void main(String[] args) {
		
	}
}
