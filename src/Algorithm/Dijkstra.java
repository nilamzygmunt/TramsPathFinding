package Algorithm;
import Graph.*;
import FileReader.CSVReader;

import java.util.HashSet;
import java.util.HashMap;
import java.io.IOException;

public class Dijkstra implements PathSearchingAlgorithm
{
    Graph graph = new Graph();
    private int counterVertex =0;
    @Override
    public void loadGarph() throws IOException {
        CSVReader reader = new CSVReader();
        reader.read("connection_graph.csv");
        //reader.read("foo.csv");
        graph = reader.createGraph();

    }

    @Override
    public void printGraph() {
        graph.printGraph();
    }

    @Override
    public void findShortestPath(String source, String destination, Integer startTime)
    {
        //jeszcze nocne
        Vertex startVertex = graph.getVertex(source);
        Vertex endVertex = graph.getVertex(destination);
        HashMap<Vertex, Integer> minTimeVisitedVertex = new HashMap<Vertex, Integer>();
        HashMap<Vertex, Edge> parentPath = new HashMap<Vertex, Edge>();
        HashSet<Vertex> unvisited = new HashSet<Vertex>();
        Vertex current;

        for(Vertex v : graph.getVertices().values())
        {
            minTimeVisitedVertex.put(v, Integer.MAX_VALUE);
            unvisited.add(v);
        }

        current = startVertex;
        minTimeVisitedVertex.put(startVertex, 0);
        while(unvisited.size() > 0)
        {

            if(!unvisited.contains(startVertex))
            {
                current = getMinDistance(minTimeVisitedVertex, unvisited);
            }
            counterVertex++;
            if(current == endVertex) {
                break;
            }
            unvisited.remove(current);
            for(Edge neighbour : current.getNeighbours())
            {
                if(unvisited.contains(neighbour.getEndStop()) && neighbour.getStartTime() >= startTime && neighbour.getStartTime() >= minTimeVisitedVertex.get(neighbour.getStart() ))
                {
                    neighbour.setCost(minTimeVisitedVertex.get(neighbour.getStart()), "");
                    int timeToArriveAtNeighbourVertex = neighbour.getCost();
                    if(timeToArriveAtNeighbourVertex > 0)
                    {
                        int newTimeVisitedVertex = minTimeVisitedVertex.get(current) + timeToArriveAtNeighbourVertex;
                        if (newTimeVisitedVertex < minTimeVisitedVertex.get(neighbour.getEndStop()))
                        {
                            minTimeVisitedVertex.put(neighbour.getEndStop(), newTimeVisitedVertex);
                            parentPath.put(neighbour.getEndStop(), neighbour);
                        }
                    }
                }
            }
        }
        //printPath(parentPath, endVertex, startVertex);
        System.out.println(counterVertex);
        counterVertex =0;
    }

    private void printPath(HashMap<Vertex,Edge> parentPath, Vertex child, Vertex start)
    {
        Vertex tmp = child;
        if(parentPath.get(child) != null )
        {
            child = parentPath.get(child).getStart();
            printPath(parentPath, child, start);
            System.out.println("\u001B[35m"+parentPath.get(tmp).toString()+"\u001B[0m");
        }


    }


    public Vertex getMinDistance(HashMap<Vertex, Integer> minTimeVisitedVertex, HashSet<Vertex> unvisited)
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
