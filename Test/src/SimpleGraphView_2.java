
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import org.apache.commons.collections15.Transformer;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedOrderedSparseMultigraph;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.visualization.BasicVisualizationServer;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;


public class SimpleGraphView_2 {
	
	
	static Graph<Integer, String> g;

	/** Creates a new instance of SimpleGraphView */
	public void SimpleGraphView(int vet, ArrayList<HashMap<Integer, Integer>> edg ,ArrayList<String> cap) {

		g = new DirectedOrderedSparseMultigraph<Integer, String>();

		for (int i = 0; i < vet; i++) g.addVertex((Integer) i);
		for (int j = 0; j < edg.size(); j++) {
			for (int i = 0; i < vet; i++) {
				if (edg.get(j).get(i) != null && i != edg.get(j).get(i)) 
					g.addEdge(String.valueOf(j), i, edg.get(j).get(i));		
			}

		}

		printGraph(cap);
	}

	/**
	 * @param args the command line arguments
	 */
	public static void printGraph(ArrayList<String> cap) {

		Layout<Integer, String> layout = new CircleLayout<Integer, String>(g);
		layout.setSize(new Dimension(300, 300));
		BasicVisualizationServer<Integer, String> vv = new BasicVisualizationServer<Integer, String>(layout);
		vv.setPreferredSize(new Dimension(350, 350));
		// Setup up a new vertex to paint transformer...
		Transformer<Integer, Paint> vertexPaint = new Transformer<Integer, Paint>() {
			public Paint transform(Integer i) {
				return Color.GREEN;
			}
		};
		// Set up a new stroke Transformer for the edges
		float dash[] = { 10.0f };
		final Stroke edgeStroke = new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f, dash,
				0.0f);

		Transformer<String, Stroke> edgeStrokeTransformer = new Transformer<String, Stroke>() {
            public Stroke transform(String s) {
                return edgeStroke;
            }
        };
		
		vv.getRenderContext().setVertexFillPaintTransformer(vertexPaint);
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
		
		for(int i = 0 ; i<cap.size();i++) {
			vv.getRenderContext().setEdgeLabelTransformer(new Transformer<String, String>() {
	            public String transform(String s) {
	                return cap.get(Integer.parseInt(s));
	            }
			});
		}
		
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);

		JFrame frame = new JFrame("Ford Fulkason");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

}