package prop.domain;

import java.util.ArrayList;
import java.util.Set;

/**
 * Clique Percolation method class based on Bron Kerbosch algorithm
 * @author joaquim.motger
 */
public class CliquePercolation {

    private Graph graph;

    private ArrayList<Set<Vertex>> cliques;

    public CliquePercolation(Graph graph) {
        this.graph = graph;
    }

    public ArrayList<Set<Vertex>> getMaximalCliques() {
        ArrayList<Set<Vertex>> cliques = new ArrayList<>();
        ArrayList<Vertex> potential_cliques = new ArrayList<>();
        ArrayList<Vertex> candidates = new ArrayList<>();
        ArrayList<Vertex> found = new ArrayList<>();
        //candidates.addAll(graph.getVertices());
        cliques = findCliques(potential_cliques, candidates, found);
        return cliques;
    }

    public ArrayList<Set<Vertex>> getBiggestMaximalCliques() {
        ArrayList<Set<Vertex>> cliques = new ArrayList<>();
        return cliques;
    }

    public ArrayList<Set<Vertex>> findCliques(ArrayList<Vertex> potential_cliques, ArrayList<Vertex> candidates, ArrayList<Vertex> found) {
        ArrayList<Set<Vertex>> cliques = new ArrayList<>();
        return cliques;
    }

}
