import java.io.FileInputStream;
import java.util.Properties;

public class ReadPropertyFile {

	public static void main(String[] args) {
		
		String filePath = "C:\\Users\\nusrans\\Desktop\\te.properties";
		boolean isOk = false;
		
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(filePath));
			isOk = true;
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		
		if(isOk)System.out.println(properties.getProperty("name"));
	}

}
