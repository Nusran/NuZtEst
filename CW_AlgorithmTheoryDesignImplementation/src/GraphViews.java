
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

	private static Color GRAY_COLOR = Color.LIGHT_GRAY;
	private static Color GREEN_COLOR = Color.GREEN;
	private static Nodes[] nodes = Nodes.values();
	private static Graph<String, String> g;
	private static int count = 0;

	public GraphViews(int numOfNodes, Bag<FlowEdge>[] adj, ArrayList<ArrayList<Integer>> AugemntPaths) {

		BasicVisualizationServer<String, String> vv = viewGraph(numOfNodes, adj);
		
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
					Graph<String, String> g = getAugmentingGraph(AugemntPaths.get(count));
					// BasicVisualizationServer<String, String> vv =
					// getVisualaization(g,capacity,Color.RED);
					JOptionPane.showMessageDialog(frame, vv);
					count++;
				} else {
					JOptionPane.showMessageDialog(frame, "!No Augmenting paths to display.");
				}
			}
		});
		frame.pack();
		frame.setVisible(true);
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

	public static Transformer setColor(ArrayList<Paint> colors, Color color) {
		Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
			public Paint transform(String i) {
				return colors.get(Integer.parseInt(i));
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

	public static BasicVisualizationServer<String, String> viewGraph(int numOfNodes, Bag<FlowEdge>[] adj){
		
		BasicVisualizationServer<String, String> vv = getVisualLayout(getGraph(numOfNodes, adj));
		for (int i = 0; i < nodes.length; i++) {
			System.out.println("VVVV: "+g.getVertices().contains(nodes[i].name()));
			//if(g.getVertices().contains(nodes[i].name()))
			  //vv.getRenderContext().setVertexFillPaintTransformer(setColor(nodes[i].name(), Color.LIGHT_GRAY));
		}
		
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		return vv;
	}
	
	public static Graph<String, String> getAugmentingGraph(ArrayList<Integer> lstNodes) {
		g = new DirectedOrderedSparseMultigraph<String, String>();
		int numOfNodes = lstNodes.size();

		for (int i = 0; i < numOfNodes; i++) {
			if (i == 0)
				g.addVertex((String) nodes[nodes.length - 1].name());
			else
				g.addVertex((String) nodes[lstNodes.get(i)].name());
		}

		return g;
	}
}