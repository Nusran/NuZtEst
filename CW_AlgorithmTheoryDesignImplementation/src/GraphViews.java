
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

enum NodesNames {
	A, B, C, D, E, F, G, H, I, J, K, L
}

public class GraphViews {

	private static Nodes[] nodes = Nodes.values();
	private static Graph<String, String> g;
	private static int count = 0;
	private static HashMap<String, Paint> colorNodes  = new HashMap<String, Paint> ();

	public GraphViews(int numOfNodes, Bag<FlowEdge>[] adj, ArrayList<ArrayList<Integer>> AugemntPaths) {
		
		reSetColor();
		Graph<String, String> graph = getGraph(numOfNodes, adj);
		BasicVisualizationServer<String, String> vv = getVisualaization(graph, colorNodes);
		
		JFrame frame = new JFrame("Graph");
		JButton btnAugPath = new JButton("Augmenting Path");
		JPanel pnlBase = new JPanel();
		frame.setLocation(100, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(pnlBase);
		pnlBase.add(vv);
		pnlBase.add(btnAugPath);
		btnAugPath.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (count < AugemntPaths.size()) {
					setAugmentingNode(AugemntPaths.get(count));
					BasicVisualizationServer<String, String> vv = getVisualaization(graph, colorNodes);
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

	public static void reSetColor() { 
		for (int i = 0; i < nodes.length; i++) {
			colorNodes.put(nodes[i].name(), Color.LIGHT_GRAY);
		}
	}
	
	public static BasicVisualizationServer<String, String> getVisualaization(Graph g ,HashMap<String, Paint> colorNodes){
		BasicVisualizationServer<String, String> vv = getVisualLayout(g);
		vv.getRenderContext().setVertexFillPaintTransformer(setColor(colorNodes));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		//addCapacity(capacity,vv);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        return vv;
	}
	
	public static BasicVisualizationServer<String, String> getVisualLayout(Graph g) {
		Layout<String, String> layout = new CircleLayout<String, String>(g);
		layout.setSize(new Dimension(400, 400));
		BasicVisualizationServer<String, String> visualLayout = new BasicVisualizationServer<String, String>(layout);
		return visualLayout;
	}

	public static Transformer addCapacity(int edge, double capacity) {
		Transformer<String, String> edgeLabel = new Transformer<String, String>() {
			public String transform(String label) {
				if (label.equals(Integer.toString(edge)))
					return Double.toString(capacity);
				else
					return "";
			}

		};
		return edgeLabel;
	}

	public static Transformer setColor(HashMap<String, Paint> colorNodes) {
		Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
			public Paint transform(String i) {
				return colorNodes.get(i);
			}
		};
		return vertexPaint;
	}

	public static Graph<String, String> getGraph(int numOfNodes, Bag<FlowEdge>[] adj) {
		g = new DirectedSparseGraph<String, String>();

		for (int i = 0; i < numOfNodes; i++) {
			if (i == numOfNodes - 1)
				g.addVertex((String) nodes[nodes.length - 1].name());
			else
				g.addVertex((String) nodes[i].name());
		}

		int E = 0;
		for (int i = 0; i < numOfNodes; i++) {
			for (FlowEdge e : adj[i]) {
				if (e.from() != e.to() && e.from() != numOfNodes - 1)
					if (e.to() == numOfNodes - 1)
						g.addEdge(String.valueOf(E), nodes[e.from()].name(), nodes[nodes.length - 1].toString());
					else
						g.addEdge(String.valueOf(E), nodes[e.from()].name(), nodes[e.to()].name());

				E++;
			}
		}

		return g;
	}
	
	public static void setAugmentingNode(ArrayList<Integer> lstNodes) {
		for (int i = 0; i < lstNodes.size(); i++) {
			colorNodes.put(nodes[lstNodes.get(i)].name(), Color.GREEN);
			colorNodes.put("L", Color.GREEN);
		}
	}
}