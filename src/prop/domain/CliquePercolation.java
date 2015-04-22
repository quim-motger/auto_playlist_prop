package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clique Percolation method class based on Bron Kerbosch algorithm (Tomita version)
 * @author joaquim.motger
 */
public class CliquePercolation{

    int n;
    int m;
    int i;
    ArrayList<Integer>[] cliques;
    ArrayList<Integer>[] graph;
    
    public void execute() {
        Result ret = new Result();
        i = 0;
        ArrayList<Integer> R = new ArrayList<>();
        ArrayList<Integer> P = new ArrayList<>();
        ArrayList<Integer> X = new ArrayList<>();
        int k = 0;
        while (k < n) {
            P.add(k);
            ++k;
        }
        BronKerboschTomita(R,P,X);
    }

    private void BronKerboschTomita(ArrayList<Integer> R, ArrayList<Integer> P, ArrayList<Integer> X) {
        if (P.size() == 0 && X.size() == 0) {
            cliques[i] = R;
            ++i;
        }
        else {
            Integer u = pivot(union(P,X));
            ArrayList<Integer> Pn = difference(P,neighbours(u));
            for (Integer v : Pn) {
                ArrayList<Integer> un = new ArrayList<>();
                un.add(v);
                ArrayList<Integer> n = neighbours(v);
                BronKerboschTomita(union(R,un), intersection(P,n), intersection(X,n));
                P = difference(P, un);
                X = union(P, un);
            }
        }
    }

    private int pivot(ArrayList<Integer> A) {
        int v = A.get(0);
        for (int a : A) {
            if (graph[a].size() > graph[v].size()) {
                v = a;
            }
        }
        return v;
    }

    private ArrayList<Integer> union(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> u = new ArrayList<>();
        return u;
    }

    private ArrayList<Integer> intersection(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> i = new ArrayList<>();
        return i;
    }

    private ArrayList<Integer> difference(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> d = new ArrayList<>();
        return d;
    }

    private ArrayList<Integer> neighbours(int v) {
        ArrayList<Integer> n = new ArrayList<>();
        for (int u : graph[v]) {
            n.add(u);
        }
        return n;
    }

    public void readGraph() {
        Scanner in = new Scanner(System.in);
        n = in.nextInt();
        m = in.nextInt();
        graph = (ArrayList<Integer>[])new ArrayList[n];
        for (int i = 0; i < n; ++i)
            graph[i] = new ArrayList<>();
        int a, b;
        for (int i = 0; i < m; ++i) {
            a = in.nextInt();
            b = in.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }
    }

    public void writeGraph() {
        System.out.println("Adjacency list:");
        for (ArrayList<Integer> l : graph) {
            for (int v : l) {
                System.out.print(v + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
