package prop.domain;

import prop.ErrorString;
import prop.PropException;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Algorithm Controller Driver
 * @author oscar.manas
 */
public class AlgorithmControllerDriver {

    public static void main(String[] args) {
        System.out.println("**********************************************************");
        System.out.println("** AlgorithmController");
        System.out.println("**********************************************************");
        System.out.print("\n");
        printInfo();
        System.out.println("WARNING: it is highly recommendable to start with option #2\n");

        AlgorithmController ac = null;
        ListController listController = new ListController();
        RelationController relationController = new RelationControllerStub();
        ArrayList<String> log = null;

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
                    ac = new AlgorithmController();
                    break;
                case 3:
                    String title = in.next();
                    int algorithm = in.nextInt();
                    int k = in.nextInt();
                    try {
                        log = ac.execute(title, algorithm, k, listController, relationController);
                        System.out.println(log.get(log.size() - 1));
                    }
                    catch (PropException|NullPointerException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        for (String s : log)
                            System.out.print(s);
                        System.out.print("\n");
                    }
                    catch (NullPointerException e) {
                        System.out.print(ErrorString.ALGORITHM_NOT_EXECUTED);
                    }
                    break;
                case 5:
                    String title2 = in.next();
                    System.out.println(listController.getListString(title2));
                    break;
                case 6:
                    System.out.println(listController.getListSetString());
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
        sb.append("2:  AlgorithmController()\n");
        sb.append("3:  ArrayList<String> execute(String title, int algorithm, int k)\n");
        sb.append("4:  void printLog()\n");
        sb.append("5:  String getListString(int id)\n");
        sb.append("6:  String getListSetString()\n");
        sb.append("\n");
        System.out.print(sb.toString());
    }

    private static class RelationControllerStub extends RelationController {

        private Graph graph = null;
        private Song song0 = new Song("title0","artist0","album0",2000,Genre.getGenreById(0),Genre.getGenreById(0),000);
        private Song song1 = new Song("title1","artist1","album1",2001,Genre.getGenreById(1),Genre.getGenreById(1),111);
        private Song song2 = new Song("title2","artist2","album2",2002,Genre.getGenreById(2),Genre.getGenreById(2),222);
        private Song song3 = new Song("title3","artist3","album3",2003,Genre.getGenreById(3),Genre.getGenreById(3),333);
        private Song song4 = new Song("title4","artist4","album4",2004,Genre.getGenreById(4),Genre.getGenreById(4),444);
        private Song song5 = new Song("title5","artist5","album5",2005,Genre.getGenreById(5),Genre.getGenreById(5),555);
        private Song song6 = new Song("title6","artist6","album6",2006,Genre.getGenreById(6),Genre.getGenreById(6),666);

        public Graph getGraph() {
            readGraph();
            return graph;
        }

        private void readGraph(){
            graph = new Graph<Song>();
            graph.addVertex(song0);
            graph.addVertex(song1);
            graph.addVertex(song2);
            graph.addVertex(song3);
            graph.addVertex(song4);
            graph.addVertex(song5);
            graph.addVertex(song6);
            graph.addEdgeT(song0, song1, 3.0);
            graph.addEdgeT(song0, song2, 6.0);
            graph.addEdgeT(song0, song3, 3.0);
            graph.addEdgeT(song1, song3, 1.0);
            graph.addEdgeT(song1, song4, 4.0);
            graph.addEdgeT(song2, song3, 2.0);
            graph.addEdgeT(song2, song3, 2.0);
            graph.addEdgeT(song3, song4, 2.0);
            graph.addEdgeT(song3, song4, 2.0);
            graph.addEdgeT(song5, song6, 1.0);
        }
    }

}
