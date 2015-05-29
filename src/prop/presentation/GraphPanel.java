package prop.presentation;

import edu.uci.ics.jung.algorithms.layout.KKLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.graph.DirectedSparseGraph;
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

public class GraphPanel extends JPanel{

         /**
         * the graph
         */
        DirectedSparseGraph<String, Number> graph;

        /**
         * the visual component and renderer for the graph
         */
        VisualizationViewer<String, Number> vv;

        /**
         * create an instance of a simple graph with controls to
         * demo the zoom features.
         *
         */
        public GraphPanel() {

            // create a simple graph for the demo
            graph = new DirectedSparseGraph<String, Number>();
            String[] v = createVertices(100);
            createEdges(v);


            Layout<String, Number> layout = new KKLayout(graph);
            layout.setSize(new Dimension(800,600));
            vv =  new VisualizationViewer<String,Number>(layout);

            vv.setPreferredSize(new Dimension(800,600));
            vv.addPostRenderPaintable(new VisualizationViewer.Paintable(){
                int x;
                int y;
                Font font;
                FontMetrics metrics;
                int swidth;
                int sheight;


                public void paint(Graphics g) {
                    Dimension d = vv.getSize();
                    if(font == null) {
                        font = new Font(g.getFont().getName(), Font.BOLD, 30);
                        metrics = g.getFontMetrics(font);

                        sheight = metrics.getMaxAscent()+metrics.getMaxDescent();
                        x = (d.width-swidth)/2;
                        y = (int)(d.height-sheight*1.5);
                    }
                    g.setFont(font);
                    Color oldColor = g.getColor();
                    g.setColor(Color.white);

                    g.setColor(oldColor);
                }
                public boolean useTransform() {
                    return false;
                }
            });


            vv.getRenderer().setVertexRenderer(
                    new GradientVertexRenderer<String,Number>(
                            Color.white, Color.red,
                            Color.white, Color.blue,
                            vv.getPickedVertexState(),
                            false));
            vv.getRenderContext().setEdgeDrawPaintTransformer(new ConstantTransformer(Color.lightGray));
            vv.getRenderContext().setArrowFillPaintTransformer(new ConstantTransformer(Color.lightGray));
            vv.getRenderContext().setArrowDrawPaintTransformer(new ConstantTransformer(Color.lightGray));

            // add my listeners for ToolTips
            vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
            vv.setEdgeToolTipTransformer(new Transformer<Number,String>() {
                public String transform(Number edge) {
                    return "E"+graph.getEndpoints(edge).toString();
                }});

            vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<String>());
            vv.getRenderer().getVertexLabelRenderer().setPositioner(new InsidePositioner());
            vv.getRenderer().getVertexLabelRenderer().setPosition(Renderer.VertexLabel.Position.AUTO);
            vv.setForeground(Color.lightGray);

            // create a frome to hold the graph
            //final JFrame frame = new JFrame();
            this.setLayout(new BorderLayout(5,5));
            final GraphZoomScrollPane panel = new GraphZoomScrollPane(vv);
            add(panel);
            final AbstractModalGraphMouse graphMouse = new DefaultModalGraphMouse<String,Number>();
            vv.setGraphMouse(graphMouse);

            vv.addKeyListener(graphMouse.getModeKeyListener());
            vv.setToolTipText("<html><center>Type 'p' for Pick mode<p>Type 't' for Transform mode");

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

        /**
         * create some vertices
         * @param count how many to create
         * @return the Vertices in an array
         */
        private String[] createVertices(int count) {
            String[] v = new String[count];
            for (int i = 0; i < count; i++) {
                v[i] = "V"+i;
                graph.addVertex(v[i]);
            }
            return v;
        }

        /**
         * create edges for this demo graph
         * @param v an array of Vertices to connect
         */
        void createEdges(String[] v) {
            graph.addEdge(new Double(Math.random()), v[0], v[1], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[0], v[3], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[0], v[4], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[4], v[5], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[3], v[5], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[1], v[2], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[1], v[4], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[8], v[2], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[3], v[8], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[6], v[7], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[7], v[5], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[0], v[9], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[9], v[8], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[7], v[6], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[6], v[5], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[4], v[2], EdgeType.DIRECTED);
            graph.addEdge(new Double(Math.random()), v[5], v[4], EdgeType.DIRECTED);
        }

}
