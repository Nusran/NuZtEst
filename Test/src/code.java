import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

class code {

	public static void main(String[] args) {
		
//		String st1 = "abc";
//		String st2 = "ab";
//		
//		System.out.println(String.format("%5s", st1).replace(" ", "0"));
//		System.out.println(String.format("%5s", st2).replace(" ", "0"));
	
		ArrayList lst = new ArrayList<String>(Arrays.asList("a","b","c"));
		String str = lst.stream().collect(Collectors.joining(File.separator)).toString();
		System.out.println(str);
				
	}

}
