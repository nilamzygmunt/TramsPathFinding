import Algorithm.*;
import Algorithm.Dijkstra;
import FileReader.CSVReader;

import java.io.IOException;

public class Main
{
    public static void main(String [] args) throws IOException
    {
//        CSVReader csvReader = new CSVReader();
////        csvReader.read("foo.csv");
//        Dijkstra dijkstra = new Dijkstra();
//        dijkstra.loadGarph();
      //  dijkstra.findShortestPath("Tramwajowa", "Wyszyńskiego", 7*3600+40*60);
        AStar2 astar = new AStar2();
        astar.loadGarph();
        astar.findShortestPath("BISKUPIN", "POŚWIĘTNE", 17*3600);
    }
}
