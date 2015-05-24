package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Clique Percolation class driver
 * @author joaquim.motger
 */
public class CliquePercolationDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Clique Percolation method - Bron Kerbosch algorithm");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfoComplete();

        Scanner in = new Scanner(System.in);
        CliquePercolation cp = null;
        AlgorithmOutput ao = null;
        Graph graph = null;
        int i = -1;
        while (i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfoComplete();
                    break;
                case 2:
                    cp = new CliquePercolation();
                    break;
                case 3:
                    int n = in.nextInt();
                    int m = in.nextInt();
                    graph = new Graph<>();
                    ArrayList<Song> songs = new ArrayList<>();
                    for (int j = 0; j < n; ++j) {
                        String title = in.next();
                        String artist = in.next();
                        String album = in.next();
                        int year = in.nextInt();
                        Genre genre = Genre.getGenreById(in.nextInt());
                        Genre subgenre = Genre.getGenreById(in.nextInt());
                        int duration = in.nextInt();
                        Song s = null;
                        try {
                            s = new Song(title, artist, album, year, genre, subgenre, duration);
                        } catch (PropException e) {
                            System.out.println(e.getMessage());
                        }
                        songs.add(s);
                        graph.addVertex(s);
                    }
                    for (int j = 0; j < m; ++j) {
                        int s1 = in.nextInt();
                        int s2 = in.nextInt();
                        double w = in.nextDouble();
                        graph.addEdgeT(songs.get(s1),songs.get(s2),w);
                    }
                    break;
                case 4:
                    writeGraph(graph);
                    break;
                case 5:
                    int k = in.nextInt();
                    ao = cp.execute(graph,k);
                    break;
                case 6:
                    try {
                        ArrayList<String> log = ao.getLog();
                        for (String s : log)
                            System.out.print(s);
                    }
                    catch (NullPointerException e) {
                        System.out.println(ErrorString.ALGORITHM_NOT_EXECUTED);
                    }
                    break;
                case 7:
                    try {
                        ArrayList<Graph> communities = ao.getCommunities();
                        for (int j = 0; j < communities.size(); ++j) {
                            System.out.println("Community #" + j);
                            writeGraph(communities.get(j));
                        }
                    }
                    catch (NullPointerException e) {
                        System.out.println(ErrorString.ALGORITHM_NOT_EXECUTED);
                    }
                    break;
                default:
                    printInfoComplete();
            }
            if (i > 1 && i < 8) printInfoBrief();
        }
    }

    private static void printInfoComplete() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  BronKerboschTomita()\n");
        sb.append("3:  void readGraph()\n");
        sb.append("4:  void writeGraph()\n");
        sb.append("5:  void execute(Graph graph, int k)\n");
        sb.append("6:  ArrayList<String> getLog()\n");
        sb.append("7:  ArrayList<Graph> getCommunitites()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static void printInfoBrief() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        System.out.print(sb.toString());
    }

    private static void writeGraph(Graph G) {
        Song s;
        for (int i = 0; i < G.numberOfVertices(); ++i) {
            s = (Song) G.getVertexT(i);
            System.out.print(s.getTitle() + ": ");
            for (Integer j : (Iterable<Integer>) G.adjacentVertices(i)) {
                s = (Song) G.getVertexT(j);
                System.out.print(s.getTitle() + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
