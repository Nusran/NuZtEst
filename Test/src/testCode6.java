import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 */

/**
 * @author NusranS
 *
 */
public class testCode6 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
////			ArrayList lst = new ArrayList<>();
////			for(int i=0; i<10; i++) {
////				String[] dtr = {"a","b"};
////				lst.add(dtr);
////			}
////
////			for (int i = 0; i < lst.size(); i++) {
////				String[] lstA = (String[])lst.get(i);
////				System.out.println(lstA[0]+","+lstA[1]);
////			}
//		ArrayList lst = new ArrayList<>(Arrays.asList("a","b","c"));
//
//		System.out.println(lst.size());
//		
//		lst.remove(0);
//		System.out.println(lst.size());
//		
//		lst.remove(0);
//		System.out.println(lst.size());
//		
//		lst.remove(0);
//		System.out.println(lst.size());
		
		String str1 = "", str2="",str3="" ;
		String[] str = {str1,str2,str3};
		ArrayList lst = new ArrayList<>();
		lst.add(str);
		
		String[] lst2 = (String[]) lst.get(0);
		for(int i=0;i<lst2.length;i++) {
			System.out.println(lst2[i]);
		}
	}

}
