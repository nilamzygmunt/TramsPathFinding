package FileReader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Graph.*;
public class CSVReader
{
    private String delimeter = ",";
    private String line, word;
    private String [] rowdata = new String[13];
    List<String []> data = new ArrayList<String []>();

    public void read(String file) throws IOException
    {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while ((line = reader.readLine()) != null)
        {
            rowdata = line.split(delimeter);
            data.add(rowdata);
        }
        data.remove(0);
        createGraph();
    }

    public Graph createGraph()
    {
        Graph graph = new Graph();
        HashSet<String> stopSet = createUniqueStops(data);
        graph.setVertices(makeVertices(stopSet));
        for(int i = 0; i < data.size(); i++)
        {
            graph.addEdge(data.get(i)[5], data.get(i)[6], convertToSeconds(data.get(i)[3]), convertToSeconds(data.get(i)[4]), data.get(i)[2], data.get(i)[1]);
        }
        //graph.printGraph();
        return graph;
    }

    public HashSet<String> createUniqueStops(List<String[]> data)
    {
        HashSet<String> stopSet = new HashSet<String>();
        for(int i = 0; i <data.size(); i++)
        {
            stopSet.add(data.get(i)[5]);
        }
        return stopSet;
    }

    public Map<String, Vertex> makeVertices(HashSet<String> stops)
    {
        Map<String, Vertex> vertices = new HashMap<String, Vertex>();
        for(String s : stops)
        {
            vertices.put(s, new Vertex(s));
        }
        return vertices;
    }
    public int convertToSeconds(String time)
    {
        String [] array = new String[3];
        array = time.split(":");
        return 3600 *  (Integer.parseInt(array[0] ) % 24)  + 60 * (Integer.parseInt(array[1])) + (Integer.parseInt(array[2]));
    }
}
