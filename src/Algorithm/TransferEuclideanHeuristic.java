package Algorithm;

import Graph2.Vertex;

import java.util.HashSet;

public class TransferEuclideanHeuristic implements TransferHeuristic{
    public int getHeuristicValue(Vertex current, double endXCoordinate, double endYCordinate)
    {
       return (int) (100*Math.sqrt(Math.pow((current.getxCoordinateEnd())- endXCoordinate, 2) + Math.pow((current.getyCoordinateEnd())-endYCordinate, 2)));
    }
}
