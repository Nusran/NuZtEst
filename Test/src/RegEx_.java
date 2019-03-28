import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegEx_ {
	
	public static void main(String[] args) {
		System.out.println("IS VALID IP ? ");
		System.out.println("192.168.50.251 : "+isValidIp("192.168.50.251"));
		System.out.println("10.11.12.13.13 : "+isValidIp("10.11.12.13.13"));
		System.out.println("10.11.12.13    : "+isValidIp("10.11.12.13"));
		System.out.println("1.11.1.13      : "+isValidIp("10.11.12.13"));
	}
	
	public static boolean isValidIp(String ip) {
		Pattern pattern = Pattern.compile("^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$");
		Matcher matcher = pattern.matcher(ip);
		return matcher.find();
	}
	
}
