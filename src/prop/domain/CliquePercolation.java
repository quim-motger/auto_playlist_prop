package prop.domain;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clique Percolation method class based on Bron Kerbosch algorithm (Tomita version)
 * @author joaquim.motger
 */
public class CliquePercolation{

    int n;
    int m;
    ArrayList<ArrayList<Integer>> cliques;
    ArrayList<Integer>[] graph;
    
    public void execute() {
        Result ret = new Result();
        cliques = new ArrayList<>();
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
        System.out.print("R:");
        for (int r : R) System.out.print(" " + r);
        System.out.print("\nP:");
        for (int p : P) System.out.print(" " + p);
        System.out.print("\nX:");
        for (int x : X) System.out.print(" " + x);
        System.out.print("\n");
        if (P.isEmpty() && X.isEmpty()) {
            cliques.add(R);
            System.out.print("A new clique was found\n");
        }
        else {
            System.out.print("Lets work\n");
            int u = pivot(union(P,X));
            ArrayList<Integer> Pn = difference(P, neighbours(u));
            for(int v : Pn) {
                ArrayList<Integer> un = new ArrayList<>();
                un.add(v);
                ArrayList<Integer> n = neighbours(v);
                BronKerboschTomita(un, intersection(P,n), intersection(X,n));
                P = difference(P, un);
                X.add(v);
            }
        }
    }

    public int pivot(ArrayList<Integer> A) {
        int v = A.get(0);
        for (int a : A) {
            if (graph[a].size() > graph[v].size()) {
                v = a;
            }
        }
        return v;
    }

    public ArrayList<Integer> union(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> u = A;
        for(int b : B) {
            if (!u.contains(b)) u.add(b);
        }
        return u;
    }

    public ArrayList<Integer> intersection(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> i = new ArrayList<>();
        for (int a: A) {
            if (B.contains(a)) i.add(a);
        }
        return i;
    }

    public ArrayList<Integer> difference(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> d = new ArrayList<>();
        for (int a: A) {
            if (!B.contains(a)) d.add(a);
        }
        return d;
    }

    public ArrayList<Integer> neighbours(int v) {
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

    public void getCliques() {
        System.out.println("Cliques:");
        for (ArrayList<Integer> l : cliques) {
            for (int i : l) {
                System.out.print(i + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
