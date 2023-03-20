package Graph;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Vertex
{
    private String stop;

    private double yCoordinate;
    private double xCoordinate;



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
    public double getyCoordinate() {
        return yCoordinate;
    }


    public double getxCoordinate() {
        return xCoordinate;
    }


    public void setyCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    public void setxCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public void printVertex() {
        System.out.println("STOP: "+stop +" x: "+ xCoordinate + " y: "+ yCoordinate);
        for(Edge e : neighbours)
        {
            System.out.println(e.toString());
        }
    }

}
