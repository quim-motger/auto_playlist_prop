package prop.domain;

import java.util.Scanner;

/**
 * Girvan-Newman algorithm Driver
 * @author oscar.manas
 */
public class GirvanNewmanDriver {

    private static Graph graph = null;
    private static Song song0 = new Song("title0","artist0","album0",2000,Genre.getGenreById(0),Genre.getGenreById(0),000);
    private static Song song1 = new Song("title1","artist1","album1",2001,Genre.getGenreById(1),Genre.getGenreById(1),111);
    private static Song song2 = new Song("title2","artist2","album2",2002,Genre.getGenreById(2),Genre.getGenreById(2),222);
    private static Song song3 = new Song("title3","artist3","album3",2003,Genre.getGenreById(3),Genre.getGenreById(3),333);
    private static Song song4 = new Song("title4","artist4","album4",2004,Genre.getGenreById(4),Genre.getGenreById(4),444);

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** Girvan-Newman algorithm");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2\n");

        GirvanNewman gn = null;

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
                    gn = new GirvanNewman();
                    break;
                case 3:
                    readGraph();
                    break;
                case 4:
                    graph.writeGraph();
                    break;
                case 5:
                    int k = in.nextInt();
                    gn.execute(graph,k);
                    break;
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
        sb.append("5:  void execute(int n)\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static void readGraph(){
        graph = new Graph<Song>();
        graph.addVertex(song0);
        graph.addVertex(song1);
        graph.addVertex(song2);
        graph.addVertex(song3);
        graph.addVertex(song4);
        graph.addEdge(song0,song1,3);
        graph.addEdge(song0,song2,6);
        graph.addEdge(song0,song3,3);
        graph.addEdge(song1,song3,1);
        graph.addEdge(song1,song4,4);
        graph.addEdge(song2,song3,2);
        graph.addEdge(song3,song4,2);
    }

}
