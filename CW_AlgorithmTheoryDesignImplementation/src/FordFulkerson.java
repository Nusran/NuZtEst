/**
 * Student Name : Nusran Saleem
 * Student ID : IIT-2016504 , UOW - w1628101
 */

import java.util.ArrayList;
import java.util.Random;

public class FordFulkerson {
	
	private static final double FLOATING_POINT_EPSILON = 1E-11;
	private static int V; // number of vertices
	private boolean[] marked; // marked[v] = true iff s->v path in residual graph
	public static FlowEdge[] edgeTo; // edgeTo[v] = last edge on shortest residual s->v path
	private double value; // current value of max flow
	public static ArrayList lstAugPaths;
	
	/**
	 * Compute a maximum flow from vertex {@code s} to vertex {@code t}.
	 * 
	 * @param G the flow network
	 * @param s the source vertex
	 * @param t the sink vertex
	 * @throws IllegalArgumentException unless {@code 0 <= s < V}
	 * @throws IllegalArgumentException unless {@code 0 <= t < V}
	 * @throws IllegalArgumentException if {@code s == t}
	 * @throws IllegalArgumentException if initial flow is infeasible
	 */
	public FordFulkerson(FlowNetwork G, int s, int t) {
		
		V = G.V();
		validate(s);
		validate(t);
		
		lstAugPaths = new ArrayList();
		AugmentingPath ap = new AugmentingPath();
		ap.augNodes = new ArrayList();
		
		if (s == t)
			throw new IllegalArgumentException("Source equals sink");
		
		while (hasAugmentingPath(G, s, t)) {
			
			// compute bottleneck capacity
			double bottle = Double.POSITIVE_INFINITY;
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
				ap.augNodes.add(edgeTo[v].to());
				if(edgeTo[v].from()==0) {
					ap.augNodes.add(edgeTo[v].from());
				    ap.flowCapacity = bottle+" / "+edgeTo[v].capacity(); 
					lstAugPaths.add(ap);
					ap = new AugmentingPath();
					ap.augNodes = new ArrayList();
				}
			}
			// augment flow
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				edgeTo[v].addResidualFlowTo(v, bottle);
			}

			value += bottle;
		}

		// check optimality conditions
		assert check(G, s, t);
	}

	/**
	 * Returns the value of the maximum flow.
	 *
	 * @return the value of the maximum flow
	 */
	public double value() {
		return value;
	}

	/**
	 * Returns true if the specified vertex is on the {@code s} side of the mincut.
	 *
	 * @param v vertex
	 * @return {@code true} if vertex {@code v} is on the {@code s} side of the
	 *         micut; {@code false} otherwise
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public boolean inCut(int v) {
		validate(v);
		return marked[v];
	}

	// throw an IllegalArgumentException if v is outside prescibed range
	private void validate(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}


	private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
		edgeTo = new FlowEdge[G.V()];
		marked = new boolean[G.V()];

		// breadth-first search
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(s);
		marked[s] = true;
		while (!queue.isEmpty() && !marked[t]) {
			int v = queue.dequeue();

			for (FlowEdge e : G.adj(v)) {
				int w = e.other(v);

				// if residual capacity from v to w
				if (e.residualCapacityTo(w) > 0) {
					if (!marked[w]) {
						edgeTo[w] = e;
						marked[w] = true;
						queue.enqueue(w);
					}
				}
			}
		}
		// is there an augmenting path?
		return marked[t];
	}

	// check optimality conditions
	private boolean check(FlowNetwork G, int s, int t) {

		// check that s is on the source side of min cut and that t is not on source
		// side
		if (!inCut(s)) {
			System.err.println("source " + s + " is not on source side of min cut");
			return false;
		}
		if (inCut(t)) {
			System.err.println("sink " + t + " is on source side of min cut");
			return false;
		}

		// check that value of min cut = value of max flow
		double mincutValue = 0.0;
		for (int v = 0; v < G.V(); v++) {
			for (FlowEdge e : G.adj(v)) {
				if ((v == e.from()) && inCut(e.from()) && !inCut(e.to()))
					mincutValue += e.capacity();
			}
		}

		if (Math.abs(mincutValue - value) > FLOATING_POINT_EPSILON) {
			System.err.println("Max flow value = " + value + ", min cut value = " + mincutValue);
			return false;
		}

		return true;
	}

	public static int getVertices() {
		return getRandomNumber(6, 12);
	}

	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public static int getEdges() {
		return getRandomNumber(6, 20);
	}

	/**
	 * Unit tests the {@code FordFulkerson} data type.
	 *
	 * @param args the command-line arguments
	 */
	public static void main(String[] args) {

		// create flow network with V vertices and E edges
		int V = getVertices();
		int E = getEdges();
		
		Stopwatch sw = new Stopwatch();
		
		int s = 0, t = V - 1;
		FlowNetwork G = new FlowNetwork(V, E);
		StdOut.println(G);
		
		// compute maximum flow and minimum cut
		FordFulkerson maxflow = new FordFulkerson(G, s, t);
		StdOut.println("Max flow from 0 to " + t);
		for (int v = 0; v < G.V(); v++) {
			for (FlowEdge e : G.adj(v)) {
				if ((v == e.from()) && e.flow() > 0) {
					StdOut.println(e);			
				}
			}
		}
	
		StdOut.println("Max flow value = " + maxflow.value());
		StdOut.println("Augmenting Path: "+lstAugPaths.toString());
		GraphViews view = new GraphViews(FlowNetwork.adj,lstAugPaths);
		System.out.println("====>Elapsed time : "+sw.elapsedTime());
		
	}
	
}


/**
 * Student Name : Nusran Saleem
 * Student ID : IIT-2016504 , UOW - w1628101
 */
class AugmentingPath{
	
	ArrayList augNodes;
	String flowCapacity;
	
	public String getFlowCapacity() {
		return flowCapacity;
	}

	public ArrayList getAugNodes() {
		return augNodes;
	}
	
	public String toString() {
		return augNodes+"->("+flowCapacity+")  ";
	}
}

class Stopwatch { 

    private final long start;

   /**
     * Create a stopwatch object.
     */
    public Stopwatch() {
        start = System.currentTimeMillis();
    } 


   /**
     * Return elapsed time (in seconds) since this object was created.
     */
    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

} 

 