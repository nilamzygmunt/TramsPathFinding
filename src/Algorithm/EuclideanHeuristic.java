package Algorithm;

import Graph.Vertex;

public class EuclideanHeuristic implements Heuristic{
    @Override
    public int getHeuristicValue(Vertex current, Vertex end) {
        return (int) (100*Math.sqrt(Math.pow((current.getxCoordinate())- end.getxCoordinate(), 2) + Math.pow((current.getyCoordinate())- end.getyCoordinate(), 2)));
    }
}
