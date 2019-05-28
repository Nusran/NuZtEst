
import java.io.Console;
import java.io.IOException;

public class ReadPassword {
	public static void main(String args[]) throws IOException {
		Console console = System.console();
        try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Please enter your input: ");
		String input = console.readLine();
		System.out.println("User Input from console: " + input);
		System.out.println("Reading password from Console in Java: "); // password will not be echoed to console and
																		// stored in char array
		char[] password = console.readPassword();
		System.out.println("Password entered by user: " + new String(password));

	}
}
