package prop.domain;

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

    private static final Song song0 = new Song("title0","artist0","album0",2000,Genre.getGenreById(0),Genre.getGenreById(0),000);
    private static final Song song1 = new Song("title1","artist1","album1",2001,Genre.getGenreById(1),Genre.getGenreById(1),111);
    private static final Song song2 = new Song("title2","artist2","album2",2002,Genre.getGenreById(2),Genre.getGenreById(2),222);
    private static final Song song3 = new Song("title3","artist3","album3",2003,Genre.getGenreById(3),Genre.getGenreById(3),333);
    private static final Song song4 = new Song("title4","artist4","album4",2004,Genre.getGenreById(4),Genre.getGenreById(4),444);
    private static final Song song5 = new Song("title5","artist5","album5",2005,Genre.getGenreById(5),Genre.getGenreById(5),555);
    private static final Song song6 = new Song("title6","artist6","album6",2006,Genre.getGenreById(6),Genre.getGenreById(6),666);

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
                    readGraph(graph);
                    break;
                case 4:
                    writeGraph(graph);
                    break;
                case 5:
                    if (louvain != null) {
                        log = louvain.execute(graph,in.nextInt());
                        ArrayList<String> mess = log.getLog();
                        for (String s : mess)
                            System.out.println(s);
                    }
                    break;
                case 6:
                    ArrayList<Graph> communities = null;
                    if (log != null) {
                        communities = log.getCommunities();
                        for (int j = 0; j < communities.size(); ++j) {
                            System.out.println("Community #" + j);
                            writeGraph(communities.get(j));
                        }
                    }
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
        sb.append("5:  AlgorithmOutput log = execute(Graph<Song> graph)\n");
        sb.append("6:  foreach community in log: writeGraph()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static void readGraph(Graph<Song> graph){
        graph.addVertex(song0);
        graph.addVertex(song1);
        graph.addVertex(song2);
        graph.addVertex(song3);
        graph.addVertex(song4);
        graph.addVertex(song5);
        graph.addVertex(song6);
        graph.addEdge(song0,song1,1.0);
        graph.addEdge(song0,song2,1.0);
        graph.addEdge(song1,song2,1.0);
        graph.addEdge(song0,song3,1.0);
        graph.addEdge(song4,song3,1.0);
        graph.addEdge(song3,song5,1.0);
        graph.addEdge(song4,song5,1.0);
        graph.addEdge(song6,song4,1.0);
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
