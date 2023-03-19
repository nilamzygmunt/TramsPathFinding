package Algorithm;

import Graph.Vertex;

public class TimeHeuristic implements Heuristic{

    @Override
    public double getHeuristicValue(Vertex current, Vertex end) {
        return Math.abs(current.getxCoordinate() - end.getxCoordinate()) + Math.abs(current.getyCoordinate()) - end.getyCoordinate();
    }
}
