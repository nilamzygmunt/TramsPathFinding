import Algorithm.*;
import Algorithm.Dijkstra;
import FileReader.CSVReader;

import java.io.IOException;

public class Main
{
    public static void main(String [] args) throws IOException
    {
//        CSVReader csvReader = new CSVReader();
//        csvReader.read("connection_graph.csv");
//        csvReader.makeATest(5000);
        Tester tester = new Tester("test100.csv", new AStarTransfers());
        tester.loadTestData();
        tester.executeTests();
//        AStarTransfers astar = new AStarTransfers();
//        astar.loadGarph();
//        astar.findShortestPath("KROMERA", "DH Astra", 12*3600);
    }


}
