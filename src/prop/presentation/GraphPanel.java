package prop.presentation;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.EdgeType;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.*;
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
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.security.cert.CollectionCertStoreParameters;
import java.util.*;

public class GraphPanel extends JPanel{
        private UndirectedSparseGraph<String, Double> graph;
        //the visual component and renderer for the graph
        private VisualizationViewer<String, Double> vv;
        private int numberOfVertices = 20;
        private AggregateLayout<String,Double> clusteringLayout;
        private Class subLayoutType = CircleLayout.class;
        private Dimension subLayoutSize;
    ScalingControl scaler2 = new CrossoverScalingControl();
        public GraphPanel() {
            graph = new UndirectedSparseGraph<String, Double>();
            createVertices(numberOfVertices);
            createEdges();
            clusteringLayout = new AggregateLayout<String,Double>(new KKLayout<String, Double>(graph));
            // circle layout i fr no es mofiquen al fer uncluster
            subLayoutSize = new Dimension(100,100);
            Dimension visualizationModelSize = new Dimension(550,420);
            Dimension preferredSize = getSize();

            final VisualizationModel<String,Double> visualizationModel =
                    new DefaultVisualizationModel<String,Double>(clusteringLayout, visualizationModelSize);
            vv =  new VisualizationViewer<String,Double>(visualizationModel, preferredSize);


          /*  //possible layouts: ISOMLayout, KKLayout, FRLayout
            Layout<String, Double> layout = new CircleLayout<>(graph);
            // layout size should scale with number of vertices
            layout.setSize(new Dimension(1000,1000));
            */
            /*for (int i = 0; i < numberOfVertices; i++) {
                layout.lock("V" + i, true);
            }*/
           /* for(String v : graph.getVertices()) {
                transparency.put(v, new Double(0.9));
            }
*/
            vv.setBackground(Color.white);

            Transformer<String, Paint> vertexColor = new Transformer<String, Paint>() {
                public Paint transform(String i) {
                    if(i.equals("V0")) return Color.GREEN;
                    return Color.MAGENTA;
                }
            };
            Transformer<String, Shape> vertexSize = new Transformer<String, Shape>(){
                public Shape transform(String i){
                    Ellipse2D circle = new Ellipse2D.Double(-10,-10, 20, 20);
                    // in this case, the vertex is twice as large
                    //if(i == 2) return AffineTransform.getScaleInstance(2, 2).createTransformedShape(circle);
                    return circle;
                }
            };

            vv.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<Double, Paint>() {
                public Paint transform(Double e) {
                    if (e == 10.5) return Color.RED;
                    return Color.blue;
                }
            });

            vv.getRenderContext().setEdgeStrokeTransformer(new Transformer<Double, Stroke>() {
                protected final Stroke THIN = new BasicStroke(1);
                protected final Stroke THICK = new BasicStroke(4);

                public Stroke transform(Double e) {
                    if (e == 10.5)
                        return THIN;
                    else
                        return THICK;
                }
            });

            vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
            vv.getRenderContext().setVertexShapeTransformer(vertexSize);


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

            /** MOUSE **/
            final AbstractModalGraphMouse graphMouse = new DefaultModalGraphMouse<String,Double>();
            vv.setGraphMouse(graphMouse);
            vv.addKeyListener(graphMouse.getModeKeyListener());

            /** BUTTONS **/

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

            JButton cluster = new JButton("cluster");
            cluster.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cluster(true);
                }
            });

            JButton uncluster = new JButton("uncluster");
            uncluster.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cluster(false);
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
            controls.add(uncluster);
            controls.add(cluster);
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



        private Layout getLayoutFor(Class layoutClass, Graph graph) throws Exception {
            Object[] args = new Object[]{graph};
            Constructor constructor = layoutClass.getConstructor(new Class[] {Graph.class});
            return  (Layout)constructor.newInstance(args);
        }

        // aqui, per cada vertex de comunitat en comptes de picked
        private void cluster(boolean state) {
            if(state) {
                // put the picked vertices into a new sublayout
                Collection<String> picked = new HashSet<String>();
                String[] cv = new String[] { "V0", "V1", "V2", "V3", "V4", "V5", "V6" }; // example vertices
                ArrayList<String> acv = new ArrayList<String>(Arrays.asList(cv));
                picked.addAll(acv);

                Point2D center = new Point2D.Double();
                double x = 0;
                double y = 0;
                for(String vertex : picked) {
                    Point2D p = clusteringLayout.transform(vertex); // gets location of the vertex
                    x += p.getX();
                    y += p.getY();
                }
                x /= picked.size();
                y /= picked.size();
                center.setLocation(x,y);

                //UndirectedSparseGraph<String, Double> subGraph = new UndirectedSparseGraph<>();
                Graph<String, Double> subGraph;
                    try {
                        subGraph = graph.getClass().newInstance();
                        for(String vertex : picked) {
                            subGraph.addVertex(vertex);
                            Collection<Double> incidentEdges = graph.getIncidentEdges(vertex);
                            for(Double edge : incidentEdges) {
                                Pair<String> endpoints = graph.getEndpoints(edge);
                                if(picked.containsAll(endpoints)) {
                                    // put this edge into the subgraph
                                    subGraph.addEdge(edge, endpoints.getFirst(), endpoints.getSecond());
                                }
                            }
                        }

                        Layout<String,Double> subLayout = getLayoutFor(subLayoutType, subGraph);
                        subLayout.setInitializer(vv.getGraphLayout());
                        subLayout.setSize(subLayoutSize);
                        clusteringLayout.put(subLayout,center);
                        vv.setGraphLayout(clusteringLayout);

                    }
                    catch (NullPointerException ne) {
                        System.out.println("nullexc");
                        ne.printStackTrace();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            else {
                // remove all sublayouts
                this.clusteringLayout.removeAll();
                vv.setGraphLayout(clusteringLayout);
            }
        }



    public void zoomIn() {
        setZoom(1);
    }

    public void zoomOut() {
        setZoom(-1);
    }

    private void setZoom(int amount) {
        scaler2.scale(vv, amount > 0 ? 1.1f : 1 / 1.1f, vv.getCenter());
    }

}
