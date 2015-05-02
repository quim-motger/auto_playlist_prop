package prop.domain;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * AlgorithmOutputDriver
 * @author Carles Garcia Cabot
 */
public class AlgorithmOutputDriver {
    public static void main(String[] args) {
        try {
            System.out.println("**********************************************************");
            System.out.println("** Algorithm Output");
            System.out.println("**********************************************************");
            System.out.print("\n");
            printInfo();

            Graph<Integer> graph = null;
            ArrayList<Graph> arrayGraph = null;
            ArrayList<String> arrayString = null;
            String string = null;

            Scanner in = new Scanner(System.in);
            int i = -1;
            AlgorithmOutput ao = null;
            while (i != 0) {
                i = in.nextInt();
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        printInfo();
                        break;
                    case 2:
                        ao = new AlgorithmOutput();
                        break;
                    case 3:
                        ao = new AlgorithmOutput(arrayGraph, arrayString);
                        break;
                    case 4:
                        ao.add(graph);
                        break;
                    case 5:
                        ao.add(string);
                        break;
                    case 6:
                        System.out.println(ao.densestGraph());
                        break;
                    case 7:
                        for (Graph gra : ao.getCommunities()) System.out.println(gra);
                        break;
                    case 8:
                        for (String s : ao.getLog()) System.out.println(s);
                        break;
                    case 9:
                        graph = new Graph<Integer>();
                        break;
                    case 10:
                        graph.addArc(in.nextInt(), in.nextInt());
                        break;
                    case 11:
                        graph.addArc(in.nextInt(),in.nextInt(),in.nextDouble());
                        break;
                    case 12:
                        graph.addEdge(in.nextInt(), in.nextInt());
                        break;
                    case 13:
                        graph.addEdgeT(in.nextInt(), in.nextInt(), in.nextDouble());
                        break;
                    case 14:
                        graph.addVertex(in.nextInt());
                        break;
                    case 15:
                        string = in.next();
                        break;
                    case 16:
                        arrayGraph = new ArrayList<>();
                        break;
                    case 17:
                        arrayGraph.add(graph);
                        break;
                    case 18:
                        arrayString = new ArrayList<>();
                        break;
                    case 19:
                        arrayString.add(string);
                        break;
                    default:
                        printInfo();
                }
                printInfoBrief();
            }
        }
        catch(Exception e) {
            System.err.println("Caught Exception: " + e.getMessage());
        }
    }
    private static void printInfo() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("terminate program");
        sb.add("info");
        sb.add("AlgorithmOutput()");
        sb.add("AlgorithmOutput(java.util.ArrayList<Graph> communities, java.util.ArrayList<java.lang.String> log)");
        sb.add("void 	add(graph)");
        sb.add("void 	add(string)");
        sb.add("Graph 	densestGraph()");
        sb.add("java.util.ArrayList<Graph> 	getCommunities()");
        sb.add("java.util.ArrayList<java.lang.String> 	getLog()");
        sb.add("graph = new Graph<Integer>");
        sb.add("graph.addArc(int v1, int v2)");
        sb.add("graph.addArc(int v1, int v2, double weight)");
        sb.add("graph.addEdge(int v1, int v2)");
        sb.add("graph.addEdge(int v1, int v2, double weight)");
        sb.add("graph.addEdgeT(T v1, T v2, double weight)");
        sb.add("graph.addVertex(T v)");
        sb.add("string = new String(String s)");
        sb.add("arrayGraph = new ArrayList<Graph>()");
        sb.add("arrayGraph.add(graph)");
        sb.add("arrayString = new ArrayList<String>()");
        sb.add("arrayString.add(string)");
        for (int i = 0; i < sb.size(); ++i) {
            System.out.println(i + ": " + sb.get(i));
        }
    }
    private static void printInfoBrief() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("terminate program");
        sb.add("info");
        for (int i = 0; i < sb.size(); ++i) {
            System.out.println(i + ": " + sb.get(i));
        }
    }
}
