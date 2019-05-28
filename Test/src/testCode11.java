
public class testCode11 {
	
	public static void M() {
		new Object() {
		    void hi(String in) {
		        System.out.println(in);
		    }
		}.hi("weird");
	}
	
	public static void main(String[] args) {
		testCode11 tc = new testCode11();
		tc.M();
	}
}
