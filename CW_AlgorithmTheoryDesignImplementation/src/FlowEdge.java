/**
 * Student Name : Nusran Saleem
 * Student ID : IIT-2016504 , UOW - w1628101
 */


public class FlowEdge {
	private final int v, w;
	private final double capacity;
	private double flow;

	public FlowEdge(int v, int w, double flow,double capacity) {
		this.v = v;
		this.w = w;
		this.flow = flow;
		this.capacity = capacity;
	}

	public int from() {
		return v;
	}

	public int to() {
		return w;
	}

	public double capacity() {
		return capacity;
	}

	public double flow() {
		return flow;
	}

	public int other(int vertex) {
		if (vertex == v)
			return w;
		else if (vertex == w)
			return v;
		else
			throw new IllegalArgumentException();
	}

	public double residualCapacityTo(int vertex) {
		if (vertex == v)
			return flow;
		else if (vertex == w)
			return capacity - flow;
		else
			throw new IllegalArgumentException();
	}

	public void addResidualFlowTo(int vertex, double delta) {
		if (vertex == v)
			flow -= delta;
		else if (vertex == w)
			flow += delta;
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Returns a string representation of the edge.
	 * 
	 * @return a string representation of the edge
	 */
	public String toString() {
		return v + "->" + w + " " + flow + "/" + capacity;
	}
	
}
