import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test {

	public static void main(String[] args) {
		int a =1;
		String str = "This is a string";
		double b =2.5;
		
		Integer[] intArr = {1,2,3,4};
		String[] strArr = {"a","b","c"};
		Double[] dblArr = {1.5,2.5,3.5,4.5};
		
		System.out.println("=======DATA==========");
		printAnyData(a);
		System.out.println("=====================");
		printAnyData(str);
		System.out.println("=====================");
		printAnyData(b);
		System.out.println("=======ARRAYS========");
		printAnyArrays(intArr);
		System.out.println("=====================");
		printAnyArrays(strArr);
		System.out.println("=====================");
		printAnyArrays(dblArr);

	}
	
	public static <T> void printAnyData(T data) {
		System.out.println(data);
	}
	
	public static <T> void printAnyArrays(T[] arr) {
		 for(T prnt : arr) 
	            System.out.println(prnt);
	}
}
