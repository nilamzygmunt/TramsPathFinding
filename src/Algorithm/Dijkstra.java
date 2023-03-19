package Algorithm;
import Graph.*;
import FileReader.CSVReader;
import kotlin.Triple;

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
        //reader.read("foo.csv");
        graph = reader.createGraph();

    }

    public void findShortestPath(String source, String destination, int startTime)
    {
        //jeszcze nocne
        Vertex startVertex = graph.getVertex(source);
        Vertex endVertex = graph.getVertex(destination);
        HashMap<Vertex, Double> minTimeVisitedVertex = new HashMap<Vertex, Double>();
        HashMap<Vertex, Edge> parentPath = new HashMap<Vertex, Edge>();
        HashSet<Vertex> unvisited = new HashSet<Vertex>();
        ArrayList<Edge> path = new ArrayList<Edge>();
        Vertex current;

        for(Vertex v : graph.getVertices().values())
        {
            minTimeVisitedVertex.put(v, Double.MAX_VALUE);
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
       // timeCost.put(startVertex, 0);
        minTimeVisitedVertex.put(startVertex, 0.0);
        System.out.println("CURR: "+ current.getStop());
        while(unvisited.size() > 0)
        {

            if(!unvisited.contains(startVertex))
            {
                current = getMinDistance(minTimeVisitedVertex, unvisited);
                System.out.println("CURR: "+ current.getStop());
            }
            if(current == endVertex) {
                break;
            }
            unvisited.remove(current);
            for(Edge neighbour : current.getNeighbours())
            {
                if(unvisited.contains(neighbour.getEndStop()) && neighbour.getStartTime() >= startTime && neighbour.getStartTime() >= minTimeVisitedVertex.get(neighbour.getStart() ))
                {
                    System.out.println("NEIGHBOUR: "+ neighbour.toString());
                    neighbour.setCost(minTimeVisitedVertex.get(neighbour.getStart()), "");
                    System.out.println("visited: "+ minTimeVisitedVertex.get(neighbour.getStart()));
                    System.out.println("neighbour cost + waiting time: "+ neighbour.getCost());
//                    System.out.println("cost: "+ neighbour.getCost());
                    double timeToArriveAtNeighbourVertex = neighbour.getCost();
                    if(timeToArriveAtNeighbourVertex > 0)
                    {
                        double newTimeVisitedVertex = minTimeVisitedVertex.get(current) + timeToArriveAtNeighbourVertex;
                        if (newTimeVisitedVertex < minTimeVisitedVertex.get(neighbour.getEndStop()))
                        {
                           // timeCost.put(neighbour.getEndStop(), newTimeVisitedVertex);
                            minTimeVisitedVertex.put(neighbour.getEndStop(), newTimeVisitedVertex);
                            parentPath.put(neighbour.getEndStop(), neighbour);
                            path.add(neighbour);
                        }
                    }
                }
            }
            for(Vertex v : minTimeVisitedVertex.keySet())
            {
                System.out.println("v: "+v.getStop() + " :  "+ minTimeVisitedVertex.get(v));
            }
        }
        //printPathToDestination(path,destination);
        printWell(parentPath, endVertex, startVertex);
    }

    private void printWell(HashMap<Vertex,Edge> parentPath, Vertex child, Vertex start)
    {
        Vertex tmp = child;
        if(parentPath.get(child) != null )
        {
            child = parentPath.get(child).getStart();
            printWell(parentPath, child, start);
            System.out.println("\u001B[35m"+parentPath.get(tmp).toString()+"\u001B[0m");
        }


    }


    public Vertex getMinDistance(HashMap<Vertex, Double> minTimeVisitedVertex, HashSet<Vertex> unvisited)
    {
        double minDistance = Double.MAX_VALUE;
        Vertex minVertex = null;
        for (Vertex v : unvisited)
        {
            if(minTimeVisitedVertex.get(v) < minDistance)
            {
                minDistance = minTimeVisitedVertex.get(v);
                minVertex = v;
            }
        }

        return minVertex;
    }



}
