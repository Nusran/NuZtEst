import java.util.InputMismatchException;
import java.util.Scanner;

public class Solution7 {

	static Scanner scan = new Scanner(System.in);
	public static void p(){  
		int a =0;
		do {
		    try {
		        System.out.print("Enter an integer: ");
		         a = scan.nextInt();
		    } catch (InputMismatchException e) {
		        System.out.print("!  Invalid input.\n ");
		    }
		    scan.nextLine(); // clears the buffer
		} while (a <= 0);
	}
	public static void main(String[] args) {
		
		p();
	}

}
