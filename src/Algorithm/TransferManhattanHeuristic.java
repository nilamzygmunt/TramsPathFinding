package Algorithm;

import Graph2.Vertex;

public class TransferManhattanHeuristic implements TransferHeuristic
{
    @Override
    public int getHeuristicValue(Vertex current, double endXCoordinate, double endYCordinate) {
        return (int) (100*Math.abs(current.getyCoordinateEnd() - endXCoordinate) + Math.abs(current.getyCoordinateEnd()) - endYCordinate);
    }
}
