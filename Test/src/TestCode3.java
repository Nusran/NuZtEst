import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class TestCode3 {

	interface PerformOperation {
		boolean check(int a);
	}

	static class MyMaths {

		public static PerformOperation isOdd() {
			return (int a) -> a % 2 != 0;
		}

		public static PerformOperation isPrime() {
			return (int a) -> BigInteger.valueOf(a).isProbablePrime(1);
		}

		public static PerformOperation isPalindrome() {
			return (int a) -> String.valueOf(a).equals(new StringBuilder(String.valueOf(a)).reverse().toString());
		}

		public static boolean checker(PerformOperation p, int num) {
			return p.check(num);
		}
	}

	public static void main(String[] args) throws IOException {

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
				op = MyMaths.isOdd();
				ret = MyMaths.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = MyMaths.isPrime();
				ret = MyMaths.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = MyMaths.isPalindrome();
				ret = MyMaths.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

			}
			System.out.println(ans);
		}

	}

}
