package Graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex
{
    private String stop;

    List<Edge> neighbours = new ArrayList<Edge>();

    public Vertex(String stop)
    {
        this.stop = stop;
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
        System.out.println("STOP: "+stop);
        for(Edge e : neighbours)
        {
            System.out.println(e.toString());
        }
    }
}
