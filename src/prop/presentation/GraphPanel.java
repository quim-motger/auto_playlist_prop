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
import java.util.*;

public class GraphPanel extends JPanel{
        //the visual component and renderer for the graph
        private VisualizationViewer<String, JungEdge> vv;

        private AggregateLayout<String,JungEdge> clusteringLayout;
        private Class subLayoutType = CircleLayout.class;
        private Dimension subLayoutSize;

        ScalingControl scaler2 = new CrossoverScalingControl(); //not necessary at this moment

        public GraphPanel(final UndirectedSparseGraph<String,JungEdge> originalGraph, ArrayList<UndirectedSparseGraph<String,JungEdge>> communities) {
            clusteringLayout = new AggregateLayout<String,JungEdge>(new KKLayout<String, JungEdge>(originalGraph));
            // circle layout i fr no es modifiquen al fer uncluster
            subLayoutSize = new Dimension(100,100);
            Dimension visualizationModelSize = new Dimension(550,420);
            Dimension preferredSize = getSize();

            final VisualizationModel<String,JungEdge> visualizationModel =
                    new DefaultVisualizationModel<String,JungEdge>(clusteringLayout, visualizationModelSize);
            vv =  new VisualizationViewer<String,JungEdge>(visualizationModel, preferredSize);


          /*  //possible layouts: ISOMLayout, KKLayout, FRLayout
            Layout<String, JungEdge> layout = new CircleLayout<>(graph);
            // layout size should scale with number of vertices
            layout.setSize(new Dimension(1000,1000));
            */
            /*for (int i = 0; i < numberOfVertices; i++) {
                layout.lock("V" + i, true);
            }*/
           /* for(String v : graph.getVertices()) {
                transparency.put(v, new JungEdge(0.9));
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

            vv.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<JungEdge, Paint>() {
                public Paint transform(JungEdge e) {
                    if (e.getWeight() == 10.5) return Color.RED;
                    return Color.blue;
                }
            });

            vv.getRenderContext().setEdgeStrokeTransformer(new Transformer<JungEdge, Stroke>() {
                protected final Stroke THIN = new BasicStroke(1);
                protected final Stroke THICK = new BasicStroke(4);

                public Stroke transform(JungEdge e) {
                    //if (e.getWeight() == 10.5)
                        return THIN;
                    //else
                    //    return THICK;
                }
            });

            vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
            vv.getRenderContext().setVertexShapeTransformer(vertexSize);


            // add my listeners for ToolTips
            vv.setVertexToolTipTransformer(new ToStringLabeller<String>());
            vv.setEdgeToolTipTransformer(new Transformer<JungEdge, String>() {
                public String transform(JungEdge edge) {
                    return "E" + originalGraph.getEndpoints(edge).toString();
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
            final AbstractModalGraphMouse graphMouse = new DefaultModalGraphMouse<String,JungEdge>();
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
                    cluster(originalGraph,true);
                }
            });

            JButton uncluster = new JButton("uncluster");
            uncluster.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cluster(originalGraph,false);
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

        private Layout getLayoutFor(Class layoutClass, Graph graph) throws Exception {
            Object[] args = new Object[]{graph};
            Constructor constructor = layoutClass.getConstructor(new Class[] {Graph.class});
            return  (Layout)constructor.newInstance(args);
        }

        // aqui, per cada vertex de comunitat en comptes de picked
        private void cluster(UndirectedSparseGraph<String,JungEdge> graph, boolean state) {
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

                //UndirectedSparseGraph<String, JungEdge> subGraph = new UndirectedSparseGraph<>();
                Graph<String, JungEdge> subGraph;
                    try {
                        subGraph = graph.getClass().newInstance();
                        for(String vertex : picked) {
                            subGraph.addVertex(vertex);
                            Collection<JungEdge> incidentEdges = graph.getIncidentEdges(vertex);
                            for(JungEdge edge : incidentEdges) {
                                Pair<String> endpoints = graph.getEndpoints(edge);
                                if(picked.containsAll(endpoints)) {
                                    // put this edge into the subgraph
                                    subGraph.addEdge(edge, endpoints.getFirst(), endpoints.getSecond());
                                }
                            }
                        }

                        Layout<String,JungEdge> subLayout = getLayoutFor(subLayoutType, subGraph);
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
