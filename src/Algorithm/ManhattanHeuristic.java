package Algorithm;

import Graph.Vertex;

public class ManhattanHeuristic implements Heuristic{

    @Override
    public int getHeuristicValue(Vertex current, Vertex end) {
        return (int) (100*Math.abs(current.getxCoordinate() - end.getxCoordinate()) + Math.abs(current.getyCoordinate()) - end.getyCoordinate());
    }
}
