
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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

enum Nodes {
	A, B, C, D, E, F, G, H, I, J, K, L
}

public class GraphView {

	private static NodesNames[] nodes = NodesNames.values();
	private static Graph<String, String> g;
	private static int count = 0;
	
	public GraphView(int numOfNodes,  ArrayList<HashMap<Integer, Integer>> lstEdges, ArrayList<String> capacity,ArrayList<ArrayList<Integer>> AugemntPaths) {

		Graph<String, String> g = getGraph(numOfNodes, lstEdges);
		BasicVisualizationServer<String, String> vv = getVisualaization(g,capacity,Color.LIGHT_GRAY);

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
				if(count<AugemntPaths.size()) {
					Graph<String, String> g = getAugmentingGraph(AugemntPaths.get(count));
					BasicVisualizationServer<String, String> vv = getVisualaization(g,capacity,Color.RED);
					JOptionPane.showMessageDialog(frame, vv);
					count++;
				}else {
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
		BasicVisualizationServer<String, String> vv = new BasicVisualizationServer<String, String>(layout);
		return vv;
	}
    
//	public static void addCapacity(ArrayList<String> capacity , BasicVisualizationServer<String, String> vv) {
//		for (int i = 0; i < capacity.size(); i++) {
//			vv.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>() {
//				public String transform(String s) {
//					return capacity.get(Integer.parseInt(s));
//				}
//			});
//		}
//	}
	
	public static Transformer colorNodes(Color color) {
		Transformer<String, Paint> vertexPaint = new Transformer<String, Paint>() {
			public Paint transform(String i) {
				return color;
			}
		};
		return vertexPaint;
	}
	
	public static BasicVisualizationServer<String, String> getVisualaization(Graph g , ArrayList<String> capacity,Color color){
		BasicVisualizationServer<String, String> vv = getVisualLayout(g);
		vv.getRenderContext().setVertexFillPaintTransformer(colorNodes(color));
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		//addCapacity(capacity,vv);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
        return vv;
	}
	
	public Graph<String, String> getGraph(int numOfNodes, ArrayList<HashMap<Integer, Integer>> lstEdges) {
		g = new DirectedSparseGraph<String, String>();

		for (int i = 0; i < numOfNodes; i++) {
			if (i == numOfNodes - 1)
				g.addVertex((String) nodes[nodes.length - 1].name());
			else
				g.addVertex((String) nodes[i].name());
		}
		for (int j = 0; j < lstEdges.size(); j++) {
			for (int i = 0; i < numOfNodes; i++) {
				if (lstEdges.get(j).get(i) != null && i != lstEdges.get(j).get(i))
					if (lstEdges.get(j).get(i) == numOfNodes - 1)
						g.addEdge(String.valueOf(j), nodes[i].name(), nodes[nodes.length - 1].toString());
					else if (i == numOfNodes - 1)
						g.addEdge(String.valueOf(j), nodes[nodes.length - 1].name(),nodes[lstEdges.get(j).get(i)].name());
					else
						g.addEdge(String.valueOf(j), nodes[i].name(), nodes[lstEdges.get(j).get(i)].name());
			}
		}
		
		return g;
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
		
			for (int i = 0; i < numOfNodes; i++) {
					if (i == 0)
						g.addEdge(String.valueOf(i), nodes[i].name(), nodes[nodes.length - 1].toString());
				//	else if (i == numOfNodes - 1)
						//g.addEdge(String.valueOf(i), nodes[nodes.length - 1].name(),nodes[]);
					//else
					//	g.addEdge(String.valueOf(i), nodes[i].name(), nodes[lstEdges.get(j).get(i)].name());
			}

		
		return g;
	}
}