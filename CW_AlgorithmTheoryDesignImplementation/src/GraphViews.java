
/**
 * Student Name : Nusran Saleem
 * Student ID : IIT-2016504 , UOW - w1628101
 */

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

enum Nodes {
	A, B, C, D, E, F, G, H, I, J, K, L
}

public class GraphViews {

	private static Nodes[] nodes = Nodes.values();
	private static Graph<String, String> g;
	private static int E = 0;
	private static int count = 0;
	private static int numOfNodes;
	private static Bag<FlowEdge>[] adj;

	private static HashMap<String, Paint> colorNodes = new HashMap<String, Paint>();
	private static HashMap<String, String> lblEdges = new HashMap<String, String>();

	private JFrame frame;
	private JPanel contentPane;
	private JPanel graphPanel;


	public GraphViews(Bag<FlowEdge>[] adj, ArrayList<AugmentingPath> AugemntPaths) {

		GraphViews.numOfNodes = adj.length;
		GraphViews.adj = adj;

		reSetColor();
		g = getGraph();
		BasicVisualizationServer<String, String> vv = getVisualaization(g, colorNodes, lblEdges);

		frame = new JFrame("Graph");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 204, 160);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		graphPanel = new JPanel();
		graphPanel.add(vv);

		frame.setContentPane(contentPane);

		Panel panel = new Panel();
		contentPane.add(panel, BorderLayout.CENTER);

		panel.add(graphPanel);

		Button button = new Button("Augmenting Path");
		contentPane.add(button, BorderLayout.SOUTH);
		
		/* Print augmenting path when button is clicked*/
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (count < AugemntPaths.size()) {
					setAugmentingNode(AugemntPaths.get(count));
					JOptionPane.showMessageDialog(frame, vv);	
					reSetColor();
					count++;
				} else {
					JOptionPane.showMessageDialog(frame, "!No Augmenting paths to display.");
				}
			}
		});
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Returns the number of edges in the edge-weighted graph.
	 * 
	 * @return the number of edges in the edge-weighted graph
	 */
	public int E() {
		return E;
	}
	public static void reSetColor() {
		for (int i = 0; i < nodes.length; i++) {
			colorNodes.put(nodes[i].name(), Color.YELLOW);
		}
	}
	/**
	 * Returns the visualization of the graph
	 * @return visualization with layout
	 */
	public static BasicVisualizationServer<String, String> getVisualaization(Graph g, HashMap<String, Paint> colorNodes,HashMap<String, String> lblEdges) {
		BasicVisualizationServer<String, String> vv = getVisualLayout(g);
		vv.getRenderContext().setVertexFillPaintTransformer(setColor(colorNodes));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderContext().setEdgeLabelTransformer(setCapacity(lblEdges));
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		return vv;
	}

	/**
	 * Returns the visual layout of the graph
	 * @return   layout
	 */
	public static BasicVisualizationServer<String, String> getVisualLayout(Graph g) {
		Layout<String, String> layout = new CircleLayout<String, String>(g);
		layout.setSize(new Dimension(400, 400));
		BasicVisualizationServer<String, String> visualLayout = new BasicVisualizationServer<String, String>(layout);
		return visualLayout;
	}

	/**
	 * get capacity for the edge
	 * @return capacity
	 */
	public static Transformer setCapacity(HashMap<String, String> Capacity) {
		Transformer<String, String> edgeLabel = new Transformer<String, String>() {
			public String transform(String label) {
				return Capacity.get(label);
			}
		};
		return edgeLabel;
	}

	/**
	 * Set color for the node
	 */
	public static Transformer setColor(HashMap<String, Paint> colorNodes) {
		Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
			public Paint transform(String i) {
				return colorNodes.get(i);
			}
		};
		return vertexPaint;
	}

	/**
	 * Set graph
	 */
	public static Graph<String, String> getGraph() {
		g = new DirectedSparseGraph<String, String>();

		for (int i = 0; i < numOfNodes; i++) {
			if (i == numOfNodes - 1)
				g.addVertex((String) nodes[nodes.length - 1].name());
			else
				g.addVertex((String) nodes[i].name());
		}

		for (int i = 0; i < numOfNodes; i++) {
			for (FlowEdge e : adj[i]) {
				if (e.from() != e.to() && e.from() != numOfNodes - 1)
					if (e.to() == numOfNodes - 1)
						g.addEdge(String.valueOf(E), nodes[e.from()].name(), nodes[nodes.length - 1].toString());
					else
						g.addEdge(String.valueOf(E), nodes[e.from()].name(), nodes[e.to()].name());

				lblEdges.put(String.valueOf(E), " 0 /" + e.capacity());
				E++;
			}
		}
		return g;
	}
	/**
	 * Get augmenting path
	 */
	public static void setAugmentingNode(AugmentingPath augmentingPath) {
		ArrayList lstNodes = augmentingPath.getAugNodes();
		
		for (int i = 0; i < lstNodes.size(); i++) {
			colorNodes.put(nodes[(int)lstNodes.get(i)].name(), Color.GREEN);
			colorNodes.put("L", Color.GREEN);
			
			if((int)lstNodes.get(i)!=0) {
				String edge = g.findEdge(nodes[(int)lstNodes.get(i+1)].name(),nodes[(int)lstNodes.get(i)].name());
				if(edge != null) lblEdges.put(edge,augmentingPath.getFlowCapacity());
			}	
			String edge = g.findEdge(nodes[(int)lstNodes.get(1)].name(),"L");
			if(edge != null) lblEdges.put(edge,augmentingPath.getFlowCapacity());
		}

	}
}