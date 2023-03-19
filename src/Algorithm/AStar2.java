package Algorithm;

import FileReader.CSVReader;
import Graph.Edge;
import Graph.Graph;
import Graph.Vertex;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class AStar2
{
    Graph graph = new Graph();
    Heuristic heuristic = new TransferHeuristic();


    HashMap<Vertex, Double> g = new HashMap<Vertex, Double>();
    HashMap<Vertex, Double> h = new HashMap<Vertex, Double>();
    HashMap<Vertex, Double> f = new HashMap<Vertex, Double>();
    public void loadGarph() throws IOException {
        CSVReader reader = new CSVReader();
       reader.read("connection_graph.csv");
      //  reader.read("foo.csv");
        graph = reader.createGraph();
       // graph.printGraph();

    }

    public void findShortestPath(String source, String destination, Integer startTime)
    {
        Vertex startVertex = graph.getVertex(source);
        Vertex endVertex = graph.getVertex(destination);
        HashMap<Edge, Integer> minTimeVisitedVertex = new HashMap<Edge, Integer>();
        HashMap<Edge, String> lastLineVisitedVertex = new HashMap<Edge, String>();
        HashMap<Vertex, Edge> parentPath = new HashMap<Vertex, Edge>();
        HashMap<Edge, Edge> childParentEdge = new HashMap<Edge, Edge>();
        List<Vertex> open = new ArrayList<Vertex>();
        List<Vertex> closed = new ArrayList<Vertex>();
        Vertex current = null;
        double currentCost;
        g.put(startVertex, 0.0);
        h.put(startVertex, 0.0);
        f.put(startVertex, g.get(startVertex)+ h.get(startVertex));
        open.add(startVertex);

        for(Vertex v : graph.getVertices().values())
        {
            for(Edge e : v.getNeighbours())
            {
                minTimeVisitedVertex.put(e, Integer.MAX_VALUE);
            }
        }

       // minTimeVisitedVertex.put(startVertex, 0);

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
            for(Edge neighbourEdge : current.getNeighbours())
            {
                if(!open.contains(neighbourEdge.getEndStop()) && !closed.contains(neighbourEdge.getEndStop()))
                {
                    //System.out.println("NEIGHBOUR: " + neighbourEdge.toString());
                    open.add(neighbourEdge.getEndStop());
                    if(childParentEdge.get(neighbourEdge) == null)
                    {
                        neighbourEdge.setCost2(1);
                        for (Edge e : neighbourEdge.getEndStop().getNeighbours())
                        {
                            if(e.getLine().equals(neighbourEdge.getLine()) || e.getStartTime() == neighbourEdge.getEndTime())
                            {
                                neighbourEdge.setCost2(0);
                            }
                        }
                    }
                    else {
                        neighbourEdge.setCost2(minTimeVisitedVertex.get(childParentEdge.get(neighbourEdge)), lastLineVisitedVertex.get(childParentEdge.get(neighbourEdge)));
                    }
                    lastLineVisitedVertex.put(neighbourEdge, neighbourEdge.getLine());
                    minTimeVisitedVertex.put(neighbourEdge, neighbourEdge.getEndTime());
                    System.out.println("vertex: "+ neighbourEdge.getEndStop().getStop() + " line: "+neighbourEdge.getLine());
                    h.put(neighbourEdge.getEndStop(), heuristic.getHeuristicValue(neighbourEdge.getEndStop(), endVertex));
                    g.put(neighbourEdge.getEndStop(), g.get(current) + neighbourEdge.getCost());
                    f.put(neighbourEdge.getEndStop(), g.get(neighbourEdge.getEndStop()) + h.get(neighbourEdge.getEndStop()));
                    parentPath.put(neighbourEdge.getEndStop(), neighbourEdge);
                    childParentEdge.put(neighbourEdge, parentPath.get(neighbourEdge.getStart()) );
                }
                else
                {
                    if(childParentEdge.get(neighbourEdge) == null)
                    {
                        neighbourEdge.setCost2(1);
                    }
                    else {
                        neighbourEdge.setCost2(minTimeVisitedVertex.get(childParentEdge.get(neighbourEdge)), lastLineVisitedVertex.get(childParentEdge.get(neighbourEdge)));
                    }
                    if(g.get(neighbourEdge.getEndStop()) > g.get(current) + neighbourEdge.getCost())
                    {
//                        lastLineVisitedVertex.put(neighbourEdge, neighbourEdge.getLine());
//                        minTimeVisitedVertex.put(neighbourEdge, neighbourEdge.getEndTime());

                        System.out.println("vertex: "+ neighbourEdge.getEndStop() + " line: "+neighbourEdge.getLine());
                        g.put(neighbourEdge.getEndStop(), g.get(current) + neighbourEdge.getCost());
                        f.put(neighbourEdge.getEndStop(),g.get(neighbourEdge.getEndStop()) + h.get(neighbourEdge.getEndStop()));
                        parentPath.put(neighbourEdge.getEndStop(), neighbourEdge);
                        childParentEdge.put(neighbourEdge, parentPath.get(neighbourEdge.getStart()));
                        if(closed.contains(neighbourEdge.getEndStop()))
                        {
                            open.add(neighbourEdge.getEndStop());
                            closed.remove(neighbourEdge.getEndStop());
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
        for(Vertex closedV : closed)
        {
            System.out.println("zamkniety koszt: "+ closedV.getStop()+" "+ g.get(closedV));
        }
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
