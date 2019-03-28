import java.io.IOException;
import java.lang.Runtime;

public class testCode10 {

	public static void main(String[] args) {
		try {
			Runtime.getRuntime().exec("mspaint");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
