package prop.domain;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Scanner;

/**
 * Class GraphDriver
 * @author Carles Garcia Cabot
 */
public class GraphDriver {
    public static void main(String[] args) {
        try {
            System.out.println("**********************************************************");
            System.out.println("** Algorithm Output");
            System.out.println("**********************************************************");
            System.out.print("\n");
            printInfo();
            System.out.println("Graph is a generic class. To facilitate testing a Graph<Integer> will be used" +
                    "\n Therefore, vertices are Integers");
            Scanner in = new Scanner(System.in);
            int i = -1;
            Graph<Integer> g = null;
            while (i != 0) {
                i = in.nextInt();
                switch (i) {
                    case 0:
                        break;
                    case 1:
                        printInfo();
                        break;
                    case 2:
                        g = new Graph<Integer>();
                        break;
                    case 3:
                        g.addArc(in.nextInt(),in.nextInt());
                        break;
                    case 4:
                        g.addArc(in.nextInt(),in.nextInt(),in.nextDouble());
                        break;
                    case 5:
                        g.addEdge(in.nextInt(),in.nextInt());
                        break;
                    case 6:
                        g.addEdge(in.nextInt(),in.nextInt(),in.nextDouble());
                        break;
                    case 7:
                        g.addEdgeT(in.nextInt(), in.nextInt(),in.nextDouble());
                        break;
                    case 8:
                        //g.addVertex()
                        break;
                    case 9:
                        LinkedHashSet<Integer> lhs = g.adjacentVertices(in.nextInt());
                        for (Integer a : lhs) System.out.println(a);
                        break;
                    case 10:
                        //contains
                        break;
                    case 11:
                        System.out.println(g.getArcLoopDegree(in.nextInt()));
                        break;
                    case 12:
                        System.out.println(g.getDefaultWeight());
                        break;
                    case 13:
                        System.out.println(g.getDegree(in.nextInt()));
                        break;
                    case 14:
                        System.out.println(g.getIndegree(in.nextInt()));
                        break;
                    case 15:
                        System.out.println(g.getLoopDegree(in.nextInt()));
                        break;
                    case 16:
                        for (Integer t: g.getOriginalVertices()) System.out.println(t.toString());
                        break;
                    case 17:
                        System.out.println(g.getOutdegree(in.nextInt()));
                        break;
                    case 18:
                        // getvertex(t)
                        break;
                    case 19:
                        System.out.println(g.getVertexT(in.nextInt()).toString());
                        break;
                    case 20:
                        System.out.println(g.hasArc(in.nextInt(), in.nextInt()));
                        break;
                    case 21:
                        System.out.println(g.hasEdge(in.nextInt(), in.nextInt()));
                        break;
                    case 22:
                        System.out.println(g.isEmpty());
                        break;
                    case 23:
                        System.out.println(g.numberOfEdges());
                        break;
                    case 24:
                        System.out.println(g.numberOfEdges(in.nextInt(),in.nextInt()));
                        break;
                    case 25:
                        System.out.println(g.numberOfVertices());
                        break;
                    case 26:
                        System.out.println(g.removeArc(in.nextInt(), in.nextInt()));
                        break;
                    case 27:
                        System.out.println(g.removeEdge(in.nextInt(), in.nextInt()));
                        break;
                    case 28:
                        g.setDefaultWeight(in.nextDouble());
                        break;
                    case 29:
                        System.out.println(g.totalEdges(in.nextInt()));
                        break;
                    case 30:
                        System.out.println(g.weight(in.nextInt(),in.nextInt()));
                        break;
                    case 31:
                        ArrayList<Double> ad = g.weights(in.nextInt(),in.nextInt());
                        for (Double d : ad) System.out.println(d);
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

    private static void printInfoBrief() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("terminate program");
        sb.add("info");
        for (int i = 0; i < sb.size(); ++i) {
            System.out.println(i + ": " + sb.get(i));
        }
    }
    private static void printInfo() {
        ArrayList<String> sb = new ArrayList<>();
        sb.add("terminate program");
        sb.add("info");
        sb.add("Graph()");
        sb.add("void 	addArc(int v1, int v2)");
        sb.add("void 	addArc(int v1, int v2, double weight)");
        sb.add("void 	addEdge(int v1, int v2)");
        sb.add("void 	addEdge(int v1, int v2, double weight)");
        sb.add("void 	addEdgeT(T v1, T v2, double weight)");
        sb.add("void 	addVertex(T v)");
        sb.add("java.util.LinkedHashSet<java.lang.Integer> 	adjacentVertices(int v)");
        sb.add("boolean 	areAdjacent(int v1, int v2)");
        sb.add("boolean 	contains(T v)");
        sb.add("int 	getArcLoopDegree(int v)");
        sb.add("double 	getDefaultWeight()");
        sb.add("int 	getDegree(int v)");
        sb.add("int 	getIndegree(int v)");
        sb.add("int 	getLoopDegree(int v)");
        sb.add("java.util.ArrayList<T> 	getOriginalVertices()");
        sb.add("int 	getOutdegree(int v)");
        sb.add("int 	getVertex(T v)");
        sb.add("T 	getVertexT(int v)");
        sb.add("boolean 	hasArc(int v1, int v2)");
        sb.add("boolean 	hasEdge(int v1, int v2)");
        sb.add("boolean 	isEmpty()");
        sb.add("int 	numberOfEdges()");
        sb.add("int 	numberOfEdges(int v1, int v2)");
        sb.add("int 	numberOfVertices()");
        sb.add("boolean 	removeArc(int v1, int v2)");
        sb.add("boolean 	removeEdge(int v1, int v2)");
        sb.add("void 	setDefaultWeight(double w)");
        sb.add("int 	totalEdges(int v)");
        sb.add("double 	weight(int v1, int v2)");
        sb.add("java.util.ArrayList<java.lang.Double> 	weights(int v1, int v2)");

        for (int i = 0; i < sb.size(); ++i) {
            System.out.println(i + ": " + sb.get(i));
        }
    }
}