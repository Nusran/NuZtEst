import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Printer {
   <P> void printArray(P[] p) { 
	   System.out.println(p.getClass().getSimpleName());
		for (P prnt : p)
			System.out.println(prnt);
	}
}

//class Player {
//	String name;
//	int score;
//
//	Player(String name, int score) {
//		this.name = name;
//		this.score = score;
//	}
//}
//
//class Checker implements Comparator<Player> {
//
//	@Override
//	public int compare(Player p1, Player p2) {
//		if (p1.score == p2.score)
//			return 0;
//		else
//			return 0;
//	}
//
//}

public class Solution {

	public static void main(String args[]) {

		// =============================================================================
		Printer myPrinter = new Printer();
		Integer[] intArray = { 1, 2, 3 };
		String[] stringArray = { "Hello", "World" };
		Double[] dbleArray = { 1.5, 2.0, 5.5 };
		myPrinter.printArray(intArray);
		myPrinter.printArray(stringArray);
		myPrinter.printArray(dbleArray);
		printVar(intArray[2]);
		printVar(stringArray[0]);
		int count = 0;

		for (Method method : Printer.class.getDeclaredMethods()) {
			String name = method.getName();

			if (name.equals("printArray"))
				count++;
		}

		if (count > 1)
			System.out.println("Method overloading is not allowed!");

//		Scanner scan = new Scanner(System.in);
//		int n = scan.nextInt();
//
//		Player[] player = new Player[n];
//		Checker checker = new Checker();
//
//		for (int i = 0; i < n; i++) {
//			player[i] = new Player(scan.next(), scan.nextInt());
//		}
//		scan.close();
//
//		Arrays.sort(player, checker);
//		for (int i = 0; i < player.length; i++) {
//			System.out.printf("%s %s\n", player[i].name, player[i].score);
//		}
	}

	public static <A> void printVar(A var) {
		System.out.println(var);
	}
}