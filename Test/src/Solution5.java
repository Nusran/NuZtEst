class MySolution<A, B> {
	A a;
	B b;

	MySolution(A a, B b) {
		this.a = a;
		this.b = b;
	}

	public A getA() {
		return a;
	}

	public void setA(A a) {
		this.a = a;
	}

	public B getB() {
		return b;
	}

	public void setB(B b) {
		this.b = b;
	}
	
	public void print() {
		System.out.println("a: "+a+" , b: "+b);
	}
}

public class Solution5 {

	public static void main(String[] args) {
		MySolution sol = new MySolution("hello", 5);
		sol.print();
		MySolution sol2 = new MySolution(10, "world");
		sol2.print();
		print("hi",1.2);
	}

	public static <U,T> void print(U u, T t) {
		System.out.println("U: "+u+" , T: "+t);
	}
}
