package prop.presentation;

import edu.uci.ics.jung.graph.UndirectedSparseGraph;
import prop.PropException;
import prop.domain.*;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AlgorithmPController {

    private AlgorithmController algorithmController;
    private RelationController relationController;
    private SongController songController;
    private UserController userController;

    private ArrayList<String> log;
    private static final String delimiter = "\n";


    public AlgorithmPController(SongController sc, UserController uc) {
        algorithmController = new AlgorithmController();
        relationController = new RelationController();
        songController = sc;
        userController = uc;
    }

    /* GETTERS */
    public AlgorithmController getAlgorithmController() {
        return algorithmController;
    }

    public RelationController getRelationController() {
        return relationController;
    }

    public String getLog() {
        StringBuilder sb = new StringBuilder();
        for (String s : log) {
            sb.append(s);
            sb.append("\n");
        }
        return sb.toString();
    }


    public UndirectedSparseGraph<String, JungEdge> getOriginalGraph() {
       String s = algorithmController.getOriginalGraph();
       return createGraph(s);
    }

    /* OTHER METHODS */

    /** Gets the String communities from the algorithmController and converts each one to graph */
    public ArrayList<UndirectedSparseGraph<String, JungEdge>> getCommunities() {
        ArrayList<UndirectedSparseGraph<String, JungEdge>> communities = new ArrayList<>();
        String[] cs = algorithmController.getCommunities();
        for (String s : cs) {
            communities.add(createGraph(s));
        }
        return communities;
    }

    /**
     * Converts a string to a graph
     * @param graph
     * @return
     */
    private UndirectedSparseGraph<String, JungEdge> createGraph(String graph) {
        UndirectedSparseGraph<String, JungEdge> gr = new UndirectedSparseGraph<>();
        String[] vertices = graph.split(Pattern.quote(delimiter));
        System.err.println("vertices length " + vertices.length);
        for (int i = 0; i < vertices.length-1;) {
            gr.addVertex(vertices[i]);
            int degree = Integer.parseInt(vertices[i+1]);
            int j = i+2;
            for (; j < j+degree*2; j+=2) {
                if (j >= vertices.length-1) break;
                gr.addVertex(vertices[j]);
                gr.addEdge(new JungEdge(Double.parseDouble(vertices[j+1])), vertices[i], vertices[j]);
            }
            i = j;
        }
        return gr;
    }

    public void addRelation(String[] simpRel, String compRel, int n) throws PropException {
        relationController.addRelation(simpRel, compRel, n);
    }

    public void initGraph() {
       relationController.initGraph(songController, userController);
    }

    public void execute(String title, int algorithm, int k, ListController lc, RelationController rc) throws PropException {
        log = algorithmController.execute(title, algorithm, k, lc, rc);
    }
}