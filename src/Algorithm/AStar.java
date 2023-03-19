package Algorithm;

import FileReader.CSVReader;
import Graph.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AStar
{
    Graph graph = new Graph();
    Heuristic heuristic = new TimeHeuristic();


    HashMap<Vertex, Double> g = new HashMap<Vertex, Double>();
    HashMap<Vertex, Double> h = new HashMap<Vertex, Double>();
    HashMap<Vertex, Double> f = new HashMap<Vertex, Double>();
    public void loadGarph() throws IOException {
        CSVReader reader = new CSVReader();
       reader.read("connection_graph.csv");
       // reader.read("foo.csv");
        graph = reader.createGraph();
       // graph.printGraph();

    }

    public void findShortestPath(String source, String destination, Integer startTime)
    {
        Vertex startVertex = graph.getVertex(source);
        Vertex endVertex = graph.getVertex(destination);
        HashMap<Vertex, Integer> minTimeVisitedVertex = new HashMap<Vertex, Integer>();
        HashMap<Vertex, Edge> parentPath = new HashMap<Vertex, Edge>();
        List<Vertex> open = new ArrayList<Vertex>();
        List<Vertex> closed = new ArrayList<Vertex>();
        HashSet<Vertex> unvisited = new HashSet<Vertex>();
        ArrayList<Edge> path = new ArrayList<Edge>();
        Vertex current = null;
        double currentCost;
        g.put(startVertex, 0.0);
        h.put(startVertex, 0.0);
        f.put(startVertex, g.get(startVertex)+ h.get(startVertex));
        open.add(startVertex);

        for(Vertex v : graph.getVertices().values())
        {
            minTimeVisitedVertex.put(v, Integer.MAX_VALUE);
            unvisited.add(v);
        }

        minTimeVisitedVertex.put(startVertex, 0);

        while(open.size() > 0)
        {
            current = null;
            currentCost = Integer.MAX_VALUE;
            for(Vertex vertex : open)
            {
                if(f.get(vertex) < currentCost)
                {
                    current = vertex;
                    currentCost = f.get(vertex);
                }
            }
            System.out.println("CURR: "+ current.getStop());
            if(current == endVertex) {
                break;
            }
            open.remove(current);
            closed.add(current);
            for(Edge neighbour : current.getNeighbours())
            {

                if(!open.contains(neighbour.getEndStop()) && !closed.contains(neighbour.getEndStop()) && neighbour.getStartTime() > startTime && neighbour.getStartTime() >= g.get(neighbour.getStart())) {
                    System.out.println("NEIGHBOUR: " + neighbour.toString());
                    open.add(neighbour.getEndStop());
                    h.put(neighbour.getEndStop(), heuristic.getHeuristicValue(neighbour.getEndStop(), endVertex));
                    neighbour.setCost(g.get(neighbour.getStart()), "");
                    g.put(neighbour.getEndStop(), g.get(current) + neighbour.getCost());
                    f.put(neighbour.getEndStop(), g.get(neighbour.getEndStop()) + h.get(neighbour.getEndStop()));
                    System.out.println("WKŁADAM DO G I F: " + neighbour.toString());
                    parentPath.put(neighbour.getEndStop(), neighbour);
                }
                else if(neighbour.getStartTime() > startTime && neighbour.getStartTime() >= g.get(neighbour.getStart()))
                {
                    neighbour.setCost(g.get(neighbour.getStart()),"");

                    if(g.get(neighbour.getEndStop()) > g.get(current) + neighbour.getCost())
                    {
                        g.put(neighbour.getEndStop(), g.get(current) + neighbour.getCost());
                        f.put(neighbour.getEndStop(),g.get(neighbour.getEndStop()) + h.get(neighbour.getEndStop()));
                        parentPath.put(neighbour.getEndStop(), neighbour);
                        if(closed.contains(neighbour.getEndStop()))
                        {
                            open.add(neighbour.getEndStop());
                            closed.remove(neighbour.getEndStop());
                        }
                    }
                }
            }
//            for(Vertex v : minTimeVisitedVertex.keySet())
//            {
//                System.out.println("v: "+v.getStop() + " :  "+ minTimeVisitedVertex.get(v));
//            }
       }
        //printPathToDestination(path,destination);
        printWell(parentPath, endVertex);
    }

    private void printWell(HashMap<Vertex,Edge> parentPath, Vertex child)
    {
//        for(Vertex v : parentPath.keySet())
//        {
//            System.out.println("\u001B[35m"+"v: "+v.getStop() + " : "+ parentPath.get(v).toString()+"\u001B[0m");
//            System.out.println("cost: "+g.get(v));
//        }
        Vertex tmp = child;
        if(parentPath.get(child) != null )
        {
            System.out.println("\u001B[35m"+parentPath.get(tmp).toString()+"\u001B[0m");
            System.out.println(g.get(child));
            child = parentPath.get(child).getStart();
            printWell(parentPath, child);

        }


    }
    public Vertex getMinDistance(HashMap<Vertex, Integer> minTimeVisitedVertex, HashSet<Vertex> unvisited)
    {
        int minDistance = Integer.MAX_VALUE;
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
