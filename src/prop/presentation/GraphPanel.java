package prop.presentation;

import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.visualization.GraphZoomScrollPane;
import edu.uci.ics.jung.visualization.Layer;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer.InsidePositioner;
import edu.uci.ics.jung.visualization.renderers.GradientVertexRenderer;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;
import org.apache.commons.collections15.functors.ConstantTransformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

public class GraphPanel extends JPanel{
        private UndirectedSparseGraph<String, Double> graph;
        //the visual component and renderer for the graph
        private VisualizationViewer<String, Double> vv;
        private int numberOfVertices = 20;
        public GraphPanel() {
            graph = new UndirectedSparseGraph<String, Double>();
            createVertices(numberOfVertices);
            createEdges();

            Layout<String, Double> layout = new KKLayout<>(graph);
            // layout size should scale with number of vertices
            layout.setSize(new Dimension(3000,3000));

            /*for (int i = 0; i < numberOfVertices; i++) {
                layout.lock("V" + i, true);
            }*/
           /* for(String v : graph.getVertices()) {
                transparency.put(v, new Double(0.9));
            }
*/
            vv =  new VisualizationViewer<String, Double>(layout);
            vv.setBackground(Color.white);
            vv.setPreferredSize(new Dimension(800, 600));
            Transformer<String, Paint> vertexColor = new Transformer<String, Paint>() {
                public Paint transform(String i) {
                    if(i.equals("V0")) return Color.GREEN;
                    return Color.MAGENTA;
                }
            };
            Transformer<String, Shape> vertexSize = new Transformer<String, Shape>(){
                public Shape transform(String i){
                    Ellipse2D circle = new Ellipse2D.Double(-5,-5, 10, 10);
                    // in this case, the vertex is twice as large
                    //if(i == 2) return AffineTransform.getScaleInstance(2, 2).createTransformedShape(circle);
                    return circle;
                }
            };
            vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
            vv.getRenderContext().setVertexShapeTransformer(vertexSize);
            vv.getRenderContext().setEdgeDrawPaintTransformer(new ConstantTransformer(Color.BLACK));
            vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.BLACK));
            vv.getRenderContext().setArrowDrawPaintTransformer(new ConstantTransformer(Color.BLACK));

            // add my listeners for ToolTips
            vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
            vv.setEdgeToolTipTransformer(new Transformer<Double, String>() {
                public String transform(Double edge) {
                    return "E" + graph.getEndpoints(edge).toString();
                }
            });

            /** LABELS **/
            vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
            vv.getRenderer().getVertexLabelRenderer().setPositioner(new InsidePositioner());
            vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);
            vv.setForeground(Color.black); //label color


            this.setLayout(new BorderLayout(5, 5));
            final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
            add(panel);

            /** MOUSE AND BUTTONS **/
            final AbstractModalGraphMouse graphMouse = new DefaultModalGraphMouse<String,Double>();
            vv.setGraphMouse(graphMouse);
            vv.addKeyListener(graphMouse.getModeKeyListener());

            final ScalingControl scaler = new CrossoverScalingControl();

            JButton plus = new JButton("+");
            plus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    scaler.scale(vv, 1.1f, vv.getCenter());
                }
            });
            JButton minus = new JButton("-");
            minus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    scaler.scale(vv, 1 / 1.1f, vv.getCenter());
                }
            });

            JButton reset = new JButton("reset");
            reset.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
                    vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
                }
            });

            JPanel controls = new JPanel();
            controls.add(plus);
            controls.add(minus);
            controls.add(reset);
            add(controls, BorderLayout.SOUTH);
        }

        private void createVertices(int count) {
            for (int i = 0; i < count; i++) {
                graph.addVertex("V"+i);
            }
            graph.addVertex("Highway to hell (ACDC)");
        }

        private void createEdges() {
            graph.addEdge(10.5, "V0", "V1");
            graph.addEdge(14.5, "V1", "V3");
            graph.addEdge(313.5, "V6", "V3");
            graph.addEdge(11.5, "V0", "V12");
            graph.addEdge(9.5, "V15", "V9");
            graph.addEdge(13.5, "V0", "V11");
            graph.addEdge(1.5, "V5", "V1");
            graph.addEdge(3.5, "V10", "V19");
            graph.addEdge(121.5, "V6", "V8");
            graph.addEdge(10.25, "V2", "V7");
        }

}
