package Algorithm;

import FileReader.CSVReader;
import Graph.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AStar implements PathSearchingAlgorithm
{
    int closedTimes = 0;
    Graph graph = new Graph();
    Heuristic heuristic = new EuclideanHeuristic();

    HashMap<Vertex, Integer> g = new HashMap<Vertex, Integer>();
    HashMap<Vertex, Integer> h = new HashMap<Vertex, Integer>();
    HashMap<Vertex, Integer> f = new HashMap<Vertex, Integer>();
    @Override
    public void loadGarph() throws IOException {
        CSVReader reader = new CSVReader();
       reader.read("connection_graph.csv");
        graph = reader.createGraph();

    }

    @Override
    public void printGraph() {
        graph.printGraph();
    }


    @Override
    public void findShortestPath(String source, String destination, Integer startTime)
    {
        Vertex startVertex = graph.getVertex(source);
        Vertex endVertex = graph.getVertex(destination);
        HashMap<Vertex, Edge> parentPath = new HashMap<Vertex, Edge>();
        List<Vertex> open = new ArrayList<Vertex>();
        List<Vertex> closed = new ArrayList<Vertex>();
        ArrayList<Edge> path = new ArrayList<Edge>();
        Vertex current = null;
        double currentCost;
        g.put(startVertex, 0);
        h.put(startVertex, 0);
        f.put(startVertex, g.get(startVertex)+ h.get(startVertex));
        open.add(startVertex);

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
            closedTimes++;
            if(current == endVertex) {
                break;
            }
            open.remove(current);
            closed.add(current);
            for(Edge neighbour : current.getNeighbours())
            {
                if(!open.contains(neighbour.getEndStop()) && !closed.contains(neighbour.getEndStop()) && neighbour.getStartTime() > startTime && neighbour.getStartTime() >= g.get(neighbour.getStart())) {

                    open.add(neighbour.getEndStop());
                    h.put(neighbour.getEndStop(), heuristic.getHeuristicValue(neighbour.getEndStop(), endVertex));
                    neighbour.setCost(g.get(neighbour.getStart()));
                    g.put(neighbour.getEndStop(), g.get(current) + neighbour.getCost());
                    f.put(neighbour.getEndStop(), g.get(neighbour.getEndStop()) + h.get(neighbour.getEndStop()));
                    parentPath.put(neighbour.getEndStop(), neighbour);
                }
                else if(neighbour.getStartTime() > startTime && neighbour.getStartTime() >= g.get(neighbour.getStart()))
                {
                    neighbour.setCost(g.get(neighbour.getStart()));

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
       }
        //printPath(parentPath, endVertex);
        System.out.println(closedTimes);
        closedTimes = 0;
    }

    private void printPath(HashMap<Vertex,Edge> parentPath, Vertex child)
    {
        Vertex tmp = child;
        if(parentPath.get(child) != null )
        {

           // System.out.println(g.get(child));
            child = parentPath.get(child).getStart();
            printPath(parentPath, child);
            System.out.println("\u001B[35m"+parentPath.get(tmp).toString()+"\u001B[0m");

        }
    }
}
