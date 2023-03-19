package Graph;

public class TransferDistanceCounting implements EdgeDistanceCounting{
    @Override
    public double calculateWaitingTime(int startTime, double previousEndTime, String line, String line2) {

        if( (startTime - previousEndTime > 0) || !line.equals(line2) ||(startTime == 0 || previousEndTime ==0) )
        {
            return 1000000;
        }
        return 0;
    }
}
