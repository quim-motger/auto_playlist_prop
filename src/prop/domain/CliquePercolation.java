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
    int i;
    ArrayList<Integer>[] cliques;
    ArrayList<Integer>[] graph;
    
    public void execute() {
        ArrayList<Integer> R = new ArrayList<>();
        ArrayList<Integer> P = new ArrayList<>();
        ArrayList<Integer> X = new ArrayList<>();
        cliques = (ArrayList<Integer>[])new ArrayList[n];
        i = 0;
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
            System.out.print("Cliques since now:\n");
            getCliques();
            cliques[i] = new ArrayList<>();
            cliques[i] = R;
            i = i+1;
            System.out.print("New clique found:\n");
            getCliques();
        }
        ArrayList<Integer> P1 = new ArrayList<>(P);
        for (int v : P) {
            if (!R.contains(v)) R.add(v);
            BronKerboschTomita(R, intersection(P1, neighbours(v)), intersection(X, neighbours(v)));
            R = remove(R,v);
            P1 = remove(P1,v);
            X.add(v);
        }
    }

    private ArrayList<Integer> intersection(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> intersection = new ArrayList<>(A);
        intersection.retainAll(B);
        printList(intersection);
        return intersection;
    }

    private ArrayList<Integer> remove(ArrayList<Integer> A, int v) {
        ArrayList<Integer> B = new ArrayList<>();
        B.add(v);
        ArrayList<Integer> difference = new ArrayList<>(A);
        difference.removeAll(B);
        printList(difference);
        return difference;
    }

    private ArrayList<Integer> neighbours(int v) {
        ArrayList<Integer> n = new ArrayList<>();
        for (int u : graph[v]) {
            n.add(u);
        }
        return n;
    }

    private void printList(ArrayList<Integer> l) {
        System.out.print("Llista:");
        for (int i : l) {
            System.out.print(" " + i);
        }
        System.out.print("\n");
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
        int k;
        for (k = 0; k < i; ++k) {
            for (int p : cliques[k]) {
                System.out.print(" " + p);
            }
            System.out.print("\n");
        }
    }

}
