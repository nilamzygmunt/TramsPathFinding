package Graph;

public class Edgecopy {
    private Vertex start;
    private Vertex endStop;

    public EdgeDistanceCounting edgeDistanceCounting = new TransferDistanceCounting();

    public Edgecopy getParentEdge() {
        return parentEdge;
    }

    public void setParentEdge(Edgecopy parentEdge) {
        this.parentEdge = parentEdge;
    }

    private Edgecopy parentEdge;
    private int startTime;
    private int endTime;
    private String line;
    private String company;
    private double cost;
    Edgecopy(Vertex start, Vertex endStop, int startTime, int endTime, String line, String company)
    {
        this.start = start;
        this.endStop= endStop;
        this.startTime = startTime;
        this.endTime = endTime;
        this.line = line;
        this.company = company;
    }
    public Vertex getEndStop() {
        return endStop;
    }

    public void setEnd_stop(Vertex endStop) {
        this.endStop = endStop;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double previousEndTime, String line2) {
        this.cost = (endTime - previousEndTime)%(24*3600);
    }

    public void setStart(Vertex start) {
        this.start = start;
    }

    public Vertex getStart() {
        return start;
    }

    public String convertToTime(int seconds)
    {
        int h, m, s;
        h = (seconds/3600);
        m = (seconds - (3600 *h))/60;
        s = (seconds -(3600 *h) - (m * 60));
        return h+":"+m+":"+s;
    }
    public void setStrategy(EdgeDistanceCounting edgeDistanceCounting)
    {
        this.edgeDistanceCounting = edgeDistanceCounting;
    }

    @Override
    public String toString() {
        if(start != null)
            return "\tstart "+start.getStop()+" stop: " + endStop.getStop() + " start time: "+convertToTime(startTime) + " end time: "+ convertToTime(endTime) + " line " + line + " company " +company;
        return "\tstop: " + endStop.getStop() + " start time: "+convertToTime(startTime) + " end time: "+ convertToTime(endTime) + " line " + line + " company " +company;

    }
}
