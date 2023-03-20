package Algorithm;

import java.io.IOException;

public interface PathSearchingAlgorithm {
    void loadGarph() throws IOException;
    void printGraph();
    void findShortestPath(String source, String destination, Integer startTime);
}
