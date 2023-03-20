package Graph;

public class Edge {
    private Vertex start;
    private Vertex endStop;

    public Edge getParentEdge() {
        return parentEdge;
    }

    public void setParentEdge(Edge parentEdge) {
        this.parentEdge = parentEdge;
    }

    private Edge parentEdge;
    private int startTime;
    private int endTime;
    private String line;
    private String company;
    private int cost;
    Edge(Vertex start, Vertex endStop, int startTime, int endTime, String line, String company)
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

    public int getCost() {
        return cost;
    }

    public void setCost(int previousEndTime, String line2) {
        this.cost = Math.abs((endTime - previousEndTime)%(24*3600) );
    }

    public void setCost2(double previousEndTime, String previousLine)
    {
        System.out.println("times: "+previousEndTime + " " + startTime);
        System.out.println("lines: "+previousLine + " " + line);
        System.out.println(previousEndTime != startTime || !previousLine.equals(line));

        if(previousEndTime != startTime || !previousLine.equals(line))
        {
            this.cost = 1;
        }
        this.cost = 0;
    }
    public void setCost2(int cost)
    {
        this.cost = cost;
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

    @Override
    public String toString() {
        if(start != null)
            return "\tstart "+start.getStop()+" stop: " + endStop.getStop() + " start time: "+convertToTime(startTime) + " end time: "+ convertToTime(endTime) + " line " + line + " company " +company;
        return "\tstop: " + endStop.getStop() + " start time: "+convertToTime(startTime) + " end time: "+ convertToTime(endTime) + " line " + line + " company " +company;

    }
}
