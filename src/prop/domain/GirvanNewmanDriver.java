package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**
 * Girvan-Newman algorithm Driver
 * @author oscar.manas
 */
public class GirvanNewmanDriver {

    private static Graph graph = null;

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Girvan-Newman algorithm");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2\n");

        GirvanNewman gn = null;
        AlgorithmOutput ao = null;

        Scanner in = new Scanner(System.in).useLocale(Locale.US);
        int i = -1;
        while (i != 0) {
            i = in.nextInt();
            switch (i) {
                case 0:
                    break;
                case 1:
                    printInfo();
                    break;
                case 2:
                    gn = new GirvanNewman();
                    break;
                case 3:
                    int n = in.nextInt();
                    int m = in.nextInt();
                    graph = new Graph<Song>();
                    ArrayList<Song> songs = new ArrayList<>();
                    for (int j = 0; j < n; ++j) {
                        String title = in.next();
                        String artist = in.next();
                        String album = in.next();
                        int year = in.nextInt();
                        try {
                            Genre genre = Genre.getGenreById(in.nextInt());
                            Genre subgenre = Genre.getGenreById(in.nextInt());
                            int duration = in.nextInt();
                            Song s = new Song(title, artist, album, year, genre, subgenre, duration);
                            songs.add(s);
                            graph.addVertex(s);
                        } catch (PropException e) {
                            System.out.println(e.getMessage());
                        }
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
                    ao = gn.execute(graph,k);
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
                    printInfo();
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  GirvanNewman()\n");
        sb.append("3:  void readGraph()\n");
        sb.append("4:  void writeGraph()\n");
        sb.append("5:  void execute(Graph graph, int k)\n");
        sb.append("6:  ArrayList<String> getLog()\n");
        sb.append("7:  ArrayList<Graph> getCommunities()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static void writeGraph(Graph G) {
        Song s;
        for (int i = 0; i < G.numberOfVertices(); ++i) {
            s = (Song) G.getVertexT(i);
            System.out.print(s.getTitle() + ": ");
            for (Integer j : (Iterable<Integer>) G.adjacentVertices(i)) {
                s = (Song) G.getVertexT((int)j);
                System.out.print(s.getTitle() + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
