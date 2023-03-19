package Algorithm;
import Graph.*;
public interface Heuristic {
    double getHeuristicValue(Vertex current, Vertex end);
}
