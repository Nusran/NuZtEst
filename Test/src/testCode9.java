import java.io.Console;

public class testCode9 {

	public static void main(String[] args) {
		System.out.println("hello");
		System.out.println("hello");
		java.io.Console console = System.console();
		String username = console.readLine("Username: ");
		String password = new String(console.readPassword("Password: "));
		System.out.println(username + "/" + password);

	}
}