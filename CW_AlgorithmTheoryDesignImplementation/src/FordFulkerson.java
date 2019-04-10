import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class FordFulkerson {
	private static final double FLOATING_POINT_EPSILON = 1E-11;
	private static int V; // number of vertices
	private boolean[] marked; // marked[v] = true iff s->v path in residual graph
	private FlowEdge[] edgeTo; // edgeTo[v] = last edge on shortest residual s->v path
	private double value; // current value of max flow
	public static ArrayList lstAugPaths;
	public static ArrayList augNodes;
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
		augNodes = new ArrayList();
		if (s == t)
			throw new IllegalArgumentException("Source equals sink");
		// if (!isFeasible(G, s, t)) throw new IllegalArgumentException("Initial flow is
		// infeasible");
		
		// while there exists an augmenting path, use it
		// value = excess(G, t);
		while (hasAugmentingPath(G, s, t)) {
			
			// compute bottleneck capacity
			double bottle = Double.POSITIVE_INFINITY;
			for (int v = t; v != s; v = edgeTo[v].other(v)) {
				bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
				System.out.println("===->" + edgeTo[v].from() + "," + edgeTo[v].to());
				augNodes.add(edgeTo[v].to());
				if(edgeTo[v].from()==0) {
					augNodes.add(edgeTo[v].from());
				    lstAugPaths.add(augNodes);
				    augNodes = new ArrayList();
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

	// is there an augmenting path?
	// if so, upon termination edgeTo[] will contain a parent-link representation of
	// such a path
	// this implementation finds a shortest augmenting path (fewest number of
	// edges),
	// which performs well both in theory and in practice
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
		return getRandomNumber(4, 10);
	}

	public static int getRandomNumber(int min, int max) {
		Random random = new Random();
		return random.nextInt(max - min) + min;
	}

	public static int getEdges() {
		return getRandomNumber(4, 20);
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

		int s = 0, t = V - 1;
		FlowNetwork G = new FlowNetwork(V, E);
		StdOut.println(G);
		HashSet<Integer> set1=new HashSet<Integer>();
		// compute maximum flow and minimum cut
		FordFulkerson maxflow = new FordFulkerson(G, s, t);
		StdOut.println("Max flow from 0 to " + t);
		for (int v = 0; v < G.V(); v++) {
			for (FlowEdge e : G.adj(v)) {
				if ((v == e.from()) && e.flow() > 0) {
					StdOut.println(" ============= " + e);
					StdOut.println(e.from() + "," + e.to());
					
				}
				set1.add(e.to());
			}
		}
		StdOut.println("Max flow value = " + maxflow.value());
		System.out.println(set1);
		StdOut.println("Augmenting Path: "+lstAugPaths);
		GraphViews view = new GraphViews(FlowNetwork.adj.length, FlowNetwork.adj,lstAugPaths);
	}

}
