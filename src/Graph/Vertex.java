package Graph;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vertex
{
    private String stop;

    private ArrayList<Pair<String, Integer>> lineAndTime = new ArrayList<Pair<String, Integer>>();
    public double getxCoordinate() {
        return xCoordinate;
    }

//    public void addLineAndTime(String line, Integer time)
//    {
//        Pair<String, Integer> pair = new Pair<>(line, time);
//        lineAndTime.add(pair);
//    }

//    public boolean lineAndTimeContains(String line, Integer time)
//    {
//        for (Pair pair : lineAndTime)
//        {
//            if(pair.getFirst() == line && pair.getSecond() == time)
//                return true;
//        }
//        return false;
//    }
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    private double xCoordinate;

    public double getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    private double yCoordinate;
    List<Edge> neighbours = new ArrayList<Edge>();

    public Vertex(String stop, double xCoordinate, double yCoordinate)
    {
        this.stop = stop;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void addNeighbour(Edge edge)
    {
        neighbours.add(edge);
    }
    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public List<Edge> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(List<Edge> neighbours) {
        this.neighbours = neighbours;
    }
    public void printVertex() {
        System.out.println("STOP: "+stop +" x: "+ xCoordinate + " y: "+ yCoordinate);
        for(Edge e : neighbours)
        {
            System.out.println(e.toString());
        }
    }
}
