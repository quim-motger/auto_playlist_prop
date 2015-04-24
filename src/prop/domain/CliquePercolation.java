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
        //R: list of vertices that may compose a clique
        ArrayList<Integer> R = new ArrayList<>();
        //P: candidates to be added in a clique
        ArrayList<Integer> P = new ArrayList<>();
        //X: rejected vertices
        ArrayList<Integer> X = new ArrayList<>();
        //cliques: list of cliques in graph
        cliques = (ArrayList<Integer>[])new ArrayList[n];
        i = 0;
        int k = 0;
        while (k < n) {
            //P contains all vertices in graph initially
            P.add(k);
            ++k;
        }
        //Executes the clique percolation algorithm
        BronKerboschTomita(R,P,X);
        getCliques();
    }

    private void BronKerboschTomita(ArrayList<Integer> R, ArrayList<Integer> P, ArrayList<Integer> X) {
        System.out.print("Provisional clique:");
        for (int r : R) System.out.print(" " + r);
        System.out.print("\nCandidates:");
        for (int p : P) System.out.print(" " + p);
        System.out.print("\nRejected:");
        for (int x : X) System.out.print(" " + x);
        System.out.print("\n\n");
        //If there are no more candidates to be add to the clique (P is empty) and
        //there aren't any other vertices connected with all of those in R who have
        //been rejected (X is empty), then R contains a maximal clique
        if (P.isEmpty() && X.isEmpty()) {
            System.out.print("New clique found\n");
            printClique(R);
            cliques[i] = new ArrayList<>();
            //R is added to array of maximal cliques
            cliques[i] = R;
            i = i+1;
            System.out.print("\n");
        }
        ArrayList<Integer> P1 = new ArrayList<>(P);
        //Expand of every vertex in P
        for (int v : P) {
            System.out.print("Expand on " + v + "\n");
            //R U v (add v to list of vertices that may compose a clique)
            if (!R.contains(v)) R.add(v);
            //Remove non-neighbours of v from candidates to be added to clique (P) and from
            //rejected who could be added to the clique, then backtrack
            BronKerboschTomita(R, intersection(P1, neighbours(v)), intersection(X, neighbours(v)));
            //Remove v from vertices that may compose a clique (R)
            R = remove(R,v);
            //Remove v from candidates to be added (P1)
            P1 = remove(P1,v);
            //Add v to the rejected candidates
            X.add(v);
        }
    }

    private ArrayList<Integer> intersection(ArrayList<Integer> A, ArrayList<Integer> B) {
        ArrayList<Integer> intersection = new ArrayList<>(A);
        intersection.retainAll(B);
        return intersection;
    }

    private ArrayList<Integer> remove(ArrayList<Integer> A, int v) {
        ArrayList<Integer> B = new ArrayList<>();
        B.add(v);
        ArrayList<Integer> difference = new ArrayList<>(A);
        difference.removeAll(B);
        return difference;
    }

    private ArrayList<Integer> neighbours(int v) {
        ArrayList<Integer> n = new ArrayList<>();
        for (int u : graph[v]) {
            n.add(u);
        }
        return n;
    }

    private void printClique(ArrayList<Integer> l) {
        System.out.print("New clique:");
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
