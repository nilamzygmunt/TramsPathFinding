package Algorithm;

import Graph2.Vertex;

public interface TransferHeuristic {
    int getHeuristicValue(Vertex current, double endXCoordinate, double endYCordinate);

}
