import Algorithm.AStar;
import Algorithm.Dijkstra;
import Algorithm.PathSearchingAlgorithm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tester {
    PathSearchingAlgorithm algorithm;
    String testFile;
    ArrayList<String []> data = new ArrayList<>();

    Tester(String testFile, PathSearchingAlgorithm algorithm)
    {
        this.testFile = testFile;
        this.algorithm = algorithm;

    }
    public void loadTestData() throws IOException {
        String line;
        String [] rowdata;
        String delimeter = ",";
        BufferedReader reader = new BufferedReader(new FileReader(testFile));
        while ((line = reader.readLine()) != null)
        {
            rowdata = line.split(delimeter);
            data.add(rowdata);
        }
    }
    public void executeTests() throws IOException {
        algorithm.loadGarph();
        //algorithm.printGraph();
        for(String [] datum : data)
        {
            executeTest(datum[0], datum[1], Integer.parseInt(datum[2]));
        }
    }

    public void executeTest(String start, String end, int timeInSeconds)
    {
        //System.out.println("shortest path for: "+ start+" "+end);
        algorithm.findShortestPath(start,end,timeInSeconds);
        algorithm.printGraph();
    }

}
