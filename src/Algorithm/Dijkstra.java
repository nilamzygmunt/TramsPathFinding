package Algorithm;
import Graph.*;
import FileReader.CSVReader;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.HashMap;
import java.io.IOException;
import java.util.Objects;

public class Dijkstra {
    Graph graph = new Graph();

    public void loadGarph() throws IOException {
        CSVReader reader = new CSVReader();
        reader.read("connection_graph.csv");
     //   reader.read("foo.csv");
        graph = reader.createGraph();

    }

    public void findShortestPath(String source, String destination, int startTime)
    {
        Vertex startVertex = graph.getVertex(source);
        Vertex endVertex = graph.getVertex(destination);
        //should it be edges??
        HashMap<Vertex, Integer> Cost = new HashMap<Vertex, Integer>();
        HashMap<Vertex, Integer> timeCost = new HashMap<Vertex, Integer>();
        HashMap<Vertex, Integer> timeVisited = new HashMap<Vertex, Integer>();
        HashSet<Vertex> unvisited = new HashSet<Vertex>();
        ArrayList<Edge> path = new ArrayList<Edge>();
        Vertex current;

        for(Vertex v : graph.getVertices().values())
        {
            timeCost.put(v, Integer.MAX_VALUE);
            unvisited.add(v);
        }
//        for(Vertex v : graph.getVertices().values())
//        {
//            for(Edge e : v.getNeighbours())
//            {
//                time.put(e, Integer.MAX_VALUE);
//            }
//        }
        current = startVertex;
        timeCost.put(startVertex, 0);
        timeVisited.put(startVertex, 0);
        while(unvisited.size() > 0)
        {
            System.out.println("CURR: "+ current.getStop());
            if(!unvisited.contains(startVertex))
            {
                current = getMinDistance(timeCost, unvisited);
            }
            if(current == endVertex) {
                break;
            }
            unvisited.remove(current);
            for(Edge neighbour : current.getNeighbours())
            {
                if(unvisited.contains(neighbour.getEndStop()))
                {
                    //System.out.println("NEIGHBOUR: "+ neighbour.getEndStop().getStop());
                    neighbour.setCost(timeVisited.get(current));
//                    System.out.println("time Visited: "+ neighbour.getCost());
//                    System.out.println("cost: "+ neighbour.getCost());
                    int distanceToNeighbour = neighbour.getCost();
                    if(distanceToNeighbour > 0)
                    {
                        int timeCostFromStart = timeCost.get(current) + distanceToNeighbour;
                        if (timeCostFromStart < timeCost.get(neighbour.getEndStop()))
                        {
                            timeCost.put(neighbour.getEndStop(), timeCostFromStart);
                            neighbour.setStart(current);
                            timeVisited.put(neighbour.getEndStop(), neighbour.getEndTime());
                            path.add(neighbour);
                        }
                    }
                }
            }
        }
        printPathToDestination(path,destination);
    }

    public void printPathToDestination(ArrayList<Edge> path, String destination)
    {
        int  minCost = Integer.MAX_VALUE;
        Edge curr = null;
        for(Edge e : path)
        {
            if (e.getEndStop().getStop().equals( destination) && e.getCost()<minCost)
            {
                curr = e;
                minCost = e.getCost();
            }
        }
        printPath(curr, path);
    }
    public void printPath(Edge curr, ArrayList<Edge> path)
    {
        for(Edge e : path) {


            if (e.getEndStop().getStop().equals(curr.getStart().getStop())) {
                printPath(e, path);
                break;
            }
        }
        System.out.println("\u001B[35m"+curr.toString()+"\u001B[0m");
    }

    public Vertex getMinDistance(HashMap<Vertex, Integer> distances, HashSet<Vertex> unvisited)
    {
        int minDistance = Integer.MAX_VALUE;
        Vertex minVertex = null;
        for (Vertex v : unvisited)
        {
            if(distances.get(v) < minDistance)
            {
                minDistance = distances.get(v);
                minVertex = v;
            }
        }

        return minVertex;
    }
    

}
