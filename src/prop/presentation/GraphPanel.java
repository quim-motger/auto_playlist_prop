package prop.presentation;

import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import edu.uci.ics.jung.graph.util.Pair;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.control.AbstractModalGraphMouse;
import edu.uci.ics.jung.visualization.control.CrossoverScalingControl;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ScalingControl;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.BasicVertexLabelRenderer.InsidePositioner;
import edu.uci.ics.jung.visualization.renderers.Renderer;
import org.apache.commons.collections15.Transformer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.regex.Pattern;

/*
Then create a random generator:

        Random rand = new Random();

        Color randomColor = new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat());
// Will produce a random colour with more red in it (usually "pink-ish")
        float r = rand.nextFloat();
        float g = rand.nextFloat(0.5);
        float b = rand.nextFloat(0.5);

// Will produce only bright / light colours:
        float r = rand.nextFloat(0.5) + 0.5;
        float g = rand.nextFloat(0.5) + 0.5;
        float b = rand.nextFloat(0.5) + 0.5;

        There are various other colour functions that can be used with the Color class, such as making the colour brighter:
        randomColor.brighter();
*/


public class GraphPanel extends JPanel{

        protected AlgorithmPController algorithmPController;
        protected UndirectedSparseGraph<String,JungEdge> originalGraph;
        private ArrayList<UndirectedSparseGraph<String,JungEdge>> communities;

        //the visual component and renderer for the graph
        protected VisualizationViewer<String, JungEdge> vv;

        private AggregateLayout<String,JungEdge> clusteringLayout;
        private Class subLayoutType = CircleLayout.class;
        private Dimension subLayoutSize;
        private ArrayList<Color> colors;

        protected JPanel controls;

        public GraphPanel(AlgorithmPController apc) {
            algorithmPController = apc;
            originalGraph = algorithmPController.getOriginalGraph();
            communities = algorithmPController.getCommunities();

            //initialize colors
            colors = new ArrayList<>();
            Random rand = new Random();
            for (int x = 0; x < communities.size(); ++x) {
                colors.add(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
            }

            final UndirectedSparseGraph<String,JungEdge> selectedCommunity = communities.get(communities.size()-1);

            clusteringLayout = new AggregateLayout<String,JungEdge>(new KKLayout<>(originalGraph));
            subLayoutSize = new Dimension(50,50);
            Dimension visualizationModelSize = new Dimension(550,420);
            Dimension preferredSize = getSize();

            final VisualizationModel<String,JungEdge> visualizationModel =
                    new DefaultVisualizationModel<String,JungEdge>(clusteringLayout, visualizationModelSize);
            vv =  new VisualizationViewer<String,JungEdge>(visualizationModel, preferredSize);

            vv.setBackground(Color.white);

            // Vertices' color
            final Transformer<String, Paint> vertexColor = new Transformer<String, Paint>() {
                public Paint transform(String i) {
                   for (int j = 0; j < communities.size(); ++j)
                       if (communities.get(j).containsVertex(i)) return colors.get(j);
                   return Color.white;
                }
            };

            // Vertices' size
            Transformer<String, Shape> vertexSize = new Transformer<String, Shape>(){
                public Shape transform(String i){
                    Ellipse2D circle = new Ellipse2D.Double(-10,-10, 20, 20);
                    return circle;
                }
            };

            // Edges' color
            vv.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<JungEdge, Paint>() {
                public Paint transform(JungEdge e) {
                    Pair<String> p = originalGraph.getEndpoints(e);
                    for (int j = 0; j < communities.size(); ++j) {
                        if (communities.get(j).containsVertex(p.getFirst()) && communities.get(j).containsVertex(p.getSecond())) {
                            e.setColor(colors.get(j));
                            return colors.get(j);
                        }
                    }
                    return Color.black;
                 }
            });

            // Edges' thickness
            vv.getRenderContext().setEdgeStrokeTransformer(new Transformer<JungEdge, Stroke>() {
                protected final Stroke THIN = new BasicStroke(2);
                protected final Stroke THICK = new BasicStroke(4);

                public Stroke transform(JungEdge e) {
                 /*   Pair<String> p = originalGraph.getEndpoints(e);
                    if (selectedCommunity.containsVertex(p.getFirst()) && selectedCommunity.containsVertex(p.getSecond()))
                        return THICK;*/
                    return THIN;
                }
            });

            vv.getRenderContext().setVertexFillPaintTransformer(vertexColor);
            vv.getRenderContext().setVertexShapeTransformer(vertexSize);

            // Edge weight tooltip
            vv.setVertexToolTipTransformer(new Transformer<String, String>() {
                public String transform(String s) {
                    for (int i = 0; i < communities.size(); ++i) {
                            if (communities.get(i).containsVertex(s)) return "Community " + i;
                    }
                    return null;
                }
            });
            vv.setEdgeToolTipTransformer(new Transformer<JungEdge, String>() {
                public String transform(JungEdge edge) {
                    return edge.toString();
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

            JButton plus = new JButton("Zoom in");
            plus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    scaler.scale(vv, 1.1f, vv.getCenter());
                }
            });
            JButton minus = new JButton("Zoom out");
            minus.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    scaler.scale(vv, 1 / 1.1f, vv.getCenter());
                }
            });

            final JButton cluster = new JButton("Cluster");
            final JButton uncluster = new JButton("Undo cluster");
            cluster.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cluster.setEnabled(false);
                    uncluster.setEnabled(true);
                    cluster(communities,true);
                }
            });
            uncluster.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    cluster.setEnabled(true);
                    uncluster.setEnabled(false);
                    cluster(communities, false);
                }
            });


            JButton reset = new JButton("Reset");
            reset.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.LAYOUT).setToIdentity();
                    vv.getRenderContext().getMultiLayerTransformer().getTransformer(Layer.VIEW).setToIdentity();
                }
            });

            controls = new JPanel();
            controls.add(plus);
            controls.add(minus);
            controls.add(reset);
            controls.add(cluster);
            controls.add(uncluster);
            uncluster.setEnabled(false);
            add(controls, BorderLayout.SOUTH);

            // Lock vertices to avoid movement
            lock(originalGraph, clusteringLayout);

        }

        private Layout getLayoutFor(Class layoutClass, Graph graph) throws Exception {
            Object[] args = new Object[]{graph};
            Constructor constructor = layoutClass.getConstructor(new Class[] {Graph.class});
            return  (Layout)constructor.newInstance(args);
        }

        private void cluster(ArrayList<UndirectedSparseGraph<String,JungEdge>> graphs, boolean state) {
            if(state) {
                for (UndirectedSparseGraph graph : graphs) {
                    if (graph.getVertexCount() > 1) {
                        // put the picked vertices into a new sublayout
                        Collection<String> picked = new HashSet<String>();
                        picked.addAll(graph.getVertices());

                        Point2D center = new Point2D.Double();
                        double x = 0;
                        double y = 0;
                        for (String vertex : picked) {
                            Point2D p = clusteringLayout.transform(vertex); // gets location of the vertex
                            x += p.getX();
                            y += p.getY();
                        }
                        x /= picked.size();
                        y /= picked.size();
                        center.setLocation(x, y);

                        Graph<String, JungEdge> subGraph;
                        try {
                            subGraph = graph.getClass().newInstance();
                            for (String vertex : picked) {
                                subGraph.addVertex(vertex);
                                Collection<JungEdge> incidentEdges = graph.getIncidentEdges(vertex);
                                for (JungEdge edge : incidentEdges) {
                                    Pair<String> endpoints = graph.getEndpoints(edge);
                                    if (picked.containsAll(endpoints)) {
                                        // put this edge into the subgraph
                                        subGraph.addEdge(edge, endpoints.getFirst(), endpoints.getSecond());
                                    }
                                }
                            }

                            Layout<String, JungEdge> subLayout = getLayoutFor(subLayoutType, subGraph);
                            subLayout.setInitializer(vv.getGraphLayout());
                            subLayout.setSize(subLayoutSize);
                            clusteringLayout.put(subLayout, center);
                            vv.setGraphLayout(clusteringLayout);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            else {
                // remove all sublayouts
                this.clusteringLayout.removeAll();
                vv.setGraphLayout(clusteringLayout);
            }
        }

    private void lock(UndirectedSparseGraph<String,JungEdge> g, AggregateLayout<String,JungEdge> alayout) {
        for (String s : g.getVertices()) {
            alayout.lock(s,true);
        }
    }

    public static class ExecutionPanel extends GraphPanel {

        private int step;
        private ArrayList<String> log;
        private static final char delimiter = '|';
        private ArrayList<Pair<String>> hiddenEdges;
        private HashMap<Integer,Color> hashColors;
        private HashMap<String, Integer> vColors;

        private void paint() {
            final Transformer<String, Paint> vertexC = new Transformer<String, Paint>() {
                public Paint transform(String i) {
                    return hashColors.get(vColors.get(i));
                }
            };
            vv.getRenderContext().setVertexFillPaintTransformer(vertexC);
        }

        public ExecutionPanel(AlgorithmPController apc) {
            super(apc);
            hiddenEdges = new ArrayList<>();
            hashColors = new HashMap<>();
            hashColors.put(-1000, Color.white);
            vColors = new HashMap<>();
            for (String sv : originalGraph.getVertices()) {
                vColors.put(sv, -1000);
            }

            step = -1;
            log = algorithmPController.getLogArray();

            controls.removeAll();
            final JButton backButton = new JButton("<-");
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    backButtonActionPerformed(e);
                }
            });
            controls.add(backButton);

            JButton nextButton = new JButton("->");
            nextButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    nextButtonActionPerformed(e);
                }
            });
            controls.add(nextButton);

            // Vertices' color
            paint();
            // Edges' color
            vv.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<JungEdge, Paint>() {
                public Paint transform(JungEdge e) {
                    return Color.black;
                }
            });
        }

        private void backButtonActionPerformed(ActionEvent evt) {
            if (step > -1) {
                String op[] = log.get(step).split(Pattern.quote(String.valueOf(delimiter)));
                int code = Integer.parseInt(op[0]);
                switch (code) {
                    case 0:
                        ArrayList<Integer> removedVertexs  = new ArrayList<>();
                        for(int i = 3; i<op.length;++i)
                            removedVertexs.add(Integer.parseInt(op[i]));
                        paintVertexsIntoColor(Integer.parseInt(op[1]), removedVertexs);
                        break;
                    case 2:
                        addEdge(Integer.parseInt(op[1]), Integer.parseInt(op[2]));
                        break;
                }
                --step;
            }
        }

        private void nextButtonActionPerformed(ActionEvent evt) {
            if (step < log.size()-1) {
                ++step;
                String op[] = log.get(step).split(Pattern.quote(String.valueOf(delimiter)));
                int code = Integer.parseInt(op[0]);
                switch (code) {
                    case 0:
                        ArrayList<Integer> addedVertexs  = new ArrayList<>();
                        for(int i = 3; i<op.length;++i) 
                            addedVertexs.add(Integer.parseInt(op[i]));
                        paintVertexsIntoColor(Integer.parseInt(op[2]), addedVertexs);
                        break;
                    case 2:
                        removeEdge(Integer.parseInt(op[1]), Integer.parseInt(op[2]));
                        break;
                }
            }
        }

        private void paintVertexsIntoColor(int color, ArrayList<Integer> vertexsToPaint) {
            if (!hashColors.containsKey(color)) {
                Random rand2 = new Random();
                hashColors.put(color, new Color(rand2.nextFloat(), rand2.nextFloat(), rand2.nextFloat()));
            }
            for (Integer in : vertexsToPaint) {
                vColors.put(algorithmPController.getSongId(in), color);
            }
            paint();

        }

        private void removeEdge(int u, int v) {
            hiddenEdges.add(new Pair<String>(algorithmPController.getSongId(u),algorithmPController.getSongId(v)));
            paintEdges();
        }

        private void addEdge(int u, int v) {
            for (Pair<String> p : hiddenEdges) {
                if ((p.getFirst().equals(algorithmPController.getSongId(u)) && p.getSecond().equals(algorithmPController.getSongId(v)))
                        || (p.getFirst().equals(algorithmPController.getSongId(v)) && p.getSecond().equals(algorithmPController.getSongId(u))))
                    hiddenEdges.remove(p);
            }
            paintEdges();
        }

        private void paintEdges() {
            vv.getRenderContext().setEdgeDrawPaintTransformer(new Transformer<JungEdge, Paint>() {
                public Paint transform(JungEdge e) {
                    Pair<String> p = originalGraph.getEndpoints(e);
                    Color col = e.getColor();
                    for (Pair<String> ps : hiddenEdges) {
                        if ((p.getFirst().equals(ps.getFirst()) && p.getSecond().equals(ps.getSecond()))
                                || (p.getFirst().equals(ps.getSecond()) && p.getSecond().equals(ps.getFirst())))
                            col = new Color(col.getRed(), col.getGreen(), col.getBlue(), 1);
                    }
                    return col;
                }
            });
        }

    }

}
