package Graph;

import java.util.HashMap;
import java.util.Map;

public class Graph
{
    private Map<String, Vertex> vertices = new HashMap<String, Vertex>();

    public void addEdge(String startStop, String endStop, int startTime, int endTime, String line, String company)
    {
        Vertex start = vertices.get(startStop);
        Vertex destination = vertices.get(endStop);
        start.addNeighbour(new Edge(start, destination, startTime, endTime, line, company));
    }
    public Vertex getVertex(String stop)
    {
        return vertices.get(stop);
    }
    public void addVertex(Vertex vertex)
    {
        vertices.put(vertex.getStop(), vertex);
    }
    public Map<String, Vertex> getVertices() {
        return vertices;
    }
    public void setVertices(Map<String, Vertex> vertices) {
        this.vertices = vertices;
    }

    public void printGraph()
    {
        for(var v : vertices.values())
        {
            v.printVertex();
        }
    }



}
