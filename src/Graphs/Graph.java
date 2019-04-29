package Graphs;

import java.util.*;
import java.util.stream.*;

public class Graph {

    /** define a Graph using adjency list: each element of the vertex list contains a list of neighbouring vertices
     * We call methods with labels only for it is simpler, no need to instantiate new Vertices
     *
     *
     * package-private access = no explicit modifier
     *
     * **/

    public Map<Vertex, List<Vertex>> neighbours;

    // be sure to initialize neighbours with something
    Graph() {
        neighbours = new HashMap<>();
    }



    void addVertex(String label) {
        // If the specified key is not already associated with a value (or is mapped to null) associates it with the given value and returns null, else returns the current value.
        List<Vertex> A = new ArrayList<>();
       // A.add(new Vertex("b"));
        //System.out.println(A.get(0).label);
       // System.out.println(A.size());

        neighbours.putIfAbsent(new Vertex(label), A);

    }

    void removeVertex(String label) {
        Vertex v = new Vertex(label);
        neighbours.values()
                // parse all values
                .stream()
                // TODO define what happens
                .map(e -> e.remove(v))
                .collect(Collectors.toList());
        neighbours.remove(new Vertex(label));
    }


    // method for adding an edge = neighbours to the vertex
    void addNeighbours(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);

        //System.out.println("2: " + v1.hashCode());
        //System.out.println("3: " + v2.hashCode());


        //System.out.println(neighbours.containsKey(v1));


       // neighbours.get(v2).remove(B);

        neighbours.get(v1).add(v2); // get v1 neighbours list, and add v2 to the list as its neighbour
        neighbours.get(v2).add(v1); // do the opposite for v2
    }


    // method for removing edge
    void removeNeighbours(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        List<Vertex> V1neighbours = neighbours.get(v1);  // get will return the value of the key, here the list
        List<Vertex> V2neighbours = neighbours.get(v2);

        if (V1neighbours != null)
            V1neighbours.remove(v2);
        if (V2neighbours != null)
            V2neighbours.remove(v1);
    }


    List<Vertex> getNeighbours(String label) {
        Vertex v = new Vertex(label);
        return neighbours.get(v);

    }




    boolean getVertex(String label) {
        return neighbours.containsKey(new Vertex(label));
    }

    int getSize() {
        return neighbours.size();
    }


    void getElements() {
        Set<Vertex> S = neighbours.keySet();  // return a Set view of the keys contained in the map

        //String[] V = S.toArray(new String[]);

        for (Vertex v : S) {
            List<Vertex> L = neighbours.get(v);
            System.out.print("node " + v.label + " | neighbours: ");
            for (Vertex c : L) {
                System.out.print(c.label + " ");
            }
            System.out.print("\n");
        }

    }







    /**--------------- BFS raw --------------
     *
     * : a Set is an Unordered collection, while a List is an Ordered collection
     **/
    Set<String> BreadthFirstSearch(Graph graph, String source) {

        Set<String> visited = new LinkedHashSet<String>(); //we dont want to keep an order so, an HashSet

        // the frontier, current visiting vertices
        Queue<String> queue = new LinkedList<String>();

        // initialization
        queue.add(source);
        visited.add(source);

        // while there's still nodes to explore
        while (!queue.isEmpty()) {

            String topVertex = queue.poll(); // retrieve and remove the head of the queue

            // for each neighbour of the retrieved vertex
            for (Vertex v : graph.getNeighbours(topVertex)) {

                // if we didn't already visit it
                if (!visited.contains(v.label)) {
                    visited.add(v.label);  // add it to th visited list
                    queue.add(v.label); // add it to the queue as the new frontier, since the frontier expanded
                }
            }
        }
        return visited;  // we can do something with visited, for pathfinding and path memory

    }






    /**--------------- BFS with pathfinding --------------
     *
     * We keep track of where we came from. To reconstruct path, follow the nodes from the goal to the start
     *
     *
     *
     * : a Set is an Unordered collection, while a List is an Ordered collection
     **/
    Map<String,String> BFS(Graph graph, String source, String goal) {

        Map<String,String> cameFrom = new LinkedHashMap<>(); //each location we visited is linked to the previous one, effectively allowing us to find the path taken

        /*for (int i = 0; i < graph.getSize(); i++) {
            cameFrom.add("0");
        }*/
        //String[] cameFrom = new String[16];

        // the frontier, current visiting vertices
        Queue<String> queue = new LinkedList<String>();

        System.out.println(cameFrom.size());

        // initialization
        queue.add(source);
        cameFrom.put(source,null); //the source doesn't have any parents


        // while there's still nodes to explore
        while (!queue.isEmpty()) {

            String topVertex = queue.poll(); // retrieve and remove the head of the queue

            //early exit, no need to keep going if we reached the goal
            if(topVertex.equals(goal)) break;


            // for each neighbour of the retrieved vertex
            for (Vertex v : graph.getNeighbours(topVertex)) {

                // if we didn't already visit it
                if (!cameFrom.containsKey(v.label) && !cameFrom.containsValue(v.label)) {
                    cameFrom.put(v.label,topVertex);  // add it to the visited list
                    System.out.println("adding child "+ v.label + " to parent " + topVertex );
                    queue.add(v.label); // add it to the queue as the new frontier, since the frontier expanded
                }
            }
        }
        return cameFrom;  // we can do something with visited, for pathfinding and path memory

    }





    List<String> Path_reconstruct(Map<String,String> cameFrom, String source, String goal) {

        List<String> path = new ArrayList<>();  //initiate the path
        String current = goal; // start from the goal and navigate the map

        while (!current.equals(source)) {  // while we haven't reach the source
            path.add(current);  // add to path
            current = cameFrom.get(current);  //get the parent in the map
        }

        path.add(source); // add the source for completion
        Collections.reverse(path);
        return path;

    }












}
