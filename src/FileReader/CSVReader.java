package FileReader;

import java.io.*;
import java.util.*;

import Graph.*;
public class CSVReader
{
    private String delimeter = ",";
    private String line, word;
    private String [] rowdata = new String[13];

    HashMap<String, String> xCoordinates = new HashMap<String, String>();
    HashMap<String, String> yCoordinates = new HashMap<String, String>();
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
        createGraph2();
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
        for (String[] datum : data) {
            stopSet.add(datum[5]);
            xCoordinates.put(datum[5], datum[7]);
            yCoordinates.put(datum[5], datum[8]);
        }
        for (String[] datum : data) {
            if(!stopSet.contains(datum[6]))
            {
                stopSet.add(datum[6]);
                xCoordinates.put(datum[6], datum[9]);
                yCoordinates.put(datum[6], datum[10]);
            }
        }
        return stopSet;
    }

    public Map<String, Vertex> makeVertices(HashSet<String> stops)
    {
        Map<String, Vertex> vertices = new HashMap<String, Vertex>();
        for(String s : stops)
        {
            vertices.put(s, new Vertex(s, convertToCoordinate(xCoordinates.get(s)), convertToCoordinate(yCoordinates.get(s))));
        }
        return vertices;
    }
    public int convertToSeconds(String time)
    {
        String [] array = new String[3];
        array = time.split(":");
        return 3600 *  (Integer.parseInt(array[0] ) % 24)  + 60 * (Integer.parseInt(array[1])) + (Integer.parseInt(array[2]));
    }

    public double convertToCoordinate(String text)
    {
        return Double.parseDouble(text);
    }

    public ArrayList<Graph2.Vertex> createGraph2()
    {
        ArrayList<Graph2.Vertex> graph2 = new ArrayList<Graph2.Vertex>();
        for(String [] row : data)
        {
            graph2.add(new Graph2.Vertex(row[5], row[6], convertToSeconds(row[3]), convertToSeconds(row[4]), row[2], row[1], convertToCoordinate(row[9]), convertToCoordinate(row[10])));
        }
        return graph2;
    }

    public void makeATest(int testCaseNumber) throws IOException {
        String fileName = "test"+testCaseNumber+".csv";
        File csvFile = new File(fileName);
        FileWriter fileWriter = new FileWriter(csvFile);

        HashSet<String> stopSet = createUniqueStops(data);
        ArrayList<String> stops = new ArrayList();
        for(String stop : stopSet)
        {
            stops.add(stop);
        }
        int idx;
        StringBuilder line = new StringBuilder();
        Random rnd = new Random();
        for(int i =0; i < testCaseNumber; i++)
        {
            idx = rnd.nextInt(stops.size());
            line.append(stops.get(idx));
            line.append(',');
            idx = rnd.nextInt(stops.size());
            line.append(stops.get(idx));
            line.append(',');
            line.append(rnd.nextInt(82800));
            line.append("\n");

        }
        fileWriter.write(line.toString());
        fileWriter.close();
    }
}
