package prop.domain;

import java.util.ArrayList;
import java.util.Set;

/**
 * Clique Percolation method class based on Bron Kerbosch algorithm (Tomita version)
 * @author joaquim.motger
 */
public class CliquePercolation extends Algorithm {

    ArrayList<ArrayList<Vertex>> clusters;
    Graph graph;

    @Override
    public Result execute(Graph graph) {
        Result ret = new Result();
        this.graph = graph;
        clusters = new ArrayList<>();
        ArrayList<Vertex> R = new ArrayList<>();
        ArrayList<Vertex> P = new ArrayList<>();
        ArrayList<Vertex> X = new ArrayList<>();
        //P = getVertices();
        BronKerboschTomita(R,P,X);
        return ret;
    }

    private void BronKerboschTomita(ArrayList<Vertex> R, ArrayList<Vertex> P, ArrayList<Vertex> X) {
        if (P.size() == 0 && X.size() == 0) {
            clusters.add(R);
        }
        else {
            Vertex u = pivot(union(P,X));
            ArrayList<Vertex> Pn = difference(P,neighbours(graph,u));
            for (Vertex v : Pn) {
                ArrayList<Vertex> un = new ArrayList<>();
                ArrayList<Vertex> newR = R;
                un.add(v);
                ArrayList<Vertex> n = neighbours(graph, v);
                BronKerboschTomita(newR, intersection(P,n), intersection(X,n));
                P = difference(P, un);
                X = union(P, un);
            }
        }
    }

    private Vertex pivot(ArrayList<Vertex> A) {
        Vertex v = new Vertex();
        return v;
    }

    private ArrayList<Vertex> union(ArrayList<Vertex> A, ArrayList<Vertex> B) {
        ArrayList<Vertex> u = new ArrayList<>();
        return u;
    }

    private ArrayList<Vertex> intersection(ArrayList<Vertex> A, ArrayList<Vertex> B) {
        ArrayList<Vertex> i = new ArrayList<>();
        return i;
    }

    private ArrayList<Vertex> difference(ArrayList<Vertex> A, ArrayList<Vertex> B) {
        ArrayList<Vertex> d = new ArrayList<>();
        return d;
    }

    private ArrayList<Vertex> neighbours(Graph graph, Vertex v) {
        ArrayList<Vertex> n = new ArrayList<>();
        return n;
    }

}
