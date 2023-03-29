package Algorithm;

import FileReader.CSVReader;

import Graph2.*;

import java.io.IOException;
import java.util.*;

public class AStarTransfers implements PathSearchingAlgorithm
{
    int closedTimes = 0;
    ArrayList<Integer> counter = new ArrayList<Integer>();

    ArrayList<Vertex> graph = new ArrayList<>();
    TransferHeuristic heuristic = new TransferManhattanHeuristic();

    HashMap<Vertex, Integer> g = new HashMap<Vertex, Integer>();
    HashMap<Vertex, Integer> h = new HashMap<Vertex, Integer>();
    HashMap<Vertex, Integer> f = new HashMap<Vertex, Integer>();
    HashSet<String> endLines = new HashSet<String>();

    public void loadGarph() throws IOException {
        CSVReader reader = new CSVReader();
        reader.read("connection_graph.csv");
       // reader.read("foo.csv");
        graph = reader.createGraph2();
       // graph.printGraph();
//        for(Vertex v : graph)
//        {
//            System.out.println(v.toString());
//        }

    }

    @Override
    public void printGraph() {
        for(Integer count : counter)
        {
            System.out.println(count);
        }
    }

    public void findShortestPath(String source, String destination, Integer startTime)
    {

        Vertex startVertex = null;
        double xCoordinateDestination =0.0;
        double yCoordinateDestination= 0.0;
        Vertex endVertex = null;
        startVertex = new Vertex(source, source, startTime,0,"", "", 0.0,0.0);
        graph.add(startVertex);

        for(Vertex v : graph)
        {
            if( v.getEndStop().equals(destination) )
            {
               xCoordinateDestination = v.getxCoordinateEnd();
               yCoordinateDestination = v.getyCoordinateEnd();
               endLines.add(v.getLine());
            }
        }
        List<Vertex> open = new ArrayList<Vertex>();
        List<Vertex> closed = new ArrayList<Vertex>();
        HashMap<Vertex, Vertex> childParent = new  HashMap<Vertex, Vertex>();


        Vertex current = startVertex;
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
            if(closedTimes % 5000 == 0)
                System.out.println(closedTimes);
//            closedTimesSystem.out.println("CURR: "+ current.toString());
//            System.out.println("f CURR: "+ f.get(current));
            if(current.getEndStop().equals(destination)) {
                endVertex = current;
                break;
            }
            open.remove(current);
            closed.add(current);
            for(Vertex neighbour : getNeighbours(current))
            {
                if(!open.contains(neighbour) && !closed.contains(neighbour))
                {
                    open.add(neighbour);
                    h.put(neighbour,heuristic.getHeuristicValue(neighbour, xCoordinateDestination, yCoordinateDestination));
//                    System.out.println(neighbour.toString());
//                    System.out.println(h.get(neighbour));
                    g.put(neighbour, g.get(current) + getDistance(current,neighbour));
                    f.put(neighbour,g.get(neighbour) + h.get(neighbour));
                    childParent.put(neighbour, current);
                }
                else
                {

                    if(g.get(neighbour) > g.get(current) + getDistance(current,neighbour))
                    {
                        g.put(neighbour, g.get(current) +  getDistance(current,neighbour));
                        f.put(neighbour,g.get(neighbour) + h.get(neighbour));
                        childParent.put(neighbour, current);
                        if(closed.contains(neighbour))
                        {
                            open.add(neighbour);
                            closed.remove(neighbour);
                        }
                    }

                }
            }
       }
        //printPathToDestination(path,destination);
        printPath(childParent, endVertex);
        counter.add(closedTimes);
        closedTimes = 0;
    }

    private int getDistance(Vertex current, Vertex neighbour)
    {
        if((sameLine(current, neighbour) && !sameTime(current, neighbour)) || current.getEndTime() > neighbour.getStartTime())
            return 1000000000;
        if(sameLine(current, neighbour) && sameTime(current, neighbour) && (!current.getStart().equals(neighbour.getEndStop())))
            return 1;
        if(current.getLine().equals("")) return 0;
        return 1000;

    }

    private int getDistanceModification(Vertex current, Vertex neighbour)
    {
        if((sameLine(current, neighbour) && !sameTime(current, neighbour)) || current.getEndTime() > neighbour.getStartTime())
            return 1000000000;
        if(!isEndLine(neighbour))
            return 1000000;
        if(sameLine(current, neighbour) && sameTime(current, neighbour) && (!current.getStart().equals(neighbour.getEndStop())))
            return 1;
        if(current.getLine().equals("")) return 0;
        return 1000;

    }
    public boolean isEndLine(Vertex neighbour)
    {
        return endLines.contains(neighbour.getLine());

    }
    private boolean sameLine(Vertex current, Vertex neighbour) {
        return current.getLine().equals(neighbour.getLine());
    }

    private boolean sameTime(Vertex current, Vertex neighbour) {
        return current.getEndTime() == neighbour.getStartTime();
    }

    private void printPath(HashMap<Vertex, Vertex> childparent, Vertex child)
    {
        if(childparent.get(child) != null)
        {

            printPath(childparent, childparent.get(child));
            System.out.println("\u001B[35m" + child.toString() + "\u001B[0m");
        }
    }
public ArrayList<Vertex> getNeighbours(Vertex currentVertex)
{
    ArrayList<Vertex> neighbours = new ArrayList<Vertex>();
    for(Vertex v : graph)
    {
        if(v.getStart().equals(currentVertex.getEndStop()) && v.getEndTime() > currentVertex.getStartTime())
        {
            neighbours.add(v);

        }
    }
    return neighbours;
}
}


