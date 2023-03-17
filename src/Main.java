import Algorithm.Dijkstra;
import FileReader.CSVReader;

import java.io.IOException;

public class Main
{
    public static void main(String [] args) throws IOException
    {
//        CSVReader csvReader = new CSVReader();
//        csvReader.read("foo.csv");
        Dijkstra dijkstra = new Dijkstra();
        dijkstra.loadGarph();
        dijkstra.findShortestPath("Tramwajowa", "Hala Stulecia", 54000);
    }
}
