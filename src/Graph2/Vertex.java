package Graph2;

public class Vertex {
        private String startStop;
        private String endStop;


    public double getxCoordinateEnd() {
        return xCoordinateEnd;
    }

    public void setxCoordinateEnd(double xCoordinateEnd) {
        this.xCoordinateEnd = xCoordinateEnd;
    }

    private double xCoordinateEnd;

    public double getyCoordinateEnd() {
        return yCoordinateEnd;
    }

    public void setyCoordinateEnd(double yCoordinateEnd) {
        this.yCoordinateEnd = yCoordinateEnd;
    }

    private double yCoordinateEnd;

    public Vertex getNextInLine() {
        return nextInLine;
    }

    public void setNextInLine(Vertex nextInLine) {
        this.nextInLine = nextInLine;
    }

    private Vertex nextInLine;

        //public EdgeDistanceCounting edgeDistanceCounting = new TimeDistanceCounting();

    //    public Graph.Edge getParentEdge() {
//            return parentEdge;
//        }
//
//     //   public void setParentEdge(Graph.Edge parentEdge) {
//            this.parentEdge = parentEdge;
//        }

    //    private Graph.Edge parentEdge;

        private int startTime;
        private int endTime;
        private String line;
        private String company;
        private double cost;
        public Vertex(String start, String endStop, int startTime, int endTime, String line, String company, double xCoordinateEnd, double yCoordinateEnd)
        {
            this.startStop = start;
            this.endStop= endStop;
            this.startTime = startTime;
            this.endTime = endTime;
            this.line = line;
            this.company = company;
            this.xCoordinateEnd = xCoordinateEnd;
            this.yCoordinateEnd = yCoordinateEnd;
        }
        public String getEndStop() {
            return endStop;
        }

        public void setEndStop(String endStop) {
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

        public void setCost(int cost)
        {
            this.cost = cost;
        }

        public void setStart(String start) {
            this.startStop = start;
        }

        public String getStart() {
            return startStop;
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
              return  "startStop: "+ startStop+" endstop: " + endStop + " start time: "+convertToTime(startTime) + " end time: "+ convertToTime(endTime) + " line " + line + " company " +company;

        }
    }


