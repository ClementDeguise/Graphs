package Graphs;

import java.util.*;
import java.util.stream.*;

public class main {

    public static void main(String[] args) {

        Graph testGraph = new Graph();

        testGraph.addVertex("0");
        testGraph.addVertex("1");
        testGraph.addVertex("2");
        testGraph.addVertex("3");
        testGraph.addVertex("4");
        testGraph.addVertex("5");
        testGraph.addVertex("6");
        testGraph.addVertex("7");
        testGraph.addVertex("8");
        testGraph.addVertex("9");
        testGraph.addVertex("10");
        testGraph.addVertex("11");
        testGraph.addVertex("12");
        testGraph.addVertex("13");
        testGraph.addVertex("14");
        testGraph.addVertex("15");

        //List<Vertex> V = testGraph.getNeighbours("0");
        //System.out.println(testGraph.getVertex("0"));

        testGraph.addNeighbours("0","1");
        testGraph.addNeighbours("10","2");
        testGraph.addNeighbours("11","1");
        testGraph.addNeighbours("11","12");
        testGraph.addNeighbours("2","0");
        testGraph.addNeighbours("0","4");
        testGraph.addNeighbours("4","13");
        testGraph.addNeighbours("8","3");
        testGraph.addNeighbours("3","7");
        testGraph.addNeighbours("7","14");
        testGraph.addNeighbours("9","5");
        testGraph.addNeighbours("5","6");
        testGraph.addNeighbours("6","15");
        testGraph.addNeighbours("2","8");
        testGraph.addNeighbours("8","9");
        testGraph.addNeighbours("10","1");
        testGraph.addNeighbours("0","3");
        testGraph.addNeighbours("3","5");
        testGraph.addNeighbours("11","4");
        testGraph.addNeighbours("4","7");
        testGraph.addNeighbours("7","6");
        testGraph.addNeighbours("12","13");
        testGraph.addNeighbours("13","14");
        testGraph.addNeighbours("14","15");

        //testGraph.getElements();

        int t = testGraph.getSize();
        //System.out.println("graph size: " + t);


        // testing BFS
        /*Set<String> visitedNaive = testGraph.BreadthFirstSearch(testGraph,"10");

        for (String s : visitedNaive) {
            System.out.print(s + " ");
        }*/

        Map<String,String> cameFrom = testGraph.BFS(testGraph,"10","7");

        List<String> path = testGraph.Path_reconstruct(cameFrom,"10","7");

        for (String s : path) { System.out.print(s + " "); }




    }
}
