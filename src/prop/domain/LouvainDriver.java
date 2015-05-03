package prop.domain;

import prop.ErrorString;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * LouvainDriver in prop.domain
 *
 * @author gerard.casas.saez
 * @version 1.0
 *          Creation Date: 22/04/15
 */
public class LouvainDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Louvain algorithm");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        
        

        Louvain louvain = null;
        Graph<Song> graph = new Graph<>();
        AlgorithmOutput log = null;
        
        Scanner in = new Scanner(System.in);
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
                    louvain = new Louvain();
                    break;
                case 3:
                    //System.out.println("Introduce number of vertices and number of edges");
                    int n = in.nextInt();
                    int m = in.nextInt();
                    //System.out.println("for each node introduce a song : ");
                    //System.out.println("Song(String title, String artist, String album, int year, Genre genre, Genre subgenre, int duration)");
                    graph = new Graph<Song>();
                    ArrayList<Song> songs = new ArrayList<>();
                    for (int j = 0; j < n; ++j) {
                        String title = in.next();
                        String artist = in.next();
                        String album = in.next();
                        int year = in.nextInt();
                        Genre genre = Genre.getGenreById(in.nextInt());
                        Genre subgenre = Genre.getGenreById(in.nextInt());
                        int duration = in.nextInt();
                        Song s = new Song(title, artist, album, year, genre, subgenre, duration);
                        songs.add(s);
                        graph.addVertex(s);
                    }
                    //System.out.println("for each  each edge introduce two index of each song and weight: ");
                    for (int j = 0; j < m; ++j) {
                        int s1 = in.nextInt();
                        int s2 = in.nextInt();
                        double w = in.nextDouble();
                        graph.addEdgeT(songs.get(s1), songs.get(s2), w);
                    }
                    break;
                case 4:
                    writeGraph(graph);
                    break;
                case 5:
                    if (louvain != null) {
                        log = louvain.execute(graph,in.nextInt());
                    }
                    break;
                case 6:
                    if (log != null) {
                        ArrayList<String> mess = log.getLog();
                        for (String s : mess)
                            System.out.print(s);
                    } else {
                        System.out.println(ErrorString.ALGORITHM_NOT_EXECUTED);
                    }
                    break;
                case 7:
                    ArrayList<Graph> communities = null;
                    if (log != null) {
                        communities = log.getCommunities();
                        for (int j = 0; j < communities.size(); ++j) {
                            System.out.println("Community #" + j);
                            writeGraph(communities.get(j));
                        }
                    } else {
                        System.out.println(ErrorString.ALGORITHM_NOT_EXECUTED);
                    }
                    break;
                default:
                    printInfo();
                    break;
            }
        }
    }

    private static void printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("0:  terminate program\n");
        sb.append("1:  info\n");
        sb.append("2:  Louvain()\n");
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
            for (Object j :  G.adjacentVertices(i)) {
                s = (Song) G.getVertexT((int)j);
                System.out.print(s.getTitle() + " ");
            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }

}
