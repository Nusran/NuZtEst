import java.io.*;
import java.math.BigInteger;
import java.util.*;


interface PerformOperation {
	boolean check(int a);
}

class MyMath {
	public boolean checker(PerformOperation p, int num) {
		return p.check(num);
	}

	public PerformOperation isOdd() {
		return (int oddNum) -> oddNum % 2 != 0;
	}

	public PerformOperation isPrime() {
		return (int primeNum) -> BigInteger.valueOf(primeNum).isProbablePrime(1);
	}

	public PerformOperation isPalindrome() {
		return (int palindrome) -> Integer.toString(palindrome).equals(new StringBuilder(Integer.toString(palindrome)).reverse().toString());
	}
	
	public PerformOperation isTriangle() {
		return (int triangle) -> (long) (Math.sqrt(8*triangle+1)*Math.sqrt(8*triangle+1)) == 8*triangle+1;
	}
}

public class Solution2 {

	public static void main(String[] args) throws IOException {
		MyMath ob = new MyMath();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		PerformOperation op;
		boolean ret = false;
		String ans = null;
		while (T-- > 0) {
			String s = br.readLine().trim();
			StringTokenizer st = new StringTokenizer(s);
			int ch = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (ch == 1) {
				op = ob.isOdd();
				ret = ob.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = ob.isPrime();
				ret = ob.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = ob.isPalindrome();
				ret = ob.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

			}else if(ch == 4) {
				op = ob.isTriangle();
				ret = ob.checker(op, num);
				ans = (ret) ? "TRIANGLE" : "NOT TRIANGLE";
			}
			System.out.println(ans);
		}
	}
}
