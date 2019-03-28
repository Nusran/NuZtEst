import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class TestCode {

	public static void main(String[] args) {
		
		HashMap<String,String> map1 = new HashMap<String,String>();
		HashMap<String,String> map2 = new HashMap<String,String>();
		ArrayList lst = new ArrayList<>();
		
		
		map1.put("a1", "aa");
		map1.put("b", "bb");
		map1.put("c", "cc");
		map1.put("d", "dd");
		map1.put("e", "ee");
		lst.add(map1);
		map2.put("a1", "aaa");
		map2.put("b", "bbb");
		map2.put("c", "ccc");
		map2.put("d", "ddd");
		map2.put("e", "eee");
		lst.add(map2);
		
		lst.forEach(n->{
			java.util.HashMap< String, String> blueshiftData = (java.util.HashMap<String, String>) n;
			
			System.out.println(blueshiftData.get("a1"));
			System.out.println(blueshiftData.get("b"));
			System.out.println(blueshiftData.get("c"));
			System.out.println(blueshiftData.get("d"));
			System.out.println(blueshiftData.get("e"));
		});
		
	}
}