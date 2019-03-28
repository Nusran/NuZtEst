import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TestCode2 {

	public static void main(String[] args) {

//		String data = ""; 
//	    try {
//			data = new String(Files.readAllBytes(Paths.get("C:\\Users\\nusrans\\Desktop\\textFile.txt")));
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
//	    System.out.println(data);

//		String date = "19960115";
//		SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
//		String newDate = f.parse(date);
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//		String formatDateTime = date .format(date,formatter);
//		
//		boolean isBefore = LocalDate.parse(formatDateTime).isBefore(LocalDate.parse(LocalDate.now().toString()));
//		
//     	System.out.println(isBefore);

		String d = "19990101";
		SimpleDateFormat fromUser = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");

		try {
			String reformattedStr = myFormat.format(fromUser.parse(d));
			System.out.println(reformattedStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}
	
	
}
