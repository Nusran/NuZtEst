/**
 * Student Name : Nusran Saleem
 * Student ID : IIT-2016504 , UOW - w1628101
 */

public class FlowNetwork {
	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	public static Bag<FlowEdge>[] adj;
	
	/**
	 * Initializes an empty flow network with {@code V} vertices and 0 edges.
	 * 
	 * @param V the number of vertices
	 * @throws IllegalArgumentException if {@code V < 0}
	 */
	public FlowNetwork(int V) {
		if (V < 0)
			throw new IllegalArgumentException("Number of vertices in a Graph must be nonnegative");
		this.V = V;
		this.E = 0;
		adj = (Bag<FlowEdge>[]) new Bag[V];
		for (int v = 0; v < V; v++) {
			adj[v] = new Bag<FlowEdge>();
		}
	}

	/**
	 * Initializes a random flow network with {@code V} vertices and <em>E</em>
	 * edges. The capacities are integers between 5 and 20 and the flow values are
	 * zero.
	 * 
	 * @param V the number of vertices
	 * @param E the number of edges
	 * @throws IllegalArgumentException if {@code V < 0}
	 * @throws IllegalArgumentException if {@code E < 0}
	 */
	public FlowNetwork(int V, int E) {
		this(V);
		if (E < 0)
			throw new IllegalArgumentException("Number of edges must be nonnegative");
		for (int i = 0; i < E; i++) {
			int v = StdRandom.uniform(V);
			int w = StdRandom.uniform(V);
			double flow = 0;
			double capacity = StdRandom.uniform(5, 20);
			addEdge(new FlowEdge(v, w, flow,capacity), V - 1);
			addMissingEdges();
		}
	}

	/**
	 * Returns the number of vertices in the edge-weighted graph.
	 * 
	 * @return the number of vertices in the edge-weighted graph
	 */
	public int V() {
		return V;
	}

	/**
	 * Returns the number of edges in the edge-weighted graph.
	 * 
	 * @return the number of edges in the edge-weighted graph
	 */
	public int E() {
		return E;
	}

	// throw an IllegalArgumentException unless {@code 0 <= v < V}
	private void validateVertex(int v) {
		if (v < 0 || v >= V)
			throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V - 1));
	}

	/**
	 * Adds the edge {@code e} to the network.
	 * 
	 * @param e the edge
	 * @throws IllegalArgumentException unless endpoints of edge are between
	 *                                  {@code 0} and {@code V-1}
	 */

	public void addEdge(FlowEdge e, int sink) {
		setEdge(e, sink);
		E++;
	}

	/**
	 * Set edge for flow network
	 * @param e the edge
	 * @param sink sink of the network
	 * 
	 */
	public void setEdge(FlowEdge e, int sink) {
		int v = e.from();
		int w = e.to();
		validateVertex(v);
		validateVertex(w);

		if (!isSource(w) && !isSink(v, sink) && !isEdgeExist(v, w)) {
			adj[v].add(e);
			adj[w].add(e);
		}
	}
	/**
	 * Validate sink
	 * @param e the edge
	 * @param sink sink of the network
	 * 
	 */

	public boolean isSink(int e, int sink) {
		return (e == sink);
	}

	/**
	 * Validate source
	 * @param e the edge
	 * 
	 */
	public boolean isSource(int e) {
		return (e == 0);
	}

	/**
	 * Validate edge
	 * @param v from node
	 * @param w to node
	 * 
	 */
	public boolean isEdgeExist(int v, int w) {
		boolean isExists = false;
		for (int i = 0; i < V(); i++) {
			for (FlowEdge e : adj(i)) {
				if (e.from() == v && e.to() == w) {
					isExists = true;
					break;
				}
			}
			if (isExists)
				break;
		}
		return isExists;
	}
	/**
	 * Validate from edge
	 * @param v from
	 */
	public boolean hasFromEdge(int v) {
		boolean hasFrom = false;
		for (int i = 0; i < V(); i++) {
			for (FlowEdge e : adj(i)) {
				if (e.from() == v) {
					hasFrom = true;
					break;
				}
			}
			if (hasFrom)
				break;
		}

		return hasFrom;
	}

	/**
	 * Validate to edge
	 * @param w to
	 */
	public boolean hasToEdge(int w) {
		boolean hasTo = false;
		for (int i = 0; i < V(); i++) {
			for (FlowEdge e : adj(i)) {
				if (e.to() == w) {
					hasTo = true;
					break;
				}
			}
			if (hasTo)
				break;
		}
		return hasTo;
	}
	/**
	 * Add missing edges to flow network
	 */
	public void addMissingEdges() {
		for (int i = 0; i < V(); i++) {
			if (!hasFromEdge(i)) {
				int w = StdRandom.uniform(V);
				double flow = 0;
				double capacity = StdRandom.uniform(5, 20);
				setEdge(new FlowEdge(i, w,flow, capacity), V - 1);
			}

			if (!hasToEdge(i)) {
				int v = StdRandom.uniform(V);
				double flow = 0;
				double capacity = StdRandom.uniform(5, 20);
				setEdge(new FlowEdge(v, i,flow, capacity), V - 1);
			}
		}
	}

	/**
	 * Returns the edges 
	 * to and from V
	 * 
	 * @param v the vertex
	 * @return the edges incident on vertex {@code v} as an Iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<FlowEdge> adj(int v) {
		validateVertex(v);
		return adj[v];
	}
	
	// return list of all edges - excludes self loops
	public Iterable<FlowEdge> edges() {
		Bag<FlowEdge> list = new Bag<FlowEdge>();
		for (int v = 0; v < V; v++)
			for (FlowEdge e : adj(v)) {
				if (e.to() != v)
					list.add(e);
			}
		return list;
	}

	/**
	 * Returns a string representation of the flow network. This method takes time
	 * proportional to <em>E</em> + <em>V</em>.
	 * 
	 * @return the number of vertices <em>V</em>, followed by the number of edges
	 *         <em>E</em>, followed by the <em>V</em> adjacency lists
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " " + E + NEWLINE);
		for (int v = 0; v < V; v++) {
			s.append(v + ":  ");
			for (FlowEdge e : adj[v]) {
				if (e.to() != v)
					s.append(e + "  ");
			}
			s.append(NEWLINE);
		}
		return s.toString();
	}

}
